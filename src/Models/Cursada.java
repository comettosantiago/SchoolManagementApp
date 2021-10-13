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
public class Cursada {

    //attributes 
    private int idCursada;
    private Alumno alumno;
    private Materia materia;
    private float nota;
    private boolean activo;

    //constructor
    public Cursada(Alumno alumno, Materia materia, float nota, boolean activo) {
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
        this.activo = activo;
    }

    public Cursada() {

    }

    //methods
    public int getIdCursada() {
        return idCursada;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public float getNota() {
        return nota;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
