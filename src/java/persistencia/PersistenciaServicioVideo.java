/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import compartidos.beans.entidades.ServicioVideo;
import compartidos.beans.excepciones.MiExcepcionPersistencia;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author Mathias
 */
public class PersistenciaServicioVideo implements IPersistenciaServicioVideo {
    
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
