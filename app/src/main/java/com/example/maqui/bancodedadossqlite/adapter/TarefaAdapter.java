package com.example.maqui.bancodedadossqlite.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maqui.bancodedadossqlite.R;
import com.example.maqui.bancodedadossqlite.model.Tarefa;

import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.MyViewHolder> {

    private List<Tarefa> list;


    public TarefaAdapter(List<Tarefa> list) {
        this.list= list;
    }

    @Override//retorna a lista
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        View itemLista= LayoutInflater.from( parent.getContext() ).inflate( R.layout.lista_tarefa_adapter, parent,false );

        return new MyViewHolder( itemLista );
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {

        Tarefa tarefa= list.get( i );

        myViewHolder.tarefa.setText( tarefa.getNomeTarefa() );
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tarefa;

        public MyViewHolder(@NonNull View itemView) {
            super( itemView );

            tarefa=itemView.findViewById( R.id.textTarefa );
        }
    }
}
