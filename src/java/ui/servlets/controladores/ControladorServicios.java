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
                cargarMensaje("ERROR! Se ha producido un error al cargar al cliente", request);
            }
            mostrarVista("index", request, response);
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
