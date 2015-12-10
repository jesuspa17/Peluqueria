package com.dam.salesianostriana.ad.ejemplo01_basedatossqlite.pojo_lista;

/**
 * Created by Jesús Pallares on 04/12/2015.
 */
public class Cliente {

    private String nombre;
    private String apellidos;
    private int telefono;
    private int num_pelados;


    public Cliente(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Cliente(String nombre, String apellidos, int telefono) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
    }

    public Cliente(String nombre, String apellidos, int telefono, int num_pelados) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.num_pelados = num_pelados;
    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getNum_pelados() {
        return num_pelados;
    }

    public void setNum_pelados(int num_pelados) {
        this.num_pelados = num_pelados;
    }
}
