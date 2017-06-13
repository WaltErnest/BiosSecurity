/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import compartidos.beans.entidades.Alarma;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import compartidos.beans.excepciones.MiExcepcion;

/**
 *
 * @author Diego
 */
class PersistenciaAlarma implements IPersistenciaAlarma {

    @Override
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

}
