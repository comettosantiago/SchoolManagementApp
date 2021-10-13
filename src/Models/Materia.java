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
public class Materia {

    //attributes
    private int idMateria = -1;
    private String nombreMateria;
    private int anio;
    private boolean activo;

    //constructor
    public Materia(String nombreMateria, int anio, boolean activo) {
        this.nombreMateria = nombreMateria;
        this.anio = anio;
        this.activo = activo;
    }

    public Materia() {

    }

    //methods
    public int getIdMateria() {
        return idMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public int getAnio() {
        return anio;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
