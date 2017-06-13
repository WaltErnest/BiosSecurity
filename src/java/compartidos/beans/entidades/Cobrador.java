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
public class Cobrador extends Empleado implements Serializable{

    private String _tipoTransporte;

    public Cobrador() {
    }

    public Cobrador(long pCedula, String pClave, String pNombre, Date pFechaIngreso,
            double pSueldo, String pTipoTransporte) {
        super(pCedula, pClave, pNombre, pFechaIngreso, pSueldo);
        setTipoTransporte(pTipoTransporte);
    }
    
    public String getTipoTransporte() {
        return _tipoTransporte;
    }

    public void setTipoTransporte(String _tipoTransporte) {
        this._tipoTransporte = _tipoTransporte;
    }
}
