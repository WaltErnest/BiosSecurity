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
public class Propiedad implements Serializable{
    private int _numeroPropiedad;
    private TipoPropiedad _tipoPropiedad;
    private String _direccion;
    private Cliente _dueno;

    public Propiedad(int pNumero, TipoPropiedad pTipo, String pDireccion,
            Cliente pDueno) {
        setNumeroPropiedad(pNumero);
        setTipoPropiedad(pTipo);
        setDireccion(pDireccion);
        setDueno(pDueno);
    }
    
    public Propiedad(){
        
    }

    public enum TipoPropiedad {
        Casa, Apartamento, Local, Terreno;
    }

    public int getNumeroPropiedad() {
        return _numeroPropiedad;
    }

    public void setNumeroPropiedad(int _numeroPropiedad) {
        this._numeroPropiedad = _numeroPropiedad;
    }

    public TipoPropiedad getTipoPropiedad() {
        return _tipoPropiedad;
    }

    public void setTipoPropiedad(TipoPropiedad _tipoPropiedad) {
        this._tipoPropiedad = _tipoPropiedad;
    }

    public String getDireccion() {
        return _direccion;
    }

    public void setDireccion(String _direccion) {
        this._direccion = _direccion;
    }

    public Cliente getDueno() {
        return _dueno;
    }

    public void setDueno(Cliente _dueno) {
        this._dueno = _dueno;
    }
}
