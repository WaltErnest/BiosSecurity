/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import compartidos.beans.entidades.Tecnico;
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
 * 
 * @version Diego, agrego login
 */
public class PersistenciaTecnico implements IPersistenciaTecnico {

    @Override
    public void AltaTecnico(Tecnico pTecnico) throws ClassNotFoundException, SQLException, MiExcepcion {
        Connection cnn = null;
        CallableStatement consulta = null;
        try {
            cnn = Conexion.getConexion();
            consulta = cnn.prepareCall(
                    "{ CALL altaTecnico(?, ?, ?, ?, ?, ?, ?, ?) }");

            consulta.setLong(1, pTecnico.getCedula());
            consulta.setString(2, pTecnico.getClave());
            consulta.setString(3, pTecnico.getNombre());
            consulta.setDate(4, java.sql.Date.valueOf(pTecnico.getFechaIngreso().toString()));
            consulta.setDouble(5, pTecnico.getSueldo());
            consulta.setBoolean(6, pTecnico.getAlarmas());
            consulta.setBoolean(7, pTecnico.getCamaras());
            consulta.registerOutParameter(8, java.sql.Types.VARCHAR);

            consulta.executeUpdate();

            String error = consulta.getString(8);

            if (error != null) {
                throw new MiExcepcion("Error en dar de alta el técnico "
                        + pTecnico.getCedula() + ": " + error);
            }
        } finally {
            Conexion.cerrarRecursos(cnn, consulta);
        }
    }

    @Override
    public Tecnico BuscarTecnico(long pCedula) throws ClassNotFoundException, SQLException {
        Connection cnn = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        try {
            Tecnico tecBuscado = null;

            cnn = Conexion.getConexion();
            consulta = cnn.prepareStatement(
                    "SELECT e.*, t.alarmas, t.camaras FROM empleados e INNER JOIN tecnicos t ON e.cedula = t.cedula WHERE e.cedula = ?;");

            consulta.setLong(1, pCedula);

            resultado = consulta.executeQuery();

            String clave;
            String nombre;
            Date fechaIngreso;
            double sueldo;
            boolean alarmas;
            boolean camaras;

            if (resultado.next()) {
                clave = resultado.getString("clave");
                nombre = resultado.getString("nombre");
                fechaIngreso = resultado.getDate("fechaIngreso");
                sueldo = resultado.getDouble("sueldo");
                alarmas = resultado.getBoolean("alarmas");
                camaras = resultado.getBoolean("camaras");
                tecBuscado = new Tecnico(pCedula, clave, nombre, fechaIngreso, sueldo, alarmas, camaras);
            }

            return tecBuscado;
        } finally {
            Conexion.cerrarRecursos(cnn, consulta);
        }
    }
    
    @Override
    public void ModificarTecnico(Tecnico pTecnico) throws ClassNotFoundException, SQLException, MiExcepcion {
        Connection cnn = null;
        CallableStatement consulta = null;
        try {
            cnn = Conexion.getConexion();
            consulta = cnn.prepareCall(
                    "{ CALL modificarTecnico(?, ?, ?, ?, ?, ?, ?, ?) }");

            consulta.setLong(1, pTecnico.getCedula());
            consulta.setString(2, pTecnico.getClave());
            consulta.setString(3, pTecnico.getNombre());
            consulta.setDate(4, java.sql.Date.valueOf(pTecnico.getFechaIngreso().toString()));
            consulta.setDouble(5, pTecnico.getSueldo());
            consulta.setBoolean(6, pTecnico.getAlarmas());
            consulta.setBoolean(7, pTecnico.getCamaras());
            consulta.registerOutParameter(8, java.sql.Types.VARCHAR);

            consulta.executeUpdate();

            String error = consulta.getString(8);

            if (error != null) {
                throw new MiExcepcion("Error en modificar el técnico "
                        + pTecnico.getCedula() + ": " + error);
            }
        } finally {
            Conexion.cerrarRecursos(cnn, consulta);
        }
    }
    
    @Override
    public Tecnico LoginTecnico(long pCedula, String pPass) throws ClassNotFoundException, SQLException, MiExcepcion {
        Connection cnn = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        Tecnico pTec = null;
        try {
                       
            cnn = Conexion.getConexion();
            consulta = cnn.prepareStatement(
                     "SELECT * FROM empleados WHERE cedula in(select tecnicos.cedula from tecnicos where tecnicos.cedula = empleados.cedula) and cedula = ? and clave = ?;");
            consulta.setLong(1, pCedula);
            consulta.setString(2, pPass);
            resultado = consulta.executeQuery();
            
            String clave;
            String nombre;
            Date fechaIngreso;
            double sueldo;
            boolean alarmas;
            boolean camaras;

            if (resultado.next()) {
                clave = resultado.getString("clave");
                nombre = resultado.getString("nombre");
                fechaIngreso = resultado.getDate("fechaIngreso");
                sueldo = resultado.getDouble("sueldo");
                alarmas = resultado.getBoolean("alarmas");
                camaras = resultado.getBoolean("camaras");
                pTec = new Tecnico(pCedula, clave, nombre, fechaIngreso, sueldo, alarmas, camaras);
            }

            return pTec;
        } finally {
            Conexion.cerrarRecursos(cnn, consulta, resultado);
        }
    }
    
    @Override
    public void EliminarTecnico(long pCedula) throws ClassNotFoundException, SQLException, MiExcepcion {
        Connection cnn = null;
        CallableStatement consulta = null;

        try {
            cnn = Conexion.getConexion();
            consulta = cnn.prepareCall(
                    "{ CALL bajaTecnico(?, ?) }");

            consulta.setLong(1, pCedula);
            consulta.registerOutParameter(2, java.sql.Types.VARCHAR);

            consulta.executeUpdate();

            String error = consulta.getString(2);

            if (error != null) {
                throw new MiExcepcion("Error en eliminar el técnico "
                        + pCedula + ": " + error);
            }
        } finally {
            Conexion.cerrarRecursos(cnn, consulta);
        }
    }
}
