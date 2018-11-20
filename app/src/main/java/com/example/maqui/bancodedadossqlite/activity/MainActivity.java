package com.example.maqui.bancodedadossqlite.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.example.maqui.bancodedadossqlite.R;
import com.example.maqui.bancodedadossqlite.Helper.RecyclerItemClickListener;
import com.example.maqui.bancodedadossqlite.adapter.TarefaAdapter;
import com.example.maqui.bancodedadossqlite.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TarefaAdapter tarefaAdapter;
    private List<Tarefa> tarefaList= new ArrayList<>( );

    @Override
    protected void onCreate(Bundle savedInstanceState) {//é chamado apenas a primeira vez que é carregado a interface
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );


        recyclerView = findViewById( R.id.recyclerView );

        //adicionar evento de clique
        recyclerView.addOnItemTouchListener( new RecyclerItemClickListener( getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.i("clique","onItemClick");
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Log.i("clique","onLongItemClick");
            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        } ) );

        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( MainActivity.this, AdicionarTarefas.class ) );
            }
        } );
    }

    public void carregarListaTarefaas(){

        //listar tarefas
        Tarefa tarefa= new Tarefa();
        tarefa.setNomeTarefa( "Ir ao mercado" );
        tarefaList.add(tarefa);

        Tarefa tarefa1= new Tarefa();
        tarefa1.setNomeTarefa( "Ir a feira" );
        tarefaList.add(tarefa1);

        //configurar um adapter
        tarefaAdapter= new TarefaAdapter(tarefaList);

        //configurar recyclerview
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager( MainActivity.this );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize( true );
        recyclerView.addItemDecoration( new DividerItemDecoration( MainActivity.this,LinearLayout.VERTICAL ) );
        recyclerView.setAdapter( tarefaAdapter );

    }

    @Override
    protected void onStart() {//é chamado sempre q volta para a main
        carregarListaTarefaas();
        super.onStart();
    }


}
