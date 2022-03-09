package com.example.pruebascovid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pruebascovid.pdfGenerator.pdfGenerator;
import com.example.pruebascovid.pojos.Paciente;
import com.example.pruebascovid.pojos.Usuario;

public class MainActivity extends AppCompatActivity {


    Button btnLogin, btnPrueba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btn_ingresar);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        btnPrueba = (Button) findViewById(R.id.btn_pruebapdf);
        btnPrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pruebaPdf();
            }
        });

    }


    public void login(){
        Intent intent = new Intent(this,Usuarios.class);
        startActivity(intent);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void pruebaPdf(){
        /*Toast toast = Toast.makeText(getApplicationContext(),"Funciona",Toast.LENGTH_SHORT);
        toast.show();*/
        Paciente paciente = new Paciente();
        pdfGenerator pdf = new pdfGenerator(paciente,"A quien corresponda","Negativo","No Referido","Serológica","Antígeno","Le pique el cerebro",this);
        pdf.generarPDF();
    }

}