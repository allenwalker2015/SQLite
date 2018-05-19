package com.sama.sqlite;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private View v;
    private List<Estudiante> lista;

    public Adapter(List<Estudiante> lista) {
        this.lista = lista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            holder.nombre.setText(lista.get(position).getNombre());
            holder.carnet.setText(lista.get(position).getCarnet());
            holder.nota.setText(lista.get(position).getNota());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView carnet,nombre,nota;
        public ViewHolder(View itemView) {
            super(itemView);
            carnet = itemView.findViewById(R.id.carnet);
            nombre = itemView.findViewById(R.id.nombre);
            nota = itemView.findViewById(R.id.nota);
        }
    }
}
