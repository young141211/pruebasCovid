package com.example.pruebascovid.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebascovid.R;
import com.example.pruebascovid.Usuarios;
import com.example.pruebascovid.pojos.Usuario;
import com.example.pruebascovid.servicios.RetrofitClient;
import com.example.pruebascovid.servicios.responseGeneral;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class adapterUsuarios extends RecyclerView.Adapter<adapterUsuarios.ViewHolder> {
private ArrayList<Usuario> localDataSet;
private Context contexto;

/**
 * Provide a reference to the type of views that you are using
 * (custom ViewHolder).
 */
public static class ViewHolder extends RecyclerView.ViewHolder {
    private final TextView nombre;
    private final TextView tipo;
    private final Switch eliminar;

    public ViewHolder(View view) {
        super(view);
        // Define click listener for the ViewHolder's View

       nombre = (TextView) view.findViewById(R.id.nombre_usuario_item);
       tipo = (TextView) view.findViewById(R.id.tipo_usuario_item);
       eliminar = (Switch) view.findViewById(R.id.eliminar_usuario_item);

    }



    public TextView getNombre() {
        return nombre;
    }

    public TextView getTipo() {
        return tipo;
    }

    public Switch getEliminar() {
        return eliminar;
    }
}

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public adapterUsuarios(ArrayList dataSet, Context context) {
        localDataSet = dataSet;
        contexto = context;
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
        if(localDataSet.get(position).getActivo().equalsIgnoreCase("000")){
            viewHolder.getEliminar().setChecked(false);
        }else{
            viewHolder.getEliminar().setChecked(true);
        }
        int i = position ;
        viewHolder.getEliminar().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d("hugo", "cambio: "+b+" "+localDataSet.get(i).getNombre()+" "+localDataSet.get(i).getTokenession());
                if(b){
                    //activar
                    activarUsuario(localDataSet.get(i));
                }else{
                    //desactivar

                    desactivarUSuario(localDataSet.get(i));
                }
            }
        });

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



    public void activarUsuario(Usuario user){
        Call<responseGeneral> call = RetrofitClient.getInstance().getMyApi().activarUsuario(user);

        call.enqueue(new Callback<responseGeneral>() {
            @Override
            public void onResponse(Call<responseGeneral> call, Response<responseGeneral> response) {
                Log.d("Hugo" , "si jalo "+ response.code());
                Log.d("Hugo", response.body().code+"");
                if(response.isSuccessful()){
                    //   Log.d("Hugo", response.body().message);
                    if(response.body().code == 200){
                        Toast.makeText(contexto.getApplicationContext(), "Registro exitoso!!", Toast.LENGTH_LONG).show();

                    }else{
                        Toast.makeText(contexto.getApplicationContext(), "no jaloo", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(contexto.getApplicationContext(), "Algo salio mal", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<responseGeneral> call, Throwable t) {
                Log.d("Hugo" , "error"+t.toString());
                Toast.makeText(contexto.getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }

    public void desactivarUSuario(Usuario user){
        Call<responseGeneral> call = RetrofitClient.getInstance().getMyApi().desactivarUsuario(user);

        call.enqueue(new Callback<responseGeneral>() {
            @Override
            public void onResponse(Call<responseGeneral> call, Response<responseGeneral> response) {
                Log.d("Hugo" , "si jalo "+ response.code());
                Log.d("Hugo", response.body().code+"");
                if(response.isSuccessful()){
                    //   Log.d("Hugo", response.body().message);
                    if(response.body().code == 200){
                        Toast.makeText(contexto.getApplicationContext(), "Registro exitoso!!", Toast.LENGTH_LONG).show();

                    }else{
                        Toast.makeText(contexto.getApplicationContext(), "no jaloo", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(contexto.getApplicationContext(), "Algo salio mal", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<responseGeneral> call, Throwable t) {
                Log.d("Hugo" , "error"+t.toString());
                Toast.makeText(contexto.getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }
}