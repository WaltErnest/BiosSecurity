/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import compartidos.beans.entidades.Dispositivo;
import java.sql.SQLException;
import compartidos.beans.excepciones.MiExcepcion;
import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public interface ILogicaDispositivo {
    public void AltaDispositivo(Dispositivo pDispositivo) throws ClassNotFoundException, SQLException, MiExcepcion;
    ArrayList<Dispositivo> ListarDispositivos()throws ClassNotFoundException, SQLException;
    ArrayList<Dispositivo> BuscarDispositivos(String busqueda)throws ClassNotFoundException, SQLException;
    Dispositivo BuscarDispositivo(String busqueda)throws ClassNotFoundException, SQLException;
    void ModificarDispositivo(Dispositivo pDispositivo) throws ClassNotFoundException, SQLException, MiExcepcion;
    void EliminarDispositivo(Dispositivo pDispositivo) throws ClassNotFoundException, SQLException, MiExcepcion;
}
