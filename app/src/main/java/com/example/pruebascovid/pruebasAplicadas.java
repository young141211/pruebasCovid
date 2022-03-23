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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pruebascovid.adapters.adapterPruebasUsuario;
import com.example.pruebascovid.adapters.adapterUsuarios;
import com.example.pruebascovid.databinding.ActivityModificacionPruebaBinding;
import com.example.pruebascovid.pojos.Usuario;
import com.example.pruebascovid.pojos.pruebaUsuario;
import com.example.pruebascovid.servicios.RetrofitClient;
import com.example.pruebascovid.servicios.responseGetPruebas;
import com.example.pruebascovid.servicios.responseGetUsuarios;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pruebasAplicadas extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Usuario userLogin;
    TextView nombreUser;
    TextView tipoUsuario;

    FloatingActionButton addUsuarios;
    RecyclerView listaPruebas;
    adapterPruebasUsuario adapterLista;
    ArrayList<pruebaUsuario> arrayPruebas;

    SharedPreferences sharedPref;
    DrawerLayout drawerLayout;

    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebas_aplicadas);

        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String json = sharedPref.getString(getResources().getString(R.string.str_session), "");

        if (json.isEmpty()) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            userLogin = new Gson().fromJson(json, Usuario.class);
        }

        contexto = this;
        /*************/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_pruebas);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_pruebas);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.empty, R.string.empty);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.navigation_view_pruebas);
        navigationView.setNavigationItemSelectedListener(this);


        View header = navigationView.getHeaderView(0);
        nombreUser = (TextView) header.findViewById(R.id.nombre_user_nav);
        tipoUsuario = (TextView) header.findViewById(R.id.tipo_usuario_nav);
        nombreUser.setText(userLogin.getNombreCompleto());
        tipoUsuario.setText(userLogin.getTipo());
        navigationView.getMenu().getItem(0).setVisible(false);
        navigationView.getMenu().getItem(1).setVisible(false);
        navigationView.getMenu().getItem(2).setVisible(false);


        /*************/


        listaPruebas = findViewById(R.id.lista_pruebas);
        listaPruebas.setLayoutManager(new LinearLayoutManager(this));




        arrayPruebas = new ArrayList<>();
        adapterLista = new adapterPruebasUsuario(arrayPruebas, this);
        listaPruebas.setAdapter(adapterLista);



        addUsuarios = (FloatingActionButton) findViewById(R.id.fab_add_prueba);
        addUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevaPrueba();
            }
        });




        getPruebas();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.close_sesion:
                //close session
                cerraSession();
                break;
        }

        return false;
    }


    public void nuevaPrueba(){
        Intent intent = new Intent(this, registroPaciente.class);
        startActivity(intent);
        finish();
    }



    private void getPruebas() {

        arrayPruebas = new ArrayList<pruebaUsuario>();
        Call<responseGetPruebas> call = RetrofitClient.getInstance().getMyApi().getPruebas(userLogin);
        call.enqueue(new Callback<responseGetPruebas>() {
            @Override
            public void onResponse(Call<responseGetPruebas> call, Response<responseGetPruebas> response) {
                Log.d("Hugo" , "si jalo "+ response.isSuccessful());
                Log.d("Hugo", response.body().code+"");
                if(response.isSuccessful()){
                    if(response.body().code == 200){
                        for(int i = 0 ; i < response.body().getData().size(); i++){
                            //Usuario user = new Usuario();
                            //user.setNombre(response.body().getData().get(i).getNombre());
                            Log.d("Hugo", response.body().getData().get(i).getFechaNacimiento());
                            arrayPruebas.add(response.body().getData().get(i));
                        }
                        adapterLista = new adapterPruebasUsuario(arrayPruebas, contexto);
                        listaPruebas.setAdapter(adapterLista);
                        Log.d("Hugo" , response.body().getData().size()+"");
                    }
                }else{

                }
            }

            @Override
            public void onFailure(Call<responseGetPruebas> call, Throwable t) {
                Log.d("Hugo" , "error"+t.toString());
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }

    private void cerraSession(){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();

    }
}