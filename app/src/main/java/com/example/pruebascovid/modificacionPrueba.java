package com.example.pruebascovid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pruebascovid.pdfGenerator.pdfGenerator;
import com.example.pruebascovid.pojos.paciente;
import com.example.pruebascovid.pojos.prueba;
import com.example.pruebascovid.servicios.RetrofitClient;
import com.example.pruebascovid.servicios.responseAltaPaciente;
import com.example.pruebascovid.servicios.responseGeneral;
import com.example.pruebascovid.servicios.responsePaciente;
import com.example.pruebascovid.servicios.responsePrueba;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class modificacionPrueba extends AppCompatActivity {

    prueba prueba;
    paciente paci;

    TextView observaciones, dirigidoA;
    Spinner valorReferencia;
    Spinner resultado;
    Spinner metodo;

    Button btnActualizar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificacion_prueba);
        prueba = new prueba();
        prueba.setIdPrueba(getIntent().getExtras().getInt("idPrueba"));
        paci = new paciente();
        paci.setNombre(getIntent().getExtras().getString("nombre_paciente"));
        paci.setApPaterno(getIntent().getExtras().getString("ap_paterno"));
        paci.setApMaterno(getIntent().getExtras().getString("ap_materno"));
        paci.setFechaNacimiento(getIntent().getExtras().getString("fecha_nacimiento"));

        metodo = (Spinner) findViewById(R.id.spinner_metodo_prueba);
        resultado = (Spinner) findViewById(R.id.spinner_resultado_pruebas);
        valorReferencia = (Spinner) findViewById(R.id.spinner_valor_referencia);
        observaciones =  (TextView) findViewById(R.id.notas_prueba);
        dirigidoA = (TextView) findViewById(R.id.notas_dirigido_a);

        ArrayAdapter<String> adapterMetodo = new ArrayAdapter<String>(this, R.layout.item_spinner,getResources().getStringArray(R.array.metodo_array));
        metodo.setAdapter(adapterMetodo);

        ArrayAdapter<String> adapterResultado = new ArrayAdapter<String>(this, R.layout.item_spinner,getResources().getStringArray(R.array.resultados_array));
        resultado.setAdapter(adapterResultado);

        ArrayAdapter<String> adapterValorReferecnia = new ArrayAdapter<String>(this, R.layout.item_spinner,getResources().getStringArray(R.array.valor_referencia_array));
        valorReferencia.setAdapter(adapterValorReferecnia);


        getDataPrueba();

        btnActualizar = (Button) findViewById(R.id.actualizar_nota_btn);
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarPrueba();
            }
        });


    }


private void actualizarPrueba(){
        if(validaPrueba()){
            if(dirigidoA.getText().toString().isEmpty()){
                prueba.setDirigidoA("A quíen corresponda");
            }else{
                prueba.setDirigidoA(dirigidoA.getText().toString());
            }

            prueba.setMetodo(metodo.getSelectedItem().toString());
            prueba.setReferido(valorReferencia.getSelectedItem().toString());
            prueba.setResultado(resultado.getSelectedItem().toString());
            prueba.setNotas(observaciones.getText().toString());


            Call<responseGeneral> call = RetrofitClient.getInstance().getMyApi().updatePrueba(prueba);
            call.enqueue(new Callback<responseGeneral>() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onResponse(Call<responseGeneral> call, Response<responseGeneral> response) {
                    Log.d("Hugo" , "actualizo "+ response.isSuccessful());
                    Log.d("Hugo", response.body().code+" "+response.body().message);
                    if(response.isSuccessful()){
                        if(response.body().code == 200){
                            pruebaPdf();
                            goToInicio();
                        }
                    }else{

                    }
                }
                @Override
                public void onFailure(Call<responseGeneral> call, Throwable t) {
                    Log.d("Hugo" , "error"+t.toString());
                    Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
                }

            });
        }


}

private void getDataPrueba(){
    Call<responsePrueba> call = RetrofitClient.getInstance().getMyApi().getPrueba(prueba);
    call.enqueue(new Callback<responsePrueba>() {
        @Override
        public void onResponse(Call<responsePrueba> call, Response<responsePrueba> response) {
            Log.d("Hugo" , "si jalo "+ response.isSuccessful());
            Log.d("Hugo", response.body().code+" "+response.body().message);
            if(response.isSuccessful()){
                if(response.body().code == 200){
                    prueba.setTokenSession(response.body().getData().getTokenSession());
                    prueba.setFechaImpresion(response.body().getData().getFechaImpresion());
                    prueba.setFechaSolicitud(response.body().getData().getFechaSolicitud());
                    prueba.setResultado(response.body().getData().getResultado());
                    prueba.setNotas(response.body().getData().getNotas());
                    prueba.setResultado(response.body().getData().getResultado());
                    prueba.setPaciente_id(response.body().getData().getPaciente_id());
                    prueba.setUsuario_id(response.body().getData().getUsuario_id());

                    observaciones.setText(response.body().getData().getNotas());
                    dirigidoA.setText(response.body().getData().getDirigidoA());



                    switch (response.body().getData().getResultado()) {
                        case "":
                            resultado.setSelection(0);
                            break;

                        case "Positivo":

                            resultado.setSelection(2);
                            break;

                        case "Negativo":

                            resultado.setSelection(1);
                            break;

                        case "En espera":

                            resultado.setSelection(3);

                            break;
                        default:
                            break;
                    }

                    switch (response.body().getData().getReferido()) {
                        case "":
                            valorReferencia.setSelection(0);
                            break;

                        case "Detectado":

                            valorReferencia.setSelection(1);
                            break;

                        case "No detectado":

                            valorReferencia.setSelection(2);
                            break;
                    }
/*       <item>Seleccionar</item>
        <item>Hisopo</item>
        <item>Serológica (bucal)</item>
        <item>Sangre</item>*/
                    switch (response.body().getData().getMetodo()) {
                        case "":
                            metodo.setSelection(0);
                            break;

                        case "Hisopo":

                            metodo.setSelection(1);
                            break;

                        case "Serológica (bucal)":

                            metodo.setSelection(2);
                            break;

                        case "Sangre":
                            metodo.setSelection(3);
                            break;
                    }


                  //  Log.d("Hugo", response.body().getData().getNombre());
                  /*  nombre.setText(response.body().getData().getNombre());
                    apPaterno.setText(response.body().getData().getApPaterno());
                    apMaterno.setText(response.body().getData().getApMaterno());
                    correo.setText(response.body().getData().getCorreo());
                    telefono.setText(response.body().getData().getTelefono());
                    direccion.setText(response.body().getData().getDireccion());
                    String fecha[] = response.body().getData().getFechaNacimiento().split("T")[0].split("-");

                    String formatFecha = fecha[2]+"/"+fecha[1]+"/"+fecha[0];
                    fechaNacimiento.setText(formatFecha);
                    pacienteAlta = new paciente();
                    pacienteAlta.setIdPaciente(response.body().getData().getIdPaciente());*/

                }
            }
        }
        @Override
        public void onFailure(Call<responsePrueba> call, Throwable t) {
            Log.d("Hugo" , "error"+t.toString());
            Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
        }

    });
}


private boolean validaPrueba(){
    if(metodo.getSelectedItemPosition() == 0){
        Toast.makeText(getApplicationContext(), "Selecciona el metodo de la prueba", Toast.LENGTH_LONG).show();
        return false;
    }else if(valorReferencia.getSelectedItemPosition() == 0){
        Toast.makeText(getApplicationContext(), "Selecciona el valor de referencia", Toast.LENGTH_LONG).show();
        return false;
    } else if (resultado.getSelectedItemPosition() == 0) {
        Toast.makeText(getApplicationContext(), "selecciona el resultado de la prueba", Toast.LENGTH_LONG).show();
        return false;
    }
    return true;
}

private void goToInicio(){
    Intent intent = new Intent(this, pruebasAplicadas.class);
    startActivity(intent);
    finish();
}

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void pruebaPdf(){
        /*Toast toast = Toast.makeText(getApplicationContext(),"Funciona",Toast.LENGTH_SHORT);
        toast.show();*/
        pdfGenerator pdf = new pdfGenerator(paci, prueba.getDirigidoA(),prueba.getResultado(),prueba.getReferido(),prueba.getMetodo(),prueba.getMetodo(),prueba.getNotas(),this);
        pdf.generarPDF();
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this,pruebasAplicadas.class);
        startActivity(intent);

        finish();
    }
}