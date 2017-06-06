/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;

/**
 *
 * @author Ernesto
 */
public class Alarma extends Dispositivo implements Serializable{
    
    public Alarma(long pNumeroInventario, String pUbicacion) {
        super(pNumeroInventario, pUbicacion);
    }
    
    public Alarma(){
        
    }
}
