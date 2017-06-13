/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import compartidos.beans.entidades.Alarma;
import compartidos.beans.entidades.Camara;
import compartidos.beans.entidades.Dispositivo;
import java.sql.SQLException;
import compartidos.beans.excepciones.MiExcepcion;
import persistencia.*;


/**
 *
 * @author Diego
 */
public class LogicaDispositivo implements ILogicaDispositivo{
    IPersistenciaAlarma perAlarma;
    IPersistenciaCamara perCamara;
    public void AltaDispositivo(Dispositivo pDispositivo) throws ClassNotFoundException, SQLException, MiExcepcion{
        if (pDispositivo instanceof Alarma){
            perAlarma = FabricaPersistencia.GetPersistenciaAlarma();
            perAlarma.AltaAlarma((Alarma) pDispositivo);
        } else if (pDispositivo instanceof Camara){
            perCamara = FabricaPersistencia.GetPersistenciaCamara();
            perCamara.AltaCamara((Camara) pDispositivo);
        }
    }
}
