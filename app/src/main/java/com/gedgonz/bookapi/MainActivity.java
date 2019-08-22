package com.gedgonz.bookapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.gedgonz.bookapi.Interface.IApiBook;
import com.gedgonz.bookapi.Model.Libro;
import com.gedgonz.bookapi.adapter.LibroAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private String URL="http://192.168.1.141:5000/";
    private static final String TAG= "API";

    private RecyclerView recyclerView;
    private LibroAdapter libroAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_libros);
        libroAdapter= new LibroAdapter(this);
        recyclerView.setAdapter(libroAdapter);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);



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

                ArrayList<Libro> Libros =(ArrayList<Libro>)response.body();
                //ArrayList<Libro> ListaLibros = Libros.
                libroAdapter.addicionarLista(Libros);

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
