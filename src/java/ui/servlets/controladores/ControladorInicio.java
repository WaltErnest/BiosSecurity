/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.servlets.controladores;

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
        int login = 3;
        String pName = request.getParameter("name");
        String pPass = request.getParameter("Pass");

        try {
            ILogicaEmpleado Emp = FabricaLogica.GetLogicaEmpleado();
            login = Emp.LoginAdministrativo(pName, pPass);
            if(login == 1){
                String ok = "OK";
                session.setAttribute("LogIn", ok);
                mostrarVista("index");
            }else{
                session.setAttribute("msj", login);
                session.setAttribute("name", pName);
               
                
                mostrarVista("otra");
            }
        } catch (Exception e) {
            
            session.setAttribute("msj", e);
        mostrarVista("otra");
        }
        
    }
}
