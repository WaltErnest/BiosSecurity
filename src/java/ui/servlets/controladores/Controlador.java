/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.servlets.controladores;

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

    public void index_get() {
        mostrarVista("index");
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

    protected void despacharMetodoAccion()
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String accion = request.getParameter("accion") != null ? request.getParameter("accion").toLowerCase() : "index";
        String metodoRequest = request.getMethod().toLowerCase();
        String nombreMetodoAccion = accion + "_" + metodoRequest;

        Method metodoAccion = this.getClass().getMethod(nombreMetodoAccion);
        metodoAccion.invoke(this);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;
        session = request.getSession();

        try {
            despacharMetodoAccion();
        } catch (Exception ex) {
            System.out.println("Error al despachar el método de la acción solicitada.");
        }
    }
    
    @Override
    public void init(ServletConfig config)
            throws ServletException {
        super.init(config);
        
        this.config = config;
        application = getServletContext();
    }
    
    protected void cargarMensaje(String mensaje) {
        request.setAttribute("mensaje", mensaje);
    }
    
    protected void cargarMensajeSesion(String mensaje) {
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
