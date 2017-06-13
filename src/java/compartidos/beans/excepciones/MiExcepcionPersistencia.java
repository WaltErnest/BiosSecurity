/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compartidos.beans.excepciones;

import java.io.Serializable;

/**
 *
 * @author Ernesto
 */
public class MiExcepcionPersistencia extends MiExcepcion implements Serializable {

    public MiExcepcionPersistencia() {
    }

    public MiExcepcionPersistencia(String mensaje) {
        super(mensaje);
    }

    public MiExcepcionPersistencia(String mensaje, Exception excepcionInterna) {
        super(mensaje, excepcionInterna);
    }
}
