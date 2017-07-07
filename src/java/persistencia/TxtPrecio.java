/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import compartidos.beans.entidades.Precios;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;


/**
 *
 * @author Diego
 */
public class TxtPrecio {

    public void actualizar(String lineaNew) throws FileNotFoundException, IOException {
        File archivo = new File("ListaPrecios.txt");
        //C:\Users\Diego\AppData\Roaming\NetBeans\8.2\config\GF_4.1.1\domain1\config
        if (!archivo.exists()) {
            archivo.createNewFile();
            ListaPreciosNew(archivo);
        }
        File auxFile = new File("auxiliar.txt");
        if (!auxFile.exists()) {
            auxFile.createNewFile();
        }

        if (archivo.exists() && !archivo.isDirectory()) {
            try (FileReader fr = new FileReader(archivo);
                    BufferedReader br = new BufferedReader(fr)) {

                String linea;

                while ((linea = br.readLine()) != null && linea.length()>5) {
                    System.out.println(linea);

                    String LineaAux = linea.trim().substring(0, 22);
                    String LinNewAux = lineaNew.trim().substring(0, 22);
                    if (LineaAux.equals(LinNewAux)) {
                        Escribir(auxFile, lineaNew);
                    } else {
                        Escribir(auxFile, linea);
                    }
                }
            } catch (Exception ex) {
                System.out.println("¡ERROR! Ocurrió un error al leer el archivo de log."+ex);
            }
        }
        Borrar(archivo);
        ReNombrar(auxFile);
    }

    private static void Escribir(File fl, String st) {
        
        try (FileOutputStream fos = new FileOutputStream(fl, true);
                PrintStream ps = new PrintStream(fos);) {
            ps.println(st);
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al escribir en el archivo de precios.");
        }
    }

    private static void Borrar(File fl) {
        try {
            fl.delete();
            System.out.println("Archivo borrado correctamente.");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private static void ReNombrar(File fl) {
        try {
            fl.renameTo(new File("ListaPrecios.txt"));
            System.out.println("Archivo renombreado correctamente.");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    private static void ListaPreciosNew(File fl) {

        try (FileOutputStream fos = new FileOutputStream(fl, true);
            PrintStream ps = new PrintStream(fos);) {
            System.out.println("Creando nuevo ");
            String[] lista = new String[6];
            lista[0] = "Precio base alarmas      - $ X.XX";
            lista[1] = "Precio base camaras      - $ X.XX";
            lista[2] = "Adicional por alarma     - $ X.XX";
            lista[3] = "Adicional por camara     - $ X.XX";
            lista[4] = "Tasa monitoreo alarmas   - X%";
            lista[5] = "Tasa monitoreo camaras   - X%";
            for(String st : lista)
            {
            ps.println(st);
            }
           
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al escribir en el archivo de precios.");
        }
    }
    
    public Precios cargar() throws FileNotFoundException, IOException {

        File archivo = new File("ListaPrecios.txt");
        if (!archivo.exists()) {
            archivo.createNewFile();
            ListaPreciosNew(archivo);
        }
        Precios precio = null;
        if (archivo.exists() && !archivo.isDirectory()) {
            try (FileReader fr = new FileReader(archivo);
                    BufferedReader br = new BufferedReader(fr)) {

                String linea;
                Double[] numb = new Double[6];

                int i = 0;
                while ((linea = br.readLine()) != null && linea.length() > 5) {
                    System.out.println(linea);

                    linea = linea.replaceAll("[^?0-9]+", " ");
                    System.out.println(Arrays.asList(linea.trim().split(" ")));

                    String aux = Arrays.asList(linea.trim().split(" ")).toString();
                    aux = aux.replace("[", "");
                    aux = aux.replace("]", "");
                    aux = aux.replace(", ", ".");
                    System.out.println(aux);
                    if (aux.equals("")) {
                        aux = "0.00";
                    }
                    numb[i] = Double.parseDouble(aux);
                    i++;
                }
                double pbA = numb[0];
                double pbC = numb[1]; //precio base camara
                double apA = numb[2]; //adicional por alarma
                double apC = numb[3]; //adicional por camara
                double tmA = numb[4]; //taza monitoreo alarma
                double tmC = numb[5]; //tasa monitorio camara

                precio = new Precios(pbA, pbC, apA, apC, tmA, tmC);
            } catch (Exception ex) {
                System.out.println("¡ERROR! Ocurrió un error al leer el archivo de log." + ex);
            }
        }
        return precio;
    }
}
