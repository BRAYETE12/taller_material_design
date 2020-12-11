package com.example.taller_material_desing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

import com.example.taller_material_desing.models.AdactadorEstablecimeinto;
import com.example.taller_material_desing.models.Datos;
import com.example.taller_material_desing.models.Establecimeinto;
import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdactadorEstablecimeinto.OnEstablecimeintoListener {

    private RecyclerView lista;
    private AdactadorEstablecimeinto adapter;
    private LinearLayoutManager llm;
    private ArrayList<Establecimeinto> establecimientos;
    private DatabaseReference databaseReference;
    private static String db = "Establecimientos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        lista = findViewById(R.id.listEstablecimientos);

        establecimientos = new ArrayList();
        llm = new LinearLayoutManager(this);
        adapter = new AdactadorEstablecimeinto(establecimientos, this);

        llm.setOrientation(RecyclerView.VERTICAL);
        lista.setLayoutManager(llm);
        lista.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(db).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                establecimientos.clear();
                if(snapshot.exists()){
                    for (DataSnapshot snap: snapshot.getChildren()){
                        Establecimeinto item = snap.getValue(Establecimeinto.class);
                        establecimientos.add(item);
                    }
                }
                adapter.notifyDataSetChanged();
                Datos.setEstablecimeintos(establecimientos);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void agregar(View v){
        Intent intent;
        intent = new Intent(MainActivity.this, crear.class);
        startActivity(intent);
    }

    @Override
    public void onEstablecimientoClick(Establecimeinto item) {
        Intent intent;
        Bundle bundle;

        bundle = new Bundle();
        bundle.putString("id", item.getId());
        bundle.putString("nit", item.getNit());
        bundle.putString("nombre", item.getNombre());
        bundle.putString("direccion", item.getDireccion());

        intent = new Intent(MainActivity.this, Detalle.class);
        intent.putExtra("datos",bundle);
        startActivity(intent);
    }
}
