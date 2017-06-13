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
public abstract class Servicio implements Serializable{

    private int _numero;
    private Propiedad _propiedadCliente;
    private Date _fechaContratacion;
    private Boolean _monitoreo;
    private Double _precio;

    public Servicio(int pNumero, Propiedad pPropiedad, Date pFechaContratacion,
            Boolean pMonitoreo, Double pPrecio) {
        setNumero(pNumero);
        setPropriedadCliente(pPropiedad);
        setFechaContratacion(pFechaContratacion);
        setMonitoreo(pMonitoreo);
        setPrecio(pPrecio);
    }
    
    public Servicio(){
        
    }

    public int getNumero() {
        return _numero;
    }

    public void setNumero(int _numero) {
        this._numero = _numero;
    }

    public Propiedad getPropriedadCliente() {
        return _propiedadCliente;
    }

    public void setPropriedadCliente(Propiedad _propriedadCliente) {
        this._propiedadCliente = _propriedadCliente;
    }

    public Date getFechaContratacion() {
        return _fechaContratacion;
    }

    public void setFechaContratacion(Date _fechaContratacion) {
        this._fechaContratacion = _fechaContratacion;
    }

    public Boolean getMonitoreo() {
        return _monitoreo;
    }

    public void setMonitoreo(Boolean _monitoreo) {
        this._monitoreo = _monitoreo;
    }

    public Double getPrecio() {
        return _precio;
    }

    public void setPrecio(Double _precio) {
        this._precio = _precio;
    }
}
