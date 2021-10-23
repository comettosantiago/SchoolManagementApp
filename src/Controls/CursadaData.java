/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls;

import Models.Alumno;
import Models.Cursada;
import Models.Materia;
import java.sql.Connection;
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
public class CursadaData {

    /*
    La cursada hace referencia a la inscripcion o relacion que tenga un alumno con una materia
    
     */
    //Atributes
    private Connection con = null;
    private MateriaData md = null;
    private AlumnoData ad = null;

    //Constructor
    public CursadaData(Conexion conexionCursada) {
        this.con = conexionCursada.conectar();
        md = new MateriaData(conexionCursada);
        ad = new AlumnoData(conexionCursada);
    }

    //methods
    //-------------------------------------------
    public void guardarInscripcion(Cursada i) {
        try {
            String query = "INSERT INTO cursada(idAlumno, idMateria, nota, activo) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, i.getAlumno().getIdAlumno());
            ps.setInt(2, i.getMateria().getIdMateria());
            ps.setFloat(3, i.getNota());
            ps.setBoolean(4, i.isActivo());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                i.setIdCursada(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Inscripcion agregada con exito");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al realizar inscripcion" + ex);
        }

    }

    public void borrarInscripcion(int idMateria, int idAlumno) {
        try {
            String query = "UPDATE cursada SET activo = false WHERE cursada.idMateria = ? AND cursada.idAlumno = ?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, idMateria);
            ps.setInt(2, idAlumno);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Se desinscribio al Alumno de la Materia exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo desinscribir al Alumno");
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR en Base de Datos"+ex);
        }
    }

    public List<Cursada> obtenerInscripciones() {
        ArrayList<Cursada> listaCursadas = new ArrayList<>();

        try {
            String query = "SELECT * FROM cursada WHERE activo = true";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cursada c = new Cursada();

                c.setIdCursada(rs.getInt("idCursada"));
                Alumno a = ad.buscarAlumno(rs.getInt("idAlumno"));
                c.setAlumno(a);
                Materia m = md.buscarMateria(rs.getInt("idMateria"));
                c.setMateria(m);
                c.setNota(rs.getFloat("nota"));
                c.setActivo(rs.getBoolean("activo"));

                listaCursadas.add(c);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener Inscripciones"+ex);
        }
        return listaCursadas;
    }

    public List<Materia> obtenerMateriasCursadas(int idAlumno) {
        ArrayList<Materia> listaMaterias = new ArrayList<>();

        try {
            String query = "SELECT materia.idMateria, nombreMateria, anio, materia.activo\n"
                    + "FROM materia, cursada\n"
                    + "WHERE materia.idMateria = cursada.idMateria\n"
                    + "AND cursada.activo = true \n"
                    + "AND cursada.idAlumno = ?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, idAlumno);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Materia m = new Materia();

                m.setIdMateria(rs.getInt("idMateria"));
                m.setNombreMateria(rs.getString("nombreMateria"));
                m.setAnio(rs.getInt("anio"));
                m.setActivo(rs.getBoolean("activo"));

                listaMaterias.add(m);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener Materias Cursadas" + ex);
        }
        return listaMaterias;
    }

    public List<Materia> obtenerMateriasNoCursadas(int idAlumno) {
        ArrayList<Materia> listaMaterias = new ArrayList<>();

        try {
            String query = "SELECT * \n"
                    + "FROM materia \n"
                    + "WHERE idMateria NOT IN (SELECT materia.idMateria\n"
                    + "FROM materia, cursada\n"
                    + "WHERE materia.idMateria = cursada.idMateria\n"
                    + "AND cursada.activo = true\n"
                    + "AND cursada.idAlumno = ?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, idAlumno);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Materia m = new Materia();

                m.setIdMateria(rs.getInt("idMateria"));
                m.setNombreMateria(rs.getString("nombreMateria"));
                m.setAnio(rs.getInt("anio"));
                m.setActivo(rs.getBoolean("activo"));

                listaMaterias.add(m);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener Materias no Cursadas"+ex);
        }
        return listaMaterias;
    }

    public List<Alumno> obtenerAlumnosPorMateria(int idMateria) {
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();

        try {

            String query = "SELECT cursada.idAlumno FROM cursada WHERE cursada.activo = true AND cursada.idMateria = ?";
            
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, idMateria);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Alumno a = new Alumno();

                a = ad.buscarAlumno(rs.getInt("idAlumno"));

                listaAlumnos.add(a);
            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener Alumnos"+ex);
        }

        return listaAlumnos;

    }

    public void actualizarNota(int idAlumno, int idMateria, float nota) {
        try {
            String query = "UPDATE cursada SET nota = ? WHERE cursada.idAlumno= ? AND cursada.idMateria = ?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            ps.setFloat(1, nota);

             if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Nota actualizada exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se puede actualizar la Nota");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la Nota"+ex);
        }
    }
    //--------------------------------------------
}
