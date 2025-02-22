package com.e.revistasuteq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.e.revistasuteq.Adaptadores.adtEdiciones;
import com.e.revistasuteq.Certificaciones.SelfSigningClientBuilder;
import com.e.revistasuteq.Interfaces.itfRetrofit;
import com.e.revistasuteq.Modelos.Edicion;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.e.revistasuteq.Clases.Utilitarios.EDICION_SELECCIONADA;
import static com.e.revistasuteq.Clases.Utilitarios.ID_REVISTA;
import static com.e.revistasuteq.Clases.Utilitarios.LIBRO_SELECCIONADO;

public class actEdiciones extends AppCompatActivity {

    TextView txtLibroSelec;
    RecyclerView rclEdiciones;
    actEdiciones act = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_ediciones);

        txtLibroSelec = findViewById(R.id.txtLibroSelec);
        rclEdiciones = findViewById(R.id.rclArticulos);
        txtLibroSelec.setText(LIBRO_SELECCIONADO);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        rclEdiciones.setLayoutManager(layoutManager);



        Retrofit rf = new Retrofit.Builder()
                .baseUrl("https://revistas.uteq.edu.ec/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(SelfSigningClientBuilder.createClient(this))
                .build();
        itfRetrofit itfRetrofit = rf.create(itfRetrofit.class);
        Call<List<Edicion>> call = itfRetrofit.getEdiciones("https://revistas.uteq.edu.ec/ws/issues.php?j_id="+ID_REVISTA);
        call.enqueue(new Callback<List<Edicion>>() {
            @Override
            public void onResponse(Call<List<Edicion>> call, Response<List<Edicion>> response) {
                String cod_respuesta = "Código " + response.code();
                List<Edicion> lista = response.body();

                adtEdiciones adtEdiciones = new adtEdiciones(lista, getApplicationContext());
                rclEdiciones.setAdapter(adtEdiciones);
                adtEdiciones.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int posicion=rclEdiciones.getChildAdapterPosition(view);
                        try{
                        EDICION_SELECCIONADA = Integer.toString(lista.get(posicion).getIssue_id());
                       // Toast.makeText(act.getApplicationContext(),EDICION_SELECCIONADA, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(act.getApplicationContext(), actCategorias.class);
                        startActivity(intent);}catch (Exception e){

                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Edicion>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No se ha podido establecer conexión con el servidor" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
