package com.petshop;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.petshop.db.SQLiteDBCliente;
import com.petshop.db.UsuarioDAO;
import com.petshop.model.Cliente;
import com.petshop.model.Usuario;

import java.io.ByteArrayOutputStream;

public class CadastroCliente extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = CadastroCliente.this;

    private Cliente cliente;
    private NestedScrollView nestedScrollView;
    private EditText editTextName, editTextCpf, editTextSenha, editTextIdade, editTextPhone, editTextemail;
    private ImageView imageViewProfile;
    private Button appCompatButtonRegister;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //getWindow().setStatusBarColor(Color.TRANSPARENT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);
        initViews();
        initListeners();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        editTextName = (EditText) findViewById(R.id.nome);
        //editTextCpf = (EditText) findViewById(R.id.cpf);
        editTextSenha = (EditText) findViewById(R.id.senha);
        editTextIdade = (EditText) findViewById(R.id.idade);
        editTextemail = (EditText) findViewById(R.id.email);
        //editTextPhone = (EditText) findViewById(R.id.phone);
        imageViewProfile = (ImageView) findViewById(R.id.imageProfile);

        imageViewProfile.setImageResource(R.drawable.logo_petshop);

        appCompatButtonRegister = (Button) findViewById(R.id.btdCadastrar);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btdCadastrar:
                cadastrar();
                break;
        }
    }

    public void cadastrar(){

        String nome = editTextName.getText().toString();
        //String cpfString = editTextCpf.getText().toString();
        String senha = editTextSenha.getText().toString();
        String idade = editTextIdade.getText().toString();
        //String phoneString = editTextPhone.getText().toString();
        String email = editTextemail.getText().toString();


        if ( nome.isEmpty() || senha.isEmpty() || idade.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }else{
            // Cria um usuario a partir dos campos
            Usuario user = new Usuario(nome,senha,Integer.parseInt(idade),email
            );

            // Cria o objeto que irá salvar os dados do usuário no banco de dados
            UsuarioDAO userDAO = new UsuarioDAO(this);
            if ( !userDAO.create(user) ){
                Toast.makeText(this, "Email ja cadastrado!.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public static byte[] imageViewToByte(ImageView image){
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}