/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidades.*;
import static java.lang.String.valueOf;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import miexcepcion.MiExcepcion;

/**
 *
 * @author Mathias
 */
public class PersistenciaPropiedad implements IPersistenciaPropiedad {
    
    public Propiedad buscarPropiedad(int pNumeroPropiedad, long pCedulaDueno) throws ClassNotFoundException, SQLException, MiExcepcion {
        Connection cnn = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        
        try {
            cnn = Conexion.Conectar();      
            
            consulta = cnn.prepareStatement("SELECT * FROM propiedades WHERE numero = ? AND cedula = ?");           
            
            consulta.setInt(1, pNumeroPropiedad);
            consulta.setLong(2, pCedulaDueno);
            
            Cliente clienteEncontrado = FabricaPersistencia.GetPersistenciaCliente().buscarCliente(pCedulaDueno);
            
            Propiedad propiedadEncontrada = null;
            
            if (clienteEncontrado != null) {
                resultado = consulta.executeQuery();
                
                int numeroPropiedad;
                Propiedad.TipoPropiedad tipo;
                String direccion;
                Cliente dueno;
                
                if (resultado.next()) {
                    numeroPropiedad = resultado.getInt("numero");
                    tipo = Propiedad.TipoPropiedad.valueOf(resultado.getString("tipoPropiedad"));
                    direccion = resultado.getString("direccion");
                    
                    propiedadEncontrada = new Propiedad(numeroPropiedad, tipo, direccion, clienteEncontrado);
                }
            }
            
            return propiedadEncontrada;            
            
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
