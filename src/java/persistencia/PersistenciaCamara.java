/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import compartidos.beans.entidades.Camara;
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
public class PersistenciaCamara implements IPersistenciaCamara {

    @Override
    public void AltaCamara(Camara pCamara) throws ClassNotFoundException, SQLException, MiExcepcion {
        Connection cnn = null;
        CallableStatement consulta = null;
        try {
            cnn = Conexion.getConexion();
            consulta = cnn.prepareCall("{CALL altaCamara(?,?)}");

            consulta.setLong(1, pCamara.getNumeroInventario());
            consulta.registerOutParameter(2, java.sql.Types.VARCHAR);
            consulta.executeUpdate();

            String error = consulta.getString(2);
            if (error != null) {
                throw new MiExcepcion("Error en dar de alta la alarma "
                        + pCamara.getNumeroInventario() + ": " + error);
            }
        } finally {
            Conexion.cerrarRecursos(cnn, consulta);
        }
    }

    public ArrayList<Camara> ListarCamaras() throws ClassNotFoundException, SQLException {
        Connection cnn = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        try {

            cnn = Conexion.getConexion();
            consulta = cnn.prepareStatement(
                    "SELECT * FROM dispositivos where numeroInventario in(select camaras.numeroInventario from camaras);");

            ArrayList<Camara> camaras = new ArrayList();
            Camara pCamara;

            resultado = consulta.executeQuery();

            long numero = 0;
            String descrip = "";
            boolean interior = false;

            while (resultado.next()) {
                numero = resultado.getLong(1);
                descrip = resultado.getString(2);
                pCamara = new Camara(numero, descrip, interior);
                camaras.add(pCamara);
            }
            return camaras;
        } finally {
            Conexion.cerrarRecursos(cnn, consulta, resultado);
        }
    }
    
    public ArrayList<Camara> BuscarCamaras(String busqueda) throws ClassNotFoundException, SQLException {
        Connection cnn = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        try {

            cnn = Conexion.getConexion();
            consulta = cnn.prepareStatement(
                    "SELECT d.*, c.interior FROM dispositivos d INNER JOIN camaras c ON d.numeroInventario = c.numeroInventario WHERE d.numeroInventario = ? OR descripcion = ?;");
                    //"SELECT * FROM dispositivos where numeroInventario in(select camaras.numeroInventario from camaras) and numeroInventario = ? OR descripcion = ?;");

            ArrayList<Camara> camaras = new ArrayList();
            Camara pCamara;
            consulta.setString(1, busqueda);
            consulta.setString(2, "%"+busqueda+"%");
            resultado = consulta.executeQuery();

            long numero = 0;
            String descrip = "";
            boolean interior = false;

            while (resultado.next()) {
                numero = resultado.getLong(1);
                descrip = resultado.getString(2);
                interior = resultado.getBoolean(3);
                pCamara = new Camara(numero, descrip, interior);
                camaras.add(pCamara);
            }
            return camaras;
        } finally {
            Conexion.cerrarRecursos(cnn, consulta, resultado);
        }
    }
    
    public Camara BuscarCamara(String busqueda) throws ClassNotFoundException, SQLException {
        Connection cnn = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        try {

            cnn = Conexion.getConexion();
            consulta = cnn.prepareStatement(
                    "SELECT d.*, c.interior FROM dispositivos d INNER JOIN camaras c ON d.numeroInventario = c.numeroInventario WHERE d.numeroInventario = ? OR descripcion = ?;");
                    //"SELECT * FROM dispositivos where numeroInventario in(select camaras.numeroInventario from camaras) and numeroInventario = ? OR descripcion = ?;");

            Camara pCamara = null;
            consulta.setString(1, busqueda);
            consulta.setString(2, "%"+busqueda+"%");
            resultado = consulta.executeQuery();

            long numero = 0;
            String descrip = "";
            boolean interior = false;

            while (resultado.next()) {
                numero = resultado.getLong(1);
                descrip = resultado.getString(2); 
                interior = resultado.getBoolean(3);
                pCamara = new Camara(numero, descrip, interior);
            }
            
            return pCamara;
        } finally {
            Conexion.cerrarRecursos(cnn, consulta, resultado);
        }
    }
    
    public void modificarCamara(Camara pCamara) throws ClassNotFoundException, SQLException {
        Connection cnn = null;
        PreparedStatement consulta = null;

        try {
            cnn = Conexion.getConexion();
            consulta = cnn.prepareStatement("UPDATE dispositivos SET descripcion =? WHERE numeroInventario = ?;");
            int filasAfectadas = consulta.executeUpdate();

            consulta.setLong(1, pCamara.getNumeroInventario());
            consulta.setString(2, pCamara.getDescripcionUbicacion());
            consulta.setBoolean(3, pCamara.getInterior());

            consulta = cnn.prepareStatement("UPDATE camaras  SET interior = ? WHERE numeroInventario = ?;");
            filasAfectadas = consulta.executeUpdate();

            if (filasAfectadas < 1) {
                throw new Exception();
            }
        } catch (Exception ex) {

        } finally {
            Conexion.cerrarRecursos(cnn, consulta);
        }
    }
        public void EliminarCamara(Camara pCamara)throws ClassNotFoundException, SQLException {
        Connection cnn = null;
        PreparedStatement consulta = null;

        try {
            cnn = Conexion.getConexion();
            consulta = cnn.prepareStatement("DELETE FROM camaras WHERE numeroInventario =?;");

            consulta.setLong(1, pCamara.getNumeroInventario());
         

            int filasAfectadas = consulta.executeUpdate();

            if (filasAfectadas < 1) {
                throw new Exception();
            }else{
                consulta = cnn.prepareStatement("DELETE FROM dispositivos WHERE numeroInventario =?;");
                consulta.setLong(1, pCamara.getNumeroInventario());
                filasAfectadas = consulta.executeUpdate();
            }
        } catch (Exception ex) {

        } finally {
            Conexion.cerrarRecursos(cnn, consulta);
        }
    }
}
