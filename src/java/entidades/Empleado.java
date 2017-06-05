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
public abstract class Empleado {

    private long _cedula;
    private String _clave;
    private String _nombre;
    private Date _fechaIngreso;
    private double _sueldo;
    
    public Empleado() {
    }

    public Empleado(long pCedula, String pClave, String pNombre, Date pFechaIngreso,
            double pSueldo) {
        _cedula = pCedula;
        _clave = pClave;
        _nombre = pNombre;
        _fechaIngreso = pFechaIngreso;
        _sueldo = pSueldo;
    }

    public long getCedula() {
        return _cedula;
    }

    public void setCedula(long _cedula) {
        this._cedula = _cedula;
    }

    public String getClave() {
        return _clave;
    }

    public void setClave(String _clave) {
        this._clave = _clave;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public Date getFechaIngreso() {
        return _fechaIngreso;
    }

    public void setFechaIngreso(Date _fechaIngreso) {
        this._fechaIngreso = _fechaIngreso;
    }

    public double getSueldo() {
        return _sueldo;
    }

    public void setSueldo(double _sueldo) {
        this._sueldo = _sueldo;
    }
}
