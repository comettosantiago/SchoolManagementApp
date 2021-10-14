/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Santiago Cometto
 */
public class AlumnoData {

    //attributes 
    private Connection con = null;

    //constructor
    public AlumnoData(Conexion conexionAlumno) {
        this.con = conexionAlumno.conectar();
    }

    //methods
    public void guardarAlumno(Alumno a) {
        String query = "INSERT INTO alumno(apellido, nombre, fechaNac, legajo, activo) VALUES (?, ?, ?, ?, ?)"; //revisar /consultar si va dentro o fuera del try o si es lo mismo
                                                                                    //el ide lo pone dentro
        try {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, a.getApellido());
            ps.setString(2, a.getNombre());
            ps.setDate(3, Date.valueOf(a.getFechaNacimiento()));
            ps.setInt(4, a.getLegajo());
            ps.setBoolean(5, a.isActivo());

            ResultSet rs = ps.executeQuery();          // consultar cuando executeQuery y cuando executeUpdate, y cuando es query si asignarselo o no a un ResulSet
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Alumno buscarAlumno(int idAlumno) {
        Alumno a = null;

        String query = "SELECT * FROM alumno WHERE idAlumno=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                a = new Alumno();

                a.setId(rs.getInt("id"));
                a.setLegajo(rs.getInt("legajo"));
                a.setNombre(rs.getString("nombre"));
                a.setApellido(rs.getString("apellido"));
                a.setFechaNacimiento(rs.getDate("fechaNac").toLocalDate());
                a.setActivo(rs.getBoolean("activo"));
            }
            ps.close();
        } catch (SQLException exception) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, exception);
        }
        return a;
    }

    public List<Alumno> listarAlumnos() {
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();

        String query = "SELECT * FROM alumno";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Alumno a = new Alumno();
                listaAlumnos.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaAlumnos;
    }

    public void actualizarAlumno(Alumno a) {
        String query = "UPDATE alumno SET apellido=?, nombre=?, fechaNac=?, legajo=?, activo=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, a.getApellido());
            ps.setString(2, a.getNombre());
            ps.setDate(3, Date.valueOf(a.getFechaNacimiento()));
            ps.setInt(4, a.getLegajo());
            ps.setBoolean(5, a.isActivo());

            ResultSet rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void borrarAlumno(int idAlumno) {
        String query = "DELETE FROM alumno WHERE idAlumno=?";
        
        try {

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, idAlumno);

            ResultSet rs = ps.executeQuery(); 
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
