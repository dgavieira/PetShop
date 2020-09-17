package com.projetosuper.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //Declaração dos objetos da tela
    EditText edt_username, edt_password;
    Button btn_signin, btn_go_register, btn_go_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setar os findviews by id com as variáveis
        edt_username = findViewById(R.id.login_edt_username);
        edt_password = findViewById(R.id.login_edt_password);

        btn_signin = findViewById(R.id.btn_signin);
        btn_go_register = findViewById(R.id.btn_go_register);
        btn_go_admin = findViewById(R.id.btn_go_admin);

        //eventos de click dos botoes
        btn_go_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_admin = new Intent(MainActivity.this, LoginAdminActivity.class);
                startActivity(go_to_admin);
                finish();
            }
        });
        btn_go_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_cliente_registro = new Intent(MainActivity.this, ClienteRegistroActivity.class);
                startActivity(go_to_cliente_registro);
                finish();
            }
        });
        //verificar no banco se os dados digitados existem, entao faça isso
        //Senão existir, avisar para cliente se registrar
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_area_cliente = new Intent(MainActivity.this, AreaDoClienteActivity.class);
                startActivity(go_to_area_cliente);
                finish();
            }
        });
    }
}