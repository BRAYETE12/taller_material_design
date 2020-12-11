package com.example.taller_material_desing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.taller_material_desing.models.Establecimeinto;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class crear extends AppCompatActivity {

    private EditText nit, nombre, direccion;
    private ImageView foto;
    private InputMethodManager im;
    private Uri uri;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);

        this.nit = findViewById(R.id.txNIT);
        this.nombre = findViewById(R.id.txNombre);
        this.direccion = findViewById(R.id.txDireccion);
        foto = findViewById(R.id.imgFotoSelected);

        storageReference = FirebaseStorage.getInstance().getReference();

    }


    public void guardar(View v){

        if(this.validar()) {

            im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(nit.getWindowToken(), 0);

            String nit = this.nit.getText().toString();
            String nom = this.nombre.getText().toString();
            String dir = this.direccion.getText().toString();

            Establecimeinto X = new Establecimeinto( "", nit, nom, dir);
            X.guardar();

            this.limpiar();
            Snackbar.make(v, R.string.guardado_exitoso, Snackbar.LENGTH_LONG).show();
            uri = null;
        }
    }

    public void limpiar(View v){
        this.limpiar();
    }

    public void limpiar(){
        this.nit.setText("");
        this.nombre.setText("");
        this.direccion.setText("");
        this.nit.requestFocus();
    }

    public boolean validar(){

        if(this.nit.getText().toString().isEmpty()){
            this.nit.setError( getString(R.string.error_numero) );
            this.nit.requestFocus();
            return false;
        }

        if(this.nombre.getText().toString().isEmpty()){
            this.nombre.setError( getString(R.string.error_numero) );
            this.nombre.requestFocus();
            return false;
        }

        if(this.direccion.getText().toString().isEmpty()){
            this.direccion.setError( getString(R.string.error_numero) );
            this.direccion.requestFocus();
            return false;
        }

        return true;
    }

    public void subirFoto(String id){
        StorageReference child = storageReference.child(id);
        UploadTask uploadTask = child.putFile(uri);
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(crear.this, MainActivity.class);
        startActivity(i);
    }

    public void seleccionarFoto(View v){
        Intent in = new Intent();
        in.setType("image/*");
        in.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(in,
                getString(R.string.select_foto)), 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            uri = data.getData();
            if (uri != null){
                foto.setImageURI(uri);
            }
        }
    }

}
