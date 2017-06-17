/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import compartidos.beans.entidades.ServicioAlarma;
import compartidos.beans.excepciones.MiExcepcionPersistencia;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author Mathias
 */
public class PersistenciaServicioAlarma implements IPersistenciaServicioAlarma {
    
    public void altaServicioAlarma(ServicioAlarma pServicioAlarma)
            throws ClassNotFoundException, SQLException, MiExcepcionPersistencia {
        Connection cnn = null;
        CallableStatement consulta = null;
        
        try {
            cnn = Conexion.getConexion();
            consulta = cnn.prepareCall("{ CALL altaServicioAlarma(?, ?, ?, ?, ?, ?) }");
            
            consulta.setInt(1, pServicioAlarma.getPropriedadCliente().getNumeroPropiedad());
            consulta.setLong(2, pServicioAlarma.getPropriedadCliente().getDueno().getCedula());
            consulta.setDate(3, (Date) pServicioAlarma.getFechaContratacion());
            consulta.setBoolean(4, pServicioAlarma.getMonitoreo());
            consulta.setInt(5, pServicioAlarma.getCodigoAnulacion());
            consulta.registerOutParameter(6, java.sql.Types.VARCHAR);
            
            consulta.executeUpdate();
            
            String error = consulta.getNString(4);

            if (error != null) {
                throw new MiExcepcionPersistencia("Error en dar de alta el servicio de alarma del cliente " 
                        + pServicioAlarma.getPropriedadCliente().getDueno().getCedula() 
                        + " , propiedad " + pServicioAlarma.getPropriedadCliente().getNumeroPropiedad() + ": " + error);
            }
            
        } finally {
            Conexion.cerrarRecursos(cnn, consulta);
        }
    }
}
