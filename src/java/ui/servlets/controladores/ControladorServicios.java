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
            } else {

                String cedulaCliente = request.getParameter("buscarCliente");

                if (cedulaCliente == null) {
                    mostrarVista("index", request, response);
                } else {
                    Cliente cliente = FabricaLogica.GetLogicaCliente().buscarCliente((Long.parseLong(request.getParameter("buscarCliente"))));

                    request.setAttribute("cliente", cliente);                
                }
            }
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
            cargarMensaje("¡ERROR! No se pudo agregar al cliente.", request);

            mostrarVista("altaCliente", request, response);
        }            
        
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
    
    public void altapropiedad_get(HttpServletRequest request, HttpServletResponse response) {
        mostrarVista("altaPropiedad", request, response);
    }
    
    public void altapropiedad_post(HttpServletRequest request, HttpServletResponse response) {
        int numeroPropiedad = 0;
        
        try {
            numeroPropiedad = Integer.parseInt(request.getParameter("numeroPropiedad"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! El número de propiedad no es válido", request);
            
            mostrarVista("altaPropiedad", request, response);
            
            return;
        }
        
        String valorTipoPropiedad = request.getParameter("tipoPropiedad");
        Propiedad.TipoPropiedad tipoPropiedad = null;
        
        try {
            tipoPropiedad = Propiedad.TipoPropiedad.valueOf(valorTipoPropiedad);
        } catch(IllegalArgumentException ex) {
            cargarMensaje("¡ERROR! El tipo de propiedad no es válida", request);
        }
        
        
        String direccionPropiedad = request.getParameter("direccionPropiedad");
        
        long cedulaDueno = 0;
        
        try {
            cedulaDueno = Long.parseLong(request.getParameter("dueno"));
        } catch(NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cedula del dueño no es válida", request);
        }
        
        Cliente dueno = null;
        
        try {
            dueno = FabricaLogica.GetLogicaCliente().buscarCliente(cedulaDueno);
            
            if (dueno == null) {
                cargarMensaje("¡ERROR! No se encontró al dueño de la propiedad", request);
            } else {
                Propiedad propiedad = new Propiedad(numeroPropiedad, tipoPropiedad, direccionPropiedad, dueno);
                
                FabricaLogica.GetLogicaPropiedad().altaPropiedad(propiedad);
                
                cargarMensaje("La propiedad fue agregada con éxito", request);
            }
        } catch(Exception ex) {
            cargarMensaje("¡ERROR! No se puedo agregar la propiedad", request);
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
