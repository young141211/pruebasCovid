package com.example.pruebascovid.servicios;

import com.google.gson.annotations.SerializedName;

public class responseAltaPaciente {
    @SerializedName("code")
    public int code;

    @SerializedName("data")
    public int data;

    @SerializedName("message")
    public String message;


    public responseAltaPaciente(int code,  String message) {
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

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
