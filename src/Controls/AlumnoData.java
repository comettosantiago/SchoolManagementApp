/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls;

import Models.Alumno;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, a.getApellido());
            ps.setString(2, a.getNombre());
            ps.setDate(3, Date.valueOf(a.getFechaNacimiento()));
            ps.setInt(4, a.getLegajo());
            ps.setBoolean(5, a.isActivo());
            //uso executeUpdate cuando hago consulta tipo insert/update/delete
            ps.executeUpdate();                                 //executeQuery cuando hago consulta tipo select

            ResultSet rs = ps.getGeneratedKeys();               //

            if (rs.next()) {
                a.setId(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Alumno guardado");
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error, alumno no guardado");
        }
    }

    public Alumno buscarAlumno(int idAlumno) {
        Alumno a = null;

        String query = "SELECT * FROM alumno WHERE idAlumno = ? AND activo = true";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                a = new Alumno();

                a.setId(rs.getInt("idAlumno"));
                a.setLegajo(rs.getInt("legajo"));
                a.setNombre(rs.getString("nombre"));
                a.setApellido(rs.getString("apellido"));
                a.setFechaNacimiento(rs.getDate("fechaNac").toLocalDate());
                a.setActivo(rs.getBoolean("activo"));
            }
            ps.close();
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, "ERROR");
        }
        return a;
    }

    public List<Alumno> listarAlumnos() {
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();

        String query = "SELECT * FROM alumno WHERE activo = true";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Alumno a = new Alumno();
                a.setId(rs.getInt("idAlumno"));
                a.setApellido(rs.getString(2));
                a.setNombre(rs.getString("nombre"));
                a.setFechaNacimiento(rs.getDate("fechaNac").toLocalDate());
                a.setLegajo(rs.getInt(5));
                a.setActivo(rs.getBoolean("activo"));

                listaAlumnos.add(a);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener alumno");
        }
        return listaAlumnos;
    }
    
    public List<Alumno> listarTodosLosAlumnos() {
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();

        String query = "SELECT * FROM alumno";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Alumno a = new Alumno();
                a.setId(rs.getInt("idAlumno"));
                a.setApellido(rs.getString(2));
                a.setNombre(rs.getString("nombre"));
                a.setFechaNacimiento(rs.getDate("fechaNac").toLocalDate());
                a.setLegajo(rs.getInt(5));
                a.setActivo(rs.getBoolean("activo"));

                listaAlumnos.add(a);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener alumno");
        }
        return listaAlumnos;
    }

    public void actualizarAlumno(Alumno a) {
        String query = "UPDATE alumno SET apellido = ?, nombre = ?, fechaNac = ?, legajo = ?, activo = ? WHERE idAlumno = ?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(6, a.getIdAlumno());

            ps.setString(1, a.getApellido());
            ps.setString(2, a.getNombre());
            ps.setDate(3, Date.valueOf(a.getFechaNacimiento()));
            ps.setInt(4, a.getLegajo());
            ps.setBoolean(5, a.isActivo());

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Alumno actualizado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "No existe el alumno");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar alumno");
        }

    }

    public void borrarAlumno(int idAlumno) {
        String query = "UPDATE alumno SET activo = false WHERE idAlumno = ?";

        try {

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, idAlumno);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Alumno desactivado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se realizaron cambios");
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al desactivar alumno");
        }

    }

}
