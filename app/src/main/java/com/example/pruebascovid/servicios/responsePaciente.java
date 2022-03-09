package com.example.pruebascovid.servicios;

import com.example.pruebascovid.pojos.Usuario;
import com.example.pruebascovid.pojos.paciente;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responsePaciente {

    @SerializedName("code")
    public int code;

    @SerializedName("data")
    public paciente data;
    //public Result result;

    @SerializedName("message")
    public String message;


    public responsePaciente(int code, paciente data, String message) {
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

    public paciente getData() {
        return data;
    }

    public void setData(paciente data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
