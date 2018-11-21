package com.example.maqui.bancodedadossqlite.activity;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.maqui.bancodedadossqlite.Helper.TarefaDAO;
import com.example.maqui.bancodedadossqlite.R;
import com.example.maqui.bancodedadossqlite.model.Tarefa;

public class AdicionarTarefas extends AppCompatActivity {

    private TextInputEditText editText;
    private Tarefa tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_adicionar_tarefas );

        editText= findViewById( R.id.edit_tarefa );

        //recuperar tarefa, caso seja edição
        tarefaAtual= (Tarefa)getIntent().getSerializableExtra( "tarefaSelecionada" );

        //Configurar tarefa na caixa de texto
        if(tarefaAtual!=null){
            editText.setText( tarefaAtual.getNomeTarefa() );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.menu_main, menu );//retorna o objeto q permite inflar o menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id==R.id.itemSalvar){

            TarefaDAO tarefaDAO= new TarefaDAO( AdicionarTarefas.this );

            if(tarefaAtual!=null){//edição
                String nomeTarefa= editText.getText().toString();
                if(!nomeTarefa.isEmpty()){
                    Tarefa tarefa= new Tarefa();
                    tarefa.setNomeTarefa( nomeTarefa );
                    tarefa.setId( tarefaAtual.getId() );

                    //atualizar no banco de dados
                    if(tarefaDAO.atualizar( tarefa )){
                        finish();
                        Toast.makeText( getApplicationContext(),"Sucesso ao salvar tarefa", Toast.LENGTH_SHORT );
                    }else{
                        Toast.makeText( getApplicationContext(),"Erro ao salvar tarefa", Toast.LENGTH_SHORT );
                    }
                }


            }else{//salvar

                String nomeTarefa= editText.getText().toString();

                if(!nomeTarefa.isEmpty()){
                    Tarefa tarefa= new Tarefa();
                    tarefa.setNomeTarefa( nomeTarefa );
                    if(tarefaDAO.salvar( tarefa )){
                        finish();
                        Toast.makeText( getApplicationContext(),"Sucesso ao salvar tarefa", Toast.LENGTH_SHORT );
                    }else{
                        Toast.makeText( getApplicationContext(),"Erro ao salvar tarefa", Toast.LENGTH_SHORT );
                    }
                    finish();
                }
            }

        }

        return super.onOptionsItemSelected( item );
    }
}
