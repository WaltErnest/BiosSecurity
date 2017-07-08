/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import compartidos.beans.entidades.Cliente;
import compartidos.beans.excepciones.MiExcepcion;
import compartidos.beans.excepciones.MiExcepcionLogica;
import java.sql.SQLException;
/**
 *
 * @author Mathias
 */
public interface ILogicaCliente {    
    Cliente buscarCliente(long pCedula) throws ClassNotFoundException, SQLException, MiExcepcionLogica, MiExcepcion;
    void altaCliente(Cliente pCliente) throws ClassNotFoundException, SQLException, MiExcepcionLogica, MiExcepcion;
    void modificarCliente(Cliente pCliente) throws ClassNotFoundException, SQLException, MiExcepcionLogica, MiExcepcion;
}
