package com.example.lanchonete.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;

public class bdController {
    public SQLiteDatabase db;
    public CriaBD bancoDados;

    public bdController(Context context){bancoDados = new CriaBD(context);}

    public String inserirDados(String nome, Double preco, String ingre, Double teor, String tipo){
        ContentValues values;
        long result;

        db = bancoDados.getWritableDatabase();

        values = new ContentValues();
        values.put(bancoDados.nome, nome);
        values.put(bancoDados.preco, preco);
        values.put(bancoDados.ingre, ingre);
        values.put(bancoDados.teor, teor);
        values.put(bancoDados.tipo, tipo);

        result = db.insert(bancoDados.tabela, null, values);

        if(result == -1){
            return "ERRO AO CADASTRAR!";

        }else {
            return String.format("lanche cadastrado", result);
        }
    }

    public Cursor listarDados() {
        Cursor cursor;
        String[] campos = {bancoDados.id,bancoDados.nome, bancoDados.preco, bancoDados.ingre, bancoDados.teor, bancoDados.tipo};

        db = bancoDados.getReadableDatabase();
        cursor = db.query(bancoDados.tabela, campos, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

        public Cursor carregarCampos(int id) {
            Cursor cursor;

            String[] campos = {bancoDados.id,bancoDados.nome, bancoDados.preco, bancoDados.ingre, bancoDados.teor, bancoDados.tipo};
            String where = bancoDados.id + "=" + id;
            db = bancoDados.getReadableDatabase();
            cursor = db.query(bancoDados.tabela, campos, where, null, null, null, null);

            if(cursor != null){
                cursor.moveToFirst();
            }
            db.close();
            return cursor;
    }

        public void atualizar(int id, Editable nome, double preco, String ingre, double teor, String tipo){
        ContentValues valores;
        String where;

        db = bancoDados.getWritableDatabase();

        where = bancoDados.id + "=" + id;

           valores = new ContentValues();
            valores.put(bancoDados.nome, String.valueOf(nome));
            valores.put(bancoDados.preco, preco);
            valores.put(bancoDados.ingre, String.valueOf(ingre));
            valores.put(bancoDados.teor, teor);
            valores.put(bancoDados.tipo, String.valueOf(tipo));

            db.update(bancoDados.tabela, valores, where, null);
            db.close();


        }


        public void deletar(int id){
        String where = bancoDados.id + "=" + id;
        db = bancoDados.getReadableDatabase();
        db.delete(bancoDados.tabela, where, null);
        db.close();
        }

}
