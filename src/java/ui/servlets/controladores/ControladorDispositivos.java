/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.servlets.controladores;

import compartidos.beans.entidades.Alarma;
import compartidos.beans.entidades.Camara;
import compartidos.beans.entidades.Dispositivo;
import compartidos.beans.excepciones.MiExcepcion;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.FabricaLogica;

/**
 *
 * @author Diego
 */
public class ControladorDispositivos extends Controlador {

    @Override
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        try {
            ArrayList<Dispositivo> dispositivos = FabricaLogica.GetLogicaDispositivo().BuscarDispositivos(request.getParameter("buscar"));
            request.setAttribute("dispositivos", dispositivos);
            cargarMensaje("Cantidad de dispositivos: " + dispositivos.size(), request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los dispositivos.", request);
        }

        mostrarVista("index", request, response);
    }

    @Override
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

    public void agregar_get() {
        mostrarVista("agregar", request, response);

    }

    public void agregar_get(HttpServletRequest request, HttpServletResponse response) {
        mostrarVista("agregar", request, response);
    }
    
    public void agregar_post(HttpServletRequest request, HttpServletResponse response) {

        String sel = request.getParameter("disp");
        Long inventario = null;
        try {
            inventario = Long.parseLong(request.getParameter("inventario"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.", request);
            mostrarVista("agregar", request, response);
            return;
        }
        String descripcion = request.getParameter("descripcion");
        boolean interior = Boolean.parseBoolean(request.getParameter("interior"));

        Dispositivo dsp = null;
        if (sel.equals("alarma")) {
            dsp = new Alarma(inventario, descripcion);

        } else {
            dsp = new Camara(inventario, descripcion, interior);
        }
        try {
            FabricaLogica.GetLogicaDispositivo().AltaDispositivo(dsp);
            cargarMensaje("¡Dispositivo agregado con éxito!", request.getSession());
            mostrarVista("agregar", request, response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al agregar el dispositivo.", request);

            mostrarVista("agregar", request, response);
        }

    }

    public void ver_get(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
        long numero;

        try {
            numero = Long.parseLong(request.getParameter("inventario"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! el número de inventario no es válido.", request);

            mostrarVista("ver", request, response);

            return;
        }
        try {
            Dispositivo dispositivo = FabricaLogica.GetLogicaDispositivo().BuscarDispositivo(request.getParameter("inventario"));

            if (dispositivo != null) {
                request.setAttribute("dispositivo", dispositivo);
                cargarMensaje("¡Dispositivo encontrado!", request);
            } else {
                cargarMensaje("¡ERROR! No se encontró ningún dispositivo Nº " + numero + ".", request);
            }

            mostrarVista("ver", request, response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el dispositivo.", request);
        }
    }

    public void modificar_get(HttpServletRequest request, HttpServletResponse response) {
        long numero;

        try {
            numero = Long.parseLong(request.getParameter("inventario"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! el número de inventario no es válido.", request);

            mostrarVista("ver", request, response);

            return;
        }

        try {
            Dispositivo dispositivo = FabricaLogica.GetLogicaDispositivo().BuscarDispositivo(request.getParameter("inventario"));

            if (dispositivo != null) {
                request.setAttribute("dispositivo", dispositivo);
                if (dispositivo instanceof Camara) {
                    Camara pcam = (Camara) dispositivo;
                    String chk = "";
                    if (pcam.getInterior()) {
                        chk = "checked";
                    }
                    request.setAttribute("check", chk);
                    request.setAttribute("camara", pcam);
                } else if (dispositivo instanceof Alarma) {
                    request.setAttribute("alarma", dispositivo);
                }
                cargarMensaje("¡Dispositivo encontrado!", request);
            } else {
                request.setAttribute("ocultarFormulario", true);
                cargarMensaje("¡ERROR! No se encontró ningún dispositivo Nº " + numero + ".", request);
            }
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el empleado.", request);
        }

        mostrarVista("modificar", request, response);
    }

    public void modificar_post(HttpServletRequest request, HttpServletResponse response) {

        String sel = request.getParameter("disp");
        Long inventario = null;
        try {
            inventario = Long.parseLong(request.getParameter("inventario"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.", request);
            mostrarVista("agregar", request, response);
            return;
        }
        String descripcion = request.getParameter("descripcion");
        boolean interior = Boolean.parseBoolean(request.getParameter("interior"));

        Dispositivo dsp = null;
        if (sel.equals("alarma")) {
            dsp = new Alarma(inventario, descripcion);

        } else {
            dsp = new Camara(inventario, descripcion, interior);
        }
        try {
            FabricaLogica.GetLogicaDispositivo().ModificarDispositivo(dsp);
            cargarMensaje("¡Dispositivo modificado con éxito!", request.getSession());
            mostrarVista("agregar", request, response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al agregar el dispositivo.", request);

            mostrarVista("agregar", request, response);
        }
    }

    public void eliminar_get(HttpServletRequest request, HttpServletResponse response) {
        long numero;

        try {
            numero = Long.parseLong(request.getParameter("inventario"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! el número de inventario no es válido.", request);

            mostrarVista("ver", request, response);

            return;
        }

        try {
            Dispositivo dispositivo = FabricaLogica.GetLogicaDispositivo().BuscarDispositivo(request.getParameter("inventario"));
            if(dispositivo != null){
            request.setAttribute("dispositivo", dispositivo);
            }
        }catch(Exception ex){
        cargarMensaje("¡ERROR! " + ex.getMessage(), request);
        }

        mostrarVista("eliminar", request, response);
    }

    public void eliminar_post(HttpServletRequest request, HttpServletResponse response) {
        long numero;

        try {
            numero = Long.parseLong(request.getParameter("inventario"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! el número de inventario no es válido.", request);

            mostrarVista("ver", request, response);

            return;
        }

        try {
            Dispositivo dispositivo = FabricaLogica.GetLogicaDispositivo().BuscarDispositivo(request.getParameter("inventario"));
            FabricaLogica.GetLogicaDispositivo().EliminarDispositivo(dispositivo);
            cargarMensaje("¡Dispositivo eliminado con éxito!", request.getSession());
        }catch(Exception ex){
        cargarMensaje("¡ERROR! " + ex.getMessage(), request);
        }

        mostrarVista("eliminar", request, response);
    }
}
