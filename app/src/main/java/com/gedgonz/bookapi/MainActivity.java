package com.gedgonz.bookapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.gedgonz.bookapi.Interface.IApiBook;
import com.gedgonz.bookapi.Model.Libro;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private String URL="http://192.168.1.141:5000/";
    private static final String TAG= "API";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getBook();



    }

    public void getBook()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IApiBook iApiBook = retrofit.create(IApiBook.class);

        Call<List<Libro>> call = iApiBook.getBookApi();

        call.enqueue(new Callback<List<Libro>>() {
            @Override
            public void onResponse(Call<List<Libro>> call, Response<List<Libro>> response) {
                if(!response.isSuccessful())
                {
                    Log.e(TAG,"Codigo: "+ response.code());
                }

                List<Libro> Libros = response.body();

                for (Libro libo: Libros) {

                    Log.i(TAG,"Titulo: "+ libo.titulo);
                }


            }

            @Override
            public void onFailure(Call<List<Libro>> call, Throwable t) {
                Log.e(TAG,"Error: "+ t.getMessage());
            }
        });


    }
}
