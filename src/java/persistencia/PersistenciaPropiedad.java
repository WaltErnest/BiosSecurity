/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import compartidos.beans.entidades.Cliente;
import compartidos.beans.entidades.Propiedad;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import compartidos.beans.excepciones.MiExcepcion;
import compartidos.beans.excepciones.MiExcepcionPersistencia;

/**
 *
 * @author Mathias
 */
public class PersistenciaPropiedad implements IPersistenciaPropiedad {
    IPersistenciaCliente perCliente = FabricaPersistencia.GetPersistenciaCliente();

    
    public Propiedad buscarPropiedad(int pNumeroPropiedad, long pCedulaDueno)
            throws ClassNotFoundException, SQLException, MiExcepcion {
        Connection cnn = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;

        try {
            cnn = Conexion.getConexion();

            consulta = cnn.prepareStatement("SELECT * FROM propiedades WHERE numero = ? AND cedula = ?");

            consulta.setInt(1, pNumeroPropiedad);
            consulta.setLong(2, pCedulaDueno);

            Cliente clienteEncontrado = perCliente.buscarCliente(pCedulaDueno);

            Propiedad propiedadEncontrada = null;

            if (clienteEncontrado != null) {
                resultado = consulta.executeQuery();

                int numeroPropiedad;
                Propiedad.TipoPropiedad tipo;
                String direccion;
                Cliente dueno;

                if (resultado.next()) {
                    numeroPropiedad = resultado.getInt("numero");
                    tipo = Propiedad.TipoPropiedad.valueOf(resultado.getString("tipoPropiedad"));
                    direccion = resultado.getString("direccion");

                    propiedadEncontrada = new Propiedad(numeroPropiedad, tipo, direccion, clienteEncontrado);
                }
            }

            return propiedadEncontrada;
            
        } finally {
            Conexion.cerrarRecursos(cnn, consulta, resultado);
        }
    }

    @Override
    public void altaPropiedad(Propiedad pPropiedad)
            throws ClassNotFoundException, SQLException, MiExcepcion {
        Connection cnn = null;
        CallableStatement consulta = null;

        try {
            cnn = Conexion.getConexion();
            consulta = cnn.prepareCall("{ CALL altaPropiedad(?, ?, ?, ?) }");

            consulta.setString(1, pPropiedad.getTipoPropriedad().toString());
            consulta.setString(2, pPropiedad.getDireccion());
            consulta.setLong(3, pPropiedad.getDueno().getCedula());
            consulta.registerOutParameter(4, java.sql.Types.VARCHAR);

            consulta.executeUpdate();

            String error = consulta.getNString(4);

            if (error != null) {
                throw new MiExcepcionPersistencia("Error en dar de alta la propiedad " + pPropiedad.getNumeroPropiedad()
                        + " del cliente " + pPropiedad.getDueno().getCedula() + ": " + error);
            }
        } finally {
            Conexion.cerrarRecursos(cnn, consulta);
        }
    }

    @Override
    public void modificarPropiedad(Propiedad pPropiedad)
            throws ClassNotFoundException, SQLException, MiExcepcion {
        Connection cnn = null;
        CallableStatement consulta = null;

        try {
            cnn = Conexion.getConexion();
            consulta = cnn.prepareCall("{ CALL modificarPropiedad(?, ?, ?, ?, ?)}");

            consulta.setInt(1, pPropiedad.getNumeroPropiedad());
            consulta.setString(2, pPropiedad.getTipoPropriedad().toString());
            consulta.setString(3, pPropiedad.getDireccion());
            consulta.setLong(4, pPropiedad.getDueno().getCedula());
            consulta.registerOutParameter(5, java.sql.Types.VARCHAR);

            String error = consulta.getNString(4);

            if (error != null) {
                throw new MiExcepcionPersistencia("Error al modificar la propiedad " + pPropiedad.getNumeroPropiedad()
                        + " del cliente " + pPropiedad.getDueno().getCedula() + ": " + error);
            }
        } finally {
            Conexion.cerrarRecursos(cnn, consulta);
        }
    }
}
