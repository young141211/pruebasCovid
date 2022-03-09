package com.example.pruebascovid.pojos;

import com.google.gson.annotations.SerializedName;

public class Usuario {



    @SerializedName("idUsuario")
    int idUsuario;
    @SerializedName("nombre")
    String nombre;
    @SerializedName("apPaterno")
    String apPaterno;
    @SerializedName("apMaterno")
    String apMaterno;
    @SerializedName("tipo")
    String tipo;
    @SerializedName("usuario")
    String usuario;
    @SerializedName("pass")
    String pass;
    @SerializedName("activo")
    String activo;
    @SerializedName("tokenSession")
    String tokenession;

    public Usuario(){
        idUsuario = 0;
        nombre = "";
        apPaterno = "";
        apMaterno = "";
        tipo = "";
        activo = "0";
        tokenession = "";
        pass = "";
    }

    public Usuario(int idUsuario, String nombre, String apPaterno, String apMaterno, String tipo, String usuario, String pass, String activo, String tokenession) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.tipo = tipo;
        this.usuario = usuario;
        this.pass = pass;
        this.activo = activo;
        this.tokenession = tokenession;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getTokenession() {
        return tokenession;
    }

    public void setTokenession(String tokenession) {
        this.tokenession = tokenession;
    }


    public String getNombreCompleto(){
        return  nombre+" "+apPaterno+" "+apMaterno;
    }
}
