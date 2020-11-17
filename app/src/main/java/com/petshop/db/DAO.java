package com.petshop.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.NoCopySpan;
import android.util.Log;

import com.petshop.model.Animais;

import java.util.ArrayList;
import java.util.List;

//A classe e sua extensão para conexaão com o banco de dados.
public class DAO extends SQLiteOpenHelper {

    //Criação do nome do banco de dados e versão.
    public DAO(Context context) {
        super(context, "banco", null, 8);
    }


    /*Subescrevendo o método de criação do objeto no  banco de dados.
     O método Oncreate cria o banco no momento de execução do aplicativo*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql  = "CREATE TABLE IF NOT EXISTS animais(nome TEXT UNIQUE ," +
                " categoria TEXT ," +
                " foto TEXT ," +
                " raca TEXT );";
        //Executando a ação anterior.
                db.execSQL(sql);
        db.execSQL(sql);
    }

    //Atualização do banco de dados.Usando para atualizar a estrutura pela versão do banco.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS animais;";
        db.execSQL(sql);
        onCreate(db);
    }

    //Criação do método para criar o banco.
    public void insereAnimal(Animais animais, String animaisParaAtualizar) {

        //Gravando no banco de dados.

        SQLiteDatabase db = getWritableDatabase(); //verificar
        ContentValues dados = new ContentValues();

        dados.put("nome", animais.getNome());
        dados.put("raca", animais.getRaca());

        dados.put("categoria", animais.getCategoria());

        if(!animais.getFoto().equals("")){
            dados.put("foto", animais.getFoto());
        }

        if(animaisParaAtualizar != null){
            dados.put("nome", animaisParaAtualizar);
        }else{
            dados.put("nome", animais.getNome());
        }
        try{
            db.insertOrThrow("animais",null, dados);
        }catch (SQLiteConstraintException e){
            dados.put("nome", animais.getNome());
            db.update("animais", dados, "nome = ?", new String[]{animaisParaAtualizar});

        }

    }
    // método de busca no banco.Retornando uma lista de animais.
    public List<Animais> buscaAnimais(){
        //leitura no banco de dados.
        SQLiteDatabase db = getReadableDatabase();
        String sql =  "SELECT * FROM animais;";

        //Buscando diretamente no banco de dados.
        Cursor c = db.rawQuery(sql,null);
        //Adicionando na lista.
        List<Animais> animal = new ArrayList<Animais>();

        //Enquanto tiver dados no banco. executo uma ação.
        while(c.moveToNext()){
            Animais animais = new Animais();
            animais.setNome(c.getString(c.getColumnIndex("nome")));
            animais.setCategoria(c.getString(c.getColumnIndex("categoria")));
            animais.setRaca(c.getString(c.getColumnIndex("raca")));
            animais.setFoto(c.getString(c.getColumnIndex("foto")));
            animal.add(animais);

        }
        return animal;

    }

    public void apagaAnimais(String nome){

        SQLiteDatabase db  = getReadableDatabase();
        String sql =  "DELETE FROM animais WHERE nome = " + "'" + nome + "'";
        db.execSQL(sql);
    }

}
