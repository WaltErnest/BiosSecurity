/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import compartidos.beans.entidades.Alarma;
import compartidos.beans.entidades.Camara;
import compartidos.beans.entidades.Dispositivo;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public class NewClass {
    public static void main(String args[]) throws IOException, ClassNotFoundException, SQLException{
        /*TxtPrecio p = new TxtPrecio();
        p.cargar();*/
        
                ArrayList<Dispositivo> vector = new ArrayList();
      
        ArrayList<Alarma> alarmas = new ArrayList();
        alarmas = FabricaPersistencia.GetPersistenciaAlarma().ListarAlarmas();
        for(Alarma a : alarmas)
        {
            a.getNumeroInventario();
            //System.out.println(String.valueOf(a.getNumeroInventario()));
            vector.add(a);
        }
        
        
         ArrayList<Camara> camaras = new ArrayList();
        camaras = FabricaPersistencia.GetPersistenciaCamara().ListarCamaras();
        for(Camara c : camaras)
        {
            c.getNumeroInventario();
            //System.out.println(String.valueOf(c.getNumeroInventario()));
            vector.add(c);
        }
        for (Dispositivo d : vector){
            System.out.println(String.valueOf(d.getNumeroInventario()));
        }

    }
}
