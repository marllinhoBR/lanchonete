package com.example.lanchonete.repositorio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBD extends SQLiteOpenHelper {


    public String tabela = "produto";

    public String id = "_id", nome = "nomeprod", preco = "precoprod", ingre = "ingreprod", teor = "teorc", tipo = "tipoprod";

    public int versao = 1;

    public CriaBD(Context context){
        super(context,"lanche.bd", null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE " + tabela + "(" +
                id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                nome + " VARCHAR(50) NOT NULL, " +
                preco + " DOUBLE NOT NULL, " +
                ingre + " VARCHAR(100) NOT NULL, " +
                teor + " DOUBLE NOT NULL, " +
                tipo + " VARCHAR(50) NOT NULL);" ;
        db.execSQL(sql);

    }

    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        String sql = "DROP TABLE IF EXISTS " + tabela;
        db.execSQL(sql);
        onCreate(db);
    }

}
