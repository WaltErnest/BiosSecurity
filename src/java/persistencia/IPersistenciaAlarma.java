/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidades.Alarma;
import java.sql.SQLException;
import miexcepcion.MiExcepcion;

/**
 *
 * @author Diego
 */
public interface IPersistenciaAlarma {
    void AltaAlarma(Alarma pAlarma)throws ClassNotFoundException, SQLException, MiExcepcion;
    
}
