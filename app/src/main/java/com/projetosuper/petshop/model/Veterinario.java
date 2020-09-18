package com.projetosuper.petshop.model;

import java.io.Serializable;

public class Veterinario implements Serializable {
    private int vet_id;
    private String vet_nome;
    private String vet_especialidade;
    private int vet_exp;
    private int vet_idade;
    private String vet_endereco;

    public Veterinario(int vet_id, String vet_nome, String vet_especialidade, int vet_exp, int vet_idade, String vet_endereco) {
        this.vet_id = vet_id;
        this.vet_nome = vet_nome;
        this.vet_especialidade = vet_especialidade;
        this.vet_exp = vet_exp;
        this.vet_idade = vet_idade;
        this.vet_endereco = vet_endereco;
    }

    public Veterinario(String vet_nome, String vet_especialidade, int vet_exp, int vet_idade, String vet_endereco) {
        this.vet_nome = vet_nome;
        this.vet_especialidade = vet_especialidade;
        this.vet_exp = vet_exp;
        this.vet_idade = vet_idade;
        this.vet_endereco = vet_endereco;
    }

    public int getVet_id() {
        return vet_id;
    }

    public void setVet_id(int vet_id) {
        this.vet_id = vet_id;
    }

    public String getVet_nome() {
        return vet_nome;
    }

    public void setVet_nome(String vet_nome) {
        this.vet_nome = vet_nome;
    }

    public String getVet_especialidade() {
        return vet_especialidade;
    }

    public void setVet_especialidade(String vet_especialidade) {
        this.vet_especialidade = vet_especialidade;
    }

    public int getVet_exp() {
        return vet_exp;
    }

    public void setVet_exp(int vet_exp) {
        this.vet_exp = vet_exp;
    }

    public int getVet_idade() {
        return vet_idade;
    }

    public void setVet_idade(int vet_idade) {
        this.vet_idade = vet_idade;
    }

    public String getVet_endereco() {
        return vet_endereco;
    }

    public void setVet_endereco(String vet_endereco) {
        this.vet_endereco = vet_endereco;
    }
}
