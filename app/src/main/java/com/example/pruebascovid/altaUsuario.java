package com.example.pruebascovid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.Gravity;
import android.widget.Toast;

public class altaUsuario extends AppCompatActivity {

    TextView titulo;
    EditText nombre,apPaterno,apMaterno,usuario,pass,passConfirm;
    Spinner tipo;
    Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_usuario2);

        titulo = (TextView) findViewById(R.id.titulo_registroUsuario);
        nombre = (EditText) findViewById(R.id.usuario_nombre);
        apPaterno = (EditText) findViewById(R.id.usuario_apPaterno);
        apMaterno = (EditText) findViewById(R.id.usuario_apMaterno);
        tipo = (Spinner) findViewById(R.id.usuario_tipo);
        usuario = (EditText) findViewById(R.id.usuario_usuario);
        pass = (EditText) findViewById(R.id.usuario_pass);
        passConfirm = (EditText) findViewById(R.id.usuario_passConfirm);
        registrar = (Button) findViewById(R.id.btn_registrar);

        registrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(nombre.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(),"Nombre es campo requerido",Toast.LENGTH_LONG);
                    toast.show();
                }else if(apPaterno.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(),"Apellido Paterno es campo requerido",Toast.LENGTH_LONG);
                    toast.show();
                }else if(apMaterno.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(),"Apellido Materno es campo requerido",Toast.LENGTH_LONG);
                    toast.show();
                }else if(tipo.getSelectedItem().toString().equalsIgnoreCase(getResources().getStringArray(R.array.tipo_array)[0])){
                    Toast toast = Toast.makeText(getApplicationContext(),"Tipo es campo requerido",Toast.LENGTH_LONG);
                    toast.show();
                }else if(usuario.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(),"Usuario es campo requerido",Toast.LENGTH_LONG);
                    toast.show();
                }else if(pass.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(),"Contraseña es campo requerido",Toast.LENGTH_LONG);
                    toast.show();
                }else if(passConfirm.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(),"Confirmar Contraseña es campo requerido",Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),"Datos corretos",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });


    }
}