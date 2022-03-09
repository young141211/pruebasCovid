package com.example.pruebascovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pruebascovid.pojos.Usuario;
import com.example.pruebascovid.servicios.RetrofitClient;
import com.example.pruebascovid.servicios.responseGeneral;
import com.example.pruebascovid.servicios.responseLogin;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    Button btnLogin;
    EditText usuario;
    EditText pass;
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);


        usuario = (EditText) findViewById(R.id.user_login);
        pass =  (EditText) findViewById(R.id.pass_login);

        btnLogin = (Button) findViewById(R.id.btn_ingresar);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }


    public void login(){
        if(usuario.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Ingresa el usuario", Toast.LENGTH_LONG).show();
        }else if(usuario.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Ingresa la contraseña", Toast.LENGTH_LONG).show();
        }else {
            Usuario user = new Usuario();
            user.setUsuario(usuario.getText().toString());
            user.setPass(pass.getText().toString());
            Log.d("Hugo", user.getUsuario()+" "+user.getPass());
            Call<responseLogin> call = RetrofitClient.getInstance().getMyApi().login(user);

            call.enqueue(new Callback<responseLogin>() {
                @Override
                public void onResponse(Call<responseLogin> call, Response<responseLogin> response) {
                    Log.d("Hugo" , "si jalo "+ response.code());
                    Log.d("Hugo", response.body().code+"");
                    if(response.isSuccessful()){
                        //   Log.d("Hugo", response.body().message);
                        if(response.body().code == 200){
                            Usuario user = new Usuario();
                            user.setTokenession(response.body().tokenSession);
                            user.setNombre(response.body().nombre);
                            user.setApPaterno(response.body().getApPaterno());
                            user.setApMaterno(response.body().getApMaterno());
                            user.setTipo(response.body().getTipo());
                            saveSession(user);
                            //Toast.makeText(getApplicationContext(), "login!", Toast.LENGTH_LONG).show();

                            goToAnotherActivity(user);


                        }else{
                            Toast.makeText(getApplicationContext(), "Ocurrio un error", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Algo salio mal", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<responseLogin> call, Throwable t) {
                    Log.d("Hugo" , "error"+t.toString());
                    Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
                }

            });





        }
       /* Intent intent = new Intent(this,Usuarios.class);
        startActivity(intent);

*/



    }

    public void goToAnotherActivity(Usuario user){
        if(user.getTipo().equalsIgnoreCase("Administrador")){
            Intent intent = new Intent(this,Usuarios.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this,pruebasAplicadas.class);
            startActivity(intent);
        }
    }


    public void saveSession(Usuario user) {
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString(getResources().getString(R.string.str_session), json);
        editor.commit();
    }
}