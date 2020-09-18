package com.projetosuper.petshop.model;

import android.net.Uri;

import java.io.Serializable;

public class Produto implements Serializable {
    private int id_prod;
    private Uri imageUri_prod;
    private String nome;
    private String descricao;
    private Double preco;

    public Produto(int id_prod, Uri imageUri_prod, String nome, String descricao, Double preco) {
        this.id_prod = id_prod;
        this.imageUri_prod = imageUri_prod;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Produto(Uri imageUri_prod, String nome, String descricao, Double preco) {
        this.imageUri_prod = imageUri_prod;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public Uri getImageUri_prod() {
        return imageUri_prod;
    }

    public void setImageUri_prod(Uri imageUri_prod) {
        this.imageUri_prod = imageUri_prod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
