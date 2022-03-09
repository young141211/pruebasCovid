package com.example.pruebascovid.pojos;

import com.google.gson.annotations.SerializedName;

public class paciente {
    @SerializedName("idPaciente")
    private int inPaciente;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("apPaterno")
    private String apPaterno;

    @SerializedName("apMaterno")
    private String apMaterno;

    @SerializedName("fechaNacimiento")
    private String fechaNacimiento;

    @SerializedName("correo")
    private String correo;

    @SerializedName("telefono")
    private String telefono;

    @SerializedName("direccion")
    private String direccion;

    @SerializedName("curp")
    private String curp;

    private prueba prueba;


    public paciente(int inPaciente, String nombre, String apPaterno, String apMaterno, String fechaNacimiento, String correo, String telefono, String direccion, String curp, prueba prueba) {

    }


    public paciente() {
        this.inPaciente = 0;
        this.nombre = "";
        this.apPaterno = "";
        this.apMaterno = "";
        this.fechaNacimiento = "";
        this.correo = "";
        this.telefono = "";
        this.direccion = "";
        this.curp = "";
        this.prueba = new prueba();
    }


    public com.example.pruebascovid.pojos.prueba getPrueba() {
        return prueba;
    }

    public void setPrueba(com.example.pruebascovid.pojos.prueba prueba) {
        this.prueba = prueba;
    }

    public int getInPaciente() {
        return inPaciente;
    }

    public void setInPaciente(int inPaciente) {
        this.inPaciente = inPaciente;
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
}
