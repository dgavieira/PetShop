package com.projetosuper.petshop.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class ProdutoDAO implements DAO{
    private SQLiteDatabase db;

    public ProdutoDAO(Context context){
        this.db = (new DatabaseHandler(context)).getWritableDatabase();
    }

    @Override
    public boolean create(Object obj) {
        if ( obj instanceof Produto ){
            ContentValues values = createContentValues(obj);
            if ( db.insert("Produto", null, values) != -1 ){
                db.close();
                return true;
            } else {
                Log.e("LojaModel","Error ao criar produto (ProdutoDAO)");
                db.close();
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean update(int id, Object obj) {

        if ( obj instanceof Produto ){
            Produto produto = (Produto) obj;
            ContentValues values = createContentValues(obj);
            String[] whereArgs = {String.valueOf(produto.getProd_id())};
            if ( db.update("Produto", values, "id=?", whereArgs) != 0 ){
                db.close();
                return true;
            } else {
                Log.e("LojaModel","Error ao atualizar produto (ProdutoDAO)");
                db.close();
                return false;
            }
        }
        return false;
    }
    @Override
    public boolean delete(int id) {
        String[] where = {String.valueOf(id)};
        if ( db.delete("Produto","id=?",where) != 0 ){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object get(int id){
        Produto produto = null;
        String sql = String.format(DatabaseHandler.SQL_SELECT_PRODUTO_BY_ID);
        String clauses[] = {Integer.toString(id)};
        Cursor cursor = this.db.rawQuery(sql, clauses);

        if(cursor.moveToNext()){
            produto = new Produto(
                    cursor.getInt(0),
                    //Uri.parse(cursor.getString(1)),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getDouble(4)
            );
        }
        cursor.close();
        return produto;
    }

    @Override
    public ArrayList<Object> getList(){
        Cursor cursor = this.db.rawQuery(DatabaseHandler.SQL_SELECT_ALL_PRODUTO_BY_ID,null);
        ArrayList<Object> produtos = new ArrayList<>();
        if(cursor.moveToNext()){
            do {
                produtos.add(new Produto(
                        cursor.getInt(0),
                        //Uri.parse(cursor.getString(1)),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getDouble(4)
                ));

            }while (cursor.moveToNext());
        }
        return produtos;
    }

    private ContentValues createContentValues(Object obj) {
        Produto produto = (Produto) obj;
        ContentValues values = new ContentValues();
        values.put("produto_id", produto.getProd_id());
        //values.put("produto_imageURI", produto.getProd_imageUri());
        values.put("produto_nome", produto.getProd_nome());
        values.put("produto_descricao", produto.getProd_descricao());
        values.put("produto_preco", produto.getProd_preco());
        return values;
    }
}
