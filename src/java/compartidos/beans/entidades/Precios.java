/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compartidos.beans.entidades;
import java.io.Serializable;
/**
 *
 * @author desquitin
 */
public class Precios implements Serializable{
    private double pbA; //precio base alarma
    private double pbC; //precio base camara
    private double apA; //adicional por alarma
    private double apC; //adicional por camara
    private double tmA; //taza monitoreo alarma
    private double tmC; //tasa monitorio camara

    public double getApA() {
        return apA;
    }

    public void setApA(double apA) {
        this.apA = apA;
    }

    public double getApC() {
        return apC;
    }

    public void setApC(double apC) {
        this.apC = apC;
    }

    public double getTmA() {
        return tmA;
    }

    public void setTmA(double tmA) {
        this.tmA = tmA;
    }

    public double getTmC() {
        return tmC;
    }

    public void setTmC(double tmC) {
        this.tmC = tmC;
    }

    public double getPbC() {
        return pbC;
    }

    public void setPbC(double pbC) {
        this.pbC = pbC;
    }

    public double getPbA() {
        return pbA;
    }

    public void setPbA(double pbA) {
        this.pbA = pbA;
    }
    

    public Precios(double pbA, double pbC, double apA, double apC, double tmA, double tmC) {
        setPbA(pbA);
        setPbC(pbC);
        setApA(apA);
        setApC(apC);
        setTmA(tmA);
        setTmC(tmC);
    }
    public Precios(){
        this(0, 0, 0, 0, 0, 0);
    }
}
