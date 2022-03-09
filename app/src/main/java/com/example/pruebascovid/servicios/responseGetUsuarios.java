package com.example.pruebascovid.servicios;

import com.example.pruebascovid.pojos.Usuario;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseGetUsuarios {
    @SerializedName("code")
    public int code;

    @SerializedName("data")
    public List<Usuario> data;
    //public Result result;

    @SerializedName("message")
    public String message;


    public responseGetUsuarios(int code, List<Usuario> data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Usuario> getData() {
        return data;
    }

    public void setData(List<Usuario> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
