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
    private double pbA; //precio base Alarma
    private double pbC;

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
    

    public Precios(double pbA, double pbC) {
        setPbA(pbA);
        setPbC(pbC);
    }
    public Precios(){
        this(0, 0);
    }
}
