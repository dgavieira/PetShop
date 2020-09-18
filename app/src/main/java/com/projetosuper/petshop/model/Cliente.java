package com.projetosuper.petshop.model;

import android.net.Uri;

import java.io.Serializable;

public class Cliente implements Serializable {
    private int id_cliente;
    private String nome_cliente;
    private String email_cliente;
    private String cpf_cliente;
    private int idade_cliente;
    private String telefone_cliente;
    private Uri imageUri_cliente;
    private String senha_cliente;

    public Cliente(int id_cliente, String nome_cliente, String email_cliente, String cpf_cliente,
                   int idade_cliente, String telefone_cliente, Uri imageUri_cliente) {
        this.id_cliente = id_cliente;
        this.nome_cliente = nome_cliente;
        this.email_cliente = email_cliente;
        this.cpf_cliente = cpf_cliente;
        this.idade_cliente = idade_cliente;
        this.telefone_cliente = telefone_cliente;
        this.imageUri_cliente = imageUri_cliente;
    }

    public Cliente(int id_cliente, String nome_cliente, String email_cliente, String cpf_cliente,
                   int idade_cliente, String telefone_cliente, Uri imageUri_cliente,
                   String senha_cliente) {
        this.id_cliente = id_cliente;
        this.nome_cliente = nome_cliente;
        this.email_cliente = email_cliente;
        this.cpf_cliente = cpf_cliente;
        this.idade_cliente = idade_cliente;
        this.telefone_cliente = telefone_cliente;
        this.imageUri_cliente = imageUri_cliente;
        this.senha_cliente = senha_cliente;
    }

    public Cliente(String nome_cliente, String email_cliente, String cpf_cliente, int idade_cliente,
                   String telefone_cliente, Uri imageUri_cliente) {
        this.nome_cliente = nome_cliente;
        this.email_cliente = email_cliente;
        this.cpf_cliente = cpf_cliente;
        this.idade_cliente = idade_cliente;
        this.telefone_cliente = telefone_cliente;
        this.imageUri_cliente = imageUri_cliente;
    }

    public Cliente(String nome_cliente, String email_cliente, String cpf_cliente, int idade_cliente,
                   String telefone_cliente, Uri imageUri_cliente, String senha_cliente) {
        this.nome_cliente = nome_cliente;
        this.email_cliente = email_cliente;
        this.cpf_cliente = cpf_cliente;
        this.idade_cliente = idade_cliente;
        this.telefone_cliente = telefone_cliente;
        this.imageUri_cliente = imageUri_cliente;
        this.senha_cliente = senha_cliente;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getEmail_cliente() {
        return email_cliente;
    }

    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }

    public String getCpf_cliente() {
        return cpf_cliente;
    }

    public void setCpf_cliente(String cpf_cliente) {
        this.cpf_cliente = cpf_cliente;
    }

    public int getIdade_cliente() {
        return idade_cliente;
    }

    public void setIdade_cliente(int idade_cliente) {
        this.idade_cliente = idade_cliente;
    }

    public String getTelefone_cliente() {
        return telefone_cliente;
    }

    public void setTelefone_cliente(String telefone_cliente) {
        this.telefone_cliente = telefone_cliente;
    }

    public Uri getImageUri_cliente() {
        return imageUri_cliente;
    }

    public void setImageUri_cliente(Uri imageUri_cliente) {
        this.imageUri_cliente = imageUri_cliente;
    }

    public String getSenha_cliente() {
        return senha_cliente;
    }

    public void setSenha_cliente(String senha_cliente) {
        this.senha_cliente = senha_cliente;
    }
}
