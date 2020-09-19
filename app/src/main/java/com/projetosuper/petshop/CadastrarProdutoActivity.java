package com.projetosuper.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.projetosuper.petshop.model.Produto;
import com.projetosuper.petshop.model.ProdutoDAO;

public class CadastrarProdutoActivity extends AppCompatActivity {
    ImageView img_produto;
    EditText edt_produto_nome, edt_produto_descricao, edt_produto_valor;
    Button btn_produto_camera, btn_produto_foto;
    Button btn_produto_cadastrar, btn_produto_cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produto);

        btn_produto_camera = findViewById(R.id.btn_produto_camera);
        btn_produto_foto = findViewById(R.id.btn_produto_foto);

        img_produto = findViewById(R.id.img_produto);

        edt_produto_nome = findViewById(R.id.edt_produto_nome);
        edt_produto_descricao = findViewById(R.id.edt_produto_descricao);
        edt_produto_valor = findViewById(R.id.edt_produto_valor);

        btn_produto_cadastrar = findViewById(R.id.btn_produto_cadastrar);
        btn_produto_cancelar = findViewById(R.id.btn_produto_cancelar);
    }

    public void cadastrar_produto(View view){
        String produto_nome = edt_produto_nome.getText().toString();
        String produto_descricao = edt_produto_descricao.getText().toString();
        String produto_valor = edt_produto_valor.getText().toString();

        //Passando preço para Double
        Double produto_valor_double = Double.parseDouble(produto_valor);

        //Verifica se todos os campos estão preenchidos
        if(produto_nome.isEmpty() || produto_descricao.isEmpty() || produto_valor.isEmpty()){
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }
        //Cria um produto a partir dos campos
        Produto produto = new Produto(produto_nome,produto_descricao,produto_valor_double);

        //Cria o DAO que irá salvar o produto no banco de dados
        ProdutoDAO produtoDAO = new ProdutoDAO(this);
        if(!produtoDAO.create(produto)){
            Toast.makeText(this, "Erro no cadastro do produto", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
            Intent go_to_main_admin = new Intent(getApplicationContext(), MainAdminActivity.class);
            startActivity(go_to_main_admin);
            finish();
        }
    }
}