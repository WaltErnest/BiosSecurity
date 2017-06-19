/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import compartidos.beans.entidades.Propiedad;
import compartidos.beans.excepciones.MiExcepcion;
import compartidos.beans.excepciones.MiExcepcionLogica;
import java.sql.SQLException;

/**
 *
 * @author Mathias
 */
public interface ILogicaPropiedad {
    Propiedad buscarPropiedad(int pNumeroPropiedad, long pCedula) throws ClassNotFoundException, SQLException, MiExcepcion;
    void altaPropiedad(Propiedad pPropiedad) throws ClassNotFoundException, SQLException, MiExcepcion;
    void modificarPropiedad(Propiedad pPropiedad) throws ClassNotFoundException, SQLException, MiExcepcion;
}
