package com.example.taller_material_desing.models;

public class Establecimeinto {

    private String id;
    private String nit;
    private String nombre;
    private String direccion;


    public Establecimeinto(String id ,String nit, String nombre, String direccion){
        this.setId(id);
        this.setNit(nit);
        this.setNombre(nombre);
        this.setDireccion(direccion);
    }

    public String getId() {
        return nit;
    }

    public void setId(String nit) {
        this.nit = nit;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void guardar(){
        Datos.guardar(this);
    }

}
