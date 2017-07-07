/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import compartidos.beans.entidades.Camara;
import java.sql.SQLException;
import compartidos.beans.excepciones.MiExcepcion;
import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public interface IPersistenciaCamara {
    void AltaCamara(Camara pCamara)throws ClassNotFoundException, SQLException, MiExcepcion;
    ArrayList<Camara> ListarCamaras() throws ClassNotFoundException, SQLException;
}
