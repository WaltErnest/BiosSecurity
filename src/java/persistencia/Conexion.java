/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ernesto
 */
class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/BiosSecurity";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USUARIO = "root";
    private static final String CLAVE = "root";

    static {
        try {
            Class.forName(DRIVER).newInstance();
        } catch (Exception ex) {
            System.out.println("Error al instanciar el driver para JDBC.");
        }
    }

    static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
    
    protected static void cerrarRecursos(AutoCloseable... recursos) {
        try {
            for (AutoCloseable r : recursos) {
                if (r != null) {
                    r.close();
                }
            }
        } catch (Exception ex) {
            System.out.println("Error al cerrar los recursos.");
        }
    }

    private Conexion() {
    }
}
