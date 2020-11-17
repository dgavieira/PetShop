package com.petshop.model;

public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private Double preco;
    private byte[] img;

    public Produto(String nome, String descricao, Double preco, byte[] img) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.img = img;
    }

    public Produto(int id, String nome, String descricao, Double preco, byte[] img) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.img = img;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
