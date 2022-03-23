package com.example.pruebascovid.pojos;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.Period;


public class paciente {

    @SerializedName("idPaciente")
    int idPaciente;

    @SerializedName("nombre")
    String nombre;

    @SerializedName("apPaterno")
    String apPaterno;

    @SerializedName("apMaterno")
    String apMaterno;

    @SerializedName("fechaNacimiento")
    String fechaNacimiento;

    @SerializedName("correo")
    String correo;

    @SerializedName("telefono")
    String telefono;

    @SerializedName("direccion")
    String direccion;

    @SerializedName("curp")
    String curp;

    public paciente() {
        idPaciente=0;
        nombre = "";
        apPaterno = "";
        apMaterno = "";
        fechaNacimiento = "";
        correo = "";
        telefono = "";
        direccion = "";
        curp = "";

    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int getEdad(){
        Log.d("Hugo",getFechaNacimiento() );
        String[] fNacimiento = getFechaNacimiento().split("T")[0].split("-");
        Period edad = Period.between(LocalDate.of(Integer.parseInt(fNacimiento[0]), Integer.parseInt(fNacimiento[1]), Integer.parseInt(fNacimiento[2])), LocalDate.now());
        return edad.getYears();
    }


}
