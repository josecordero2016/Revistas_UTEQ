package com.e.revistasuteq.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.revistasuteq.Certificaciones.GlideApp;
import com.e.revistasuteq.Modelos.Revista;
import com.e.revistasuteq.R;

import java.util.List;

public class adtRevistas extends RecyclerView.Adapter<adtRevistas.ViewHolder> implements View.OnClickListener{

    private View.OnClickListener listener;
    private List<Revista> datos;
    Context context;

    public adtRevistas(List<Revista> datos, Context context){
        this.datos =datos;
        this.context = context;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lyt_tarjetas, null, false);
        view.setOnClickListener(this);
        return new adtRevistas.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            holder.asignar_datos(datos.get(position));
        } catch (Exception e)
        {
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitulo;
        ImageView ivPortada;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = (TextView) itemView.findViewById(R.id.txtTitulo);
            ivPortada = (ImageView) itemView.findViewById(R.id.ivPortada);
        }

        public void asignar_datos(Revista valor) {
            txtTitulo.setText(valor.getName());
            GlideApp.with(itemView).load(valor.getPortada()).fitCenter().into(ivPortada);
        }
    }

}
