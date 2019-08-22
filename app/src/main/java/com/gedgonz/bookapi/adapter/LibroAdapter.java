package com.gedgonz.bookapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gedgonz.bookapi.Model.Libro;
import com.gedgonz.bookapi.R;

import java.util.ArrayList;
import java.util.List;

public class LibroAdapter  extends RecyclerView.Adapter<LibroAdapter.LibroViewHolder>{

    public ArrayList<Libro> dataset;
    public Context context;

    public LibroAdapter(Context context) {
        this.dataset  = new ArrayList<>();
        this.context = context;
    }

    @Override
    public LibroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_book, parent, false);
        LibroAdapter.LibroViewHolder libroViewHolder = new LibroAdapter.LibroViewHolder(view, dataset);
        return libroViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LibroViewHolder holder, int position) {

        final Libro libro =dataset.get(position);
        holder.textTitulo.setText(libro.getTitulo());
        holder.textDescirpcion.setText(libro.getDescripcion());
        holder.txtFecha.setText(libro.getFecha());
        holder.txtAutor.setText(libro.getAutor().nombre+" "+libro.getAutor().apellido);
        Glide.with(context).load(libro.portada).into(holder.pictureBook);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void addicionarLista(List<Libro> libros) {

        dataset.addAll(libros);
        notifyDataSetChanged();
    }

    public static class LibroViewHolder extends RecyclerView.ViewHolder
    {

        TextView textTitulo, textDescirpcion, txtFecha, txtAutor;
        ImageView pictureBook;
        List<Libro> libros;

        public LibroViewHolder(View view, List<Libro> libros) {
            super(view);
            this.textTitulo = (TextView) view.findViewById(R.id.txtTitulo);
            this.textDescirpcion = (TextView) view.findViewById(R.id.txtDescripcion);
            this.txtFecha = (TextView) view.findViewById(R.id.txtFecha);
            this.txtAutor = (TextView) view.findViewById(R.id.txtAutor);
            this.pictureBook = (ImageView) view.findViewById(R.id.PictureBook);
            this.libros = libros;
        }
    }
}
