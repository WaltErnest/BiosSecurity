/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.servlets.controladores;
import compartidos.beans.entidades.Precios;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistencia.TxtPrecio;
/**
 *
 * @author desquitin
 */
public class ControladorPrecios extends Controlador{          
        @Override
        public void index_get() {
            mostrarVista("index");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response); //To change body of generated methods, choose Tools | Templates.
        HttpSession sesion = request.getSession();
    

        String[] listaPrecio = new String[6];
        if (request.getParameter("pbA").equals("") || !isNumeric(request.getParameter("pbA"))) {
            listaPrecio[0] = "Precio sin cargar o no es numerico, el txt queda con el dato anterior.";
        } else {
            listaPrecio[0] = "Precio base alarmas      - $ " + request.getParameter("pbA");
        }
        if (request.getParameter("pbC").equals("") || !isNumeric(request.getParameter("pbC"))) {
            listaPrecio[0] = "Precio sin cargar o no es numerico, el txt queda con el dato anterior.";
        } else {
            listaPrecio[1] = "Precio base camaras      - $ " + request.getParameter("pbC");
        }
        if (request.getParameter("apA").equals("") || !isNumeric(request.getParameter("apA"))) {
            listaPrecio[0] = "Precio sin cargar o no es numerico, el txt queda con el dato anterior.";
        } else {
            listaPrecio[2] = "Adicional por alarma     - $ " + request.getParameter("apA");
        }
        if (request.getParameter("apC").equals("") || !isNumeric(request.getParameter("apC"))) {
            listaPrecio[0] = "Precio sin cargar o no es numerico, el txt queda con el dato anterior.";
        } else {
            listaPrecio[3] = "Adicional por camara     - $ " + request.getParameter("apC");
        }
        if (request.getParameter("tmA").equals("") || !isNumeric(request.getParameter("tmA"))) {
            listaPrecio[0] = "Precio sin cargar o no es numerico, el txt queda con el dato anterior.";
        } else {
            listaPrecio[4] = "Tasa monitoreo alarmas   - " + request.getParameter("tmA") + " %";
        }
        if (request.getParameter("tmC").equals("") || !isNumeric(request.getParameter("tmC"))) {
            listaPrecio[0] = "Precio sin cargar o no es numerico, el txt queda con el dato anterior.";
        } else {
            listaPrecio[5] = "Tasa monitoreo camaras   - " + request.getParameter("tmC") + " %";
        }
        TxtPrecio p = new TxtPrecio();
        for(String linea : listaPrecio)
        {
            p.leer(linea);
        }
        cargarMensaje("Datos guardados con éxito.");
           mostrarVista("index");
           
    }
    private static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
