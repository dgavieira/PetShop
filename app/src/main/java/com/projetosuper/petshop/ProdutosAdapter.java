package com.projetosuper.petshop;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.projetosuper.petshop.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutosAdapter extends ArrayAdapter {
    //chamando objetos da interface
    private OnCheckBoxCLickListener onCheckBoxCLickListener;
    private OnSpinnerItemSelectedListener onSpinnerItemSelectedListener;

    //construtores
    public void setOnCheckBoxCLickListener(OnCheckBoxCLickListener onCheckBoxCLickListener){
        this.onCheckBoxCLickListener = onCheckBoxCLickListener;
    }

    public void setOnSpinnerItemSelectedListener(OnSpinnerItemSelectedListener onSpinnerItemSelectedListener){
        this.onSpinnerItemSelectedListener = onSpinnerItemSelectedListener;
    }
    //construtor principal da classe
    public ProdutosAdapter(@NonNull Context context, int resource){
        super(context, resource);
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent){
        View minhaView = convertView;
        final ProdutoView produtoView;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            minhaView = inflater.inflate(R.layout.produto_celula, parent, false);

            //Criação de um Produto View
            produtoView = new ProdutoView();
            produtoView.custo = minhaView.findViewById(R.id.txtCusto);
            produtoView.img = minhaView.findViewById(R.id.imgProduto);
            produtoView.nome = minhaView.findViewById(R.id.txtItemNome);
            produtoView.spnQnt = minhaView.findViewById(R.id.spnItemQnt);
            produtoView.card = minhaView.findViewById(R.id.cardItem);
            produtoView.detalhes = minhaView.findViewById(R.id.btnDetalhes);

            minhaView.setTag(produtoView);
        }else{
            produtoView = (ProdutoView) minhaView.getTag();
        }
        //String de seleção de quantidades - 1 a 50
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 51; i++){
            list.add(i);
        }

        //Recupera o item do List View na posição correspondente

        final Produto produto = (Produto) this.getItem(position);
        produtoView.custo.setText(String.format("R$ %.2f", produto.getProd_preco()));
        //produtoView.img.setImagem lalalala
        produtoView.nome.setText(produto.getProd_nome());
        produtoView.spnQnt.setAdapter(new ArrayAdapter<Integer>(getContext(), android.R.layout.simple_dropdown_item_1line, list));

        //Chama onSpinnerItemSelectedListener
        produtoView.spnQnt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                onSpinnerItemSelectedListener.onSpinnerItemSelectedListener(produtoView.card.isChecked(),position,
                        (int) produtoView.spnQnt.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //Chama onCheckBoxClickListener
        produtoView.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                produtoView.card.toggle();
                onCheckBoxCLickListener.onCheckBoxClickListener(
                        produtoView.card.isChecked(), position, (int) produtoView.spnQnt.getSelectedItem()
                );
            }
        });
        produtoView.detalhes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog builder = new Dialog(getContext());
                builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
                builder.getWindow().setBackgroundDrawable(
                        new ColorDrawable(Color.TRANSPARENT));
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        //nothing;
                    }
                });

                View detalhesView;
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                detalhesView = inflater.inflate(R.layout.produto_detalhes,
                        parent,
                        false);
                ProdutoDetalhesView produtoDetalhesV = new ProdutoDetalhesView();
                produtoDetalhesV.card = detalhesView.findViewById(R.id.cardDetalhes);
                produtoDetalhesV.descricao = detalhesView.findViewById(R.id.txtDetalhes);
                produtoDetalhesV.nome = detalhesView.findViewById(R.id.txtDetalhesNome);
                produtoDetalhesV.img = detalhesView.findViewById(R.id.imgDetalhes);
                detalhesView.setTag(produtoDetalhesV);
                produtoDetalhesV.descricao.setText(produto.getProd_descricao());
                produtoDetalhesV.nome.setText(produto.getProd_nome());
                //imagem produtoDetalhesV.img.setImageBitmap(DbBitmapUtility.getImage(p.getImg()));

                // Cria Layout
                // Adiciona no card

                // Adiciona card View ao dialog
                builder.addContentView(detalhesView, new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                builder.show();
            }
        });

        return minhaView;
    }


    //criando interfaces que serão utilizadas
    //baseado na solução desenvolvida pelo Guilherme no projeto da Mini Loja
    public interface OnCheckBoxCLickListener{
        void onCheckBoxClickListener(boolean isChecked, int position, int qnt);
    }
    public interface OnSpinnerItemSelectedListener{
        void onSpinnerItemSelectedListener(boolean isChecked, int position, int qnt);
    }
}
