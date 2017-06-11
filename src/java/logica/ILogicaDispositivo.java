/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import entidades.Dispositivo;
import java.sql.SQLException;
import miexcepcion.MiExcepcion;

/**
 *
 * @author Diego
 */
public interface ILogicaDispositivo {
    public void AltaDispositivo(Dispositivo pDispositivo) throws ClassNotFoundException, SQLException, MiExcepcion;
}
