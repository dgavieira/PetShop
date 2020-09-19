package com.projetosuper.petshop.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;

public class CompraDAO implements DAO{
    private SQLiteDatabase db;
    public CompraDAO(Context context){
        this.db = (new DatabaseHandler(context)).getWritableDatabase();
    }

    @Override
    public boolean create(Object obj){
        if (obj instanceof Compra){
            ContentValues values = createContentValues(obj);
            if ( db.insert("Compra", null, values) != -1 ){
                //db.close();
                return true;
            } else {
                Log.e("LojaModel","Error ao criar Compra (CompraDAO)");
                //db.close();
                return false;
            }
        }
        return false;
    }
    @Override
    public boolean update(int id, Object obj) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Object get(int id) {
        return false;
    }

    @Override
    public ArrayList<Object> getList(){return null;}

    public ArrayList<Pair<Produto,Integer>> getProdutos(int cliente_id){
        String clauses[] = {Integer.toString(cliente_id)};
        String sql = DatabaseHandler.SQL_JOIN_COMPRA_PRODUTO;
        Cursor cursor = this.db.rawQuery(sql, clauses);

        ArrayList<Pair<Produto,Integer>> produtos = new ArrayList<>();

        //obtem as quantidas dos produtos e a relacao de produtos comprados
        // Obtém os produtos e as quantidades
        if ( cursor.moveToNext() ){
            do {
                produtos.add(new Pair<>(
                        new Produto(
                                cursor.getInt(0),
                                Uri.parse(cursor.getString(2)),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getDouble(5)),
                        cursor.getInt(6)
                ));
            } while( cursor.moveToNext());
        }
        // Retorna os pares <produto,qnt>
        return produtos;
    }

    private ContentValues createContentValues(Object obj){
        Compra compra = (Compra) obj;
        ContentValues values = new ContentValues();
        values.put("cliente_id", compra.getCliente_id());
        values.put("produto_id", compra.getProduto_id());
        values.put("qnt", compra.getQnt());
        return values;
    }
}


