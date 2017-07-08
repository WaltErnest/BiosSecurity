/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import compartidos.beans.entidades.Tecnico;
import java.sql.SQLException;
import compartidos.beans.excepciones.MiExcepcion;

/**
 *
 * @author Ernesto
 */
public interface IPersistenciaTecnico {
    public void AltaTecnico(Tecnico pTecnico) throws MiExcepcion;
    public Tecnico BuscarTecnico(long pCedula) throws MiExcepcion;
    public void ModificarTecnico(Tecnico pTecnico) throws MiExcepcion;
    public void EliminarTecnico(long pCedula) throws MiExcepcion;
    public Tecnico LoginTecnico(long pCedula, String pPass) throws ClassNotFoundException, SQLException, MiExcepcion;
}
