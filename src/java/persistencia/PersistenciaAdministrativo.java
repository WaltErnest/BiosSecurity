/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import compartidos.beans.entidades.Administrativo;
import compartidos.beans.excepciones.MiExcepcion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Ernesto
 * @version Diego, agrego login
 */
class PersistenciaAdministrativo implements IPersistenciaAdministrativo {

    @Override
    public void AltaAdministrativo(Administrativo pAdmin) throws ClassNotFoundException, SQLException, MiExcepcion {
        Connection cnn = null;
        CallableStatement consulta = null;

        try {
            cnn = Conexion.getConexion();
            consulta = cnn.prepareCall(
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
            Conexion.cerrarRecursos(cnn, consulta);
        }
    }

    @Override
    public Administrativo BuscarAdministrativo(long pCedula) throws ClassNotFoundException, SQLException {
        Connection cnn = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        try {
            Administrativo admBuscado = null;

            cnn = Conexion.getConexion();
            consulta = cnn.prepareStatement(
                    "SELECT e.* FROM empleados e INNER JOIN administrativos a ON e.cedula = a.cedula WHERE e.cedula = ?;");

            consulta.setLong(1, pCedula);

            resultado = consulta.executeQuery();

            String clave;
            String nombre;
            Date fechaIngreso;
            double sueldo;

            if (resultado.next()) {
                clave = resultado.getString("clave");
                nombre = resultado.getString("nombre");
                fechaIngreso = resultado.getDate("fechaIngreso");
                sueldo = resultado.getDouble("sueldo");
                admBuscado = new Administrativo(pCedula, clave, nombre, fechaIngreso, sueldo);
            }

            return admBuscado;
        } finally {
            Conexion.cerrarRecursos(cnn, consulta, resultado);
        }
    }

    @Override
    public void ModificarAdministrativo(Administrativo pAdmin) throws ClassNotFoundException, SQLException, MiExcepcion {
        Connection cnn = null;
        CallableStatement consulta = null;

        try {
            cnn = Conexion.getConexion();
            consulta = cnn.prepareCall(
                    "{ CALL modificarAdministrativo(?, ?, ?, ?, ?, ?) }");

            consulta.setLong(1, pAdmin.getCedula());
            consulta.setString(2, pAdmin.getClave());
            consulta.setString(3, pAdmin.getNombre());
            consulta.setDate(4, java.sql.Date.valueOf(pAdmin.getFechaIngreso().toString()));
            consulta.setDouble(5, pAdmin.getSueldo());
            consulta.registerOutParameter(6, java.sql.Types.VARCHAR);

            consulta.executeUpdate();

            String error = consulta.getString(6);

            if (error != null) {
                throw new MiExcepcion("Error en modificar el administrativo "
                        + pAdmin.getCedula() + ": " + error);
            }
        } finally {
                Conexion.cerrarRecursos(cnn, consulta);
        }
    }
    
    @Override
    public Administrativo LoginAdministrativo(long pCedula, String pPass) throws ClassNotFoundException, SQLException, MiExcepcion {
        Connection cnn = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        
        try {
            Administrativo pAdm = null;
           
            cnn = Conexion.getConexion();
            consulta = cnn.prepareStatement(
                    "SELECT * FROM empleados WHERE cedula in(select administrativos.cedula from administrativos where administrativos.cedula = empleados.cedula) and cedula = ? and clave = ?;");

            consulta.setLong(1, pCedula);
            consulta.setString(2, pPass);
            resultado = consulta.executeQuery();

            String clave;
            String nombre;
            Date fechaIngreso;
            double sueldo;
        
            if (resultado.next()) {
                clave = resultado.getString("clave");
                nombre = resultado.getString("nombre");
                fechaIngreso = resultado.getDate("fechaIngreso");
                sueldo = resultado.getDouble("sueldo");
                pAdm = new Administrativo(pCedula, clave, nombre, fechaIngreso, sueldo);
            }
            
            return pAdm;
        } finally {
            Conexion.cerrarRecursos(cnn, consulta, resultado);
        }
    }
}
