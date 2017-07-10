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
import javax.servlet.http.HttpSession;
import logica.FabricaLogica;

/**
 *
 * @author Mathias
 */
public class ControladorServicios extends Controlador {
        
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sesion = request.getSession();
        Empleado login = (Empleado) sesion.getAttribute("usuario");
        
        try {
            if (login == null || !(login instanceof Administrativo)) {
                cargarMensaje("Solamente administradores pueden ingresar a la página.", request);                
                response.sendRedirect("inicio");
            }
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se ha producido un error al cargar al cliente", request);
        }
            mostrarVista("index", request, response);
    }
    
    public void buscarcliente_get(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sesion = request.getSession();
        Empleado login = (Empleado) sesion.getAttribute("usuario");

        try {
            if (login == null || !(login instanceof Administrativo)) {
                cargarMensaje("Solamente administradores pueden ingresar a la página.", request);                
                response.sendRedirect("inicio");
            }
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se ha producido un error buscar al cliente", request);
        }
            mostrarVista("buscarCliente", request, response);

    }
    
    public void buscarcliente_post(HttpServletRequest request, HttpServletResponse response) {
        try {
            String cedulaCliente = request.getParameter("cedula");

            if (cedulaCliente == null) {
                mostrarVista("buscarCliente", request, response);
            } else {
                Cliente cliente = FabricaLogica.GetLogicaCliente().buscarCliente((Long.parseLong(cedulaCliente)));

                if (cliente != null) {
                    HttpSession sesionAltaServicio = request.getSession();
                    sesionAltaServicio.setAttribute("cliente", cliente);
                } else {
                    cargarMensaje("No se encontró el cliente", request);
                }
            }
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se ha producido un error buscar al cliente", request);
        }
            mostrarVista("buscarCliente", request, response);
    }
    
    public void agregarclienteservicio_get(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession sesionAltaServicio = request.getSession();
            sesionAltaServicio.getAttribute("cliente");
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! No se pudo pasar el cliente al servicio", request);
        }
        mostrarVista("index", request, response);
    }
    
    public void modificarcliente_get(HttpServletRequest request, HttpServletResponse response) {
        long cedula = 0;
        
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cedula no es válida", request);
            
            mostrarVista("modificarCliente", request, response);
            
            return;            
        }
        
        try {
            Cliente cliente = FabricaLogica.GetLogicaCliente().buscarCliente(cedula);
            
            if (cliente != null) {
                request.setAttribute("cliente", cliente);
                cargarMensaje("¡Cliente encontrado!", request);
            } else {
                request.setAttribute("ocultarFormulario", true);
            }
        } catch (MiExcepcion ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(), request);
            
            mostrarVista("modificarCliente", request, response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al modificar al cliente.", request);
        }
        
        mostrarVista("modificarCliente", request, response);
    }
    
    public void modificarcliente_post(HttpServletRequest request, HttpServletResponse response) {
        long cedula = 0;
        
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));            
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cedula no es válida", request);
            
            mostrarVista("modificarCliente", request, response);
            
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
            
            mostrarVista("modificarCliente", request, response);
            
            return;
        }
        
        Cliente cliente = new Cliente(cedula, nombre, direccionCobro, barrioDirCobro, telefono);
        
        try {
            FabricaLogica.GetLogicaCliente().modificarCliente(cliente);
            
            cargarMensaje("¡El cliente fue modificado!", request.getSession());
            
            response.sendRedirect("servicios");
        } catch (MiExcepcion ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(), request);
            
            mostrarVista("modificarCliente", request, response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al modificar al cliente.", request);
            
            mostrarVista("modificarCliente", request, response);
        }
    }
    
    public void buscarpropiedad_get(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sesion = request.getSession();
        Empleado login = (Empleado) sesion.getAttribute("usuario");

        try {
            if (login == null || !(login instanceof Administrativo)) {
                cargarMensaje("Solamente administradores pueden ingresar a la página.", request);              
                response.sendRedirect("inicio");
            }
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se ha producido un error buscar al cliente", request);
        }
        
        mostrarVista("buscarPropiedad", request, response);
    }
    
    public void buscarpropiedad_post(HttpServletRequest request, HttpServletResponse response) {
        try {
            String numeroPropiedad = request.getParameter("numeroPropiedad");
            
            if (numeroPropiedad == null) {
                cargarMensaje("No hay un número de propiedad", request);    
            } else {
                HttpSession sesionAltaServicio = request.getSession();
                Cliente cliente = (Cliente)sesionAltaServicio.getAttribute("cliente");
                
                if (cliente != null) {
                    int numPropiedad = Integer.parseInt(numeroPropiedad);
                
                    Propiedad propiedad = FabricaLogica.GetLogicaPropiedad().buscarPropiedad(numPropiedad, cliente.getCedula());
                    
                    sesionAltaServicio.setAttribute("propiedad", propiedad);
                } else {
                    cargarMensaje("No hay un cliente encontrado", request);
                }
            }
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se ha producido un error buscar la propiedad", request);
        }
        
        mostrarVista("buscarPropiedad", request, response);
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
