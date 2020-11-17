package com.petshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.petshop.adapter.ItensCompradosAdapter;
import com.petshop.db.ProdutoDAO;
import com.petshop.db.ShopDAO;
import com.petshop.model.Usuario;

import java.util.ArrayList;

public class ProdutosComprados extends AppCompatActivity {

    private Intent intent;
    private static Usuario user;
    private ShopDAO shopDAO;
    private ProdutoDAO productDAO;
    private ListView listItemsComprado;
    private SimpleCursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos_comprados);

        this.shopDAO = new ShopDAO(this);

        intent = getIntent();
        //retorna o usuario atual, passado pela intent anterior
        user = (Usuario) intent.getSerializableExtra("Usuario");

        String[] nome = user.getNome().split(" ");

        listItemsComprado = findViewById(R.id.recyclerViewProdutoComprados);
        ItensCompradosAdapter adapterItemComprados = new ItensCompradosAdapter(ProdutosComprados.this, R.layout.itens_comprados_list);

        //retorna uma lista (do banco de dados SQL) com todos os itens comprados pelo usuário atual
        ArrayList itensComprados = shopDAO.getProdutos(user.getId());

        //add cada objeto do tipo ShopItemComprado, da lista, no adapter customizado
        for(int i = 0; i < itensComprados.size(); i++){
            adapterItemComprados.add(itensComprados.get(i)); //add into adapter an object returned from the SQL
        }

        listItemsComprado.setAdapter(adapterItemComprados);

    }
}