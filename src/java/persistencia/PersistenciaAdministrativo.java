/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import compartidos.beans.entidades.Administrativo;
import compartidos.beans.entidades.Cobrador;
import compartidos.beans.entidades.Tecnico;
import compartidos.beans.entidades.Empleado;
import compartidos.beans.excepciones.MiExcepcion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Ernesto
 * @version Diego, agrego login
 */
class PersistenciaAdministrativo implements IPersistenciaAdministrativo {

    @Override
    public void AltaAdministrativo(Administrativo pAdmin) throws MiExcepcion {
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
        } catch (Exception ex) {
            String error;
            if (ex instanceof MiExcepcion) {
                error = ex.getMessage();
            } else {
                error = "Error en dar de alta el administrativo. Contactese con un administrador del sitio";
            }

            throw new MiExcepcion(error);
        } finally {
            Conexion.cerrarRecursos(cnn, consulta);
        }
    }

    @Override
    public Administrativo BuscarAdministrativo(long pCedula) throws MiExcepcion {
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
            LocalDate fechaIngreso;
            double sueldo;

            if (resultado.next()) {
                clave = resultado.getString("clave");
                nombre = resultado.getString("nombre");
                fechaIngreso = resultado.getDate("fechaIngreso").toLocalDate();
                sueldo = resultado.getDouble("sueldo");
                admBuscado = new Administrativo(pCedula, clave, nombre, fechaIngreso, sueldo);
            }

            return admBuscado;
        } catch (Exception ex) {
            String error;
            if (ex instanceof MiExcepcion) {
                error = ex.getMessage();
            } else {
                error = "Error en buscar el administrativo. Contactese con un administrador del sitio";
            }

            throw new MiExcepcion(error);
        } finally {
            Conexion.cerrarRecursos(cnn, consulta, resultado);
        }
    }

    @Override
    public void ModificarAdministrativo(Administrativo pAdmin) throws MiExcepcion {
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
        } catch (Exception ex) {
            String error;
            if (ex instanceof MiExcepcion) {
                error = ex.getMessage();
            } else {
                error = "Error en modificar el administrativo. Contactese con un administrador del sitio";
            }

            throw new MiExcepcion(error);
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
            LocalDate fechaIngreso;
            double sueldo;

            if (resultado.next()) {
                clave = resultado.getString("clave");
                nombre = resultado.getString("nombre");
                fechaIngreso = resultado.getDate("fechaIngreso").toLocalDate();
                sueldo = resultado.getDouble("sueldo");
                pAdm = new Administrativo(pCedula, clave, nombre, fechaIngreso, sueldo);
            }

            return pAdm;
        } finally {
            Conexion.cerrarRecursos(cnn, consulta, resultado);
        }
    }

    @Override
    public void EliminarAdministrativo(long pCedula) throws MiExcepcion {
        Connection cnn = null;
        CallableStatement consulta = null;

        try {
            cnn = Conexion.getConexion();
            consulta = cnn.prepareCall(
                    "{ CALL bajaAdministrativo(?, ?) }");

            consulta.setLong(1, pCedula);
            consulta.registerOutParameter(2, java.sql.Types.VARCHAR);

            consulta.executeUpdate();

            String error = consulta.getString(2);

            if (error != null) {
                throw new MiExcepcion("Error en eliminar el administrativo "
                        + pCedula + ": " + error);
            }
        } catch (Exception ex) {
            String error;
            if (ex instanceof MiExcepcion) {
                error = ex.getMessage();
            } else {
                error = "Error en eliminar el administrativo. Contactese con un administrador del sitio";
            }

            throw new MiExcepcion(error);
        } finally {
            Conexion.cerrarRecursos(cnn, consulta);
        }
    }

    public ArrayList<Empleado> ListarEmpleados(String pCriterio) throws MiExcepcion {
        ArrayList<Empleado> listaEmpleados = new ArrayList();
        Connection cnn = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;

        try {
            cnn = Conexion.getConexion();
            consulta = cnn.prepareStatement("SELECT * FROM listaEmpleados WHERE Cedula = ? OR Nombre LIKE ?;");

            consulta.setString(1, pCriterio);
            consulta.setString(2, "%" + pCriterio + "%");

            resultado = consulta.executeQuery();

            while (resultado.next()) {
                Empleado emp = null;
                long cedula = resultado.getLong("cedula");
                String nombre = resultado.getString("nombre");
                LocalDate fechaIngreso = resultado.getDate("fechaIngreso").toLocalDate();
                double sueldo = resultado.getDouble("sueldo");
                String tipo = resultado.getString("tipo");

                switch (tipo) {
                    case "C":
                        String tipoTransporte = resultado.getString("tipoTransporte");
                        emp = new Cobrador(cedula, "", nombre, fechaIngreso, sueldo, tipoTransporte);
                        break;
                    case "T":
                        Boolean alarmas = resultado.getBoolean("alarmas");
                        Boolean camaras = resultado.getBoolean("camaras");
                        emp = new Tecnico(cedula, "", nombre, fechaIngreso, sueldo, alarmas, camaras);
                        break;
                    case "A":
                    default:
                        emp = new Administrativo(cedula, "", nombre, fechaIngreso, sueldo);
                        break;
                }

                listaEmpleados.add(emp);
            }

        } catch (Exception ex) {
            throw new MiExcepcion("Error en listar los empleados.");
        } finally {
            Conexion.cerrarRecursos(cnn, consulta, resultado);
        }

        return listaEmpleados;
    }
}
