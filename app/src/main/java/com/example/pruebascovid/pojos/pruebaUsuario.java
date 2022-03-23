package com.example.pruebascovid.pojos;

import com.google.gson.annotations.SerializedName;

public class pruebaUsuario{

        @SerializedName("idPrueba")
        private int idPrueba;


        @SerializedName("idPaciente")
    int idPaciente;

    @SerializedName("nombre")
    String nombre;

    @SerializedName("apPaterno")
    String apPaterno;

    @SerializedName("apMaterno")
    String apMaterno;

    @SerializedName("fechaImpresion")
    private String fechaImpresion;


    @SerializedName("tokenSession")
    private String tokenSession;

    @SerializedName("tipo")
    private String tipo;

    @SerializedName("resultado")
    private String resultado;

    @SerializedName("fechaSolicitud")
    private String fechaSolicitud;

    @SerializedName("fechaNacimiento")
    private String fechaNacimiento;

    @SerializedName("notas")
    private String notas;

    @SerializedName("paciente_id")
    private int paciente_id;

    @SerializedName("usuario_id")
    private int usuario_id;


    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
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


        public int getIdPrueba() {
            return idPrueba;
        }

        public void setIdPrueba(int idPrueba) {
            this.idPrueba = idPrueba;
        }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
