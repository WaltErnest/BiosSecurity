/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.servlets.controladores;

import compartidos.beans.entidades.Administrativo;
import compartidos.beans.entidades.Cobrador;
import compartidos.beans.entidades.Empleado;
import compartidos.beans.entidades.Tecnico;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.FabricaLogica;

/**
 *
 * @author Ernesto
 */
public class ControladorEmpleados extends Controlador {

    @Override
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        try {
            String parametro = request.getParameter("search") == null ? "" : request.getParameter("search");
            ArrayList<Empleado> empleados = FabricaLogica.GetLogicaEmpleado().ListarEmpleados(parametro);

            request.setAttribute("empleados", empleados);
            cargarMensaje("Cantidad de empleados: " + empleados.size(), request);
        } /*catch (MiExcepcion ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(), request);
        }*/ catch (Exception ex) {
            cargarMensaje("Se produjo un error al listar los empleados.", request);
        }

        mostrarVista("index", request, response);
    }

    public void detalles_get(HttpServletRequest request, HttpServletResponse response) {
        long cedula;
        String tipo = getTipoEmpleado(request.getParameter("tipo"));

        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("Error: La cédula no es válida.", request);

            mostrarVista("detalles", request, response);

            return;
        }

        try {
            Empleado empleado = FabricaLogica.GetLogicaEmpleado().BuscarEmpleado(cedula, tipo);

            if (empleado != null) {
                request.setAttribute("empleado", empleado);
                cargarMensaje("¡Empleado encontrado!", request);
            } else {
                cargarMensaje("Error: No se encontró ningún empleado con la cédula " + cedula + ".", request);
            }
        } catch (Exception ex) {
            cargarMensaje("Error al buscar el empleado.", request);
        }

        mostrarVista("detalles", request, response);
    }

    public void eliminar_get(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sesion = request.getSession();
        Empleado login = (Empleado) sesion.getAttribute("usuario");

        if (login == null || !(login instanceof Administrativo)) {
            cargarMensaje("Solamente administradores pueden ingresar a la página.", request);
            mostrarVista("index", request, response);
        } else {

            long cedula;
            String tipo = getTipoEmpleado(request.getParameter("tipo"));

            try {
                cedula = Long.parseLong(request.getParameter("cedula"));
            } catch (NumberFormatException ex) {
                cargarMensaje("Error: La cédula no es válida.", request);

                mostrarVista("eliminar", request, response);

                return;
            }

            if (login.getCedula() == cedula) {
                cargarMensaje("Error: No puedes eliminar el usuario logueado.", request);

                mostrarVista("eliminar", request, response);

                return;
            }

            try {
                Empleado empleado = FabricaLogica.GetLogicaEmpleado().BuscarEmpleado(cedula, tipo);

                if (empleado != null) {
                    request.setAttribute("empleado", empleado);
                    cargarMensaje("¡Empleado encontrado!", request);
                } else {
                    cargarMensaje("¡ERROR! No se encontró ningún empleado con la cédula " + cedula + ".", request);
                }
            } catch (Exception ex) {
                cargarMensaje("Error al buscar el empleado.", request);
            }

            mostrarVista("eliminar", request, response);
        }
    }

    public void eliminar_post(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sesion = request.getSession();
        Empleado login = (Empleado) sesion.getAttribute("usuario");

        if (login == null || !(login instanceof Administrativo)) {
            cargarMensaje("Solamente administradores pueden ingresar a la página.", request);
            mostrarVista("index", request, response);
        } else {
            long cedula;
            String tipo = getTipoEmpleado(request.getParameter("tipo"));

            try {
                cedula = Long.parseLong(request.getParameter("cedula"));
            } catch (NumberFormatException ex) {
                cargarMensaje("Error: La cédula no es válida.", request);

                mostrarVista("eliminar", request, response);

                return;
            }

            try {
                FabricaLogica.GetLogicaEmpleado().EliminarEmpleado(cedula, tipo);

                cargarMensaje("¡Empleado eliminado con éxito!", request.getSession());

                response.sendRedirect("empleados");
            } catch (Exception ex) {
                cargarMensaje("Se produjo un error al eliminar el empleado.", request);

                mostrarVista("eliminar", request, response);
            }
        }
    }

    public void modificar_get(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sesion = request.getSession();
        Empleado login = (Empleado) sesion.getAttribute("usuario");

        if (login == null || !(login instanceof Administrativo)) {
            cargarMensaje("Solamente administradores pueden ingresar a la página.", request);
            mostrarVista("index", request, response);
        } else {
            long cedula;
            String tipo = getTipoEmpleado(request.getParameter("tipo"));

            try {
                cedula = Long.parseLong(request.getParameter("cedula"));
            } catch (NumberFormatException ex) {
                cargarMensaje("¡ERROR! La cédula no es válida.", request);

                mostrarVista("modificar", request, response);

                return;
            }

            try {
                Empleado empleado = FabricaLogica.GetLogicaEmpleado().BuscarEmpleado(cedula, tipo);

                if (empleado != null) {
                    request.setAttribute("empleado", empleado);
                    cargarMensaje("¡Empleado encontrado!", request);
                } else {
                    request.setAttribute("ocultarFormulario", true);
                    cargarMensaje("¡ERROR! No se encontró ningún empleado con la cédula " + cedula + ".", request);
                }
            } catch (Exception ex) {
                cargarMensaje("Error al buscar el empleado.", request);
            }

            mostrarVista("modificar", request, response);
        }
    }

    public void modificar_post(HttpServletRequest request, HttpServletResponse response) {
        long cedula;

        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("Error: La cédula no es válida.", request);

            mostrarVista("modificar", request, response);

            return;
        }
        String tipo = getTipoEmpleado(request.getParameter("tipo"));
        String nombre = request.getParameter("nombre");

        double sueldo;

        try {
            sueldo = Double.parseDouble(request.getParameter("sueldo"));
        } catch (NumberFormatException ex) {
            cargarMensaje("Error: El sueldo no es válido.", request);

            mostrarVista("modificar", request, response);

            return;
        }

        String clave = request.getParameter("clave");

        LocalDate fechaIngreso = LocalDate.parse(request.getParameter("fechaIngreso"));

        Empleado modificar;

        switch (tipo) {
            case "C":
                String tipoTransporte = request.getParameter("tipoTransporte");
                modificar = new Cobrador(cedula, clave, nombre, fechaIngreso, sueldo, tipoTransporte);
                break;
            case "T":
                boolean alarmas = Boolean.parseBoolean(request.getParameter("alarmas"));
                boolean camaras = Boolean.parseBoolean(request.getParameter("camaras"));
                modificar = new Tecnico(cedula, clave, nombre, fechaIngreso, sueldo, alarmas, camaras);
                break;
            case "A":
            default:
                modificar = new Administrativo(cedula, clave, nombre, fechaIngreso, sueldo);
                break;
        }

        try {
            FabricaLogica.GetLogicaEmpleado().ModificarEmpleado(modificar);

            cargarMensaje("¡Empleado modificado con éxito!", request.getSession());

            response.sendRedirect("empleados");
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al modificar el empleado.", request);

            mostrarVista("modificar", request, response);
        }
    }

    private String getTipoEmpleado(String pTipo) {
        String tipo = "A";
        switch (pTipo) {
            case "Cobrador":
                tipo = "C";
                break;
            case "Tecnico":
                tipo = "T";
                break;
            case "Administrativo":
            default:
                tipo = "A";
                break;
        }
        return tipo;
    }
}
