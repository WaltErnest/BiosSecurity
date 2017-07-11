/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.servlets.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.FabricaLogica;

/**
 *
 * @author Diego
 */
public class ControladorCobros extends Controlador {
    @Override
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        mostrarVista("index", request, response);
    }

}
