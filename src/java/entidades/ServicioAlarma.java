/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Ernesto
 */
public class ServicioAlarma extends Servicio {

    private int _codigoAnulacion;
    private List<Alarma> _alarmas;

    public ServicioAlarma(int pNumero, Propiedad pPropiedad, Date pFechaContratacion,
            Boolean pMonitoreo, Double pPrecio, int pCodigoAnulacion, List<Alarma> pAlarmas) {
        super(pNumero, pPropiedad, pFechaContratacion, pMonitoreo, pPrecio);
        _codigoAnulacion = pCodigoAnulacion;
        _alarmas = pAlarmas;
    }

    public int getCodigoAnulacion() {
        return _codigoAnulacion;
    }

    public void setCodigoAnulacion(int _codigoAnulacion) {
        this._codigoAnulacion = _codigoAnulacion;
    }

    public List<Alarma> getAlarmasContratadas() {
        return _alarmas;
    }

    public void setAlarmasContratadas(List<Alarma> _alarmasContratadas) {
        this._alarmas = _alarmasContratadas;
    }
}
