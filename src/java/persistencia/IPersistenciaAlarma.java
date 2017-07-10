/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import compartidos.beans.entidades.Alarma;
import java.sql.SQLException;
import compartidos.beans.excepciones.MiExcepcion;
import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public interface IPersistenciaAlarma {
    void AltaAlarma(Alarma pAlarma)throws ClassNotFoundException, SQLException, MiExcepcion;
    ArrayList<Alarma> ListarAlarmas() throws ClassNotFoundException, SQLException;
    ArrayList<Alarma> BuscarAlarmas(String busqueda) throws ClassNotFoundException, SQLException;
    Alarma BuscarAlarma(String busqueda) throws ClassNotFoundException, SQLException;
    void modificarAlarma(Alarma pAlarma)throws ClassNotFoundException, SQLException ;
    void EliminarAlarma(Alarma pAlarma)throws ClassNotFoundException, SQLException;
}
