/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import persistencia.*;
import compartidos.beans.entidades.*;
import compartidos.beans.excepciones.MiExcepcion;
import compartidos.beans.excepciones.MiExcepcionLogica;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import static java.time.Instant.now;
import java.time.LocalDate;
/**
 *
 * @author Mathias
 */
public class LogicaServicio implements ILogicaServicio {
    
    IPersistenciaServicioAlarma perServicioAlarma = FabricaPersistencia.GetPersistenciaServicioAlarma();
    IPersistenciaServicioVideo perServicioVideo = FabricaPersistencia.GetPersistenciaServicioVideo();
    
    public Servicio buscarServicio(int pNumero)
            throws ClassNotFoundException, SQLException, MiExcepcion {
        
        ServicioAlarma servAlarma = perServicioAlarma.buscarServicioAlarma(pNumero);
        
        if (servAlarma != null) {
            return servAlarma;
        }
        
        ServicioVideo servVideo = perServicioVideo.buscarServicioVideo(pNumero);
        
        
        if (servVideo != null) {
            return servVideo;
        } else {
            throw new MiExcepcionLogica("El servicio no existe.");
        }
    }
    public ServicioAlarma buscarServicioAlarma(int pNumero)
            throws ClassNotFoundException, SQLException, MiExcepcion {
        
        return perServicioAlarma.buscarServicioAlarma(pNumero);
    }
    
    public ServicioVideo buscarServicioVideo(int pNumero)
            throws ClassNotFoundException, SQLException, MiExcepcion {
        
        return perServicioVideo.buscarServicioVideo(pNumero);
    }
    public void altaServicio(Servicio pServicio)
            throws ClassNotFoundException, SQLException, MiExcepcion {
        if (pServicio instanceof ServicioAlarma) {
            perServicioAlarma.altaServicioAlarma((ServicioAlarma)pServicio);
        } else if (pServicio instanceof ServicioVideo) {
            perServicioVideo.altaServicioVideo((ServicioVideo)pServicio);
        } else {
            throw new MiExcepcionLogica("No existe el tipo de servicio");
        }
    }
    
    public void validarServicio(Servicio pServicio)
            throws MiExcepcion {
        if (pServicio.getPropriedadCliente().getDueno().getCedula() < 1) {
            throw new MiExcepcionLogica("La cédula del dueño de la propiedad debe ser mayor o igual a 1");
        }
        
        if (pServicio.getFechaContratacion().before(Date.from(Instant.now())) ) {
            throw new MiExcepcionLogica("La fecha de contratación debe ser a partir de hoy");
        }
    }
}
