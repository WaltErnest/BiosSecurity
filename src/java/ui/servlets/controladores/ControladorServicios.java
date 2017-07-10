/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.servlets.controladores;

import compartidos.beans.entidades.*;
import compartidos.beans.excepciones.MiExcepcion;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.FabricaLogica;
import logica.ILogicaServicio;

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
        HttpSession sesionAltaServicio = request.getSession();
        
        try {
            String cedulaCliente = request.getParameter("cedula");

            if (cedulaCliente == null) {
                mostrarVista("buscarCliente", request, response);
            } else {
                Cliente cliente = FabricaLogica.GetLogicaCliente().buscarCliente((Long.parseLong(cedulaCliente)));

                if (cliente != null) {                    
                    sesionAltaServicio.setAttribute("cliente", cliente);
                } else {
                    cargarMensaje("No se encontró el cliente", request);
                }
            }
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se ha producido un error buscar al cliente", request);
            sesionAltaServicio.invalidate();
        }
            mostrarVista("buscarCliente", request, response);
    }
    
    public void agregarclienteservicio_get(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sesionAltaServicio = request.getSession();
        
        try {
            sesionAltaServicio.getAttribute("cliente");
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! No se pudo pasar el cliente al servicio", request);
            sesionAltaServicio.invalidate();
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
        HttpSession sesionAltaServicio = request.getSession();
        try {
            String numeroPropiedad = request.getParameter("numeroPropiedad");
            
            if (numeroPropiedad == null) {
                cargarMensaje("No hay un número de propiedad", request);    
            } else {                
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
            sesionAltaServicio.invalidate();
        }
        
        mostrarVista("buscarPropiedad", request, response);
    }
    
    public void index_post(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sesionAltaServicio = request.getSession();
        sesionAltaServicio.removeAttribute("propiedad");
        sesionAltaServicio.removeAttribute("cliente");
        Cliente cliente = null;
        Propiedad propiedad = null;
        Servicio servicioAlarma = null;
        Servicio servicioVideo = null;
        
        try {            
            Cliente clienteSesion = (Cliente)sesionAltaServicio.getAttribute("cliente");
            
            if (clienteSesion != null) {                
                long cedula = clienteSesion.getCedula();
                String nombre = clienteSesion.getNombre();
                String direccionCobro = clienteSesion.getDireccionCobro();
                String barrioDirCobro = clienteSesion.getBarrioDirCobro();
                long telefono = clienteSesion.getTelefono();
                
                cliente = new Cliente(cedula, nombre, direccionCobro, barrioDirCobro, telefono);
            } else {
                long cedula = 0;
                
                try {
                    cedula = Long.parseLong(request.getParameter("cedulaCliente"));
                } catch (NumberFormatException ex) {
                    cargarMensaje("¡ERROR! La cedula del dueño no es válida", request);
                    sesionAltaServicio.removeAttribute("cliente");
                    sesionAltaServicio.removeAttribute("propiedad");
                }
                
                String nombre = request.getParameter("nombreCliente");
                String direccionCobro = request.getParameter("direccionCobroCliente");
                String barrioDirCobro = request.getParameter("barrioDirCobro");
                
                long telefono = 0;
                
                try {
                    telefono = Long.parseLong(request.getParameter("telefonoCliente"));
                } catch(NumberFormatException ex) {
                    cargarMensaje("¡ERROR! El teléfono no es válido", request);
                    sesionAltaServicio.removeAttribute("cliente");
                    sesionAltaServicio.removeAttribute("propiedad");
                }
                
                cliente = new Cliente(cedula, nombre, direccionCobro, barrioDirCobro, telefono);
            }
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Ocurrió un error con los datos del cliente", request);
            sesionAltaServicio.removeAttribute("cliente");
            sesionAltaServicio.removeAttribute("propiedad");
        }
        
        try {
            Propiedad propiedadSesion = (Propiedad)sesionAltaServicio.getAttribute("propiedad");
            
            if (propiedadSesion != null) {
                int numeroPropiedad = 0;
                Propiedad.TipoPropiedad tipoPropiedad = propiedadSesion.getTipoPropriedad();
                String direccionPropiedad = propiedadSesion.getDireccion();
                Cliente dueno = propiedadSesion.getDueno();
                
                propiedad = new Propiedad(numeroPropiedad, tipoPropiedad, direccionPropiedad, dueno);
            } else {
                int numeroPropiedad = 0;
                
                String valorTipoPropiedad = request.getParameter("tipoPropiedad");
                Propiedad.TipoPropiedad tipoPropiedad = null;
                try {
                    tipoPropiedad = Propiedad.TipoPropiedad.valueOf(valorTipoPropiedad);
                } catch (IllegalArgumentException ex) {
                    cargarMensaje("¡ERROR! El tipo de propiedad no es válida", request);
                    sesionAltaServicio.removeAttribute("cliente");
                    sesionAltaServicio.removeAttribute("propiedad");
                } catch (Exception ex) {
                    cargarMensaje("¡ERROR! Algo sucedió con el tipo de propiedad", request);
                    sesionAltaServicio.removeAttribute("cliente");
                    sesionAltaServicio.removeAttribute("propiedad");
                }

                String direccionPropiedad = request.getParameter("direccionPropiedad");
                
                Cliente dueno = cliente;
                
                propiedad = new Propiedad(numeroPropiedad, tipoPropiedad, direccionPropiedad, dueno);
            }
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Ocurrió un error con los datos de la propiedad", request);
            sesionAltaServicio.removeAttribute("cliente");
            sesionAltaServicio.removeAttribute("propiedad");
        }
        
        try {
            boolean monitoreo = Boolean.parseBoolean(request.getParameter("monitoreo"));
            boolean alarma = Boolean.parseBoolean(request.getParameter("alarma"));
            
            int codigoAnulacion = 0;
            
            try {
                codigoAnulacion = Integer.parseInt(request.getParameter("codigoAnulacion"));
            } catch(NumberFormatException ex) {
                cargarMensaje("El código de anulación no es válido", request);
                sesionAltaServicio.removeAttribute("cliente");
                sesionAltaServicio.removeAttribute("propiedad");
            }            
            
            boolean video = Boolean.parseBoolean(request.getParameter("video"));
            boolean terminalGrabacion = Boolean.parseBoolean(request.getParameter("terminalGrabacion"));
            
            if (!alarma && !video ) {
                cargarMensaje("Debe seleccionar al menos un tipo de servicio", request);
                sesionAltaServicio.removeAttribute("cliente");
                sesionAltaServicio.removeAttribute("propiedad");               
            }

            ILogicaServicio logicaServicio = FabricaLogica.GetLogicaServicio();
            
            if (alarma) {
                List<Alarma> listaAlarmas = new ArrayList<Alarma>();
                servicioAlarma = new ServicioAlarma(0, propiedad, null, monitoreo, codigoAnulacion, listaAlarmas);
                
                logicaServicio.altaServicio(servicioAlarma);
            }
            
            if (video) {
                List<Camara> listaCamaras = new ArrayList<Camara>();
                servicioVideo = new ServicioVideo(0, propiedad, null, monitoreo, terminalGrabacion, listaCamaras);
                
                logicaServicio.altaServicio(servicioVideo);
            }
            
            cargarMensaje("Servicio agregado con éxito", request);
            sesionAltaServicio.removeAttribute("cliente");
            sesionAltaServicio.removeAttribute("propiedad");
            
        } catch(Exception ex) {
            cargarMensaje("¡ERROR! Ocurrió un error con los datos del servicio" + ex, request);
            sesionAltaServicio.removeAttribute("cliente");
            sesionAltaServicio.removeAttribute("propiedad");
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
