/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import compartidos.beans.entidades.*;
import compartidos.beans.excepciones.MiExcepcion;
import compartidos.beans.excepciones.MiExcepcionPersistencia;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mathias
 */
public class PersistenciaServicioAlarma implements IPersistenciaServicioAlarma {
    
    IPersistenciaPropiedad perPropiedad = FabricaPersistencia.GetPersistenciaPropiedad();
    
    public ServicioAlarma buscarServicioAlarma(int pNumero) 
            throws SQLException, MiExcepcion, ClassNotFoundException {
        Connection cnn = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        
        try {
            cnn = Conexion.getConexion();            
            consulta = cnn.prepareStatement("SELECT s.*, sa.codigoAnulacion FROM servicios s INNER JOIN serviciosAlarma sa ON s.numero = sa.numero WHERE s.numero = ? AND sa.numero = ? AND eliminado = 0;");
            
            consulta.setInt(1, pNumero);
            consulta.setInt(2, pNumero);
            
            consulta.executeQuery();
            
            ServicioAlarma servAlarma = null;
                      
            long cedula;
            int numeroPropiedad;
            
            int numero;
            Propiedad propiedadCliente;
            Date fechaContratacion;
            Boolean monitoreo;
            int codigoAnulacion;
            List<Alarma> alarmas = null;
            
            if (resultado.next()) {                
                numero = resultado.getInt("numero");
                cedula = resultado.getLong("cedulaCliente");
                numeroPropiedad = resultado.getInt("numeroPropiedad");
                fechaContratacion = resultado.getDate("fechaContratacion");
                monitoreo = resultado.getBoolean("monitoreo");
                codigoAnulacion = resultado.getInt("codigoAnulacion");
                
                propiedadCliente = perPropiedad.buscarPropiedad(numeroPropiedad, cedula);
                
                servAlarma = new ServicioAlarma(numero, propiedadCliente, fechaContratacion, monitoreo, codigoAnulacion, alarmas);
            }
            
            return servAlarma;
            
        } catch (Exception ex) {
            throw new MiExcepcionPersistencia("Error al buscar servicio de alarma", ex);
        }
        finally {
            Conexion.cerrarRecursos(cnn, consulta, resultado);
        }
    }
    
    public void altaServicioAlarma(ServicioAlarma pServicioAlarma)
            throws ClassNotFoundException, SQLException, MiExcepcion {
        Connection cnn = null;
        CallableStatement consulta = null;
        
        try {
            cnn = Conexion.getConexion();
            cnn.setAutoCommit(false);
            
            consulta = cnn.prepareCall("{ CALL altaCliente(?, ?, ?, ?, ?, ?) }");
            
            consulta.setLong(1, pServicioAlarma.getPropriedadCliente().getDueno().getCedula());
            consulta.setString(2, pServicioAlarma.getPropriedadCliente().getDueno().getNombre());
            consulta.setString(3, pServicioAlarma.getPropriedadCliente().getDueno().getDireccionCobro());
            consulta.setString(4, pServicioAlarma.getPropriedadCliente().getDueno().getBarrioDirCobro());
            consulta.setLong(5, pServicioAlarma.getPropriedadCliente().getDueno().getTelefono());
            consulta.registerOutParameter(6, java.sql.Types.VARCHAR);
            
            consulta.executeUpdate();
            
            consulta = cnn.prepareCall("{ CALL altaPropiedad(?, ?, ?, ?) }");

            consulta.setString(1, pServicioAlarma.getPropriedadCliente().getTipoPropriedad().toString());
            consulta.setString(2, pServicioAlarma.getPropriedadCliente().getDireccion());
            consulta.setLong(3, pServicioAlarma.getPropriedadCliente().getDueno().getCedula());
            consulta.registerOutParameter(4, java.sql.Types.VARCHAR);

            consulta.executeUpdate();
            
            consulta = cnn.prepareCall("{ CALL altaServicioAlarma(?, ?, ?, ?, ?, ?) }");
            
            consulta.setInt(1, pServicioAlarma.getPropriedadCliente().getNumeroPropiedad());
            consulta.setLong(2, pServicioAlarma.getPropriedadCliente().getDueno().getCedula());
            consulta.setDate(3, (Date.valueOf(LocalDate.now())));
            consulta.setBoolean(4, pServicioAlarma.getMonitoreo());
            consulta.setInt(5, pServicioAlarma.getCodigoAnulacion());
            consulta.registerOutParameter(6, java.sql.Types.VARCHAR);
            
            consulta.executeUpdate();
            
            cnn.commit();
         
        } catch (Exception ex) {
            try {
                if (cnn != null) {
                    cnn.rollback();
                }                        
            } catch (Exception rEx) {
                throw new MiExcepcionPersistencia("Ocurrió un error al deshacer los cambios");
            }
            throw new MiExcepcionPersistencia("Ocurrió un error al agregar el servicio de alarma");
        }
        finally {
            Conexion.cerrarRecursos(cnn, consulta);
        }
    }
    
    public void bajaServicioAlarma(ServicioAlarma pServicio)
            throws ClassNotFoundException, SQLException, MiExcepcion {
        Connection cnn = null;
        CallableStatement consulta = null;
        
        try {
            cnn = Conexion.getConexion();
            consulta = cnn.prepareCall("{ CALL bajaServicioAlarma (?, ?) }");
            
            consulta.setInt(1, pServicio.getNumero());
            consulta.registerOutParameter(2, java.sql.Types.VARCHAR);
            
            consulta.executeUpdate();
            
            String error = consulta.getNString(2);
            
            if (error != null) {
                if (error.equals("")) {
                    throw new MiExcepcionPersistencia("Error al dar de baja al servicio " + pServicio.getNumero());
                }
            }
        } finally {
            Conexion.cerrarRecursos(cnn, consulta);
        }
    }
}
