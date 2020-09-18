package com.projetosuper.petshop.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;

public class ClienteDAO implements DAO {
    private SQLiteDatabase db;

    //construtor - conecta ao banco
    public ClienteDAO(Context context){
        this.db = (new DatabaseHandler(context)).getWritableDatabase();
    }

    //cria um novo cliente

    @Override
    public boolean create (Object obj){
        if(obj instanceof Cliente){
            ContentValues values = (ContentValues) createContentValues(obj);
            if(db.insert("Cliente", null, values) != -1){
                //db.close();
                return true;
            }else{
                Log.e("CadastroModel","Erro ao criar cliente (ClienteDAO)");
                //db.close();
                return false;
            }
        }
        return false;
    }
    //atualiza cliente
    @Override
    public boolean update(int id, Object obj) {
        if ( obj instanceof Cliente ){
            Cliente cliente = (Cliente) obj;
            ContentValues values = (ContentValues) createContentValues(obj);
            String[] whereArgs = {String.valueOf(cliente.getCliente_id())};
            if ( db.update("Cliente", values, "id=?", whereArgs) != 0 ){
                //db.close();
                return true;
            } else {
                Log.e("CadastroModel","Error ao atualizar cliente (ClienteDAO)");
                //db.close();
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String[] where = {String.valueOf(id)};
        if ( db.delete("Cliente","id=?",where) != 0 ){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object get(int id) {
        Cliente cliente = null;
        String sql = String.format(DatabaseHandler.SQL_SELECT_CLIENTE_BY_ID);
        String clauses[] = {Integer.toString(id)};
        Cursor cursor = this.db.rawQuery(sql, clauses);
        if ( cursor.moveToNext() ){
            cliente = new Cliente(
                    cursor.getInt(0),
                    //Uri.parse(cursor.getString(1)),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5),
                    cursor.getString(6),
                    cursor.getString(7)
            );
        }
        cursor.close();
        return cliente;
    }

    public Object get(String cliente_email){
        Cliente cliente = null;
        String sql = String.format(DatabaseHandler.SQL_SELECT_CLIENTE_BY_EMAIL);
        String clauses[] = {cliente_email};
        Cursor cursor = this.db.rawQuery(sql, clauses);
        if(cursor.moveToNext()){
            cliente = new Cliente(
                    cursor.getInt(0),
                    //Uri.parse(cursor.getString(1)),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5),
                    cursor.getString(6),
                    cursor.getString(7)
            );
        }
        cursor.close();
        return cliente;
    }

    @Override
    public ArrayList<Object> getList(){
        Cursor cursor = this.db.rawQuery(DatabaseHandler.SQL_SELECT_ALL_CLIENTE_BY_ID, null);
        ArrayList<Object> clientes = new ArrayList<>();
        if(cursor.moveToNext()){
            do{
                clientes.add(
                  new Cliente(
                          cursor.getInt(0),
                          //Uri.parse(cursor.getString(1)),
                          cursor.getString(2),
                          cursor.getString(3),
                          cursor.getString(4),
                          cursor.getInt(5),
                          cursor.getString(6),
                          cursor.getString(7) )
                );
            }while (cursor.moveToNext());
        }
        return clientes;
    }

    private Object createContentValues(Object obj) {
        Cliente cliente = (Cliente) obj;
        ContentValues values = new ContentValues();
        values.put("cliente_id", cliente.getCliente_id());
        //values.put("cliente_imageURI", cliente.getCliente_imageUri().toString());
        values.put("cliente_nome", cliente.getCliente_nome());
        values.put("cliente_email", cliente.getCliente_email());
        values.put("cliente_cpf", cliente.getCliente_cpf());
        values.put("cliente_idade", String.valueOf(cliente.getCliente_idade()));
        values.put("cliente_telefone", cliente.getCliente_telefone());
        values.put("cliente_senha", cliente.getCliente_senha());

        return values;
    }
}
