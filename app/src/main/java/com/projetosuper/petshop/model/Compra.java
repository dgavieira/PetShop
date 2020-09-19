package com.projetosuper.petshop.model;

public class Compra {
    private int compra_id;
    private int cliente_id;
    private int produto_id;
    private int qnt;

    //construtor pra todas as variáveis
    public Compra(int compra_id, int cliente_id, int produto_id, int qnt) {
        this.compra_id = compra_id;
        this.cliente_id = cliente_id;
        this.produto_id = produto_id;
        this.qnt = qnt;
    }

    //construtor para compra individual
    public Compra(int cliente_id, int produto_id, int qnt) {
        this.cliente_id = cliente_id;
        this.produto_id = produto_id;
        this.qnt = qnt;
    }

    public int getCompra_id() {
        return compra_id;
    }

    public void setCompra_id(int compra_id) {
        this.compra_id = compra_id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(int produto_id) {
        this.produto_id = produto_id;
    }

    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }
}
