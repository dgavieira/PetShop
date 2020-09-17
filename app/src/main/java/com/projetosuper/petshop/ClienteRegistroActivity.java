package com.projetosuper.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ClienteRegistroActivity extends AppCompatActivity {
    Button btn_camera, btn_imagem, btn_cliente_registrar, btn_cliente_cancelar;
    EditText edt_cliente_nome, edt_cliente_email, edt_cliente_cpf, edt_cliente_telefone;
    EditText edt_cliente_senha, edt_cliente_confirmar_senha;
    ImageView imagemCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_registro);

        //setar variáveis aos objetos
        edt_cliente_nome = findViewById(R.id.edt_cliente_nome);
        edt_cliente_email = findViewById(R.id.edt_cliente_email);
        edt_cliente_cpf = findViewById(R.id.edt_cliente_cpf);
        edt_cliente_telefone = findViewById(R.id.edt_cliente_telefone);

        //setar variáveis das senhas aos objetos
        edt_cliente_senha = findViewById(R.id.edt_cliente_senha);
        edt_cliente_confirmar_senha = findViewById(R.id.edt_cliente_confirmar_senha);

        //setar variáveis dos botoes de imagem
        btn_camera = findViewById(R.id.btn_camera);
        btn_imagem = findViewById(R.id.btn_imagem);

        //setar variáveis dos botoes de transicao
        btn_cliente_registrar = findViewById(R.id.btn_cliente_registrar);
        btn_cliente_cancelar = findViewById(R.id.btn_cliente_cancelar);

        //setar variável de imagem
        imagemCliente = findViewById(R.id.imgCliente);

        //fazer acesso da camera - Referencia Agenda01

        //fazer acesso ao diretorio de imagens - Referencia Agenda01

        //verificar se campos estão preenchidos - menos imagem

        //verificar se as senhas sao iguais

        //se os campos estao preenchidos e as senhas sao iguais, permitir transicao de tela

        //tratar entrada das variaveis

        //implementar classe Cliente e DAO para banco de dados

        btn_cliente_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ClienteRegistroActivity.this, "Cliente Cadastrado com Sucesso!", Toast.LENGTH_LONG).show();
                Intent go_to_login_cliente = new Intent(ClienteRegistroActivity.this, MainActivity.class);
                startActivity(go_to_login_cliente);
                finish();
            }
        });
    }
}