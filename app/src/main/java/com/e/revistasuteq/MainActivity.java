package com.e.revistasuteq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.e.revistasuteq.Adaptadores.adtRevistas;
import com.e.revistasuteq.Certificaciones.SelfSigningClientBuilder;
import com.e.revistasuteq.Interfaces.itfRetrofit;
import com.e.revistasuteq.Modelos.Revista;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView rclRevistas;
    TextView txtDesc, txtDesc2, txtDescripcion;
    ImageView ivDesc, ivDesc2;

    private static OkHttpClient.Builder httpClientBuilder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        txtDesc = findViewById(R.id.txtDesc);
        txtDesc2 = findViewById(R.id.txtDesc2);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        ivDesc = findViewById(R.id.ivDesc);
        ivDesc2 = findViewById(R.id.ivDesc2);
        txtDesc2.setVisibility(View.GONE);
        ivDesc2.setVisibility(View.GONE);


        rclRevistas = (RecyclerView) findViewById(R.id.rclRevistas);
        LinearLayoutManager linear = new LinearLayoutManager(getApplicationContext());
        linear.setOrientation(LinearLayoutManager.HORIZONTAL);
        rclRevistas.setLayoutManager(linear);



        Retrofit rf = new Retrofit.Builder()
                .baseUrl("https://revistas.uteq.edu.ec/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(SelfSigningClientBuilder.createClient(this))
                .build();
        itfRetrofit itfRetrofit = rf.create(itfRetrofit.class);
        Call<List<Revista>> call = itfRetrofit.getRevistas();
        call.enqueue(new Callback<List<Revista>>() {
            @Override
            public void onResponse(Call<List<Revista>> call, Response<List<Revista>> response) {
                String cod_respuesta = "Código " + response.code();
                List<Revista> lista = response.body();
                adtRevistas adtRevistas = new adtRevistas(lista, getApplicationContext());
                rclRevistas.setAdapter(adtRevistas);
                adtRevistas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int posicion=rclRevistas.getChildAdapterPosition(view);
                        //Toast.makeText(getApplicationContext(),"item:"+ posicion,Toast.LENGTH_SHORT).show();

                        txtDesc2.setVisibility(View.VISIBLE);
                        ivDesc2.setVisibility(View.VISIBLE);
                        txtDescripcion.setVisibility(View.VISIBLE);

                        String descripcion = lista.get(posicion).getDescription().replaceAll("\\<.*?\\>", "");
                        txtDescripcion.setText(descripcion);
                        txtDesc.setVisibility(View.GONE);
                        ivDesc.setVisibility(View.GONE);

                    }
                });
            }

            @Override
            public void onFailure(Call<List<Revista>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No se ha podido establecer conexión con el servidor" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }


}