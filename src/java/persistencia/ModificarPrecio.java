/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;

/**
 *
 * @author desquitin
 */
// queda a modo de prueba nada mas
public class ModificarPrecio {

/*
  public static void main(String[] args) throws IOException {
    
    String lineaOld = "Precio base alarmas		- $ X.XX";
    String lineaNew = "Precio base alarmas		- $ 9.89";
    ModificarFichero(fileOld, lineaOld, lineaNew);
  }
    */
    public void ModificarFichero(String lineaOld, String lineaNew) {  
        File fileOld = new File("C:\\Users\\desquitin\\Documents\\NetBeansProjects\\BiosSecurity\\Resources\\Lista.txt");
        Random numaleatorio = new Random(3816L);                 
        String fileNewName = fileOld.getParent() + "/auxiliar" + String.valueOf(Math.abs(numaleatorio.nextInt())) + ".txt";        
        File fileNew = new File(fileNewName);
        
        try {            
            if (fileOld.exists()) {    
                //BufferedReader br = new BufferedReader(new FileReader(fileOld));
                BufferedReader br = new BufferedReader(new InputStreamReader (new FileInputStream (fileOld), "utf-8"));
                String lineAux;   
                String cortaAux = "";
                while ((lineAux = br.readLine()) != null) {    
                    
                     cortaAux = lineAux.trim().substring(0, 19);
                    if (cortaAux.equals(lineaOld.toUpperCase().trim().substring(0, 19))) {                        
                        Ecribir(fileNew, lineaNew);
                    } else {                        
                        Ecribir(fileNew, lineAux);
                    }
                    
                }
                br.close();                
                String fileOldName = fileOld.getName();   //para msj             
                Borrar(fileOld);
                fileNew.renameTo(fileOld);                               
            } else {
                System.out.println("Archivo no existe.");
            }
        } catch (Exception ex) {           
            System.out.println(ex.getMessage());
        }
    }

    private static void Ecribir(File file, String string) {
        try {            
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "utf-8"));
            bw.write(string + "\r\n");            
            bw.close();
        } catch (Exception ex) {
            
            System.out.println(ex.getMessage());
        }
    }

    private static void Borrar(File file) {
        try {            
            if (file.exists()) {             
                file.delete();
                System.out.println("Archivo borrado correctamente.");
            }
        } catch (Exception ex) {            
            System.out.println(ex.getMessage());
        }
    }
}
