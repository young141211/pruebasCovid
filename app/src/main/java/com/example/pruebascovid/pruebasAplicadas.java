package com.example.pruebascovid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.pruebascovid.adapters.adapterUsuarios;
import com.example.pruebascovid.pojos.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;

public class pruebasAplicadas extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Usuario userLogin;
    TextView nombreUser;
    TextView tipoUsuario;

    FloatingActionButton addUsuarios;
    RecyclerView listaUsuarios;
    adapterUsuarios adapterLista;
    ArrayList<Usuario> arrayUsuarios;

    SharedPreferences sharedPref;
    DrawerLayout drawerLayout;

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
        //navigationView.getMenu().getItem(0).setVisible(false);
        /*************/



        addUsuarios = (FloatingActionButton) findViewById(R.id.fab_add_prueba);
        addUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevaPrueba();
            }
        });



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        return false;
    }


    public void nuevaPrueba(){
        Intent intent = new Intent(this, registroPaciente.class);
        startActivity(intent);
        finish();
    }
}