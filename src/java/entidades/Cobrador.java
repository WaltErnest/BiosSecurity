/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;

/**
 *
 * @author Ernesto
 */
public class Cobrador extends Empleado {

    private String _tipoTransporte;

    public Cobrador() {
    }

    public Cobrador(long pCedula, String pClave, String pNombre, Date pFechaIngreso,
            double pSueldo, String pTipoTransporte) {
        super(pCedula, pClave, pNombre, pFechaIngreso, pSueldo);
        _tipoTransporte = pTipoTransporte;
    }
    
    public String getTipoTransporte() {
        return _tipoTransporte;
    }

    public void setTipoTransporte(String _tipoTransporte) {
        this._tipoTransporte = _tipoTransporte;
    }
}
