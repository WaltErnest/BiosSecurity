/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import compartidos.beans.entidades.*;
import compartidos.beans.excepciones.MiExcepcionPersistencia;
import compartidos.beans.excepciones.MiExcepcion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Mathias
 */
public class PersistenciaServicioVideo implements IPersistenciaServicioVideo {
    
    IPersistenciaPropiedad perPropiedad = FabricaPersistencia.GetPersistenciaPropiedad();
    
    public ServicioVideo buscarServicioVideo(int pNumero)
            throws SQLException, MiExcepcion, ClassNotFoundException {
        Connection cnn = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        
        try {
            cnn = Conexion.getConexion();            
            consulta = cnn.prepareStatement("SELECT s.*, sv.terminalGrabacion FROM servicios s INNER JOIN serviciosVideo sv ON s.numero = sv.numero WHERE s.numero = ? AND sv.numero = ? AND eliminado = 0;");
            
            consulta.setInt(1, pNumero);
            consulta.setInt(2, pNumero);
            
            consulta.executeQuery();
            
            ServicioVideo servVideo = null;
                      
            long cedula;
            int numeroPropiedad;
            
            int numero;
            Propiedad propiedadCliente;
            Date fechaContratacion;
            Boolean monitoreo;
            Boolean terminalGrabacion;
            List<Camara> camaras = null;
            
            if (resultado.next()) {                
                numero = resultado.getInt("numero");
                cedula = resultado.getLong("cedulaCliente");
                numeroPropiedad = resultado.getInt("numeroPropiedad");
                fechaContratacion = resultado.getDate("fechaContratacion");
                monitoreo = resultado.getBoolean("monitoreo");
                terminalGrabacion = resultado.getBoolean("terminalGrabacion");
                
                propiedadCliente = perPropiedad.buscarPropiedad(numeroPropiedad, cedula);
                
                servVideo = new ServicioVideo(numero, propiedadCliente, fechaContratacion, monitoreo, terminalGrabacion, camaras);
            }
            
            return servVideo;
            
        } catch (Exception ex) {
            throw new MiExcepcionPersistencia("Error al buscar servicio de video", ex);
        }
        finally {
            Conexion.cerrarRecursos(cnn, consulta, resultado);
        }
    }
    
    @Override
    public void altaServicioVideo(ServicioVideo pServicioVideo) 
            throws ClassNotFoundException, SQLException, MiExcepcionPersistencia {
        Connection cnn = null;
        CallableStatement consulta = null;
        
        try {
            cnn = Conexion.getConexion();
            consulta = cnn.prepareCall("{ CALL altaServicioVideo(?, ?, ?, ?, ?, ?) }");
            
            consulta.setInt(1, pServicioVideo.getPropriedadCliente().getNumeroPropiedad());
            consulta.setLong(2, pServicioVideo.getPropriedadCliente().getDueno().getCedula());
            consulta.setDate(3, (Date) pServicioVideo.getFechaContratacion());
            consulta.setBoolean(4, pServicioVideo.getMonitoreo());
            consulta.setBoolean(5, pServicioVideo.getTerminalGrabacion());
            consulta.registerOutParameter(6, java.sql.Types.VARCHAR);
            
            String error = consulta.getNString(4);

            if (error != null) {
                throw new MiExcepcionPersistencia("Error en dar de alta el servicio de video del cliente " 
                        + pServicioVideo.getPropriedadCliente().getDueno().getCedula() 
                        + " , propiedad " + pServicioVideo.getPropriedadCliente().getNumeroPropiedad() + ": " + error);
            }
            
        } finally {
            Conexion.cerrarRecursos(cnn, consulta);
        }      
    }
}
