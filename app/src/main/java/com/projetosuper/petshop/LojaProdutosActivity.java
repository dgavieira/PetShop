package com.projetosuper.petshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.projetosuper.petshop.model.Cliente;
import com.projetosuper.petshop.model.Compra;
import com.projetosuper.petshop.model.CompraDAO;
import com.projetosuper.petshop.model.Produto;
import com.projetosuper.petshop.model.ProdutoDAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class LojaProdutosActivity extends AppCompatActivity {

    MaterialButton btn_comprar;
    ListView lista_compra;
    TextView valor_total;
    HashMap<Integer, Integer> itens_comprados;
    Cliente cliente;
    ArrayList<Object> produtos;
    ProdutosAdapter produtosAdapter;
    ItensCompradosAdapter itensCompradosAdapter;
    BottomNavigationView menu;
    double valor_total_compra = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loja_produtos);

        //Recupera cliente
        /*cliente = (Cliente) getIntent().getSerializableExtra("Cliente");
        this.setTitle("Seja bem vindo, " + cliente.getCliente_nome() + "!");*/

        //Associa views a objetos da tela por findviewbyid
        btn_comprar = findViewById(R.id.btnComprar);
        lista_compra = findViewById(R.id.listShop);
        valor_total = findViewById(R.id.txtValorTotal);

        produtos = new ProdutoDAO(LojaProdutosActivity.this).getList();
        itens_comprados = new HashMap<>();
        produtosAdapter = new ProdutosAdapter(LojaProdutosActivity.this, R.layout.produto_celula);
        produtosAdapter.addAll(produtos);

        //verifica item comprado
        produtosAdapter.setOnCheckBoxCLickListener(new ProdutosAdapter.OnCheckBoxCLickListener() {
            @Override
            public void onCheckBoxClickListener(boolean isChecked, int position, int qnt) {
                Produto produto = (Produto) produtosAdapter.getItem(position);
                if (isChecked) {
                    itens_comprados.put(produto.getProd_id(), qnt);
                } else {
                    itens_comprados.remove(produto.getProd_id());
                }
                valor_total.setText(String.format("R$ %.2f", somaPrecos()));
            }
        });

        //verifica quantidade alterada
        produtosAdapter.setOnSpinnerItemSelectedListener(new ProdutosAdapter.OnSpinnerItemSelectedListener() {
            @Override
            public void onSpinnerItemSelectedListener(boolean isChecked, int position, int qnt) {
                Produto produto = (Produto) produtosAdapter.getItem(position);
                if (isChecked) {
                    itens_comprados.put(produto.getProd_id(), qnt);
                    valor_total.setText(String.format("R$ .2f", somaPrecos()));
                }
            }
        });
        lista_compra.setAdapter(produtosAdapter);

        menu = findViewById(R.id.bottom_navigation);
        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.shopping:
                        btn_comprar.setVisibility(View.VISIBLE);
                        valor_total.setVisibility(View.VISIBLE);
                        lista_compra.setAdapter(produtosAdapter);
                        break;
                    case R.id.pedidos:
                        atualizarPedidos();
                        btn_comprar.setVisibility(View.INVISIBLE);
                        valor_total.setVisibility(View.INVISIBLE);
                        lista_compra.setAdapter(itensCompradosAdapter);
                        break;
                }
                return true;
            }
        });
    }

    public void atualizarPedidos() {
        ArrayList<Pair<Produto, Integer>> pedidos = new CompraDAO(this).getProdutos(cliente.getCliente_id());
        itensCompradosAdapter = new ItensCompradosAdapter(this, R.layout.produtos_comprados_celula);
        itensCompradosAdapter.addAll(pedidos);
    }

    private double somaPrecos() {
        double resultado = 0;

        for (Object object : produtos) {
            Produto produto = (Produto) object;
            if (itens_comprados.containsKey(produto.getProd_id())) {
                resultado += itens_comprados.get(produto.getProd_id()) * produto.getProd_preco();
            }
        }
        return resultado;
    }

    public void efetuarCompra(View view) {
        if (itens_comprados.isEmpty()) {
            Toast.makeText(this, "Nenhum item foi selecionado.", Toast.LENGTH_SHORT).show();
            return;
        }

        //gera um novo objeto compraDAO com os produtos da compra
        valor_total_compra = somaPrecos(); //obtendo o valor total da compra
        CompraDAO compraDAO = new CompraDAO(LojaProdutosActivity.this);
        for (Integer idProduto : itens_comprados.keySet()) {
            Compra compra = (Compra) new Compra(cliente.getCliente_id(), idProduto, itens_comprados.get(idProduto));
            compraDAO.create(compra);
            Bundle extras = new Bundle(); //setando um bundle para passar multiparametros via intent
            extras.putDouble("VALOR_TOTAL_COMPRA", valor_total_compra);
            extras.putSerializable("Compra", compra);
        }
        //faz compra
        lista_compra.setAdapter(produtosAdapter);
        itens_comprados.clear();
        valor_total.setText(String.format("R$ %.2f", somaPrecos()));

        //vai para cartão de crédito checkout

        Intent go_checkout = new Intent(getApplicationContext(), CheckoutActivity.class);
        startActivity(go_checkout);
    }
}