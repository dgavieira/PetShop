package com.projetosuper.petshop.model;

import android.net.Uri;

public class Produto {
    private int prod_id;
    private Uri prod_imageUri;
    private String prod_nome;
    private String prod_descricao;
    private Double prod_preco;

    public Produto(int prod_id, Uri prod_imageUri, String prod_nome, String prod_descricao, Double prod_preco) {
        this.prod_id = prod_id;
        this.prod_imageUri = prod_imageUri;
        this.prod_nome = prod_nome;
        this.prod_descricao = prod_descricao;
        this.prod_preco = prod_preco;
    }

    public Produto(int prod_id, String prod_nome, String prod_descricao, Double prod_preco) {
        this.prod_id = prod_id;
        this.prod_nome = prod_nome;
        this.prod_descricao = prod_descricao;
        this.prod_preco = prod_preco;
    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public Uri getProd_imageUri() {
        return prod_imageUri;
    }

    public void setProd_imageUri(Uri prod_imageUri) {
        this.prod_imageUri = prod_imageUri;
    }

    public String getProd_nome() {
        return prod_nome;
    }

    public void setProd_nome(String prod_nome) {
        this.prod_nome = prod_nome;
    }

    public String getProd_descricao() {
        return prod_descricao;
    }

    public void setProd_descricao(String prod_descricao) {
        this.prod_descricao = prod_descricao;
    }

    public Double getProd_preco() {
        return prod_preco;
    }

    public void setProd_preco(Double prod_preco) {
        this.prod_preco = prod_preco;
    }
}
