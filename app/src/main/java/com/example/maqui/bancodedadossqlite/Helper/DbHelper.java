package com.example.maqui.bancodedadossqlite.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION =1;
    public static String NOME_DB="DB_TAREFAS";
    public static String TABELA_TAREFAS= "tarefas";

    public DbHelper( Context context) {
        super( context, NOME_DB, null, VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//ASSIM Q O USUÁRIO INSTALAR O APP É CHAMADO E É ONDE SERÁ CRIADO AS TABELAS

        String sql= "CREATE TABLE IF NOT EXISTS " + TABELA_TAREFAS + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " + " nome TEXT NOT NULL); ";

        try{
            db.execSQL( sql );
            Log.i("INFO DB", "Sucesso ao criar a tabela");
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao criar a tabela " + e.getMessage());
        }
    }

    @Override//É CHAMADO QDO O USUÁRIO JÁ TEM O APP INSTALADO E VAI ATUALIZAR OS DADOS
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //CASO QUERIA EXCLUIR A TABELA
        String sql= "DROP TABLE IF EXISTS " + TABELA_TAREFAS + ";";

        //caso queira alterar a tabela
        //String sql="ALTER TABLE "+ TABELA_TAREFAS +" ADD COLUMN status VARCHAR(2)" ;

        try{
            db.execSQL( sql );
            onCreate( db );
            Log.i("INFO DB", "Sucesso ao atualizar a tabela");
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao criar a tabela " + e.getMessage());
        }

    }
}
