package com.projetosuper.petshop.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "PetShop.db";
    Context context = null;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_ON_ACTIVATE_FK);
        db.execSQL(SQL_CREATE_TABLE_CLIENTE);
        db.execSQL(SQL_CREATE_TABLE_PRODUTO);
        db.execSQL(SQL_CREATE_TABLE_COMPRA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_ON_ACTIVATE_FK);
        db.execSQL(SQL_DELETE_TABLE_CLIENTE);
        db.execSQL(SQL_DELETE_TABLE_PRODUTO);
        db.execSQL(SQL_DELETE_TABLE_COMPRA);
        onCreate(db);
    }

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    //ativar chaves estrangeiras
    private static final String SQL_ON_ACTIVATE_FK =
            "PRAGMA foreign_keys = ON";

    //criacao da tabela TABLE CLIENTE
    private static final String SQL_CREATE_TABLE_CLIENTE =
            "CREATE TABLE Cliente ( " +
            "cliente_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
            "cliente_imageURI TEXT, "+
            "cliente_nome TEXT NOT NULL, "+
            "cliente_email TEXT UNIQUE NOT NULL, "+
            "cliente_cpf TEXT UNIQUE NOT NULL, "+
            "cliente_idade INTEGER NOT NULL, "+
            "cliente_telefone TEXT NOT NULL, "+
            "cliente_senha TEXT NOT NULL)";

    //criacao da tabela TABLE PRODUTO
    private static final String SQL_CREATE_TABLE_PRODUTO =
            "CREATE TABLE Produto ( " +
                    "produto_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                    "produto_imageURI TEXT, "+
                    "produto_nome TEXT NOT NULL, "+
                    "produto_descricao TEXT NOT NULL, "+
                    "produto_preco REAL NOT NULL)";

    //criacao da tabela COMPRA
    private static final String SQL_CREATE_TABLE_COMPRA =
            "CREATE TABLE Compra ( "+
                    "compra_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    "cliente_id INTEGER NOT NULL, "+
                    "produto_id INTEGER NOT NULL, "+
                    "qnt INTEGER NOT NULL, "+
                    "FOREIGN KEY (cliente_id) REFERENCES Cliente(cliente_id), "+
                    "FOREIGN KEY (produto_id) REFERENCES Produto(produto_id))";

    //exclusao da tabela TABLE CLIENTE
    private static final String SQL_DELETE_TABLE_CLIENTE =
            "DROP TABLE IF EXISTS Cliente";

    //exclusao da tabela TABLE PRODUTO
    private static final String SQL_DELETE_TABLE_PRODUTO =
            "DROP TABLE IF EXISTS Produto";

    //exclusao da tabela TABLE COMPRA
    private static final String SQL_DELETE_TABLE_COMPRA =
            "DROP TABLE IF EXISTS Compra";

    //Comandos de SQL para Cliente
    protected static final String SQL_SELECT_CLIENTE_BY_ID =
            "SELECT * FROM Cliente WHERE cliente_id=?";

    protected static final String SQL_SELECT_CLIENTE_BY_EMAIL =
            "SELECT * FROM Cliente WHERE cliente_email=?";

    protected static final String SQL_SELECT_ALL_CLIENTE_BY_ID =
            "SELECT * FROM Cliente ORDER BY cliente_nome";

    //Comandos de SQL para Produto
    protected static final String SQL_SELECT_PRODUTO_BY_ID =
            "SELECT * FROM Produto WHERE produto_id=?"; //escolhe só um
    protected static final String SQL_SELECT_ALL_PRODUTO_BY_ID =
            "SELECT * FROM Produto ORDER BY produto_nome"; //escolher tudo e selecionar por produto_nome

    //Comandos de SQL para o Shop
    protected static final String SQL_SELECT_COMPRA_BY_ID =
            "SELECT * FROM Shop WHERE compra_id=?";
    protected static final String SQL_SELECT_ALL_COMPRA_BY_CLIENTE_ID =
            "SELECT * FROM Shop WHERE cliente_id=?";
    protected static final String SQL_JOIN_COMPRA_PRODUTO =
            "SELECT Produto.produto_nome, Produto.produto_preco, Compra.qnt" +
                    " FROM Compra" +
                    " INNER JOIN Produto" +
                    " ON Compra.produto_id=Produto.produto_id" +
                    " WHERE Compra.cliente_id=?" +
                    " ORDER BY Compra.compra_id DESC";
}
