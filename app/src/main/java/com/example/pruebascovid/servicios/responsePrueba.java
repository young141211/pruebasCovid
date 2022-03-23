package com.example.pruebascovid.servicios;

import com.example.pruebascovid.pojos.prueba;
import com.example.pruebascovid.pojos.pruebaUsuario;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responsePrueba {

    @SerializedName("code")
    public int code;

    @SerializedName("data")
    public prueba data;
    //public Result result;

    @SerializedName("message")
    public String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public prueba getData() {
        return data;
    }

    public void setData(prueba data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
