package com.example.taller_material_desing;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taller_material_desing.models.Establecimeinto;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class Detalle extends AppCompatActivity {

    private ImageView foto;
    private TextView nit, nombre, direccion;
    private Bundle bundle;
    private Intent intent;
    private StorageReference storageReference;
    private Establecimeinto est;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        String  nt, nb, di, id;

        foto = findViewById(R.id.imgFotoDetalle);
        nit = findViewById(R.id.lblDetalleNIT);
        nombre = findViewById(R.id.lblDetalleNombre);
        direccion = findViewById(R.id.lblDetalleDireccion);

        storageReference = FirebaseStorage.getInstance().getReference();

        //Bundle es un encapsulamiento, como un zip
        intent = getIntent();
        bundle = intent.getBundleExtra("datos");

        nt = bundle.getString("cedula");
        nb = bundle.getString("nombre");
        di = bundle.getString("apellido");

        id = bundle.getString("id");

        storageReference.child(id).getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(foto);
                    }
                });

        est = new Establecimeinto(nt, nb, di, id);
        nit.setText(nt);
        nombre.setText(nb);
        direccion.setText(di);


    }

    public void eliminar(View v){
        String positivo, negativo;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.titulo_eliminar);
        builder.setMessage(R.string.msg_eliminar);
        positivo = getString(R.string.msg_si);
        negativo = getString(R.string.msg_no);
        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                est.eliminar();
                onBackPressed();
            }
        });
        builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onBackPressed(){
        finish();
        Intent intent = new Intent(Detalle.this, MainActivity.class);
        startActivity(intent);
    }
}
