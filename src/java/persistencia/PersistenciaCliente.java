/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidades.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import miexcepcion.MiExcepcion;

/**
 *
 * @author Mathias
 */
public class PersistenciaCliente implements IPersistenciaCliente {
    
    public Cliente buscarCliente(long pCedula) throws ClassNotFoundException, SQLException, MiExcepcion {
        Connection cnn = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        
        try{
            cnn = Conexion.Conectar();      
            
            consulta = cnn.prepareStatement("SELECT * FROM clientes WHERE cedula = ?");   
            
            consulta.setLong(1, pCedula);
            
            resultado = consulta.executeQuery();
            
            Cliente clienteEncontrado = null;
            
            long cedula;
            String nombre;
            String direccionCobro;
            String barrioDirCobro;
            long telefono;
            
            if (resultado.next()) {
                cedula = resultado.getLong("cedula");
                nombre = resultado.getString("nombre");
                direccionCobro = resultado.getString("direccionCobro");
                barrioDirCobro = resultado.getString("barrioCobro");
                telefono = resultado.getLong("telefono");
                
                clienteEncontrado = new Cliente(cedula, nombre, direccionCobro, barrioDirCobro, telefono);
            }
            
            return clienteEncontrado;
            
        } finally {
            if (consulta != null) {
                consulta.close();
            }
            if (resultado != null) {
                resultado.close();
            }
            if (cnn != null) {
                Conexion.Desconectar(cnn);
            }
        }
    }
    
    public void altaCliente(Cliente pCliente) throws ClassNotFoundException, SQLException, MiExcepcion{
        Connection cnn = null;
        CallableStatement consulta = null;
        
        try {
            cnn = Conexion.Conectar();
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
                throw new MiExcepcion("Error en dar de alta al cliente" + pCliente.getCedula() + ": " + error);
            }           
        } finally {
            if (consulta != null) {
                consulta.close();
            }
            if (cnn != null) {
                Conexion.Desconectar(cnn);
            }
        }        
    }
    
    public void modificarCliente(Cliente pCliente) throws ClassNotFoundException, SQLException, MiExcepcion {
        Connection cnn = null;
        CallableStatement consulta = null;
        
        try {
            cnn = Conexion.Conectar();
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
                throw new MiExcepcion("Error al modificar el cliente" + pCliente.getCedula() + ": " + error);
            }     
        } finally {
            if (consulta != null) {
                consulta.close();
            }
            if (cnn != null) {
                Conexion.Desconectar(cnn);
            }
        }
    }
}
