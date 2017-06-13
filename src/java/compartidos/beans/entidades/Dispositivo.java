/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compartidos.beans.entidades;

import java.io.Serializable;

/**
 *
 * @author Ernesto
 */
public abstract class Dispositivo implements Serializable {

    private long _numeroInventario;
    private String _descripcionUbicacion;

    public Dispositivo(long pNumeroInventario, String pUbicacion) {
        setNumeroInventario(pNumeroInventario);
        setDescripcionUbicacion(pUbicacion);
    }

    public Dispositivo() {

    }

    public long getNumeroInventario() {
        return _numeroInventario;
    }

    public void setNumeroInventario(long _numeroInventario) {
        this._numeroInventario = _numeroInventario;
    }

    public String getDescripcionUbicacion() {
        return _descripcionUbicacion;
    }

    public void setDescripcionUbicacion(String _descripcionUbicacion) {
        this._descripcionUbicacion = _descripcionUbicacion;
    }
}
