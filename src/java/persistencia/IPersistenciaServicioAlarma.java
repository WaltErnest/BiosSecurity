/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import compartidos.beans.entidades.ServicioAlarma;
import compartidos.beans.excepciones.MiExcepcion;
import java.sql.SQLException;

/**
 *
 * @author Mathias
 */
public interface IPersistenciaServicioAlarma {
    void altaServicioAlarma(ServicioAlarma pServicioAlarma) throws ClassNotFoundException, SQLException, MiExcepcion;
    ServicioAlarma buscarServicioAlarma(int pNumero) throws ClassNotFoundException, SQLException, MiExcepcion;
    void bajaServicioAlarma(ServicioAlarma pServicio) throws ClassNotFoundException, SQLException, MiExcepcion;
}
