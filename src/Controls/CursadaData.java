/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls;

import java.sql.Connection;

/**
 *
 * @author Santiago Cometto
 */
public class CursadaData {
        //Atributes
    private Connection con = null;
        //Constructor
    public CursadaData(Conexion conexionCursada) {
        this.con = conexionCursada.conectar();
    }
        //methods
    
}
