/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Santiago Cometto
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.util.logging.PlatformLogger;

/**
 *
 * @author Santiago Cometto
 */
public class MateriaData {
    
    private Connection con=null;

    public MateriaData(Conexion conexionMateria) {
        this.con = conexionMateria.conectar();
    }
    
    public void guardarMateria(Materia m){
        
    }
    
    public Materia buscarMateria(int idMateria){
       Materia m=null;
       String query = "SELECT * FROM materia WHERE idMateria=?";

        try {
           PreparedStatement ps= con.prepareStatement(query);
           ps.setInt(1, idMateria);
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
               m = new Materia();
               m.setNombreMateria(rs.getString("nombreMateria"));
               m.setAnio(rs.getInt("anio"));
               m.setActivo(rs.getBoolean("activo"));
           }
           ps.close();
           } catch (SQLException ex) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }
    
    public List<Materia> guardarMateria(){
        ArrayList<Materia>listaMaterias = new ArrayList<>();
        String query = "SELECT * FROM materia";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);

        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Materia m = new Materia();
            listaMaterias.add(m);
        }
        } catch (SQLException ex) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  listaMaterias;
    }
    
    public void actualizarMateria(Materia m){
        String query="UPDATE materia SET nombreMateria=?, anio=?, activo=?";

     try {
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, m.getNombreMateria());
        ps.setInt(2, m.getAnio());
        ps.setBoolean(3, m.isActivo());
        
        ps.close();
    } catch (SQLException ex) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void borrarMateria(int idMateria){
         String query = "DELETE FROM alumno Where idMateria=?";

        try {
         PreparedStatement ps = con.prepareStatement(query);

         ps.setInt(1, idMateria);
         ps.executeUpdate();
         
         ps.close();
     } catch (SQLException ex) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}

