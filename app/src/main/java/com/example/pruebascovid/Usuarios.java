package com.example.pruebascovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pruebascovid.adapters.adapterUsuarios;
import com.example.pruebascovid.pojos.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Usuarios extends AppCompatActivity {


    FloatingActionButton addUsuarios;
    RecyclerView listaUsuarios;
    adapterUsuarios adapterLista;
    ArrayList<Usuario> arrayUsuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        listaUsuarios = findViewById(R.id.lista_usuarios);
        listaUsuarios.setLayoutManager(new LinearLayoutManager(this));


        arrayUsuarios = new ArrayList<>();
        adapterLista = new adapterUsuarios(arrayUsuarios);
        listaUsuarios.setAdapter(adapterLista);
        for(int i =  0; i < 5 ; i++){
            arrayUsuarios.add(new Usuario());
        }
        adapterLista.notifyDataSetChanged();
        addUsuarios = (FloatingActionButton) findViewById(R.id.fab_add_user);
        addUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                altaUsuario();
            }
        });
    }


    public void altaUsuario(){
        Intent intent = new Intent(this,altaUsuario.class);
        startActivity(intent);

    }
}