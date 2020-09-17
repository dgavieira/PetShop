package com.projetosuper.petshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VisualizarAnimaisActivity extends AppCompatActivity {
    RecyclerView recycler_animais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_animais);

        recycler_animais = findViewById(R.id.recycler_animais);
    }
}