/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import compartidos.beans.entidades.ServicioVideo;
import compartidos.beans.excepciones.MiExcepcion;
import compartidos.beans.excepciones.MiExcepcionPersistencia;
import java.sql.SQLException;

/**
 *
 * @author Mathias
 */
public interface IPersistenciaServicioVideo {
    
    ServicioVideo buscarServicioVideo(int pNumero) throws SQLException, MiExcepcion, ClassNotFoundException;
    public void altaServicioVideo(ServicioVideo pServicioVideo) throws ClassNotFoundException, SQLException, MiExcepcionPersistencia;
}
