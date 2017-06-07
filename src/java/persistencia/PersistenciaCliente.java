/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidades.*;
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
            
            consulta = cnn.prepareStatement("SELECT * FROM Clientes WHERE cedula = ?");            
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
}
