/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidades.Camara;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import miexcepcion.MiExcepcion;

/**
 *
 * @author Diego
 */
public class PersistenciaCamara implements IPersistenciaCamara{
    public void AltaCamara(Camara pCamara)throws ClassNotFoundException, SQLException, MiExcepcion {
        Connection cnn = null;
        CallableStatement consulta = null;
        try {
            cnn = Conexion.Conectar();
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
            if (cnn != null) {
                Conexion.Desconectar(cnn);
            }
        }
    }
    
}
