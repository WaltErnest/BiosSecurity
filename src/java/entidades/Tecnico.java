/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ernesto
 */
public class Tecnico extends Empleado implements Serializable{

    private Boolean _alarmas;
    private Boolean _camaras;
    
    public Tecnico() {
    }

    public Tecnico(long pCedula, String pClave, String pNombre, Date pFechaIngreso,
            double pSueldo, Boolean pAlarmas, Boolean pCamaras) {
        super(pCedula, pClave, pNombre, pFechaIngreso, pSueldo);
        setAlarmas(pAlarmas);
        setCamaras(pCamaras);
    }

    public Boolean getAlarmas() {
        return this._alarmas;
    }

    public void setAlarmas(Boolean _alarmas) {
        this._alarmas = _alarmas;
    }

    public Boolean getCamaras() {
        return _camaras;
    }

    public void setCamaras(Boolean _camaras) {
        this._camaras = _camaras;
    }
}
