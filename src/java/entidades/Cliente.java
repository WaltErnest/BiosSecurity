/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.List;

/**
 *
 * @author Ernesto
 */
public class Cliente {

    private long _cedula;
    private String _nombre;
    private String _direccionCobro;
    private String _barrioDirCobro;
    private long _telefono;

    public Cliente() {

    }

    public Cliente(long pCedula, String pNombre, String pDirCobro, String pBarrioDirCobro,
            long pTelefono) {
        _cedula = pCedula;
        _nombre = pNombre;
        _direccionCobro = pDirCobro;
        _barrioDirCobro = pBarrioDirCobro;
        _telefono = pTelefono;
    }

    public long getCedula() {
        return _cedula;
    }

    public void setCedula(long _cedula) {
        this._cedula = _cedula;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String getDireccionCobro() {
        return _direccionCobro;
    }

    public void setDireccionCobro(String _direccionCobro) {
        this._direccionCobro = _direccionCobro;
    }

    public String getBarrioDirCobro() {
        return _barrioDirCobro;
    }

    public void setBarrioDirCobro(String _barrioDirCobro) {
        this._barrioDirCobro = _barrioDirCobro;
    }

    public long getTelefono() {
        return _telefono;
    }

    public void setTelefono(long _telefono) {
        this._telefono = _telefono;
    }
}
