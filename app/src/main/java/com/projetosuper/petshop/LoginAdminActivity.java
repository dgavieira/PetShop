package com.projetosuper.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginAdminActivity extends AppCompatActivity {
    //Inicializaçao dos objetos da tela
    EditText edt_admin_user, edt_admin_password;
    Button btn_signin_admin;

    //Strings que serão usadas para pegar o que foi digitado
    String str_admin_user, str_admin_password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        //Setar os findviews by id com as variáveis
        edt_admin_user = findViewById(R.id.edt_admin_user);
        edt_admin_password = findViewById(R.id.edt_admin_password);
        btn_signin_admin = findViewById(R.id.btn_signin_admin);

        //pegar o que foi digitado
        str_admin_user = edt_admin_user.getText().toString();
        str_admin_password = edt_admin_password.getText().toString();

        //comparar o que foi digitado com o correto
        //essa tela pode ser melhorada colocando os campos que permitem acesso em uma tabela de banco de dados
        btn_signin_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(str_admin_user.equalsIgnoreCase("")
                        && str_admin_password.equalsIgnoreCase("")){
                    Intent go_to_main_admin = new Intent(getApplicationContext(), MainAdminActivity.class);
                    startActivity(go_to_main_admin);
                    finish();
                }else{
                    Toast.makeText(LoginAdminActivity.this, "Forgot Entries? Contact Administration Support", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
