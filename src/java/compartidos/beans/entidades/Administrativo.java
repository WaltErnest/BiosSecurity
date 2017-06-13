/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compartidos.beans.entidades;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ernesto
 */
public class Administrativo extends Empleado implements Serializable{

    public Administrativo() {
    }

    public Administrativo(long pCedula, String pClave, String pNombre, Date pFechaIngreso,
            double pSueldo) {
        super(pCedula, pClave, pNombre, pFechaIngreso, pSueldo);
    }
}
