/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import compartidos.beans.entidades.Empleado;
import java.sql.SQLException;
import compartidos.beans.excepciones.MiExcepcion;
import java.util.ArrayList;

/**
 *
 * @author Ernesto
 */
public interface ILogicaEmpleado {
    public void AltaEmpleado(Empleado pEmpleado) throws MiExcepcion;
    public Empleado BuscarEmpleado(long pCedula, String pTipo) throws MiExcepcion;
    public void EliminarEmpleado(long pCedula, String pTipo) throws MiExcepcion;
    public void ModificarEmpleado(Empleado pEmpleado) throws MiExcepcion;
    public Empleado Login(long pCedula, String pPass) throws ClassNotFoundException, SQLException, MiExcepcion;
    public ArrayList<Empleado> ListarEmpleados(String pCriterio) throws MiExcepcion;
}
