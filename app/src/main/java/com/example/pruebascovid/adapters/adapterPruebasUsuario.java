package com.example.pruebascovid.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebascovid.MainActivity;
import com.example.pruebascovid.R;
import com.example.pruebascovid.modificacionPrueba;
import com.example.pruebascovid.pojos.Usuario;
import com.example.pruebascovid.pojos.pruebaUsuario;
import com.example.pruebascovid.servicios.RetrofitClient;
import com.example.pruebascovid.servicios.responseGeneral;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class adapterPruebasUsuario extends RecyclerView.Adapter<adapterPruebasUsuario.ViewHolder>  {

    private ArrayList<pruebaUsuario> localDataSet;
    private Context contexto;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nombre;
        private final TextView resultado;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            nombre = (TextView) view.findViewById(R.id.nombre_paciente_prueba);
            resultado = (TextView) view.findViewById(R.id.estatus_prueba);

        }



        public TextView getNombre() {
            return nombre;
        }

        public TextView getResultado() {
            return resultado;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public adapterPruebasUsuario(ArrayList dataSet, Context context) {
        localDataSet = dataSet;
        contexto = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public adapterPruebasUsuario.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_prueba_usuario, viewGroup, false);

        return new adapterPruebasUsuario.ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(adapterPruebasUsuario.ViewHolder viewHolder, final int position) {


        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getNombre().setText(localDataSet.get(position).getNombre()+" "+localDataSet.get(position).getApPaterno()+" "+localDataSet.get(position).getApMaterno());
        viewHolder.getResultado().setText(localDataSet.get(position).getResultado());

        int i = position;
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    goToModificaPrueba(localDataSet.get(i).getIdPrueba(),localDataSet.get(i).getNombre(),localDataSet.get(i).getApPaterno(), localDataSet.get(i).getApMaterno(), localDataSet.get(i).getFechaNacimiento());

            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }






    private void goToModificaPrueba(int idPrueba,String nombre, String apPaterno, String apMaterno, String fechaNacimiento){
        Intent intent = new Intent(contexto, modificacionPrueba.class);
        intent.putExtra("idPrueba", idPrueba);
        intent.putExtra("nombre_paciente", nombre);
        intent.putExtra("ap_paterno", apPaterno);
        intent.putExtra("ap_materno", apMaterno);
        intent.putExtra("fecha_nacimiento", fechaNacimiento);
        Log.d("Hugo", fechaNacimiento);
        contexto.startActivity(intent);
        ((Activity) contexto).finish();
    }
}
