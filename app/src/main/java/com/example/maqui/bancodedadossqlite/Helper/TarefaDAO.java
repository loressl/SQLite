package com.example.maqui.bancodedadossqlite.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.maqui.bancodedadossqlite.model.Tarefa;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

//vai ser responsavel por salvar os dados no bd
public class TarefaDAO implements ITarefaDAO{

    private SQLiteDatabase escreve;
    private SQLiteDatabase ler;

    public TarefaDAO(Context context) {
        DbHelper db= new DbHelper( context );
        escreve= db.getWritableDatabase();//utiliza o metodo para recupera os dados do bd
        ler= db.getReadableDatabase();// || ler os dados do bd
    }

    @Override
    public boolean salvar(Tarefa tarefa) {
        ContentValues cv= new ContentValues(  );
        cv.put( "nome", tarefa.getNomeTarefa() );

        try{
            escreve.insert(DbHelper.TABELA_TAREFAS, null,cv);
            Log.e("INFO", "Tarefa salva com sucesso");
        }catch (Exception e){
            Log.e("INFO", "Erro ao salvar tarefa" + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {

        ContentValues cv= new ContentValues(  );
        cv.put("nome",tarefa.getNomeTarefa());

        try{
            String[] args={tarefa.getId().toString()};//se tivesse alguma condição como id=? AND status =? ... ai no args poderia colcoar {"5", "E"}, coloca os paradmetros q será usado
            escreve.update( DbHelper.TABELA_TAREFAS,cv,"id=?", args );//coloca ?  pois n sabe qual exatamente será, então o valor ao lado dirá, ai terá q instanciar um array de string

            Log.e("INFO", "Tarefa atualizada com sucesso");
        }catch (Exception e){
            Log.e("INFO", "Erro ao atualizadar tarefa" + e.getMessage());
            return false;
        }

        return true;

    }

    @Override
    public boolean deletar(Tarefa tarefa) {

        try{
            String[] args={tarefa.getId().toString()};
            escreve.delete( DbHelper.TABELA_TAREFAS,"id=?", args);
            Log.e("INFO", "Tarefa removida com sucesso");
        }catch (Exception e){
            Log.e("INFO", "Erro ao remover tarefa" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Tarefa> listar() {

        List<Tarefa> tarefas= new ArrayList<>(  );

        String sql= "SELECT * FROM "+ DbHelper.TABELA_TAREFAS + " ;";
        Cursor c= ler.rawQuery( sql, null );//no selectionArgs poderia passar os filtros


        while(c.moveToNext()){

            Tarefa tarefa= new Tarefa();

            Long id= c.getLong( c.getColumnIndex( "id" ) );
            String nomeTarefa= c.getString( c.getColumnIndex( "nome" ) );

            tarefa.setId(id);
            tarefa.setNomeTarefa( nomeTarefa );

            tarefas.add( tarefa );
        }

        return tarefas;
    }
}
