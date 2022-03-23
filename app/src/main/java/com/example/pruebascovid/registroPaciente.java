package com.example.pruebascovid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pruebascovid.adapters.adapterUsuarios;
import com.example.pruebascovid.pojos.Usuario;
import com.example.pruebascovid.pojos.paciente;
import com.example.pruebascovid.pojos.prueba;
import com.example.pruebascovid.servicios.RetrofitClient;
import com.example.pruebascovid.servicios.responseAltaPaciente;
import com.example.pruebascovid.servicios.responseGetUsuarios;
import com.example.pruebascovid.servicios.responsePaciente;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registroPaciente extends AppCompatActivity {
    private Usuario userLogin;
    DatePickerDialog picker;

    private TextView nombre;
    private TextView apPaterno;
    private TextView apMaterno;
    private TextView curp;
    private TextView correo;
    private TextView telefono;
    private TextView direccion;
    private Button fechaNacimiento;
    private paciente pacienteAlta;
    private  Button btnGuardad;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_paciente);
        pacienteAlta = new paciente();
        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String json = sharedPref.getString(getResources().getString(R.string.str_session), "");

        if (json.isEmpty()) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            userLogin = new Gson().fromJson(json, Usuario.class);
        }

        curp = (TextView) findViewById(R.id.curp_registro_paciente);
        nombre = (TextView) findViewById(R.id.nombre_registro_paciente);
        apPaterno = (TextView) findViewById(R.id.ap_paterno_registro_paciente);
        apMaterno = (TextView) findViewById(R.id.ap_materno_registro_paciente);
        correo = (TextView) findViewById(R.id.correo_registro_paciente);
        telefono = (TextView) findViewById(R.id.telfono_registro_paciente);
        direccion = (TextView) findViewById(R.id.direccion_registro_paciente);
        fechaNacimiento = (Button) findViewById(R.id.btn_fecha_nacimiento_registro_paciente);
        btnGuardad = (Button) findViewById(R.id.btn_registro_paciente_registro_paciente);


    fechaNacimiento.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showDataPicker("");
        }
    });


    btnGuardad.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            btnAltaUSuario();
        }
    });


        curp.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                Log.d("Hugo", s.toString());
                if(!s.toString().isEmpty()){
                    if(s.toString().length() == 18){
                        busquedaPaciente(s.toString());
                    }
                }
                // yourEditText...
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

    }


    private void busquedaPaciente(String curp) {

        paciente paciente = new paciente();
        paciente.setCurp(curp);
        Call<responsePaciente> call = RetrofitClient.getInstance().getMyApi().busquedaPaciente(paciente);
        call.enqueue(new Callback<responsePaciente>() {
            @Override
            public void onResponse(Call<responsePaciente> call, Response<responsePaciente> response) {
                Log.d("Hugo" , "si jalo "+ response.isSuccessful());
                Log.d("Hugo", response.body().code+" "+response.body().message);
                if(response.isSuccessful()){
                    if(response.body().code == 200){
                        Log.d("Hugo", response.body().getData().getNombre());
                        nombre.setText(response.body().getData().getNombre());
                        apPaterno.setText(response.body().getData().getApPaterno());
                        apMaterno.setText(response.body().getData().getApMaterno());
                        correo.setText(response.body().getData().getCorreo());
                        telefono.setText(response.body().getData().getTelefono());
                        direccion.setText(response.body().getData().getDireccion());
                        String fecha[] = response.body().getData().getFechaNacimiento().split("T")[0].split("-");

                        String formatFecha = fecha[2]+"/"+fecha[1]+"/"+fecha[0];
                        fechaNacimiento.setText(formatFecha);
                        pacienteAlta = new paciente();
                        pacienteAlta.setIdPaciente(response.body().getData().getIdPaciente());

                    }
                }else{
                    pacienteAlta = new paciente();
                }
            }
            @Override
            public void onFailure(Call<responsePaciente> call, Throwable t) {
                Log.d("Hugo" , "error"+t.toString());
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }

    private void altaPaciente(paciente paciente) {

        Call<responseAltaPaciente> call = RetrofitClient.getInstance().getMyApi().registroPaciente(paciente);
        call.enqueue(new Callback<responseAltaPaciente>() {
            @Override
            public void onResponse(Call<responseAltaPaciente> call, Response<responseAltaPaciente> response) {
                Log.d("Hugo" , "si jalo "+ response.isSuccessful());
                Log.d("Hugo", response.body().code+" "+response.body().message);
                if(response.isSuccessful()){
                    if(response.body().code == 200){
                        altaPrueba(response.body().data);
                    }
                }else{

                }
            }
            @Override
            public void onFailure(Call<responseAltaPaciente> call, Throwable t) {
                Log.d("Hugo" , "error"+t.toString());
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }


    private boolean validaUsuario(){
        if(nombre.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Ingresa el nombre", Toast.LENGTH_LONG).show();
            return false;
        }else  if(apPaterno.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Ingresa el apellido paterno", Toast.LENGTH_LONG).show();
            return false;
        }else  if(apMaterno.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Ingresa el apellido materno", Toast.LENGTH_LONG).show();
            return false;
        }else if(correo.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Ingresa el correo", Toast.LENGTH_LONG).show();
            return false;
        }else if(telefono.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Ingresa el teléfono", Toast.LENGTH_LONG).show();
            return false;
        }else if(fechaNacimiento.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Ingresa la fecha de nacimiento", Toast.LENGTH_LONG).show();
            return false;
        }else if(direccion.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Ingresa el apellido paterno", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void btnAltaUSuario(){
        if (pacienteAlta.getIdPaciente() != 0) {
            //todo actualizar
            Log.d("Hugo", "Actualizare "+pacienteAlta.getIdPaciente());





        }else{
            if(validaUsuario()){
                paciente paciente = new paciente();
                paciente.setCurp(curp.getText().toString());
                paciente.setNombre(nombre.getText().toString());
                paciente.setApPaterno(apPaterno.getText().toString());
                paciente.setApMaterno(apMaterno.getText().toString());
                paciente.setCorreo(correo.getText().toString());
                paciente.setTelefono(telefono.getText().toString());
                paciente.setDireccion(direccion.getText().toString());


                String fecha[] = fechaNacimiento.getText().toString().split("/");
                paciente.setFechaNacimiento(fecha[2]+"-"+fecha[1]+"-"+fecha[0]);//aaaa-mm-dd
                altaPaciente(paciente);

            }
        }
    }

    public void altaPrueba(int idPaciente){
        prueba prueba = new prueba();
        prueba.setResultado("En espera");
        prueba.setFechaSolicitud(Calendar.getInstance().getTime().toString());
        prueba.setFechaImpresion(Calendar.getInstance().getTime().toString());
        prueba.setPaciente_id(idPaciente);
        prueba.setTokenSession(userLogin.getTokenession());
        Call<responseAltaPaciente> call = RetrofitClient.getInstance().getMyApi().registroPrueba(prueba);
        call.enqueue(new Callback<responseAltaPaciente>() {
            @Override
            public void onResponse(Call<responseAltaPaciente> call, Response<responseAltaPaciente> response) {
                Log.d("Hugo" , "si jalo "+ response.isSuccessful());
                Log.d("Hugo", response.body().code+" "+response.body().message);
                if(response.isSuccessful()){
                    if(response.body().code == 200){
                        Log.d("Hugo", "tenmos alta de paciengte y de ");
                        int idPrueba = response.body().data;
                        goToModificarPrueba(idPrueba);
                    }
                }else{

                }
            }
            @Override
            public void onFailure(Call<responseAltaPaciente> call, Throwable t) {
                Log.d("Hugo" , "error"+t.toString());
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });





    }

    public void showDataPicker(String fecha){
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        picker = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        //eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        Log.d("Hugo", "Hola desde la fecha: "+dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                      fechaNacimiento.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                }, year, month, day);
        picker.show();
    }


    private void goToModificarPrueba(int idPrueba){
        Intent intent = new Intent(this,modificacionPrueba.class);
        intent.putExtra("idPrueba", idPrueba);
        intent.putExtra("nombre_paciente", nombre.getText().toString());
        intent.putExtra("ap_paterno", apPaterno.getText().toString());
        intent.putExtra("ap_materno", apMaterno.getText().toString());
        intent.putExtra("fecha_nacimiento", fechaNacimiento.getText().toString());
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {

            Intent intent = new Intent(this,pruebasAplicadas.class);
            startActivity(intent);

        finish();
    }
}





