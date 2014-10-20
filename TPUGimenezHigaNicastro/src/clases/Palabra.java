/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;

/**
 *
 * @author user
 */
public class Palabra {
    int id;
    String nombre;
    long cant;
    int cantArch;
    public Palabra(int id, String nombre, int cant, int cantArch){
        this.id=id;
        this.nombre=nombre;
        this.cant=cant;
        this.cantArch=cantArch;
    }

    public int getCantArch() {
        return cantArch;
    }

    public void setCantArch(int cantArch) {
        this.cantArch = cantArch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getCant() {
        return cant;
    }

    public void setCant(long cant) {
        this.cant = cant;
    }

    @Override
    public String toString() {
        return "Palabra{" + "id=" + id + ", nombre=" + nombre + ", cant=" + cant + '}';
    }
    
}
