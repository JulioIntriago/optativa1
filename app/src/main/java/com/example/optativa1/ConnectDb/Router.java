package com.example.optativa1.ConnectDb;


import com.example.optativa1.Models.CreatePublications;
import com.example.optativa1.Models.ListPublications;
import com.example.optativa1.Models.Messages;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Router {

    @GET("publications")
    //obtener todos los datos
    Call<ListPublications> obtenerDatos();

    @POST("publications")
    //crear publicaciones
    Call<Messages> CREATE_PUBLICATIONS_CAL(@Body CreatePublications createPublications);

    @DELETE("publications/{key}")
    Call<Messages> eliminarPublications(@Path("key") String key);

}
