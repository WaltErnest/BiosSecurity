/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import compartidos.beans.entidades.Dispositivo;
import java.sql.SQLException;
import compartidos.beans.excepciones.MiExcepcion;

/**
 *
 * @author Diego
 */
public interface ILogicaDispositivo {
    public void AltaDispositivo(Dispositivo pDispositivo) throws ClassNotFoundException, SQLException, MiExcepcion;
}
