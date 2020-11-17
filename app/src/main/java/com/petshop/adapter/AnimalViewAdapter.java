package com.petshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.petshop.AtualizaAnimal;
import com.petshop.R;
import com.petshop.resource.CircleImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class AnimalViewAdapter extends RecyclerView.Adapter<AnimalViewAdapter.ViewHolder> {
    //Recebimento quando chamar o construtor.
    Context context;
    List<String> nomes = new ArrayList<String>();
    String[] raca;
    String[] categorias;
    String[] fotos;
    View viewOnCreate;
    ViewHolder viewHolderLocal;



    public AnimalViewAdapter(Context contextRecebido, String[] nomesRecebidos,
                             String[] racaRecebidas,
                             String[] categoriasRecebidos,
                             String[] fotosRecebidas){
            context = contextRecebido;
            nomes.addAll(Arrays.asList(nomesRecebidos));
            raca = racaRecebidas;
            categorias = categoriasRecebidos;
            fotos = fotosRecebidas;


    }

    //Método para estender a claase já existente.
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView textNome;
        public TextView textRaca;
        public CircleImageView icone;

        public ViewHolder(View itemView) {
            super(itemView);
            textNome = itemView.findViewById(R.id.textNome);
            textRaca = itemView.findViewById(R.id.textRaca);
            icone = itemView.findViewById(R.id.icone);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
        }
        //Implementação do método onClick na View.
        @Override
        public void onClick(View v) {

        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewOnCreate = LayoutInflater.from(context).inflate(R.layout.item_lista_pet,parent,false);
        viewHolderLocal = new ViewHolder(viewOnCreate);
        return viewHolderLocal;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textNome.setText(nomes.get(position));
        holder.textRaca.setText(raca[position]);


        if (!fotos[position].equals("") || fotos[position].equals("null")) {
            if(categorias[position].equals("Cachorro")) {
                holder.icone.setImageResource(R.drawable.ic_dog);
            }else {
                holder.icone.setImageResource(R.drawable.ic_cat);
            }

        } else {
            byte[] imagemEmBytes;
            imagemEmBytes = Base64.decode(fotos[position], Base64.DEFAULT);
            Bitmap imageDecodificada = BitmapFactory.decodeByteArray(imagemEmBytes,0,imagemEmBytes.length);
            holder.icone.setImageBitmap(imageDecodificada);

        }

        viewOnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, AtualizaAnimal.class);
                intent.putExtra("nome", nomes.get(position));
                intent.putExtra("raca", raca[position]);
                intent.putExtra("categorias", categorias[position]);
                intent.putExtra("foto", fotos[position]);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return nomes.size();
    }
}
