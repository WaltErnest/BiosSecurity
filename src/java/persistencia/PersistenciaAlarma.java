/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import compartidos.beans.entidades.Alarma;
import compartidos.beans.entidades.Dispositivo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import compartidos.beans.excepciones.MiExcepcion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Diego
 */
class PersistenciaAlarma implements IPersistenciaAlarma {

    public void AltaAlarma(Alarma pAlarma) throws ClassNotFoundException, SQLException, MiExcepcion {
        Connection cnn = null;
        CallableStatement consulta = null;
        try {
            cnn = Conexion.getConexion();
            consulta = cnn.prepareCall("{CALL altaAlarma(?,?)}");

            consulta.setLong(1, pAlarma.getNumeroInventario());
            consulta.registerOutParameter(2, java.sql.Types.VARCHAR);
            consulta.executeUpdate();

            String error = consulta.getString(2);
            if (error != null) {
                throw new MiExcepcion("Error en dar de alta la alarma "
                        + pAlarma.getNumeroInventario() + ": " + error);
            }
        } finally {
            Conexion.cerrarRecursos(cnn, consulta);
        }
    }

    public ArrayList<Alarma> ListarAlarmas() throws ClassNotFoundException, SQLException {
        Connection cnn = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        try {

            cnn = Conexion.getConexion();
            consulta = cnn.prepareStatement(
                    "SELECT * FROM dispositivos where numeroInventario in(select alarmas.numeroInventario from alarmas);");

            ArrayList<Alarma> alarmas = new ArrayList();
            Alarma pAlarma;

            resultado = consulta.executeQuery();

            long numero = 0;
            String descrip = "";

            while (resultado.next()) {
                numero = resultado.getLong(1);
                descrip = resultado.getString(2);
                pAlarma = new Alarma(numero, descrip);
                alarmas.add(pAlarma);
            }
            return alarmas;
        } finally {
            Conexion.cerrarRecursos(cnn, consulta, resultado);
        }
    }

    public ArrayList<Alarma> BuscarAlarmas(String busqueda) throws ClassNotFoundException, SQLException {
        Connection cnn = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        try {

            cnn = Conexion.getConexion();
            consulta = cnn.prepareStatement(
                    "SELECT * FROM dispositivos where numeroInventario in(select alarmas.numeroInventario from alarmas) and numeroInventario = ? OR descripcion = ?;");

            ArrayList<Alarma> alarmas = new ArrayList();
            Alarma pAlarma;

            consulta.setString(1, busqueda);
            consulta.setString(2, "%" + busqueda + "%");
            resultado = consulta.executeQuery();

            long numero = 0;
            String descrip = "";

            while (resultado.next()) {
                numero = resultado.getLong(1);
                descrip = resultado.getString(2);
                pAlarma = new Alarma(numero, descrip);
                alarmas.add(pAlarma);
            }
            return alarmas;
        } finally {
            Conexion.cerrarRecursos(cnn, consulta, resultado);
        }
    }

    public Alarma BuscarAlarma(String busqueda) throws ClassNotFoundException, SQLException {
        Connection cnn = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        try {

            cnn = Conexion.getConexion();
            consulta = cnn.prepareStatement(
                    "SELECT * FROM dispositivos where numeroInventario in(select alarmas.numeroInventario from alarmas) and numeroInventario = ? OR descripcion = ?;");

            Alarma pAlarma = null;

            consulta.setString(1, busqueda);
            consulta.setString(2, "%" + busqueda + "%");
            resultado = consulta.executeQuery();

            long numero = 0;
            String descrip = "";

            while (resultado.next()) {
                numero = resultado.getLong(1);
                descrip = resultado.getString(2);
                pAlarma = new Alarma(numero, descrip);
            }

            return pAlarma;
        } finally {
            Conexion.cerrarRecursos(cnn, consulta, resultado);
        }
    }

    public void modificarAlarma(Alarma pAlarma)
            throws ClassNotFoundException, SQLException {
        Connection cnn = null;
        PreparedStatement consulta = null;

        try {
            cnn = Conexion.getConexion();
            consulta = cnn.prepareStatement("UPDATE dispositivos SET descripcion =? WHERE numeroInventario = ?;");

            consulta.setLong(2, pAlarma.getNumeroInventario());
            consulta.setString(1, pAlarma.getDescripcionUbicacion());

            int filasAfectadas = consulta.executeUpdate();

            if (filasAfectadas < 1) {
                throw new Exception();
            }
        } catch (Exception ex) {

        } finally {
            Conexion.cerrarRecursos(cnn, consulta);
        }
    }

    public void EliminarAlarma(Alarma pAlarma)throws ClassNotFoundException, SQLException {
        Connection cnn = null;
        PreparedStatement consulta = null;

        try {
            cnn = Conexion.getConexion();
            consulta = cnn.prepareStatement("DELETE FROM alarmas WHERE numeroInventario =?;");
            
            consulta.setLong(1, pAlarma.getNumeroInventario());
            

            int filasAfectadas = consulta.executeUpdate();

            if (filasAfectadas < 1) {
                throw new Exception();
            }else{
                consulta = cnn.prepareStatement("DELETE FROM dispositivos WHERE numeroInventario =?;");
                consulta.setLong(1, pAlarma.getNumeroInventario());
                filasAfectadas = consulta.executeUpdate();
            }
        } catch (Exception ex) {

        } finally {
            Conexion.cerrarRecursos(cnn, consulta);
        }
    }
}
