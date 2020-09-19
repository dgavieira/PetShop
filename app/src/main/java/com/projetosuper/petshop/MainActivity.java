package com.projetosuper.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projetosuper.petshop.model.Cliente;
import com.projetosuper.petshop.model.ClienteDAO;

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
    }

    @Override
    protected  void onResume(){
        super.onResume();
        edt_username.getText().clear();
        edt_password.getText().clear();
        edt_username.requestFocus();
    }

    //Login - método atribuido a btn_signin em activity_main.xml
    public void login(View view){
        String cliente_email = edt_username.getText().toString();
        String cliente_senha = edt_password.getText().toString();

        //verifica se os campos estão vazios
        if(cliente_email.isEmpty() || cliente_senha.isEmpty()){
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        ClienteDAO clienteDAO = new ClienteDAO(this);
        Cliente cliente = (Cliente) clienteDAO.get(cliente_email);

        //verifica se há cliente cadastrado com esse email na base de dados
        if (cliente == null){
            Toast.makeText(this, "Email não cadastrado.", Toast.LENGTH_SHORT).show();
            return;
        }

        //Verifica se a senha do cliente está correta
        if (cliente.getCliente_senha().equals(cliente_senha)){
            Toast.makeText(this, "Seja bem vindo. " + cliente.getCliente_nome(), Toast.LENGTH_SHORT).show();
            Intent go_to_area_cliente = new Intent(MainActivity.this, AreaDoClienteActivity.class);
            go_to_area_cliente.putExtra("Cliente", cliente);
            startActivity(go_to_area_cliente);
            finish();
        }else{
            Toast.makeText(this, "Senha incorreta!", Toast.LENGTH_SHORT).show();
        }
    }
}