/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controls.AlumnoData;
import Controls.Conexion;
import Controls.CursadaData;
import Controls.MateriaData;
import Models.Alumno;
import Models.Cursada;
import Models.Materia;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 *
 * @author Santiago Cometto
 */
public class Principal {

    public static void main(String pepe[]) {
        Conexion conexion = new Conexion();
        //Alumno santiago=new Alumno(1002, "Santiago", "Cometto", LocalDate.of(1999, 12, 15), true);
        //AlumnoData ad=new AlumnoData(conexion);

        //ad.guardarAlumno(santiago);
        //Alumno guillermo=new Alumno(1003, "Guillermo", "Visco", LocalDate.of(1976, 9, 1), true);
        //Alumno victoria=new Alumno(1009, "Victoria", "Lopez", LocalDate.of(1985, 10, 11), true);
        //Alumno patricia=new Alumno(1006, "Patricia", "Baigorria", LocalDate.of(1978, 12, 1), true);
        //ad.guardarAlumno(guillermo);
        //ad.guardarAlumno(ramiro);
        //ad.guardarAlumno(victoria);
        //ad.guardarAlumno(patricia);
        //  System.out.println(ad.listarAlumnos());
        //  System.out.println(ad.buscarAlumno(3));
        //   System.out.println(ad.buscarAlumno(1));
        // ad.borrarAlumno(300);
        // System.out.println(ad.buscarAlumno(3));
        //System.out.println(ad.buscarAlumno(1));
        // Alumno ramiro=new Alumno(4, 1004, "Ramiro", "Alaniz", LocalDate.of(1982, 4, 25), true);
        //ramiro.setNombre("Luis Ramiro");
        //Alumno ramiro=ad.buscarAlumno(4);
        //ramiro.setNombre("Ramiro");
        //ad.actualizarAlumno(ramiro);
        /*
         AlumnoData ad=new AlumnoData(conexion);
         
         MateriaData md=new MateriaData(conexion);
         */
        CursadaData cd = new CursadaData(conexion);
        /*
         Alumno santiago = ad.buscarAlumno(1);
         
         Materia ingles = md.buscarMateria(2);
         System.out.println(ingles.getIdMateria());
         Cursada santiagoingles = new Cursada(santiago, ingles, 5, true);
         
         cd.guardarInscripcion(santiagoingles);
         */

        List<Materia> lista = cd.obtenerMateriasCursadas(1);

        for (Materia c : lista) {
            System.out.println(c.getNombreMateria());
        }

        //cd.borrarInscripcion(2, 1);
        List<Materia> listaa = cd.obtenerMateriasNoCursadas(1);

        for (Materia c : listaa) {
            System.out.println(c.getNombreMateria());
        }

        List <Alumno> listaaa=cd.obtenerAlumnosPorMateria(4);
        
        for (Alumno a: listaaa){
            System.out.println(a.getLegajo());
        }
        
        cd.actualizarNota(1, 1, 10);

    }
}
