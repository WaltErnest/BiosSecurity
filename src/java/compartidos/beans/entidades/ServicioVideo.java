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
public class ServicioVideo extends Servicio implements Serializable {

    private Boolean _terminalGrabacion;
    private List<Camara> _camaras;

    public ServicioVideo(int pNumero, Propiedad pPropiedad, Date pFechaContratacion,
            Boolean pMonitoreo, double pPrecio, Boolean pTerminalGrabacion, List<Camara> pCamaras) {
        super(pNumero, pPropiedad, pFechaContratacion, pMonitoreo, pPrecio);
        setTerminalGrabacion(pTerminalGrabacion);
        setCamaras(pCamaras);
    }
    
    public ServicioVideo(){
        
    }

    public Boolean getTerminalGrabacion() {
        return _terminalGrabacion;
    }

    public void setTerminalGrabacion(Boolean _terminalGrabacion) {
        this._terminalGrabacion = _terminalGrabacion;
    }

    public List<Camara> getCamaras() {
        return _camaras;
    }

    public void setCamaras(List<Camara> _camaras) {
        this._camaras = _camaras;
    }
}
