package com.petshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.joaquimley.faboptions.FabOptions;

public class ActivityLoja extends AppCompatActivity implements View.OnClickListener {
    Toolbar mToolbar;
    FabOptions mFabOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loja);

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("PetShop");
        setSupportActionBar(mToolbar);

        mFabOptions = findViewById(R.id.fab_options);
        mFabOptions.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_produtos:
                //tartActivity(new Intent( ActivityLoja.this, PagamentoActivity.class));
                break;

            case R.id.button_veterinarios:
                startActivity(new Intent(ActivityLoja.this, CadastroVeterinario.class));
                break;

            default:
        }

    }
}