package com.example.pruebascovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.Gravity;
import android.widget.Toast;

import com.example.pruebascovid.adapters.adapterUsuarios;
import com.example.pruebascovid.pojos.Usuario;
import com.example.pruebascovid.servicios.RetrofitClient;
import com.example.pruebascovid.servicios.responseGeneral;
import com.example.pruebascovid.servicios.responseGeneral;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                }else if(!passConfirm.getText().toString().equalsIgnoreCase(pass.getText().toString())){
                    Toast toast = Toast.makeText(getApplicationContext(),"Las contraseñas no coiciden",Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    Usuario user = new Usuario();
                    user.setUsuario(usuario.getText().toString());
                    user.setPass(pass.getText().toString());
                    user.setNombre(nombre.getText().toString());
                    user.setActivo("1");
                    user.setTipo(tipo.getSelectedItem().toString());
                    user.setApMaterno(apMaterno.getText().toString());
                    user.setApPaterno(apPaterno.getText().toString());
                    registrarPaciente(user);
                    Toast toast = Toast.makeText(getApplicationContext(),"Datos corretos",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    private void registrarPaciente(Usuario user) {

        Call<responseGeneral> call = RetrofitClient.getInstance().getMyApi().registroUsuario(user);

        call.enqueue(new Callback<responseGeneral>() {
            @Override
            public void onResponse(Call<responseGeneral> call, Response<responseGeneral> response) {
                Log.d("Hugo" , "si jalo "+ response.code());
                Log.d("Hugo", response.body().code+"");
                if(response.isSuccessful()){
                 //   Log.d("Hugo", response.body().message);
                    if(response.body().code == 200){
                        Toast.makeText(getApplicationContext(), "Registro exitoso!!", Toast.LENGTH_LONG).show();
                        goToUsuarios();
                    }else{
                        Toast.makeText(getApplicationContext(), "no jaloo", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Algo salio mal", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<responseGeneral> call, Throwable t) {
                Log.d("Hugo" , "error"+t.toString());
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }

    public void goToUsuarios(){

    }


}