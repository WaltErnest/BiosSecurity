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
import java.util.Date;
import miexcepcion.MiExcepcion;

/**
 *
 * @author Ernesto
 */
public class PersistenciaCobrador implements IPersistenciaCobrador {

    public void AltaCobrador(Cobrador pCobrador) throws ClassNotFoundException, SQLException, MiExcepcion {
        Connection cnn = null;
        try {
            cnn = Conexion.Conectar();
            CallableStatement consulta = cnn.prepareCall(
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
            if (cnn != null) {
                Conexion.Desconectar(cnn);
            }
        }
    }

    public Cobrador BuscarCobrador(long pCedula) throws ClassNotFoundException, SQLException {
        Connection cnn = null;
        try {
            Cobrador cobBuscado = null;

            cnn = Conexion.Conectar();
            PreparedStatement consulta = cnn.prepareStatement(
                    "SELECT e.*, t.alarmas, t.camaras FROM empleados e INNER JOIN tecnicos t ON e.cedula = t.cedula WHERE e.cedula = ?;");

            consulta.setLong(1, pCedula);

            ResultSet resultado = consulta.executeQuery();

            String clave;
            String nombre;
            Date fechaIngreso;
            double sueldo;
            String tipoTransporte;

            while (resultado.next()) {
                clave = resultado.getString("clave");
                nombre = resultado.getString("nombre");
                fechaIngreso = resultado.getDate("fechaIngreso");
                sueldo = resultado.getDouble("sueldo");
                tipoTransporte = resultado.getString("tipoTransporte");
                cobBuscado = new Cobrador(pCedula, clave, nombre, fechaIngreso, sueldo, tipoTransporte);
            }

            return cobBuscado;
        } finally {
            if (cnn != null) {
                Conexion.Desconectar(cnn);
            }
        }
    }
    
    public void ModificarCobrador(Cobrador pCobrador) throws ClassNotFoundException, SQLException, MiExcepcion {
        Connection cnn = null;
        try {
            cnn = Conexion.Conectar();
            CallableStatement consulta = cnn.prepareCall(
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
            if (cnn != null) {
                Conexion.Desconectar(cnn);
            }
        }
    }
}
