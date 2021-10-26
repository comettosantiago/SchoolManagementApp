/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Santiago Cometto
 */
public class Conexion {

    //attributes 
    private String url = "jdbc:mariadb://localhost/universidadg2"; //consultar url
    private Connection con = null;

    //methods
    public Connection conectar() {
        try {
            Class.forName("org.mariadb.jdbc.Driver"); //revisar o consultar
            
            con = DriverManager.getConnection(url + "?useLegacyDatetimeCode=false&serverTimezone=UTC"
                    + "&user=" + "root" + "&password=" + "");

            System.out.println("Conexion establecida correctamente");
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexion");
        }
        return con;
    }

    public Connection getCon() {
        return con;
    }

}
