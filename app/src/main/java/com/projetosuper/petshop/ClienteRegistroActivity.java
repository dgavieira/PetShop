package com.projetosuper.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.projetosuper.petshop.model.Cliente;
import com.projetosuper.petshop.model.ClienteDAO;

public class ClienteRegistroActivity extends AppCompatActivity {
    Button btn_camera, btn_imagem, btn_cliente_registrar, btn_cliente_cancelar;
    EditText edt_cliente_nome, edt_cliente_email, edt_cliente_cpf, edt_cliente_telefone;
    EditText edt_cliente_senha, edt_cliente_idade;
    ImageView imagemCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_registro);

        //setar variáveis aos objetos
        edt_cliente_nome = findViewById(R.id.edt_cliente_nome);
        edt_cliente_email = findViewById(R.id.edt_cliente_email);
        edt_cliente_cpf = findViewById(R.id.edt_cliente_cpf);
        edt_cliente_idade = findViewById(R.id.edt_cliente_idade);
        edt_cliente_telefone = findViewById(R.id.edt_cliente_telefone);

        //setar variáveis das senhas aos objetos
        edt_cliente_senha = findViewById(R.id.edt_cliente_senha);

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
    }

    //metodo setado no atributo onClick de btn_cliente_registrar em activity_cliente_registro.xml
    public void cadastrar_cliente(View view){
        String nome_cliente = edt_cliente_nome.getText().toString();
        String email_cliente = edt_cliente_email.getText().toString();
        String cpf_cliente = edt_cliente_cpf.getText().toString();
        String idade_cliente = edt_cliente_idade.getText().toString();
        String telefone_cliente = edt_cliente_telefone.getText().toString();
        String senha_cliente = edt_cliente_senha.getText().toString();

        //passa campo idade para inteiro
        int idade_cliente_int = Integer.parseInt(idade_cliente);

        //verifica se todos os campos estão preenchidos

        if(nome_cliente.isEmpty() || email_cliente.isEmpty() || idade_cliente.isEmpty() ||
        cpf_cliente.isEmpty() || telefone_cliente.isEmpty() || senha_cliente.isEmpty()){
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        //Cria um novo cliente com os dados obtidos dos campos
        Cliente cliente = new Cliente(nome_cliente, email_cliente, cpf_cliente, idade_cliente_int, telefone_cliente, senha_cliente);

        //O objeto clienteDAO irá salvar os dados do cliente no banco de dados
        ClienteDAO clienteDAO = new ClienteDAO(this);
        if(!clienteDAO.create(cliente)){
            Toast.makeText(this, "Email já cadastrado!", Toast.LENGTH_SHORT).show();
            edt_cliente_email.requestFocus();
        }else{
            Toast.makeText(ClienteRegistroActivity.this, "Cliente Cadastrado com Sucesso!", Toast.LENGTH_LONG).show();
            Intent go_to_login_cliente = new Intent(ClienteRegistroActivity.this, MainActivity.class);
            startActivity(go_to_login_cliente);
            finish();
        }
    }
    public void cancelar_cliente(View view){
        edt_cliente_nome.setText("");
        edt_cliente_email.setText("");
        edt_cliente_cpf.setText("");
        edt_cliente_telefone.setText("");
        edt_cliente_idade.setText("");
        edt_cliente_telefone.setText("");
        edt_cliente_senha.setText("");
    }
}