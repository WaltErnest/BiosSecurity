/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidades.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import miexcepcion.MiExcepcion;

/**
 *
 * @author Ernesto
 */
public class PersistenciaCobrador {

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

            String error = consulta.getString(6);

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
}
