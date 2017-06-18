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
    
    public Cliente buscarCliente(long pCedula) throws ClassNotFoundException, SQLException, MiExcepcionLogica, MiExcepcion{
        if(!verificarCedula(pCedula)){
            throw new MiExcepcionLogica("La cédula del cliente tiene más de 8 digitos");
        } else {
            return perCliente.buscarCliente(pCedula);
        }        
    }
    
    public void altaCliente(Cliente pCliente) throws ClassNotFoundException, SQLException, MiExcepcionLogica, MiExcepcion{
        verificarCliente(pCliente);        
        perCliente.altaCliente(pCliente);
    }
    
    public void modificarCliente(Cliente pCliente) throws ClassNotFoundException, SQLException, MiExcepcionLogica, MiExcepcion{
        verificarCliente(pCliente);
        perCliente.modificarCliente(pCliente);
    }
    
    public void verificarCliente(Cliente pCliente) throws MiExcepcionLogica{
        long tel = String.valueOf(pCliente.getTelefono()).length();
        
        if (!verificarCedula(pCliente.getCedula())) {
            throw new MiExcepcionLogica("La cédula del cliente tiene más de 8 digitos");
        } else if (pCliente.getNombre().length() > 50){
            throw new MiExcepcionLogica("El nombre del cliente no puede exceder los 50 caractéres");
        } else if (pCliente.getDireccionCobro().length() > 255){
            throw new MiExcepcionLogica("La dirección de cobro no puede exceder los 255 caractéres");
        } else if (pCliente.getBarrioDirCobro().length() > 30){
            throw new MiExcepcionLogica("El barrio de cobro no puede exceder los 30 caractéres");
        } else if (tel > 19 || tel < 4){
            throw new MiExcepcionLogica("El teléfono debe tener entre 4 y 19 números");
        }
    }
    
    //verifica el largo de la cédula, si tiene mas de 8 números devuelve falso, si no verdadero.
    public boolean verificarCedula(long pNumero){
        int largo = String.valueOf(pNumero).length();
        
        if (largo > 8) {
            return false;
        } else {
            return true;
        }
    }
}
