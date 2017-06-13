/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import compartidos.beans.entidades.Administrativo;
import java.sql.SQLException;
import compartidos.beans.excepciones.MiExcepcion;

/**
 *
 * @author Ernesto
 */
public interface IPersistenciaAdministrativo  {
    public void AltaAdministrativo(Administrativo pAdmin) throws ClassNotFoundException, SQLException, MiExcepcion;
    public Administrativo BuscarAdministrativo(long pCedula) throws ClassNotFoundException, SQLException;
    public void ModificarAdministrativo(Administrativo pAdmin) throws ClassNotFoundException, SQLException, MiExcepcion;
}
