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
import java.util.ArrayList;
import persistencia.*;


/**
 *
 * @author Diego
 */
public class LogicaDispositivo implements ILogicaDispositivo{
    IPersistenciaAlarma pAlarma;
    IPersistenciaCamara pCamara;
    public void AltaDispositivo(Dispositivo pDispositivo) throws ClassNotFoundException, SQLException, MiExcepcion{
        if (pDispositivo instanceof Alarma){
            pAlarma = FabricaPersistencia.GetPersistenciaAlarma();
            pAlarma.AltaAlarma((Alarma) pDispositivo);
        } else if (pDispositivo instanceof Camara){
            pCamara = FabricaPersistencia.GetPersistenciaCamara();
            pCamara.AltaCamara((Camara) pDispositivo);
        }
    }
    public ArrayList<Dispositivo> ListarDispositivos()throws ClassNotFoundException, SQLException{
        ArrayList<Dispositivo> vector = new ArrayList();
      
        ArrayList<Alarma> alarmas = FabricaPersistencia.GetPersistenciaAlarma().ListarAlarmas();
        for(Alarma a : alarmas)
        {
            a.getNumeroInventario();            
            vector.add(a);
        }
        
         ArrayList<Camara> camaras = FabricaPersistencia.GetPersistenciaCamara().ListarCamaras();
        for(Camara c : camaras)
        {
            c.getNumeroInventario();
            vector.add(c);
        }
        return vector;
    }
}
