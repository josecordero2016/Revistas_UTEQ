package com.e.revistasuteq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.e.revistasuteq.Adaptadores.adtArticulo;
import com.e.revistasuteq.Adaptadores.adtEdiciones;
import com.e.revistasuteq.Adaptadores.adtRevistas;
import com.e.revistasuteq.Certificaciones.SelfSigningClientBuilder;
import com.e.revistasuteq.Interfaces.itfRetrofit;
import com.e.revistasuteq.Modelos.Articulo;
import com.e.revistasuteq.Modelos.Categoria;
import com.e.revistasuteq.Modelos.Edicion;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.e.revistasuteq.Clases.Utilitarios.EDICION_SELECCIONADA;
import static com.e.revistasuteq.Clases.Utilitarios.ID_REVISTA;

public class actCategorias extends AppCompatActivity {

    RecyclerView rclArticulos;
    int intento = 1;
    private actCategorias act = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_categorias);

        Spinner spinner;
        spinner = findViewById(R.id.spCategorias);
        rclArticulos = findViewById(R.id.rclArticulos);



        // Categorias
        Retrofit rf = new Retrofit.Builder()
                .baseUrl("https://revistas.uteq.edu.ec/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(SelfSigningClientBuilder.createClient(this))
                .build();
        itfRetrofit itfRetrofit = rf.create(itfRetrofit.class);
        Call<List<Categoria>> call = itfRetrofit.getCategorias("https://revistas.uteq.edu.ec/ws/pubssections.php?i_id="+EDICION_SELECCIONADA);
        call.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                String cod_respuesta = "Código " + response.code();
                List<Categoria> lista = response.body();
                List<String> l = new ArrayList<String>();
                for(Categoria c: lista) {
                    l.add(c.getSection());
                }
                l.add("Todos");

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(act.getApplicationContext(), android.R.layout.simple_spinner_item,l);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    ///CARGAR ARTICULOS POR CATEGORIA
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                        if(spinner.getSelectedItem().equals("Todos")) CargarTodo();
                        else{
                        Retrofit rf = new Retrofit.Builder()
                                    .baseUrl("https://revistas.uteq.edu.ec/")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .client(SelfSigningClientBuilder.createClient(act.getApplicationContext()))
                                    .build();
                            itfRetrofit itfRetrofit = rf.create(itfRetrofit.class);

                            Call<List<Articulo>> call = itfRetrofit.getArticulos("https://revistas.uteq.edu.ec/ws/pubs.php?i_id=" + EDICION_SELECCIONADA + "&section=" + lista.get(position).getSection_id());
                            call.enqueue(new Callback<List<Articulo>>() {
                                @Override
                                public void onResponse(Call<List<Articulo>> call, Response<List<Articulo>> response) {
                                    String cod_respuesta = "Código " + response.code();
                                    List<Articulo> lista = response.body();
                                    adtArticulo adtArticulo = new adtArticulo(lista, getApplicationContext());
                                    rclArticulos.setAdapter(adtArticulo);
                                }

                                @Override
                                public void onFailure(Call<List<Articulo>> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), "No se ha podido establecer conexión con el servidor" + t.toString(), Toast.LENGTH_LONG).show();
                                }
                            });
}
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                    }

                });
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No se ha podido establecer conexión con el servidor" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
        if(intento==1)
        {
            CargarTodo();
            intento++;
        }
    }

    public void CargarTodo(){
        rclArticulos = (RecyclerView) findViewById(R.id.rclArticulos);
        LinearLayoutManager linear = new LinearLayoutManager(getApplicationContext());
        linear.setOrientation(LinearLayoutManager.VERTICAL);
        rclArticulos.setLayoutManager(linear);

        Retrofit rf = new Retrofit.Builder()
                .baseUrl("https://revistas.uteq.edu.ec/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(SelfSigningClientBuilder.createClient(this))
                .build();
        itfRetrofit itfRetrofit = rf.create(itfRetrofit.class);

        Call<List<Articulo>> call2 = itfRetrofit.getArticulos("https://revistas.uteq.edu.ec/ws/pubs.php?i_id="+EDICION_SELECCIONADA);
        call2.enqueue(new Callback<List<Articulo>>() {
            @Override
            public void onResponse(Call<List<Articulo>> call, Response<List<Articulo>> response) {
                String cod_respuesta = "Código " + response.code();
                List<Articulo> lista = response.body();
                adtArticulo adtArticulo = new adtArticulo(lista, getApplicationContext());
                rclArticulos.setAdapter(adtArticulo);
            }

            @Override
            public void onFailure(Call<List<Articulo>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No se ha podido establecer conexión con el servidor" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}