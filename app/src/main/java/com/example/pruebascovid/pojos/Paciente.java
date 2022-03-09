package com.example.pruebascovid.pojos;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.Period;


public class Paciente {
    int idPaciente;
    String nombre;
    String apPaterno;
    String apMaterno;
    String fechaNacimiento;
    String correo;
    String telefono;
    String direccion;

    public Paciente() {
        idPaciente=0;
        nombre = "Paciente";
        apPaterno = "ap";
        apMaterno = "am";
        fechaNacimiento = "1993-02-14";
        correo = "";
        telefono = "";
        direccion = "";
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int getEdad(){
        String[] fNacimiento = getFechaNacimiento().split("-");
        Period edad = Period.between(LocalDate.of(Integer.parseInt(fNacimiento[0]), Integer.parseInt(fNacimiento[1]), Integer.parseInt(fNacimiento[2])), LocalDate.now());
        return edad.getYears();
    }
}
