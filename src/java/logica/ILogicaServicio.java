/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import compartidos.beans.entidades.Servicio;
import compartidos.beans.excepciones.MiExcepcion;
import java.sql.SQLException;

/**
 *
 * @author Mathias
 */
public interface ILogicaServicio {
    
    Servicio buscarServicio(int pNumero) throws ClassNotFoundException, SQLException, MiExcepcion;
    void altaServicio(Servicio pServicio) throws ClassNotFoundException, SQLException, MiExcepcion;
}
