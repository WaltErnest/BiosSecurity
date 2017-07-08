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
import java.util.ArrayList;
import persistencia.*;

/**
 *
 * @author Ernesto
 */
public class LogicaEmpleado implements ILogicaEmpleado {

    IPersistenciaAdministrativo perAdmin;
    IPersistenciaCobrador perCobrador;
    IPersistenciaTecnico perTecnico;

    @Override
    public void AltaEmpleado(Empleado pEmpleado) throws MiExcepcion {
        ValidarEmpleado(pEmpleado);

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

    @Override
    public Empleado BuscarEmpleado(long pCedula, String pTipo) throws MiExcepcion {
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

    @Override
    public ArrayList<Empleado> ListarEmpleados(String pCriterio) throws MiExcepcion {
        perAdmin = FabricaPersistencia.GetPersistenciaAdministrativo();
        return perAdmin.ListarEmpleados(pCriterio);
    }

    @Override
    public void ModificarEmpleado(Empleado pEmpleado) throws MiExcepcion {
        ValidarEmpleado(pEmpleado);

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

    @Override
    public Empleado Login(long pCedula, String pPass) throws ClassNotFoundException, SQLException, MiExcepcion {
        Empleado pEmp = null;
        perAdmin = FabricaPersistencia.GetPersistenciaAdministrativo();
        pEmp = perAdmin.LoginAdministrativo(pCedula, pPass);
        if (pEmp == null) {
            perTecnico = FabricaPersistencia.GetPersistenciaTecnico();
            pEmp = perTecnico.LoginTecnico(pCedula, pPass);
            if (pEmp == null) {
                perCobrador = FabricaPersistencia.GetPersistenciaCobrador();
                pEmp = perCobrador.LoginCobrador(pCedula, pPass);
            }
        }
        return pEmp;
    }

    public void EliminarEmpleado(long pCedula, String pTipo) throws MiExcepcion {
        switch (pTipo) {
            case "A":
                perAdmin = FabricaPersistencia.GetPersistenciaAdministrativo();
                perAdmin.EliminarAdministrativo(pCedula);
                break;
            case "T":
                perTecnico = FabricaPersistencia.GetPersistenciaTecnico();
                perTecnico.EliminarTecnico(pCedula);
                break;
            case "C":
                perCobrador = FabricaPersistencia.GetPersistenciaCobrador();
                perCobrador.EliminarCobrador(pCedula);
                break;
        }
    }

    private void ValidarEmpleado(Empleado empleado) throws MiExcepcion {
        if (empleado == null) {
            throw new MiExcepcion("No hay empleado a validar.");
        }

        if (empleado.getCedula() < 1000000 || empleado.getCedula() > 9999999) {
            throw new MiExcepcion("La cédula debe contener 7 dígitos.");
        }

        if (empleado.getNombre().trim().isEmpty()) {
            throw new MiExcepcion("El nombre no puede ser vacío.");
        }

        if (empleado.getNombre().length() > 50) {
            throw new MiExcepcion("El nombre no puede exceder los 50 caracteres de longitud.");
        }

        if (empleado.getSueldo() < 0) {
            throw new MiExcepcion("El sueldo debe ser mayor o igual a 0.");
        }

        if (empleado instanceof Cobrador) {
            if (((Cobrador) empleado).getTipoTransporte().isEmpty()) {
                throw new MiExcepcion("El transporte no puede ser vacío.");
            }
        }

        if (empleado instanceof Tecnico) {
            if (!((Tecnico) empleado).getAlarmas() && !((Tecnico) empleado).getCamaras()) {
                throw new MiExcepcion("El técnico debe tener al menos una especialidad.");
            }
        }
    }
}
