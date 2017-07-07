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
            consulta = cnn.prepareStatement("SELECT * FROM camaras");

            ArrayList<Camara> camaras = new ArrayList();
            Camara pCamara;

            resultado = consulta.executeQuery();

            long numero = 0;
            String descrip = "";
            boolean interior = false;

            while (resultado.next()) {
                numero = resultado.getLong(1);
                pCamara = new Camara(numero, descrip, interior);
                camaras.add(pCamara);
            }
            return camaras;
        } finally {
            Conexion.cerrarRecursos(cnn, consulta, resultado);
        }
    }
}
