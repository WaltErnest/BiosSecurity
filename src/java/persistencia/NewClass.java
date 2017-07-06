/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.IOException;

/**
 *
 * @author Diego
 */
public class NewClass {
    public static void main(String args[]) throws IOException{
        TxtPrecio p = new TxtPrecio();
        p.cargar();
    }
}
