package com.example.pruebascovid.servicios;

    import com.example.pruebascovid.pojos.Usuario;
    import com.example.pruebascovid.pojos.paciente;
    import com.example.pruebascovid.pojos.prueba;
    import com.google.gson.JsonObject;

    import org.json.JSONObject;

    import java.util.List;
    import java.util.Map;

    import okhttp3.RequestBody;
    import okhttp3.ResponseBody;
    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;
    import retrofit2.http.Body;
    import retrofit2.http.GET;
    import retrofit2.http.POST;

public interface Api {
    String BASE_URL = "https://peiipowr6f.execute-api.us-west-2.amazonaws.com/produccion/";

    @POST("getusuarios")
    Call<responseGetUsuarios> getUsuarios();

    @POST("registrousuario")
    Call<responseGeneral> registroUsuario(@Body Usuario jsonObject);

    @POST("activausuario")
    Call<responseGeneral> activarUsuario(@Body Usuario jsonObject);

    @POST("deleteusuario")
    Call<responseGeneral> desactivarUsuario(@Body Usuario jsonObject);

    @POST("loginusuario")
    Call<responseLogin> login(@Body Usuario jsonObject);

    @POST("buscarpacientebycurp")
    Call<responsePaciente> busquedaPaciente(@Body paciente jsonObject);

    @POST("registropaciente")
    Call<responseAltaPaciente> registroPaciente(@Body paciente jsonObject);

    @POST("registroprueba")
    Call<responseAltaPaciente> registroPrueba(@Body prueba jsonObject);

    @POST("updateprueba")
    Call<responseGeneral> updatePrueba(@Body prueba jsonObject);

    @POST("getprueba")
    Call<responsePrueba> getPrueba(@Body prueba jsonObject);

    @POST("getpruebas")
    Call<responseGetPruebas> getPruebas(@Body Usuario jsonObject);



//  / Call<JSONObject> getUsuarios(@Body JSONObject dataModal);

}
