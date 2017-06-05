/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import miexcepcion.MiExcepcion;
import entidades.*;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Ernesto
 */
public class PersistenciaAdministrativo {

    public void AltaAdministrativo(Administrativo pAdmin) throws ClassNotFoundException, SQLException, MiExcepcion {
        try {
            Conexion.Conectar();
            CallableStatement consulta = Conexion.cnn.prepareCall(
                    "{ CALL altaAdministrativo(?, ?, ?, ?, ?, ?) }");

            consulta.setLong(1, pAdmin.getCedula());
            consulta.setString(2, pAdmin.getClave());
            consulta.setString(3, pAdmin.getNombre());
            consulta.setDate(4, java.sql.Date.valueOf(pAdmin.getFechaIngreso().toString()));
            consulta.setDouble(5, pAdmin.getSueldo());
            consulta.registerOutParameter(6, java.sql.Types.VARCHAR);

            consulta.executeUpdate();

            String error = consulta.getString(6);

            if (error != null) {
                throw new MiExcepcion("Error en dar de alta el administrativo "
                        + pAdmin.getCedula() + ": " + error);
            }
        } finally {
            Conexion.Desconectar();
        }
    }

    public Administrativo BuscarAdministrativo(long pCedula) throws ClassNotFoundException, SQLException {
        try {
            Administrativo admBuscado = null;

            Conexion.Conectar();
            PreparedStatement consulta = Conexion.cnn.prepareStatement(
                    "SELECT e.* FROM empleados e INNER JOIN administrativos a ON e.cedula = a.cedula WHERE e.cedula = ?;");

            consulta.setLong(1, pCedula);

            ResultSet resultado = consulta.executeQuery();

            String clave;
            String nombre;
            Date fechaIngreso;
            double sueldo;

            while (resultado.next()) {
                clave = resultado.getString("clave");
                nombre = resultado.getString("nombre");
                fechaIngreso = resultado.getDate("fechaIngreso");
                sueldo = resultado.getDouble("sueldo");
                admBuscado = new Administrativo(pCedula, clave, nombre, fechaIngreso, sueldo);
            }

            return admBuscado;
        } finally {
            Conexion.Desconectar();
        }
    }
}
