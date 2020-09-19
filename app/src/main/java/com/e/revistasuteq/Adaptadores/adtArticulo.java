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
import com.e.revistasuteq.Modelos.Articulo;
import com.e.revistasuteq.Modelos.Edicion;
import com.e.revistasuteq.R;

import java.util.ArrayList;
import java.util.List;

public class adtArticulo extends RecyclerView.Adapter<adtArticulo.ViewHolder> implements View.OnClickListener{

    private View.OnClickListener listener;
    private List<Articulo> datos;
    Context context;

    public adtArticulo(List<Articulo> datos, Context context){
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
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lyt_articulos, null, false);
        view.setOnClickListener(this);
        return new adtArticulo.ViewHolder(view);
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

        TextView txtTituloA, txtSeccionA, txtDoiA, txtFechaA, txtAutoresA;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTituloA = (TextView) itemView.findViewById(R.id.txtTituloA);
            txtSeccionA = (TextView) itemView.findViewById(R.id.txtSeccionA);
            txtDoiA = (TextView) itemView.findViewById(R.id.txtDoiA);
            txtFechaA = (TextView) itemView.findViewById(R.id.txtFechaA);
            txtAutoresA = (TextView) itemView.findViewById(R.id.txtAutoresA);
        }

        public void asignar_datos(Articulo valor) {
            txtTituloA.setText(valor.getTitle());
            txtSeccionA.setText(valor.getSection());
            txtDoiA.setText(valor.getDoi());
            txtFechaA.setText("Fecha: "+valor.getDate_published());
            List<String> l = new ArrayList<String>();
            for(Articulo.Authors a : valor.getAuthors()){ l.add(a.getNombres()); }
            String autores = "Autores: ";
            for(String s : l){ autores+=s+", ";}
            autores.substring(0,autores.length()-1);
            txtAutoresA.setText(autores);
        }
    }

}
