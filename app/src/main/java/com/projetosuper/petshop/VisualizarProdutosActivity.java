package com.projetosuper.petshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VisualizarProdutosActivity extends AppCompatActivity {
    RecyclerView recycler_produtos;
    FloatingActionButton fab_produtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_produtos);

        recycler_produtos = findViewById(R.id.recycler_produtos);
        fab_produtos = findViewById(R.id.fab_produtos);

        fab_produtos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_cadastrar_produto = new Intent(getApplicationContext(), CadastrarProdutoActivity.class);
                startActivity(go_to_cadastrar_produto);
                finish();
            }
        });
    }
}