/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidades.Camara;
import java.sql.SQLException;
import miexcepcion.MiExcepcion;

/**
 *
 * @author Diego
 */
public interface IPersistenciaCamara {
    void AltaCamara(Camara pCamara)throws ClassNotFoundException, SQLException, MiExcepcion;
    
}
