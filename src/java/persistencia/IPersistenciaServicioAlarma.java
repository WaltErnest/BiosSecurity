/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import compartidos.beans.entidades.ServicioAlarma;
import compartidos.beans.excepciones.MiExcepcionPersistencia;
import java.sql.SQLException;

/**
 *
 * @author Mathias
 */
public interface IPersistenciaServicioAlarma {
    public void altaServicioAlarma(ServicioAlarma pServicioAlarma) throws ClassNotFoundException, SQLException, MiExcepcionPersistencia;
    
}
