package com.example.pruebascovid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.service.controls.templates.TemperatureControlTemplate;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pruebascovid.adapters.adapterUsuarios;
import com.example.pruebascovid.pojos.Usuario;
import com.example.pruebascovid.servicios.RetrofitClient;
import com.example.pruebascovid.servicios.responseGetUsuarios;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.w3c.dom.Text;


import java.util.ArrayList;
import java.util.List;
import java.util.function.ToLongBiFunction;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Usuarios extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    Usuario userLogin;
    TextView nombreUser;
    TextView tipoUsuario;


    FloatingActionButton addUsuarios;
    RecyclerView listaUsuarios;
    adapterUsuarios adapterLista;
    ArrayList<Usuario> arrayUsuarios;
    SharedPreferences sharedPref;
    DrawerLayout drawerLayout;
    private Context contexto ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        //declarando el sp
        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String json = sharedPref.getString(getResources().getString(R.string.str_session), "");

        if (json.isEmpty()) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            userLogin = new Gson().fromJson(json, Usuario.class);
        }


        /*************/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_menu);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.empty, R.string.empty);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        nombreUser = (TextView) header.findViewById(R.id.nombre_user_nav);
        tipoUsuario = (TextView) header.findViewById(R.id.tipo_usuario_nav);


        nombreUser.setText(userLogin.getNombreCompleto());
        tipoUsuario.setText(userLogin.getTipo());
        /*************/
        listaUsuarios = findViewById(R.id.lista_usuarios);
        listaUsuarios.setLayoutManager(new LinearLayoutManager(this));

        contexto = this;
        arrayUsuarios = new ArrayList<>();
        adapterLista = new adapterUsuarios(arrayUsuarios, this);
        listaUsuarios.setAdapter(adapterLista);
        /*for(int i =  0; i < 5 ; i++){
            arrayUsuarios.add(new Usuario());
        }*/
        adapterLista.notifyDataSetChanged();
        addUsuarios = (FloatingActionButton) findViewById(R.id.fab_add_user);
        addUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                altaUsuario();
            }
        });

        getUsuarios();
    }


    public void altaUsuario(){
        Intent intent = new Intent(this,altaUsuario.class);
        startActivity(intent);
    }


    private void getUsuarios() {

        arrayUsuarios = new ArrayList<Usuario>();
        Call<responseGetUsuarios> call = RetrofitClient.getInstance().getMyApi().getUsuarios();
        call.enqueue(new Callback<responseGetUsuarios>() {
            @Override
            public void onResponse(Call<responseGetUsuarios> call, Response<responseGetUsuarios> response) {
                Log.d("Hugo" , "si jalo "+ response.isSuccessful());
                Log.d("Hugo", response.body().code+"");
                if(response.isSuccessful()){
                    if(response.body().code == 200){
                        for(int i = 0 ; i < response.body().getData().size(); i++){
                            Log.d("Hugo",response.body().getData().get(i).getNombre());
                            Log.d("Hugo",response.body().getData().get(i).getActivo());
                            Log.d("Hugo",response.body().getData().get(i).getTokenession());
                            //Usuario user = new Usuario();
                            //user.setNombre(response.body().getData().get(i).getNombre());
                            arrayUsuarios.add(response.body().getData().get(i));
                        }
                        adapterLista = new adapterUsuarios(arrayUsuarios, contexto);
                        listaUsuarios.setAdapter(adapterLista);
                        Log.d("Hugo" , response.body().getData().size()+"");
                    }
                }else{

                }
            }

            @Override
            public void onFailure(Call<responseGetUsuarios> call, Throwable t) {
                Log.d("Hugo" , "error"+t.toString());
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {



        switch (item.getItemId()) {
            case R.id.close_sesion:
                //close session
                cerrarSesion();
                break;
        }


        return false;
    }


    private void cerrarSesion(){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}