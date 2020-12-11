package com.example.taller_material_desing.models;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taller_material_desing.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdactadorEstablecimeinto extends RecyclerView.Adapter<AdactadorEstablecimeinto.EstablecimeintoViewHolder>{


       private ArrayList<Establecimeinto> establecimeintos;
       private OnEstablecimeintoListener clickListener;

       public  AdactadorEstablecimeinto(ArrayList<Establecimeinto> establecimeintos, OnEstablecimeintoListener clickListener){
              this.establecimeintos = establecimeintos;
              this.clickListener = clickListener;
       }

       @NonNull
       @Override
       public EstablecimeintoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

              View v = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.item, parent, false);
              return new EstablecimeintoViewHolder(v);
       }

       @Override
       public void onBindViewHolder(@NonNull final EstablecimeintoViewHolder holder, int position) {
              final Establecimeinto p = establecimeintos.get(position);
              StorageReference storageReference;
              storageReference = FirebaseStorage.getInstance().getReference();

              storageReference.child(p.getId()).
                      getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                     @Override
                     public void onSuccess(Uri uri) {
                            Picasso.get().load(uri).into(holder.img);
                     }
              });

              holder.nit.setText(p.getNit());
              holder.nombre.setText(p.getNombre());
              holder.direccion.setText(p.getDireccion());
              holder.view.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                            clickListener.onEstablecimientoClick(p);
                     }
              });
       }

       @Override
       public int getItemCount() {
              return this.establecimeintos.size();
       }

       public static class EstablecimeintoViewHolder extends RecyclerView.ViewHolder {


              private CircleImageView img;
              private TextView nit;
              private TextView nombre;
              private TextView direccion;
              private View view;

              public EstablecimeintoViewHolder(View itemView){
                     super(itemView);
                     this.view =  itemView;
                     this.img = this.view.findViewById(R.id.imgDetalle);
                     this.nit = this.view.findViewById(R.id.lbNIT);
                     this.nombre = this.view.findViewById(R.id.lbNombre);
                     this.direccion = this.view.findViewById(R.id.lbDireccion);
              }

       }

       public interface OnEstablecimeintoListener {
              void onEstablecimientoClick(Establecimeinto p);
       }

}
