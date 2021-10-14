/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.time.LocalDate;

/**
 *
 * @author Santiago Cometto
 */
public class Alumno {

    //attributes
    private int idAlumno = -1;
    private int legajo;
    private String nombreAlumno;
    private String apellidoAlumno;
    private LocalDate fechaNacimiento;
    boolean activo;

    //constructo
    public Alumno(int legajo, String nombre, String apellido, LocalDate fechaNacimiento, boolean activo) {
        this.legajo = legajo;
        this.nombreAlumno = nombre;
        this.apellidoAlumno = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.activo = activo;
    }

    public Alumno() {

    }

    //methods
    public int getIdAlumno() {
        return idAlumno;
    }

    public int getLegajo() {
        return legajo;
    }

    public String getNombre() {
        return nombreAlumno;
    }

    public String getApellido() {
        return apellidoAlumno;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public void setNombre(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public void setApellido(String apellidoAlumno) {
        this.apellidoAlumno = apellidoAlumno;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "ID=" + idAlumno + ", legajo=" + legajo + ", " + apellidoAlumno + ", " + nombreAlumno; //reveer si poner activo
    }

    void setId(int id) {
        this.idAlumno=id; //reveer 
    }

}
