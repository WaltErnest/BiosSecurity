/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import compartidos.beans.excepciones.MiExcepcionLogica;
import java.sql.SQLException;
import persistencia.*;
import compartidos.beans.entidades.Cliente;
import compartidos.beans.excepciones.MiExcepcion;
/**
 *
 * @author Mathias
 */
public class LogicaCliente implements ILogicaCliente {
    IPersistenciaCliente perCliente = FabricaPersistencia.GetPersistenciaCliente();
    
    public Cliente buscarCliente(long pCedula) throws ClassNotFoundException, SQLException, MiExcepcion {
        if(pCedula < 1){
            throw new MiExcepcionLogica("La cédula del cliente debe ser mayor o igual a 1");
        } else {
            return perCliente.buscarCliente(pCedula);
        }
    }
    
    public void modificarCliente(Cliente pCliente) throws ClassNotFoundException, SQLException, MiExcepcion {
        validarCliente(pCliente);
        perCliente.modificarCliente(pCliente);
    }
    
    //verifica que los datos del cliente esten correctos
    public void validarCliente(Cliente pCliente) throws MiExcepcion {        
        if (pCliente.getCedula() < 1) {
            throw new MiExcepcionLogica("La cédula del cliente debe ser mayor o igual a 1");
        }
        
        if (pCliente.getNombre().equals("")) {
            throw new MiExcepcionLogica("El nombre del cliente no puede estar vacío");
        }
        if (pCliente.getNombre().trim().length() > 50) {
            throw new MiExcepcionLogica("El nombre del cliente no puede exceder los 50 caractéres");
        }      

        if (pCliente.getDireccionCobro().equals("")) {
            throw new MiExcepcionLogica("La dirección de cobro no puede estar vacía");
        }
        
        if (pCliente.getDireccionCobro().trim().length() > 255) {
            throw new MiExcepcionLogica("La dirección de cobro no puede exceder los 255 caractéres");
        }
        
        if (pCliente.getBarrioDirCobro().equals("")) {
            throw new MiExcepcionLogica("El barrio de cobro no puede estar vacío");
        }
        
        if (pCliente.getBarrioDirCobro().trim().length() > 30) {
            throw new MiExcepcionLogica("El barrio de cobro no puede exceder los 30 caractéres");
        }
        
        if (pCliente.getTelefono() < 1) {
            throw new MiExcepcionLogica("El teléfono debe ser mayor o igual a 1");
        }
    }
}