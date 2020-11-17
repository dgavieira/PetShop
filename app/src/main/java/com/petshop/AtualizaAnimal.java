package com.petshop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.petshop.db.DAO;
import com.petshop.model.Animais;
import com.petshop.resource.CircleImageView;

import java.io.ByteArrayOutputStream;


public class AtualizaAnimal extends AppCompatActivity {

    CircleImageView activity_dados_pessoas_avatar;
    String activity_dados_pessoas_fotoEmString = "";
    ImageView activity_dados_pessoas_icone;
    EditText activity_dados_pessoas_editText_nome_recebido,
            activity_dados_pessoas_editText_raca_recebido,
            activity_dados_pessoas_editText_categoria_recebido;

    Button activity_dados_pessoas_botao_atualizar,
            activity_dados_pessoas_botao_excluir,
            activity_dados_pessoas_botao_voltar;

    Spinner activity_dados_pessoas_spinner_categorias_recebido;
    String activity_dados_pessoas_nome_recebido;

    String activity_dados_pessoas_categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atualiza_cadastro_animal);

        // Widgets do primeiro layout (editText´s e o Spinner).

        activity_dados_pessoas_avatar = findViewById(R.id.activity_dados_pessoas_avatar);
        activity_dados_pessoas_editText_nome_recebido = findViewById(R.id.activity_dados_pessoas_editText_nome_recebido);
        activity_dados_pessoas_editText_raca_recebido = findViewById(R.id.activity_dados_pessoas_editText_raca_recebido);
        activity_dados_pessoas_spinner_categorias_recebido = findViewById(R.id.activity_dados_pessoas_spinner_categorias_recebido);

        // Widgets do segundo layout (Botões)
        activity_dados_pessoas_botao_atualizar = findViewById(R.id.activity_dados_pessoas_button_atualizar);
        activity_dados_pessoas_botao_excluir = findViewById(R.id.activity_dados_pessoas_button_excluir);
        activity_dados_pessoas_botao_voltar = findViewById(R.id.activity_dados_pessoas_button_voltar);

        Intent intent = getIntent();


        if (intent.getStringExtra("foto") != null) {
            //Verificação de recebimento vazio ou campo.

            if(intent.getStringExtra("foto").equals("") ||
            intent.getStringExtra("foto").equals("null")){
                activity_dados_pessoas_avatar.setImageResource(android.R.drawable.ic_menu_camera);

            }else{
                byte[] imagemEmBytes;
                imagemEmBytes = Base64.decode(intent.getStringExtra("foto"), Base64.DEFAULT);
                Bitmap imageDecodificada = BitmapFactory.decodeByteArray(imagemEmBytes,0,imagemEmBytes.length);
                activity_dados_pessoas_avatar.setImageBitmap(imageDecodificada);
            }

        }


        if (intent.getStringExtra("categorias").equals("Cachorro")){
            //activity_dados_pessoas_icone.setImageResource(R.drawable.ic_dog);
            activity_dados_pessoas_spinner_categorias_recebido.setSelection(1);
            activity_dados_pessoas_categoria = "Cachorro";
        }else{
            //activity_dados_pessoas_icone.setImageResource(R.drawable.ic_cat);
            activity_dados_pessoas_spinner_categorias_recebido.setSelection(0);
            activity_dados_pessoas_categoria = "Gato";

        }

        activity_dados_pessoas_avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,1);
            }
        });

        activity_dados_pessoas_spinner_categorias_recebido.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(activity_dados_pessoas_spinner_categorias_recebido.getSelectedItem().equals("Gato")){
                    activity_dados_pessoas_categoria = "Gato";
                }else{
                    activity_dados_pessoas_categoria = "Cachorro";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        activity_dados_pessoas_nome_recebido = intent.getStringExtra("nome");

        activity_dados_pessoas_editText_nome_recebido.setText(intent.getStringExtra("nome"));
        activity_dados_pessoas_editText_raca_recebido.setText(intent.getStringExtra("raca"));

        activity_dados_pessoas_botao_atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizaAnimais();

            }
        });

        activity_dados_pessoas_botao_excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder confirmaExclusao = new AlertDialog.Builder(AtualizaAnimal.this);
                confirmaExclusao.setTitle("Aviso!");
                confirmaExclusao.setMessage("Têm certeza que deseja excluir esse cadastro " +
                       activity_dados_pessoas_nome_recebido + " ?" );
                confirmaExclusao.setCancelable(false);
                confirmaExclusao.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        apagaAnimais();
                    }
                });
                confirmaExclusao.setNegativeButton("Não",null);
                confirmaExclusao.create().show();

            }
        });


        activity_dados_pessoas_botao_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void atualizaAnimais( ) {
        DAO dao = new DAO(getApplicationContext());
        Animais animaisParaAtualizar = new Animais();
        animaisParaAtualizar.setNome(activity_dados_pessoas_editText_nome_recebido.getText().toString());
        animaisParaAtualizar.setRaca(activity_dados_pessoas_editText_raca_recebido.getText().toString());
        animaisParaAtualizar.setCategoria(activity_dados_pessoas_categoria);
        animaisParaAtualizar.setFoto(activity_dados_pessoas_fotoEmString);
        dao.insereAnimal(animaisParaAtualizar, activity_dados_pessoas_nome_recebido);
        dao.close();
        finish();
    }

    private void apagaAnimais() {
        DAO dao = new DAO(getApplicationContext());
        dao.apagaAnimais(activity_dados_pessoas_nome_recebido);
        finish();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent dados) {

        super.onActivityResult(requestCode, resultCode, dados);
        if(requestCode == 1) {
            try{
                Bitmap fotoRegistrada = (Bitmap) dados.getExtras().get("data");
                activity_dados_pessoas_avatar.setImageBitmap(fotoRegistrada);

                byte[] fotoEmBytes;
                ByteArrayOutputStream streamDaFotoEmBytes = new ByteArrayOutputStream();

                //Conversão, codificação e transformação em byte 64.

                fotoRegistrada.compress(Bitmap.CompressFormat.PNG, 70, streamDaFotoEmBytes);
                fotoEmBytes = streamDaFotoEmBytes.toByteArray();
                //Armazenamento da foto no banco de dados.
                activity_dados_pessoas_fotoEmString = Base64.encodeToString(fotoEmBytes, Base64.DEFAULT);

            }catch (Exception e ) {

            }

        }
    }
}