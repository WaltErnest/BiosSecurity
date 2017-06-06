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
final class Conexion {

    private static String _url = "jdbc:mysql://localhost:3306/BiosSecurity";
    private static String _driver = "com.mysql.jdbc.Driver";
    private static String _usuario = "root";
    private static String _clave = "root";

    static Connection Conectar() throws ClassNotFoundException, SQLException {
        Class.forName(_driver);
        return DriverManager.getConnection(_url, _usuario, _clave);
    }
    
    static void Desconectar(Connection cnn) throws SQLException{
        cnn.close();
    }

    private Conexion() {
    }
}
