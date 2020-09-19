package com.e.revistasuteq.Interfaces;

import com.e.revistasuteq.Modelos.Articulo;
import com.e.revistasuteq.Modelos.Categoria;
import com.e.revistasuteq.Modelos.Edicion;
import com.e.revistasuteq.Modelos.Revista;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface itfRetrofit {

    // url del javarest
    @GET("ws/journals.php")
    Call<List<Revista>> getRevistas();

    @GET
    Call<List<Edicion>> getEdiciones(@Url String url);

    @GET
    Call<List<Categoria>> getCategorias(@Url String url);

    @GET
    Call<List<Articulo>> getArticulos(@Url String url);
}

