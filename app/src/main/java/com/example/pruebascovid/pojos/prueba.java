package com.example.pruebascovid.pojos;

import com.google.gson.annotations.SerializedName;

public class prueba {

    @SerializedName("idPrueba")
    private int idPrueba;

    @SerializedName("tokenSession")
    private String tokenSession;

   @SerializedName("tipo")
   private String tipo;

   @SerializedName("resultado")
   private String resultado;

    @SerializedName("fechaSolicitud")
   private String fechaSolicitud;

    @SerializedName("fechaImpresion")
   private String fechaImpresion;

    @SerializedName("notas")
    private String notas;

    @SerializedName("paciente_id")
   private int paciente_id;

    @SerializedName("usuario_id")
   private int usuario_id;

    @SerializedName("dirigido")
    private String referido;

    @SerializedName("referencia")
    private String DirigidoA;

    @SerializedName("metodo")
    private String metodo;



    public prueba(String tipo, String resultado, String fechaSolicitud, String fechaImpresion, String notas, int paciente_id, int usuario_id) {
        this.tipo = tipo;
        this.resultado = resultado;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaImpresion = fechaImpresion;
        this.notas = notas;
        this.paciente_id = paciente_id;
        this.usuario_id = usuario_id;
    }


    public prueba() {
        this.tipo = "Antigeno";
        this.resultado = "";
        this.fechaSolicitud = "";
        this.fechaImpresion = "";
        this.notas = "";
        this.paciente_id = 0;
        this.usuario_id = 0;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }


    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getFechaImpresion() {
        return fechaImpresion;
    }

    public void setFechaImpresion(String fechaImpresion) {
        this.fechaImpresion = fechaImpresion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public int getPaciente_id() {
        return paciente_id;
    }

    public void setPaciente_id(int paciente_id) {
        this.paciente_id = paciente_id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }


    public String getTokenSession() {
        return tokenSession;
    }

    public void setTokenSession(String tokenSession) {
        this.tokenSession = tokenSession;
    }

    public int getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(int idPrueba) {
        this.idPrueba = idPrueba;
    }

    public String getReferido() {
        return referido;
    }

    public void setReferido(String referido) {
        this.referido = referido;
    }

    public String getDirigidoA() {
        return DirigidoA;
    }

    public void setDirigidoA(String dirigidoA) {
        DirigidoA = dirigidoA;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }
}
