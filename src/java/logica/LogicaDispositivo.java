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

    public ArrayList<Dispositivo> BuscarDispositivos(String busqueda) throws ClassNotFoundException, SQLException {
        ArrayList<Dispositivo> vector = new ArrayList();
        if (busqueda == null || busqueda.length() == 0) {
            
            ArrayList<Alarma> alarmas = FabricaPersistencia.GetPersistenciaAlarma().ListarAlarmas();
            for (Alarma a : alarmas) {
                a.getNumeroInventario();                
                vector.add(a);
            }
            
            ArrayList<Camara> camaras = FabricaPersistencia.GetPersistenciaCamara().ListarCamaras();
            for (Camara c : camaras) {
                c.getNumeroInventario();
                vector.add(c);
            }
        } else {
            ArrayList<Alarma> alarmas = FabricaPersistencia.GetPersistenciaAlarma().BuscarAlarmas(busqueda);
            for (Alarma a : alarmas) {
                a.getNumeroInventario();                
                vector.add(a);
            }
            
            ArrayList<Camara> camaras = FabricaPersistencia.GetPersistenciaCamara().BuscarCamaras(busqueda);
            for (Camara c : camaras) {
                c.getNumeroInventario();
                vector.add(c);
            }
        }
        return vector;
    }

    public Dispositivo BuscarDispositivo(String busqueda) throws ClassNotFoundException, SQLException {
        Dispositivo dispositivo = null;
        try {
            Alarma pAlarma = FabricaPersistencia.GetPersistenciaAlarma().BuscarAlarma(busqueda);
            if (pAlarma != null) {
                dispositivo = pAlarma;
            } else {
                Camara pCamara = FabricaPersistencia.GetPersistenciaCamara().BuscarCamara(busqueda);
                if (pCamara != null) {
                    dispositivo = pCamara;
                }
            }
        } catch (Exception ex) {

        }
        return dispositivo;
    }

    public ArrayList<Dispositivo> ListarDispositivos(String busqueda) throws ClassNotFoundException, SQLException {
        ArrayList<Dispositivo> vector = new ArrayList();    
            ArrayList<Alarma> alarmas = FabricaPersistencia.GetPersistenciaAlarma().ListarAlarmas();
            for (Alarma a : alarmas) {
                a.getNumeroInventario();                
                vector.add(a);
            }
            
            ArrayList<Camara> camaras = FabricaPersistencia.GetPersistenciaCamara().ListarCamaras();
            for (Camara c : camaras) {
                c.getNumeroInventario();
                vector.add(c);
            }
            return vector;
        }

    public void ModificarDispositivo(Dispositivo pDispositivo) throws ClassNotFoundException, SQLException, MiExcepcion {
        if (pDispositivo instanceof Alarma) {
            pAlarma = FabricaPersistencia.GetPersistenciaAlarma();
            pAlarma.modificarAlarma((Alarma) pDispositivo);
        } else if (pDispositivo instanceof Camara) {
            pCamara = FabricaPersistencia.GetPersistenciaCamara();
            pCamara.modificarCamara((Camara) pDispositivo);
        }
    }
    public void EliminarDispositivo(Dispositivo pDispositivo) throws ClassNotFoundException, SQLException, MiExcepcion {
        if (pDispositivo instanceof Alarma) {
            pAlarma = FabricaPersistencia.GetPersistenciaAlarma();
            pAlarma.EliminarAlarma((Alarma) pDispositivo);
        } else if (pDispositivo instanceof Camara) {
            pCamara = FabricaPersistencia.GetPersistenciaCamara();
            pCamara.EliminarCamara((Camara) pDispositivo);
        }
    }      
    @Override
    public ArrayList<Dispositivo> ListarDispositivos() throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
