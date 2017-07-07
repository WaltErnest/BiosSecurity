/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.servlets.controladores;

import compartidos.beans.entidades.Dispositivo;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.FabricaLogica;

/**
 *
 * @author Diego
 */
public class ControladorDispositivos extends Controlador {

    @Override
    public void index_get() {
        try {            
            ArrayList<Dispositivo> dispositivos = FabricaLogica.GetLogicaDispositivo().ListarDispositivos();
            request.setAttribute("dispositivos", dispositivos);
            cargarMensaje("Cantidad de dispositivos: " + dispositivos.size(), request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los dispositivos.", request);
        }
        
        mostrarVista("index");
    }

    @Override
    protected void mostrarVista(String vista, HttpServletRequest request, HttpServletResponse response) {
        agregarMensajeSesionAMensaje(request);

        try {
            String nombreCarpetaVista = this.getClass().getSimpleName().replaceFirst("Controlador", "");

            RequestDispatcher despachador = request.getRequestDispatcher("WEB-INF/vistas/" + nombreCarpetaVista + "/" + vista + ".jsp");

            if (despachador != null) {
                despachador.forward(request, response);
            }
        } catch (Exception ex) {
            System.out.println("¡ERROR! No se pudo mostrar la vista " + vista + ".");
        }
    }
    public void agregar_get(){
        mostrarVista("agregar", request, response);

    }
    public void agregar_get(HttpServletRequest request, HttpServletResponse response) {
        mostrarVista("agregar", request, response);

    }
}
