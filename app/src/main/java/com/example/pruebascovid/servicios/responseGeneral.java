package com.example.pruebascovid.servicios;

import com.example.pruebascovid.pojos.Usuario;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseGeneral {
    @SerializedName("code")
    public int code;

    @SerializedName("message")
    public String message;


    public responseGeneral(int code,  String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
