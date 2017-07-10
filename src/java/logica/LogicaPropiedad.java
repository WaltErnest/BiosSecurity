/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;
import compartidos.beans.excepciones.MiExcepcion;
import java.sql.SQLException;
import persistencia.*;
import compartidos.beans.entidades.*;
import compartidos.beans.excepciones.MiExcepcionLogica;

/**
 *
 * @author Mathias
 */
public class LogicaPropiedad implements ILogicaPropiedad {
    IPersistenciaPropiedad perPropiedad = FabricaPersistencia.GetPersistenciaPropiedad();
    
    @Override
    public Propiedad buscarPropiedad(int pNumeroPropiedad, long pCedula) 
            throws ClassNotFoundException, SQLException, MiExcepcion {
        if(pCedula < 1){
            throw new MiExcepcionLogica("La cédula del dueño de la propiedad debe ser mayor o igual a 1");
        }
        else {
            return perPropiedad.buscarPropiedad(pNumeroPropiedad, pCedula);            
        }
    }
    
    @Override
    public void altaPropiedad(Propiedad pPropiedad)
            throws ClassNotFoundException, SQLException, MiExcepcion {
        validarPropiedad(pPropiedad);
        
        perPropiedad.altaPropiedad(pPropiedad);
    }
    
    @Override
    public void modificarPropiedad(Propiedad pPropiedad)
            throws ClassNotFoundException, SQLException, MiExcepcion {
        validarPropiedad(pPropiedad);
        
        perPropiedad.modificarPropiedad(pPropiedad);
    }
    
    public void validarPropiedad(Propiedad pPropiedad) throws MiExcepcion {
        if (pPropiedad.getTipoPropiedad().equals("")) {
            throw new MiExcepcionLogica("El tipo de propiedad no puede estar vacío");
        }
        
        if (pPropiedad.getDireccion().equals("")){
            throw new MiExcepcionLogica("La dirección de la propiedad no puede estar vacía");
        }
        
        if (pPropiedad.getDireccion().length() > 255) {
            throw new MiExcepcionLogica("La dirección de la propiedad no puede exceder los 255 caractéres");
        }
        
        if (pPropiedad.getDueno().getCedula() < 1) {
            throw new MiExcepcionLogica("La cédula del dueño de la propiedad debe ser mayor o igual a 1");
        }
    }
}
