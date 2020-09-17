package com.projetosuper.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainAdminActivity extends AppCompatActivity {
    Button btn_veterinarios, btn_produtos, btn_admin_logout;
    Button btn_lista_veterinarios, btn_lista_produtos, btn_lista_animais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        //Setar variáveis com objetos da tela

        btn_admin_logout = findViewById(R.id.btn_admin_logout);

        btn_lista_veterinarios = findViewById(R.id.btn_lista_vet);
        btn_lista_produtos = findViewById(R.id.btn_lista_produtos);
        btn_lista_animais = findViewById(R.id.btn_lista_animais);

        //setar evento de fazer logout
        btn_admin_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_admin_login = new Intent(MainAdminActivity.this, LoginAdminActivity.class);
                startActivity(go_to_admin_login);
                finish();
            }
        });

        //setar evento de lista de animais
        btn_lista_animais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_visualizar_animais = new Intent(getApplicationContext(), VisualizarAnimaisActivity.class);
                startActivity(go_to_visualizar_animais);
            }
        });

        //setar evento de lista de veterinários
        btn_lista_veterinarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_visualizar_veterinarios = new Intent(getApplicationContext(), VisualizarVeterinariosActivity.class);
                startActivity(go_to_visualizar_veterinarios);
            }
        });

        //setar evento de lista de produtos
        btn_lista_produtos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_visualizar_produtos = new Intent(getApplicationContext(), VisualizarProdutosActivity.class);
                startActivity(go_to_visualizar_produtos);
            }
        });
    }
}