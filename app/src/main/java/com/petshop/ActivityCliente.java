package com.petshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.joaquimley.faboptions.FabOptions;
import com.petshop.adapter.ItensCompradosAdapter;
import com.petshop.db.ProdutoDAO;
import com.petshop.db.ShopDAO;
import com.petshop.model.Usuario;

import java.util.ArrayList;

public class ActivityCliente extends AppCompatActivity implements  View.OnClickListener {

    Toolbar mToolbar;
    private TextView textViewName, textViewEmail;
    FabOptions mFabOptions;
    private Intent intent;
    private static Usuario usuario;
    private ShopDAO shopDAO;
    private ProdutoDAO productDAO;
    private ListView listItemsComprado;
    private SimpleCursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        this.shopDAO = new ShopDAO(this);

        intent = getIntent();
        //retorna o usuario atual, passado pela intent anterior
        usuario = (Usuario) intent.getSerializableExtra("Usuario");

        this.setTitle("Seja bem vindo, " + usuario.getNome() + "!");

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("PetShop");
        setSupportActionBar(mToolbar);

        textViewName = (TextView) findViewById(R.id.nome);
        textViewName.setText(usuario.getNome());

        textViewEmail = (TextView) findViewById(R.id.email);
        textViewEmail.setText(usuario.getEmail());

        mFabOptions = findViewById(R.id.fab_options);
        mFabOptions.setOnClickListener(this);

        String[] nome = usuario.getNome().split("");

        listItemsComprado = findViewById(R.id.recyclerViewProdutoComprados);
        ItensCompradosAdapter adapterItemComprados = new ItensCompradosAdapter(ActivityCliente.this, R.layout.itens_comprados_list);

        //retorna uma lista (do banco de dados SQL) com todos os itens comprados pelo usuário atual
        ArrayList itensComprados = shopDAO.getProdutos(usuario.getId());

        //add cada objeto do tipo ShopItemComprado, da lista, no adapter customizado
        for(int i = 0; i < itensComprados.size(); i++){
            adapterItemComprados.add(itensComprados.get(i)); //add into adapter an object returned from the SQL
        }

        listItemsComprado.setAdapter(adapterItemComprados);

    }

    @Override
    public void onClick(View view) {
        Usuario usuario = (Usuario) getIntent().getSerializableExtra("Usuario");
        switch (view.getId()) {
            case R.id.comprar_produto:
                Intent intent = new Intent(ActivityCliente.this, ActivityShop.class);
                intent.putExtra("Usuario", usuario);
                startActivity(intent);
                break;

            case R.id.contratar_veterinario:
                Intent intent2 = new Intent(ActivityCliente.this, ProdutosComprados.class);
                intent2.putExtra("Usuario", usuario);
                startActivity(intent2);
                break;

            case R.id.cadastrar_pet:
                startActivity(new Intent(ActivityCliente.this, CadastroAnimal.class));
                break;

            case R.id.editar_dados:
                //startActivity(new Intent(ActivityCliente.this, CadastroAnimal.class));
                break;

            default:
        }

    }
}