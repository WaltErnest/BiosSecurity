/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import entidades.Empleado;
import java.sql.SQLException;
import miexcepcion.MiExcepcion;

/**
 *
 * @author sg0212441
 */
public interface ILogicaEmpleado {
    public void AltaEmpleado(Empleado pEmpleado) throws ClassNotFoundException, SQLException, MiExcepcion;
    public Empleado BuscarEmpleado(long pCedula, String pTipo) throws ClassNotFoundException, SQLException;
    public void ModificarEmpleado(Empleado pEmpleado) throws ClassNotFoundException, SQLException, MiExcepcion;
}
