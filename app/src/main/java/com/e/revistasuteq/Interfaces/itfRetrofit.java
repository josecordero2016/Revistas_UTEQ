package com.e.revistasuteq.Interfaces;

import com.e.revistasuteq.Modelos.Revista;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface itfRetrofit {

    // url del javarest
    @GET("ws/journals.php")
    Call<List<Revista>> getRevistas();

}

