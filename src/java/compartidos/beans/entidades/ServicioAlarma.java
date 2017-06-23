/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compartidos.beans.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ernesto
 */
public class ServicioAlarma extends Servicio implements Serializable{

    private int _codigoAnulacion;
    private List<Alarma> _alarmas;

    public ServicioAlarma(int pNumero, Propiedad pPropiedad, Date pFechaContratacion,
            Boolean pMonitoreo, int pCodigoAnulacion, List<Alarma> pAlarmas) {
        super(pNumero, pPropiedad, pFechaContratacion, pMonitoreo);
        setCodigoAnulacion(pCodigoAnulacion);
        setAlarmas(pAlarmas);
    }
    
    public ServicioAlarma(){
        
    }

    public int getCodigoAnulacion() {
        return _codigoAnulacion;
    }

    public void setCodigoAnulacion(int _codigoAnulacion) {
        this._codigoAnulacion = _codigoAnulacion;
    }

    public List<Alarma> getAlarmas() {
        return _alarmas;
    }

    public void setAlarmas(List<Alarma> _alarmas) {
        this._alarmas = _alarmas;
    }
}
