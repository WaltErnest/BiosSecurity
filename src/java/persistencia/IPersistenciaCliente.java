/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import compartidos.beans.entidades.Cliente;
import java.sql.SQLException;
import compartidos.beans.excepciones.MiExcepcion;
import java.util.ArrayList;

/**
 *
 * @author Mathias
 */
public interface IPersistenciaCliente {
    
    Cliente buscarCliente(long pCedula) throws ClassNotFoundException, SQLException, MiExcepcion;
    ArrayList<Cliente> buscarClientes(long pCedula, String pNombre ) throws ClassNotFoundException, SQLException, MiExcepcion;
    void altaCliente(Cliente pCliente) throws ClassNotFoundException, SQLException, MiExcepcion;
    void modificarCliente(Cliente pCliente) throws ClassNotFoundException, SQLException, MiExcepcion;
}
