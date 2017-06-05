/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Ernesto
 */
public class Camara extends Dispositivo {

    private Boolean _interior;

    public Camara(long pNumeroInventario, String pUbicacion, Boolean pInterior) {
        super(pNumeroInventario, pUbicacion);
        _interior = pInterior;
    }

    public Boolean getInterior() {
        return _interior;
    }

    public void setInterior(Boolean _interior) {
        this._interior = _interior;
    }
}
