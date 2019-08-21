package com.gedgonz.bookapi.Interface;

import com.gedgonz.bookapi.Model.Libro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IApiBook {

    @GET("api/values")
    Call<List<Libro>> getBookApi();
}
