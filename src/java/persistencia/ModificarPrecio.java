/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author desquitin
 */
// queda a modo de prueba nada mas
public class ModificarPrecio {
    
    public static void main(String[] args) throws IOException {

        File TextFile = new File("Resources/ListaDePreciosTest.txt");
        FileWriter TextOut = new FileWriter(TextFile, true);
        
        TextOut.write("Prueba de Grabación \r\n");
        TextOut.close();
    }
}
