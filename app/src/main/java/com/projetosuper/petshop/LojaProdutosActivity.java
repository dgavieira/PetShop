package com.projetosuper.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.projetosuper.petshop.model.Cliente;

import java.util.ArrayList;
import java.util.HashMap;

public class LojaProdutosActivity extends AppCompatActivity {

    MaterialButton btn_comprar;
    ListView lista_compra;
    TextView valor_total;
    HashMap<Integer,Integer> itens_comprados;
    Cliente cliente;
    ArrayList<Object> produtos;
    ProdutosAdapter produtosAdapter;
    ItensCompradosAdapter itensCompradosAdapter;
    BottomNavigationView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loja_produtos);
    }

    //Recupera cliente
    /*cliente = (Cliente) getIntent().getSerializableExtra("Cliente");
    this.setTitle("Seja bem vindo, " + cliente.getCliente_nome() + "!");*/
}