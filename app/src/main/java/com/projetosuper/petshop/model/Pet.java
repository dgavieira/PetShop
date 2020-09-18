package com.projetosuper.petshop.model;

import android.net.Uri;

import java.io.Serializable;

public class Pet implements Serializable {
    private int pet_id;
    private String pet_nome;
    private String pet_categoria;
    private String pet_raca;
    private Uri pet_imageUri;

    public Pet(int pet_id, String pet_nome, String pet_categoria, String pet_raca, Uri pet_imageUri) {
        this.pet_id = pet_id;
        this.pet_nome = pet_nome;
        this.pet_categoria = pet_categoria;
        this.pet_raca = pet_raca;
        this.pet_imageUri = pet_imageUri;
    }

    public Pet(String pet_nome, String pet_categoria, String pet_raca, Uri pet_imageUri) {
        this.pet_nome = pet_nome;
        this.pet_categoria = pet_categoria;
        this.pet_raca = pet_raca;
        this.pet_imageUri = pet_imageUri;
    }

    public int getPet_id() {
        return pet_id;
    }

    public void setPet_id(int pet_id) {
        this.pet_id = pet_id;
    }

    public String getPet_nome() {
        return pet_nome;
    }

    public void setPet_nome(String pet_nome) {
        this.pet_nome = pet_nome;
    }

    public String getPet_categoria() {
        return pet_categoria;
    }

    public void setPet_categoria(String pet_categoria) {
        this.pet_categoria = pet_categoria;
    }

    public String getPet_raca() {
        return pet_raca;
    }

    public void setPet_raca(String pet_raca) {
        this.pet_raca = pet_raca;
    }

    public Uri getPet_imageUri() {
        return pet_imageUri;
    }

    public void setPet_imageUri(Uri pet_imageUri) {
        this.pet_imageUri = pet_imageUri;
    }
}
