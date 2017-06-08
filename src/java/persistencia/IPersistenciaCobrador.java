/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidades.Cobrador;
import java.sql.SQLException;
import miexcepcion.MiExcepcion;

/**
 *
 * @author sg0212441
 */
public interface IPersistenciaCobrador {
    public void AltaCobrador(Cobrador pCobrador) throws ClassNotFoundException, SQLException, MiExcepcion;
    public Cobrador BuscarCobrador(long pCedula) throws ClassNotFoundException, SQLException;
    public void ModificarCobrador(Cobrador pCobrador) throws ClassNotFoundException, SQLException, MiExcepcion;
}
