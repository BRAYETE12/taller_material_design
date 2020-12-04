package com.example.taller_material_desing.models;

import java.util.ArrayList;

public class Datos {

    private static ArrayList<Establecimeinto> establecimeintos = new ArrayList();

    public static void guardar(Establecimeinto x){
        establecimeintos.add(x);
    }

    public static ArrayList<Establecimeinto> obtener(){
        return establecimeintos;
    }

}
