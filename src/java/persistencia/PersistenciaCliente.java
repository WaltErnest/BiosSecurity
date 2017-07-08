/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import compartidos.beans.entidades.Cliente;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import compartidos.beans.excepciones.MiExcepcion;
import compartidos.beans.excepciones.MiExcepcionPersistencia;
/**
 *
 * @author Mathias
 */
public class PersistenciaCliente implements IPersistenciaCliente {
    
    @Override
    public Cliente buscarCliente(long pCedula)
            throws ClassNotFoundException, SQLException, MiExcepcion {
        Connection cnn = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        
        try{
            cnn = Conexion.getConexion();      
            
            consulta = cnn.prepareStatement("SELECT * FROM clientes WHERE cedula = ?");   
            
            consulta.setLong(1, pCedula);
            
            resultado = consulta.executeQuery();
            
            Cliente cliente = null;
            
            long cedula;
            String nombre;
            String direccionCobro;
            String barrioDirCobro;
            long telefono;
            
            if(resultado.next()) {
                cedula = resultado.getLong("cedula");
                nombre = resultado.getString("nombre");
                direccionCobro = resultado.getString("direccionCobro");
                barrioDirCobro = resultado.getString("barrioCobro");
                telefono = resultado.getLong("telefono");
                
                cliente = new Cliente(cedula, nombre, direccionCobro, barrioDirCobro, telefono);
            }
            
            return cliente;
            
        } catch (Exception ex) {
            throw new MiExcepcionPersistencia("No se pudo buscar los cliente.", ex);
        }
        finally {
            Conexion.cerrarRecursos(cnn, consulta, resultado);
        }
    }
    
    @Override
    public void altaCliente(Cliente pCliente) throws ClassNotFoundException, SQLException, MiExcepcionPersistencia, MiExcepcion{
        Connection cnn = null;
        CallableStatement consulta = null;
        
        try {
            cnn = Conexion.getConexion();
            consulta = cnn.prepareCall("{ CALL altaCliente(?, ?, ?, ?, ?, ?) }");
            
            consulta.setLong(1, pCliente.getCedula());
            consulta.setString(2, pCliente.getNombre());
            consulta.setString(3, pCliente.getDireccionCobro());
            consulta.setString(4, pCliente.getBarrioDirCobro());
            consulta.setLong(5, pCliente.getTelefono());
            consulta.registerOutParameter(6, java.sql.Types.VARCHAR);
            
            consulta.executeUpdate();
            
            String error = consulta.getString(6);
            
            if (error != null) {
                throw new MiExcepcionPersistencia("Error en dar de alta al cliente" + pCliente.getCedula() + ": " + error);
            }           
        } finally {
            Conexion.cerrarRecursos(cnn, consulta);
        }        
    }
    
    @Override
    public void modificarCliente(Cliente pCliente) throws ClassNotFoundException, SQLException, MiExcepcionPersistencia, MiExcepcion {
        Connection cnn = null;
        CallableStatement consulta = null;
        
        try {
            cnn = Conexion.getConexion();
            consulta = cnn.prepareCall("{ CALL modificarCliente(?, ?, ?, ?, ?, ?)}");
            
            consulta.setLong(1, pCliente.getCedula());
            consulta.setString(2, pCliente.getNombre());
            consulta.setString(3, pCliente.getDireccionCobro());
            consulta.setString(4, pCliente.getBarrioDirCobro());
            consulta.setLong(5, pCliente.getTelefono());
            consulta.registerOutParameter(6, java.sql.Types.VARCHAR);
            
            consulta.executeUpdate();
            
            String error = consulta.getString(6);
            
            if (error != null) {
                throw new MiExcepcionPersistencia("Error al modificar el cliente" + pCliente.getCedula() + ": " + error);
            }     
        } finally {
            Conexion.cerrarRecursos(cnn, consulta);
        }
    }
}