package com.example.pruebascovid.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebascovid.R;
import com.example.pruebascovid.Usuarios;
import com.example.pruebascovid.pojos.Usuario;

import java.util.ArrayList;

public class adapterUsuarios extends RecyclerView.Adapter<adapterUsuarios.ViewHolder> {
private ArrayList<Usuario> localDataSet;
/**
 * Provide a reference to the type of views that you are using
 * (custom ViewHolder).
 */
public static class ViewHolder extends RecyclerView.ViewHolder {
    private final TextView nombre;
    private final TextView tipo;
    private final ImageView eliminar;

    public ViewHolder(View view) {
        super(view);
        // Define click listener for the ViewHolder's View

       nombre = (TextView) view.findViewById(R.id.nombre_usuario_item);
       tipo = (TextView) view.findViewById(R.id.tipo_usuario_item);
       eliminar = (ImageView) view.findViewById(R.id.eliminar_usuario_item);

    }



    public TextView getNombre() {
        return nombre;
    }

    public TextView getTipo() {
        return tipo;
    }

    public ImageView getEliminar() {
        return eliminar;
    }
}

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public adapterUsuarios(ArrayList dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
      View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_usuarios, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getNombre().setText(localDataSet.get(position).getNombre()+" "+localDataSet.get(position).getApPaterno()+" "+localDataSet.get(position).getApMaterno());
        viewHolder.getTipo().setText(localDataSet.get(position).getTipo());
        viewHolder.getEliminar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo aqui poner la logica de eliminar
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}