/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.servlets.controladores;

import compartidos.beans.entidades.*;
import compartidos.beans.excepciones.MiExcepcion;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.FabricaLogica;

/**
 *
 * @author Mathias
 */
public class ControladorServicios extends Controlador {
        
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        try {
            Cliente cliente = FabricaLogica.GetLogicaCliente().buscarCliente((Long.parseLong(request.getParameter("buscarCliente"))));

            request.setAttribute("cliente", cliente);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se ha producido un error al cargar al cliente", request);
        }
        mostrarVista("index", request, response);
    }

    public void altacliente_get(HttpServletRequest request, HttpServletResponse response) {
            mostrarVista("altaCliente", request, response);
    }
    
    public void altacliente_post(HttpServletRequest request, HttpServletResponse response) {
        long cedula = 0;
        
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));            
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cedula no es válida", request);
            
            mostrarVista("altaCliente", request, response);
            
            return;
        }
        
        String nombre = request.getParameter("nombre");
        
        String direccionCobro = request.getParameter("direccionCobro");
        
        String barrioDirCobro = request.getParameter("barrioDirCobro");
        
        long telefono = 0;
        
        try {
            telefono = Long.parseLong(request.getParameter("telefono"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! El teléfono no es válido", request);
            
            mostrarVista("altaCliente", request, response);
            
            return;
        }
        
        Cliente cliente = new Cliente(cedula, nombre, direccionCobro, barrioDirCobro, telefono);
        
        try {
            FabricaLogica.GetLogicaCliente().altaCliente(cliente);
            
            cargarMensaje("¡El cliente fue agregado!", request.getSession());
            
            response.sendRedirect("servicios");
        } catch (MiExcepcion ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(), request);
            
            mostrarVista("altaCliente", request, response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al agregar al cliente.", request);
            
            mostrarVista("altaCliente", request, response);
        }
    }
/*
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }*/
}
