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
            consulta = cnn.prepareStatement("SELECT * FROM alarmas");

            ArrayList<Alarma> alarmas = new ArrayList();
            Alarma pAlarma;

            resultado = consulta.executeQuery();

            long numero =0;
            String descrip = "";
            
            while(resultado.next()){
                numero = resultado.getLong(1);
                pAlarma = new Alarma(numero, descrip);
                alarmas.add(pAlarma);
            }
            return alarmas;
        } finally {
            Conexion.cerrarRecursos(cnn, consulta, resultado);
        }
    }
}
