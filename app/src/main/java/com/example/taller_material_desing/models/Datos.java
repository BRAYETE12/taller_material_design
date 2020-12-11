package com.example.taller_material_desing.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Datos {

    private static String db = "Establecimientos";
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private static StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private static ArrayList<Establecimeinto> establecimeintos = new ArrayList();

    public static void guardar(Establecimeinto x){
        getEstablecimeintos().add(x);
    }


    public static ArrayList<Establecimeinto> getEstablecimeintos() {
        return establecimeintos;
    }

    public static void setEstablecimeintos(ArrayList<Establecimeinto> establecimeintos) {
        Datos.establecimeintos = establecimeintos;
    }

    public static void eliminar(Establecimeinto p){
        databaseReference.child(db).child(p.getId()).removeValue();
        storageReference.child(db).child(p.getId()).delete();
    }

}
