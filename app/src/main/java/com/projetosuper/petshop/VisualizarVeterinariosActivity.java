package com.projetosuper.petshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VisualizarVeterinariosActivity extends AppCompatActivity {
    RecyclerView recycler_veterinarios;
    FloatingActionButton fab_veterinarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_veterinarios);

        recycler_veterinarios = findViewById(R.id.recycler_veterinarios);
        fab_veterinarios = findViewById(R.id.fab_veterinarios);

        fab_veterinarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_cadastrar_veterinarios = new Intent(getApplicationContext(), CadastrarVeterinarioActivity.class);
                startActivity(go_to_cadastrar_veterinarios);
                finish();
            }
        });
    }
}