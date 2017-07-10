/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.servlets.controladores;

import compartidos.beans.entidades.Empleado;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ernesto
 */
abstract class Controlador extends HttpServlet {
    protected ServletConfig config;
    protected ServletContext application;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        mostrarVista("index", request, response);
    }
    
    public void error_get(HttpServletRequest request, HttpServletResponse response) {
        mostrarVista(request, response);
    }
        protected void mostrarVista(String vista) {
        agregarMensajeSesionAMensajeRequest();

        try {
            String nombreCarpetaVista = this.getClass().getSimpleName().replaceFirst("Controlador", "");

            RequestDispatcher despachador = request.getRequestDispatcher("WEB-INF/vistas/" + nombreCarpetaVista + "/" + vista + ".jsp");

            if (despachador != null) {
                despachador.forward(request, response);
            }
        } catch (Exception ex) {
            System.out.println("Error al mostrar la vista " + vista + ".");
        }
    }
    protected void mostrarVista(HttpServletRequest request, HttpServletResponse response) {
        agregarMensajeSesionAMensaje(request);

        try {
            RequestDispatcher despachador = request.getRequestDispatcher("WEB-INF/vistas/Inicio/login.jsp");

            if (despachador != null) {
                despachador.forward(request, response);
            }
        } catch (Exception ex) {
            System.out.println("¡ERROR! No se pudo mostrar la vista login.");
        }
    }

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

    protected void agregarMensajeSesionAMensaje(HttpServletRequest request) {
        String mensajeSesion = (String) request.getSession().getAttribute("mensaje");

        if (mensajeSesion != null) {
            String mensaje = (String) request.getAttribute("mensaje");

            if (mensaje == null) {
                cargarMensaje(mensajeSesion, request);
            } else {
                cargarMensaje(mensajeSesion + "<br /><br />" + mensaje, request);
            }

            request.getSession().removeAttribute("mensaje");
        }
    }

    protected void despacharMetodoAccion(HttpServletRequest request, HttpServletResponse response)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String accion = request.getParameter("accion") != null ? request.getParameter("accion").toLowerCase() : "index";
        if (!(this instanceof ControladorInicio)) {
            HttpSession sesion = request.getSession();
            Empleado login = (Empleado) sesion.getAttribute("usuario");
            if (login == null) {
                cargarMensaje("Error: debe iniciar sesión para la página solicitada.", request);
                accion = "error";
            }
        } 
            String metodoRequest = request.getMethod().toLowerCase();
            String nombreMetodoAccion = accion + "_" + metodoRequest;

            Method metodoAccion = this.getClass().getMethod(nombreMetodoAccion, HttpServletRequest.class, HttpServletResponse.class);
            metodoAccion.invoke(this, request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            despacharMetodoAccion(request, response);
        } catch (Exception ex) {
            System.out.println("Error al despachar el método de la acción solicitada.");
        }
    }
    protected void agregarMensajeSesionAMensajeRequest() {
        String mensajeSesion = (String) session.getAttribute("mensaje");

        if (mensajeSesion != null) {
            String mensaje = (String) request.getAttribute("mensaje");

            if (mensaje == null) {
                request.setAttribute("mensaje", mensajeSesion);
            } else {
                request.setAttribute("mensaje", mensajeSesion + "<br /><br />" + mensaje);
            }

            session.removeAttribute("mensaje");
        }
    }
    protected void cargarMensaje(String mensaje, HttpServletRequest request) {
        request.setAttribute("mensaje", mensaje);
    }

    protected void cargarMensaje(String mensaje, HttpSession session) {
        session.setAttribute("mensaje", mensaje);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
