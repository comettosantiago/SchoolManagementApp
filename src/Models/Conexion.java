/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.SystemException;

/**
 *
 * @author Santiago Cometto
 */
public class Conexion {

    //attributes 
    private String url = "jdbc:mariadb://localhost/universidad"; //consultar url
    private Connection con = null;

    //constructor
    /*public Conexion(String url) throws ClassNotFoundException {
        this.url = url;
        //clases de mariadb que implementan JDBC
        Class.forName("org.mariadb.jdbc.Driver");
    }                                                      */ //CONSULTAR
    
    //methods
    public Connection conectar() {
        try {
            Class.forName("org.mariadb.jdbc.Driver"); //revisar o consultar
            con = DriverManager.getConnection(url);
            System.out.println("Conexion establecida correctamente");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public Connection getCon() {
        return con;
    }

}
