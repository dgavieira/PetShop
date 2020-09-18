package com.projetosuper.petshop.model;

import android.net.Uri;

import java.io.Serializable;

public class Cliente implements Serializable {
    private int cliente_id;
    private Uri cliente_imageUri;
    private String cliente_nome;
    private String cliente_email;
    private String cliente_cpf;
    private int cliente_idade;
    private String cliente_telefone;
    private String cliente_senha;

    public Cliente(int cliente_id, Uri cliente_imageUri, String cliente_nome, String cliente_email,
                   String cliente_cpf, int cliente_idade, String cliente_telefone,
                   String cliente_senha) {
        this.cliente_id = cliente_id;
        this.cliente_imageUri = cliente_imageUri;
        this.cliente_nome = cliente_nome;
        this.cliente_email = cliente_email;
        this.cliente_cpf = cliente_cpf;
        this.cliente_idade = cliente_idade;
        this.cliente_telefone = cliente_telefone;
        this.cliente_senha = cliente_senha;
    }

    public Cliente(String cliente_nome, String cliente_email,
                   String cliente_cpf, int cliente_idade, String cliente_telefone,
                   String cliente_senha) {
        this.cliente_nome = cliente_nome;
        this.cliente_email = cliente_email;
        this.cliente_cpf = cliente_cpf;
        this.cliente_idade = cliente_idade;
        this.cliente_telefone = cliente_telefone;
        this.cliente_senha = cliente_senha;
    }

    public Cliente(int cliente_id, String cliente_nome, String cliente_email, String cliente_cpf,
                   int cliente_idade, String cliente_telefone, String cliente_senha) {
        this.cliente_id = cliente_id;
        this.cliente_nome = cliente_nome;
        this.cliente_email = cliente_email;
        this.cliente_cpf = cliente_cpf;
        this.cliente_idade = cliente_idade;
        this.cliente_telefone = cliente_telefone;
        this.cliente_senha = cliente_senha;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public Uri getCliente_imageUri() {
        return cliente_imageUri;
    }

    public void setCliente_imageUri(Uri cliente_imageUri) {
        this.cliente_imageUri = cliente_imageUri;
    }

    public String getCliente_nome() {
        return cliente_nome;
    }

    public void setCliente_nome(String cliente_nome) {
        this.cliente_nome = cliente_nome;
    }

    public String getCliente_email() {
        return cliente_email;
    }

    public void setCliente_email(String cliente_email) {
        this.cliente_email = cliente_email;
    }

    public String getCliente_cpf() {
        return cliente_cpf;
    }

    public void setCliente_cpf(String cliente_cpf) {
        this.cliente_cpf = cliente_cpf;
    }

    public int getCliente_idade() {
        return cliente_idade;
    }

    public void setCliente_idade(int cliente_idade) {
        this.cliente_idade = cliente_idade;
    }

    public String getCliente_telefone() {
        return cliente_telefone;
    }

    public void setCliente_telefone(String cliente_telefone) {
        this.cliente_telefone = cliente_telefone;
    }

    public String getCliente_senha() {
        return cliente_senha;
    }

    public void setCliente_senha(String cliente_senha) {
        this.cliente_senha = cliente_senha;
    }
}
