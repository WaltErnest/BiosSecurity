/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compartidos.beans.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Ernesto
 */
public abstract class Empleado implements Serializable{

    private long _cedula;
    private String _clave;
    private String _nombre;
    private LocalDate _fechaIngreso;
    private double _sueldo;
    
    public Empleado() {
    }

    public Empleado(long pCedula, String pClave, String pNombre, LocalDate pFechaIngreso,
            double pSueldo) {
        setCedula(pCedula);
        setClave(pClave);
        setNombre(pNombre);
        setFechaIngreso(pFechaIngreso);
        setSueldo(pSueldo);
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

    public LocalDate getFechaIngreso() {
        return _fechaIngreso;
    }

    public void setFechaIngreso(LocalDate _fechaIngreso) {
        this._fechaIngreso = _fechaIngreso;
    }

    public double getSueldo() {
        return _sueldo;
    }

    public void setSueldo(double _sueldo) {
        this._sueldo = _sueldo;
    }
}
