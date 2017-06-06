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
public class PersistenciaTecnico {

    public void AltaTecnico(Tecnico pTecnico) throws ClassNotFoundException, SQLException, MiExcepcion {
        Connection cnn = null;
        try {
            cnn = Conexion.Conectar();
            CallableStatement consulta = cnn.prepareCall(
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

            String error = consulta.getString(6);

            if (error != null) {
                throw new MiExcepcion("Error en dar de alta el técnico "
                        + pTecnico.getCedula() + ": " + error);
            }
        } finally {
            if (cnn != null) {
                Conexion.Desconectar(cnn);
            }
        }
    }
}
