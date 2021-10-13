/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Connection;

/**
 *
 * @author Santiago Cometto
 */
public class AlumnoData {
    //attributes 
    private Connection conexionAlumno=null;
    
    //constructor
    public AlumnoData(Conexion conexionAlumno) {
        this.conexionAlumno = conexionAlumno.conectar();
    }
    
    //methods
    s
    
}
