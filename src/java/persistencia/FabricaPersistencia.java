/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

/**
 *
 * @author sg0212441
 */
public class FabricaPersistencia {
    public static IPersistenciaAdministrativo GetPersistenciaAdministrativo(){
        return new PersistenciaAdministrativo();
    }
    
    public static IPersistenciaTecnico GetPersistenciaTecnico(){
        return new PersistenciaTecnico();
    }
    
    public static IPersistenciaCobrador GetPersistenciaCobrador(){
        return new PersistenciaCobrador();
    }
    
    public static IPersistenciaCliente GetPersistenciaCliente() {
        return new PersistenciaCliente();
    }

    public static IPersistenciaPropiedad GetPersistenciaPropiedad() {
        return new PersistenciaPropiedad();
    }

    public static IPersistenciaAlarma GetPersistenciaAlarma() {
        return new PersistenciaAlarma();
    }

    public static IPersistenciaCamara GetPersistenciaCamara() {
        return new PersistenciaCamara();
    }
}
