package com.example.taller_material_desing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taller_material_desing.models.Establecimeinto;

public class crear extends AppCompatActivity {

    private EditText nit, nombre, direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);

        this.nit = findViewById(R.id.txNIT);
        this.nombre = findViewById(R.id.txNombre);
        this.direccion = findViewById(R.id.txDireccion);

    }


    public void guardar(View v){

        if(this.validar()) {
            String nit = this.nit.getText().toString();
            String nom = this.nombre.getText().toString();
            String dir = this.direccion.getText().toString();

            Establecimeinto X = new Establecimeinto( "", nit, nom, dir);
            X.guardar();

            this.limpiar();
            Toast.makeText(this, R.string.guardado_exitoso, Toast.LENGTH_LONG).show();
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


}
