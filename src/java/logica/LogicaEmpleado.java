/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import entidades.*;
import java.sql.SQLException;
import miexcepcion.MiExcepcion;
import persistencia.*;

/**
 *
 * @author Ernesto
 */
public class LogicaEmpleado {

    IPersistenciaAdministrativo perAdmin;
    PersistenciaCobrador perCobrador;
    PersistenciaTecnico perTecnico;

    public void AltaEmpleado(Empleado pEmpleado) throws ClassNotFoundException, SQLException, MiExcepcion {
        if (pEmpleado instanceof Administrativo) {
            perAdmin = FabricaPersistencia.GetPersistenciaAdministrativo();
            perAdmin.AltaAdministrativo((Administrativo) pEmpleado);
        } else if (pEmpleado instanceof Cobrador) {
            perCobrador = new PersistenciaCobrador();
            perCobrador.AltaCobrador((Cobrador) pEmpleado);
        } else if (pEmpleado instanceof Tecnico) {
            perTecnico = new PersistenciaTecnico();
            perTecnico.AltaTecnico((Tecnico) pEmpleado);
        }
    }
}
