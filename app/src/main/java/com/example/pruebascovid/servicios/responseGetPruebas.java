package com.example.pruebascovid.servicios;

import com.example.pruebascovid.pojos.Usuario;
import com.example.pruebascovid.pojos.pruebaUsuario;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseGetPruebas {
    @SerializedName("code")
    public int code;

    @SerializedName("data")
    public List<pruebaUsuario> data;
    //public Result result;

    @SerializedName("message")
    public String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<pruebaUsuario> getData() {
        return data;
    }

    public void setData(List<pruebaUsuario> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
