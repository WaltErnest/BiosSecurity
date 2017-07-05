/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import compartidos.beans.entidades.Cobrador;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import compartidos.beans.excepciones.MiExcepcion;

/**
 *
 * @author Ernesto
 * @version Diego, agrego login
 */
public class PersistenciaCobrador implements IPersistenciaCobrador {

    @Override
    public void AltaCobrador(Cobrador pCobrador) throws ClassNotFoundException, SQLException, MiExcepcion {
        Connection cnn = null;
        CallableStatement consulta = null;
        try {
            cnn = Conexion.getConexion();
            consulta = cnn.prepareCall(
                    "{ CALL altaCobrador(?, ?, ?, ?, ?, ?, ?) }");

            consulta.setLong(1, pCobrador.getCedula());
            consulta.setString(2, pCobrador.getClave());
            consulta.setString(3, pCobrador.getNombre());
            consulta.setDate(4, java.sql.Date.valueOf(pCobrador.getFechaIngreso().toString()));
            consulta.setDouble(5, pCobrador.getSueldo());
            consulta.setString(6, pCobrador.getTipoTransporte());
            consulta.registerOutParameter(7, java.sql.Types.VARCHAR);

            consulta.executeUpdate();

            String error = consulta.getString(7);

            if (error != null) {
                throw new MiExcepcion("Error en dar de alta el cobrador "
                        + pCobrador.getCedula() + ": " + error);
            }
        } finally {
            Conexion.cerrarRecursos(cnn, consulta);
        }
    }

    @Override
    public Cobrador BuscarCobrador(long pCedula) throws ClassNotFoundException, SQLException {
        Connection cnn = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        try {
            Cobrador cobBuscado = null;

            cnn = Conexion.getConexion();
            consulta = cnn.prepareStatement(
                    "SELECT e.*, t.alarmas, t.camaras FROM empleados e INNER JOIN tecnicos t ON e.cedula = t.cedula WHERE e.cedula = ?;");

            consulta.setLong(1, pCedula);

            resultado = consulta.executeQuery();

            String clave;
            String nombre;
            Date fechaIngreso;
            double sueldo;
            String tipoTransporte;

            if (resultado.next()) {
                clave = resultado.getString("clave");
                nombre = resultado.getString("nombre");
                fechaIngreso = resultado.getDate("fechaIngreso");
                sueldo = resultado.getDouble("sueldo");
                tipoTransporte = resultado.getString("tipoTransporte");
                cobBuscado = new Cobrador(pCedula, clave, nombre, fechaIngreso, sueldo, tipoTransporte);
            }

            return cobBuscado;
        } finally {
            Conexion.cerrarRecursos(cnn, consulta, resultado);
        }
    }

    @Override
    public void ModificarCobrador(Cobrador pCobrador) throws ClassNotFoundException, SQLException, MiExcepcion {
        Connection cnn = null;
        CallableStatement consulta = null;
        try {
            cnn = Conexion.getConexion();
            consulta = cnn.prepareCall(
                    "{ CALL modificarCobrador(?, ?, ?, ?, ?, ?, ?) }");

            consulta.setLong(1, pCobrador.getCedula());
            consulta.setString(2, pCobrador.getClave());
            consulta.setString(3, pCobrador.getNombre());
            consulta.setDate(4, java.sql.Date.valueOf(pCobrador.getFechaIngreso().toString()));
            consulta.setDouble(5, pCobrador.getSueldo());
            consulta.setString(6, pCobrador.getTipoTransporte());
            consulta.registerOutParameter(7, java.sql.Types.VARCHAR);

            consulta.executeUpdate();

            String error = consulta.getString(7);

            if (error != null) {
                throw new MiExcepcion("Error en modificar el cobrador "
                        + pCobrador.getCedula() + ": " + error);
            }
        } finally {
            Conexion.cerrarRecursos(cnn, consulta);
        }
    }
     @Override
    public Cobrador LoginCobrador(long pCedula, String pPass) throws ClassNotFoundException, SQLException, MiExcepcion {
        Connection cnn = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        Cobrador pCob = null;
        try {
            
           
            cnn = Conexion.getConexion();
            consulta = cnn.prepareStatement(
                    "SELECT * FROM empleados WHERE cedula = ? and clave = ?");

            consulta.setLong(1, pCedula);
            consulta.setString(2, pPass);
            resultado = consulta.executeQuery();
            
            String clave;
            String nombre;
            Date fechaIngreso;
            double sueldo;
            String tipoTransporte;
        
            if (resultado.next()) {
                clave = resultado.getString("clave");
                nombre = resultado.getString("nombre");
                fechaIngreso = resultado.getDate("fechaIngreso");
                sueldo = resultado.getDouble("sueldo");
                tipoTransporte = resultado.getString("tipoTransporte");
                pCob = new Cobrador(pCedula, clave, nombre, fechaIngreso, sueldo, tipoTransporte);
            }
            
            return pCob;
        } finally {
            Conexion.cerrarRecursos(cnn, consulta, resultado);
        }
    }
}
