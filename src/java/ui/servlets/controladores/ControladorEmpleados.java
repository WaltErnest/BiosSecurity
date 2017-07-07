/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.servlets.controladores;

import compartidos.beans.entidades.Empleado;
import compartidos.beans.excepciones.MiExcepcion;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.FabricaLogica;

/**
 *
 * @author Ernesto
 */
public class ControladorEmpleados extends Controlador {
    
    @Override
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        try {
            ArrayList<Empleado> empleados = FabricaLogica.GetLogicaEmpleado().ListarEmpleados();
            
            request.setAttribute("empleados", empleados);
            cargarMensaje("Cantidad de empleados: " + empleados.size(), request);
        } /*catch (MiExcepcion ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(), request);
        }*/ catch (Exception ex) {
            cargarMensaje("Se produjo un error al listar los empleados.", request);
        }
        
        mostrarVista("index", request, response);
    }
    
    
}
