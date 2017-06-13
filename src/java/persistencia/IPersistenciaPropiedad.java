/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import compartidos.beans.entidades.Propiedad;
import java.sql.SQLException;
import compartidos.beans.excepciones.MiExcepcion;
/**
 *
 * @author Mathias
 */
public interface IPersistenciaPropiedad {
    
    Propiedad buscarPropiedad(int pNumeroPropiedad, long pCedulaDueno) throws ClassNotFoundException, SQLException, MiExcepcion;
    void altaPropiedad(Propiedad pPropiedad) throws ClassNotFoundException, SQLException, MiExcepcion;
    void modificarPropiedad(Propiedad pPropiedad) throws ClassNotFoundException, SQLException, MiExcepcion;
}
