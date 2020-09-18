package com.projetosuper.petshop.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PetShop.db";
    Context context = null;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_CLIENTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE_CLIENTE);
        onCreate(db);
    }

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    //criacao da tabela TABLE CLIENTE
    private static final String SQL_CREATE_TABLE_CLIENTE =
            "CREATE TABLE Cliente (" +
            "cliente_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
            "cliente_imageURI TEXT, "+
            "cliente_nome TEXT NOT NULL, "+
            "cliente_email TEXT UNIQUE NOT NULL, "+
            "cliente_cpf TEXT UNIQUE NOT NULL, "+
            "cliente_idade INTEGER NOT NULL, "+
            "cliente_telefone TEXT NOT NULL, "+
            "cliente_senha TEXT NOT NULL)";

    //exclusao da tabela TABLE CLIENTE
    private static final String SQL_DELETE_TABLE_CLIENTE =
            "DROP TABLE IF EXISTS Cliente";

    //Comandos de SQL para Cliente
    protected static final String SQL_SELECT_CLIENTE_BY_ID =
            "SELECT * FROM Cliente WHERE cliente_id=?";

    protected static final String SQL_SELECT_CLIENTE_BY_EMAIL =
            "SELECT * FROM Cliente WHERE cliente_email=?";

    protected static final String SQL_SELECT_ALL_CLIENTE_BY_ID =
            "SELECT * FROM Cliente ORDER BY cliente_nome";
}
