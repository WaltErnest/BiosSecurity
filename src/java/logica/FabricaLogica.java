/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author desquitin
 */
public class FabricaLogica {
    public static ILogicaEmpleado GetLogicaEmpleado(){
        return new LogicaEmpleado();
    }
    public static ILogicaDispositivo GetLogicaDispositivo(){
        return (ILogicaDispositivo) new LogicaDispositivo();
    }
    public static ILogicaPropiedad GetLogicaPropiedad(){
        return new LogicaPropiedad();
    }
    public static ILogicaCliente GetLogicaCliente(){
        return new LogicaCliente();
    }
    public static ILogicaServicio GetLogicaServicio(){
        return new LogicaServicio();
    }
}
