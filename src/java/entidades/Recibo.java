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
public class Recibo {

    private Date _fechaCobro;
    private List<Servicio> _servicios;
    private double _totalPagar;

    public Recibo(Date pFechaCobro, List<Servicio> pServicios, double pTotal) {
        _fechaCobro = pFechaCobro;
        _servicios = pServicios;
        _totalPagar = pTotal;
    }

    public Date getFechaCobro() {
        return _fechaCobro;
    }

    public void setFechaCobro(Date _fechaCobro) {
        this._fechaCobro = _fechaCobro;
    }

    public List<Servicio> getServicios() {
        return _servicios;
    }

    public void setServicios(List<Servicio> _servicios) {
        this._servicios = _servicios;
    }

    public double getTotalPagar() {
        return _totalPagar;
    }

    public void setTotalPagar(double _totalPagar) {
        this._totalPagar = _totalPagar;
    }
}
