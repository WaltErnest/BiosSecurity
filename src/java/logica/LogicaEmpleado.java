/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import compartidos.beans.entidades.Tecnico;
import compartidos.beans.entidades.Cobrador;
import compartidos.beans.entidades.Administrativo;
import compartidos.beans.entidades.Empleado;
import java.sql.SQLException;
import compartidos.beans.excepciones.MiExcepcion;
import persistencia.*;

/**
 *
 * @author Ernesto
 */
public class LogicaEmpleado implements ILogicaEmpleado {
    IPersistenciaAdministrativo perAdmin;
    IPersistenciaCobrador perCobrador;
    IPersistenciaTecnico perTecnico;

    public void AltaEmpleado(Empleado pEmpleado) throws ClassNotFoundException, SQLException, MiExcepcion {
        if (pEmpleado instanceof Administrativo) {
            perAdmin = FabricaPersistencia.GetPersistenciaAdministrativo();
            perAdmin.AltaAdministrativo((Administrativo) pEmpleado);
        } else if (pEmpleado instanceof Cobrador) {
            perCobrador = FabricaPersistencia.GetPersistenciaCobrador();
            perCobrador.AltaCobrador((Cobrador) pEmpleado);
        } else if (pEmpleado instanceof Tecnico) {
            perTecnico = FabricaPersistencia.GetPersistenciaTecnico();
            perTecnico.AltaTecnico((Tecnico) pEmpleado);
        } else {
            throw new MiExcepcion("No existe el tipo de empleado deseado.");
        }
    }

    public Empleado BuscarEmpleado(long pCedula, String pTipo) throws ClassNotFoundException, SQLException {
        Empleado empBuscado = null;
        switch (pTipo) {
            case "A":
                perAdmin = FabricaPersistencia.GetPersistenciaAdministrativo();
                empBuscado = perAdmin.BuscarAdministrativo(pCedula);
                break;
            case "T":
                perTecnico = FabricaPersistencia.GetPersistenciaTecnico();
                empBuscado = perTecnico.BuscarTecnico(pCedula);
                break;
            case "C":
                perCobrador = FabricaPersistencia.GetPersistenciaCobrador();
                empBuscado = perCobrador.BuscarCobrador(pCedula);
                break;
        }
        return empBuscado;
    }

    public void ModificarEmpleado(Empleado pEmpleado) throws ClassNotFoundException, SQLException, MiExcepcion {
        if (pEmpleado instanceof Administrativo) {
            perAdmin = FabricaPersistencia.GetPersistenciaAdministrativo();
            perAdmin.ModificarAdministrativo((Administrativo) pEmpleado);
        } else if (pEmpleado instanceof Cobrador) {
            perCobrador = FabricaPersistencia.GetPersistenciaCobrador();
            perCobrador.ModificarCobrador((Cobrador) pEmpleado);
        } else if (pEmpleado instanceof Tecnico) {
            perTecnico = FabricaPersistencia.GetPersistenciaTecnico();
            perTecnico.ModificarTecnico((Tecnico) pEmpleado);
        } else {
            throw new MiExcepcion("No existe el tipo de empleado deseado.");
        }
    }
     public int LoginAdministrativo(long pCedula, String pPass) throws ClassNotFoundException, SQLException, MiExcepcion{
        
         perAdmin = FabricaPersistencia.GetPersistenciaAdministrativo();
         int i = perAdmin.LoginAdministrativo(pCedula, pPass);
         //if (i==0){
             //perTecnico = FabricaPersistencia.GetPersistenciaTecnico();
             
         //}
         return i;
     }
}
