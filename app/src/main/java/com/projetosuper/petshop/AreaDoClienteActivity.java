package com.projetosuper.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AreaDoClienteActivity extends AppCompatActivity {
    Button btn_cliente_logout, btn_cliente_editar_dados, btn_cliente_pet, btn_cliente_produtos, btn_cliente_veterinario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_do_cliente);

        btn_cliente_logout = findViewById(R.id.btn_cliente_logout);
        btn_cliente_editar_dados = findViewById(R.id.btn_cliente_editar_dados);
        btn_cliente_pet = findViewById(R.id.btn_cliente_pet);
        btn_cliente_produtos = findViewById(R.id.btn_cliente_produtos);
        btn_cliente_veterinario = findViewById(R.id.btn_cliente_veterinario);

        //Evento de click do Botao Logout
        btn_cliente_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logout = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(logout);
                finish();
            }
        });

        //Evento vai para tela de editar dados do cliente
        btn_cliente_editar_dados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editar_dados_cliente = new Intent(getApplicationContext(), ClienteEditarDadosActivity.class);
                startActivity(editar_dados_cliente);
            }
        });

        //Evento que vai para tela de visualizar pets
        btn_cliente_pet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent visualizar_pets = new Intent(getApplicationContext(), SeusPetsActivity.class);
                startActivity(visualizar_pets);
            }
        });

        //Evento que vai para tela de Comprar Produtos
        btn_cliente_produtos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loja = new Intent(getApplicationContext(), LojaProdutosActivity.class);
                startActivity(loja);
            }
        });
        //Evento que vai para tela de marcar consulta com veterinario
        btn_cliente_veterinario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent consulta = new Intent(getApplicationContext(), LojaVeterinarioActivity.class);
                startActivity(consulta);
            }
        });

    }
}