/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.servlets.controladores;

import compartidos.beans.entidades.Empleado;
import compartidos.beans.excepciones.MiExcepcion;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.FabricaLogica;
import logica.ILogicaEmpleado;

/**
 *
 * @author Ernesto
 */
public class ControladorInicio extends Controlador {
    @Override
        public void index_get() {
        String LogIn = (String) session.getAttribute("LogIn");
        if (LogIn == "OK"){
            mostrarVista("index");
        }else{
            mostrarVista("login");}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response); //To change body of generated methods, choose Tools | Templates.
                
        Long pCedula=null;
        try{
            pCedula = Long.parseLong(request.getParameter("cedula"));
        }catch(NumberFormatException ex)
        {
             cargarMensaje("¡ERROR! debe ingresar un usuario");
             mostrarVista("login");
        }
        String pPass = request.getParameter("pass");
        if (pCedula == null || pPass == "") {
            cargarMensaje("¡ERROR! Debe ingresar un usuario y/o contraseña");
        } else {
            try {
                
                ILogicaEmpleado Emp = FabricaLogica.GetLogicaEmpleado();
                Emp.Login(pCedula, pPass);
                
                if (Emp != null) {
                    String ok = "OK";
                    session.setAttribute("LogIn", ok);
                    mostrarVista("index");
                } else {
                    cargarMensaje("¡ERROR! La cédula no es válida.");
                    mostrarVista("login");
                }
            } catch (Exception e) {
                session.setAttribute("msj", e);
                cargarMensaje("¡ERROR! No fue posible realizar la búsqueda.");
                mostrarVista("login");
            }
        }
    }
}
