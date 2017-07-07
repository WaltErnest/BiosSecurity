/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.servlets.controladores;

import compartidos.beans.entidades.Empleado;
import compartidos.beans.excepciones.MiExcepcion;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.FabricaLogica;
import logica.ILogicaEmpleado;

/**
 *
 * @author Ernesto
 */
public class ControladorInicio extends Controlador {

    @Override
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String LogIn = (String) session.getAttribute("LogIn");
        if (LogIn == "OK") {
            mostrarVista("index", request, response);
        } else {
            mostrarVista("login", request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response); //To change body of generated methods, choose Tools | Templates.
        HttpSession session = request.getSession();
        Long pCedula = null;
        try {
            pCedula = Long.parseLong(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! debe ingresar un usuario", request);
            mostrarVista("login", request, response);
        }
        String pPass = request.getParameter("pass");
        if (pCedula == null || pPass == "") {
            cargarMensaje("¡ERROR! Debe ingresar un usuario y/o contraseña", request);
        } else {
            try {

                ILogicaEmpleado Emp = FabricaLogica.GetLogicaEmpleado();
                Empleado login = Emp.Login(pCedula, pPass);

                if (login != null) {
                    String ok = "OK";
                    session.setAttribute("LogIn", ok);
                    mostrarVista("index", request, response);
                } else {
                    cargarMensaje("¡ERROR! La cédula y/o la clave no son válidas.", request);
                    mostrarVista("login", request, response);
                }
            } catch (Exception e) {
                session.setAttribute("msj", e);
                cargarMensaje("¡ERROR! No fue posible realizar la búsqueda.", request);
                mostrarVista("login", request, response);
            }
        }
    }
}
