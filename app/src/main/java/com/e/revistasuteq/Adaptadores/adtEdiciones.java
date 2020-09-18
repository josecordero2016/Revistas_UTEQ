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
import com.e.revistasuteq.Modelos.Edicion;
import com.e.revistasuteq.R;

import java.util.List;

public class adtEdiciones extends RecyclerView.Adapter<adtEdiciones.ViewHolder> implements View.OnClickListener{

    private View.OnClickListener listener;
    private List<Edicion> datos;
    Context context;

    public adtEdiciones(List<Edicion> datos, Context context){
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
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lyt_tarjetas_ediciones, null, false);
        view.setOnClickListener(this);
        return new adtEdiciones.ViewHolder(view);
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

        TextView txtTitulo, txtEdicion, txtFecha;
        ImageView ivPortadaEd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = (TextView) itemView.findViewById(R.id.txtTituloEd);
            txtEdicion = (TextView) itemView.findViewById(R.id.txtEdicionEd);
            txtFecha = (TextView) itemView.findViewById(R.id.txtFechaEd);
            ivPortadaEd = (ImageView) itemView.findViewById(R.id.ivPortadaEd);
        }

        public void asignar_datos(Edicion valor) {
            txtTitulo.setText(valor.getTitle());
            txtEdicion.setText("Edición #"+valor.getVolume());
            txtFecha.setText(valor.getDate_published().substring(0,10));
            GlideApp.with(itemView).load(valor.getCover()).fitCenter().into(ivPortadaEd);
        }
    }

}
