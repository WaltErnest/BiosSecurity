/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import compartidos.beans.entidades.Cobrador;
import java.sql.SQLException;
import compartidos.beans.excepciones.MiExcepcion;

/**
 *
 * @author Ernesto
 */
public interface IPersistenciaCobrador {
    public void AltaCobrador(Cobrador pCobrador) throws ClassNotFoundException, SQLException, MiExcepcion;
    public Cobrador BuscarCobrador(long pCedula) throws ClassNotFoundException, SQLException;
    public void ModificarCobrador(Cobrador pCobrador) throws ClassNotFoundException, SQLException, MiExcepcion;
    public void EliminarCobrador(long pCedula) throws ClassNotFoundException, SQLException, MiExcepcion;
    public Cobrador LoginCobrador(long pCedula, String pPass) throws ClassNotFoundException, SQLException, MiExcepcion;
}
