/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.servlets.controladores;
import compartidos.beans.entidades.Precios;
import persistencia.ModificarPrecio;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        Precios precios = (Precios)sesion.getAttribute("precios");
        ModificarPrecio mp; 
        if(precios==null){
            precios = new Precios();
            sesion.setAttribute("precios", precios);
        }
        precios.setPbA(Double.parseDouble(request.getParameter("pbA")));
        mp = new ModificarPrecio();
        String lineaOld = "Precio base alarmas";
        String lineaNew = "Precio base alarmas  - $ " + request.getParameter("pbA");
        mp.ModificarFichero(lineaOld, lineaNew);
        mostrarVista("index");
    }
        
}
