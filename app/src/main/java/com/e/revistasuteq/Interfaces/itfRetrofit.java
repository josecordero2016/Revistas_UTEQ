package com.e.revistasuteq.Interfaces;

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

}

