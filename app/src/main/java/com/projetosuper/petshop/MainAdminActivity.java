package com.projetosuper.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainAdminActivity extends AppCompatActivity {
    Button btn_veterinarios, btn_produtos, btn_admin_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        btn_veterinarios = findViewById(R.id.btn_veterinarios);
        btn_produtos = findViewById(R.id.btn_produtos);
        btn_admin_logout = findViewById(R.id.btn_admin_logout);

        //setar evento de fazer logout
        btn_admin_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_admin_login = new Intent(MainAdminActivity.this, LoginAdminActivity.class);
                startActivity(go_to_admin_login);
                finish();
            }
        });
    }
}