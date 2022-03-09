package com.example.pruebascovid.servicios;

import com.example.pruebascovid.pojos.Usuario;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseLogin {

   /* {
        "nombre": "Jose Eduardo",
            "apPaterno": "Gonzalez",
            "apMaterno": "Galvan",
            "tipo": "Administrador",
            "tokenSession": "8574b9085ba802dc51fc1c4fafc74078",
            "code": 200
    }*/
   @SerializedName("nombre")
   public String nombre;

    @SerializedName("apPaterno")
    public String apPaterno;

    @SerializedName("apMaterno")
    public String apMaterno;

    @SerializedName("tokenSession")
    public String tokenSession;

    @SerializedName("tipo")
    public String tipo;

    @SerializedName("code")
    public int code;


    public responseLogin(String nombre, String apPaterno, String apMaterno, String tokenSession, String tipo, int code) {
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.tokenSession = tokenSession;
        this.tipo = tipo;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public String getTokenSession() {
        return tokenSession;
    }

    public void setTokenSession(String tokenSession) {
        this.tokenSession = tokenSession;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
