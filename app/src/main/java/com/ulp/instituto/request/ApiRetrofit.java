package com.ulp.instituto.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ulp.instituto.modelo.Clave;
import com.ulp.instituto.modelo.Materia;
import com.ulp.instituto.modelo.MisCuentasView;
import com.ulp.instituto.modelo.MisMateriasView;
import com.ulp.instituto.modelo.MisNotasView;
import com.ulp.instituto.modelo.Persona;
import com.ulp.instituto.modelo.Registronotas;
import com.ulp.instituto.modelo.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class ApiRetrofit {

  //  private static final String PATH="http://practicastuds.ulp.edu.ar/api/";
 // private static final String PATH="http://192.168.100.4:5000/";
  private static final String PATH="http://192.168.56.1:5000/";
    private static  ServiceInstituto servicioInstituto;

    public static ServiceInstituto getServiceInstituto(){

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        servicioInstituto=retrofit.create(ServiceInstituto.class);

        return servicioInstituto;
    }





    public interface ServiceInstituto{
//http://practicastuds.ulp.edu.ar/api/

        //Propietarios
        @POST("Personas/login")
        Call<String> login(@Body Usuario user);

        @POST("Personas/actualizarClave")
        Call<Clave> actualizarClave (@Header("Authorization") String token,@Body Clave clave);


        @POST("Personas/emailPedido")
        Call<String> emailPedido (@Body String email);

        @GET("Materias/listarMaterias")
        Call<List<MisMateriasView>> listarMaterias (@Header("Authorization") String token);

        @GET("Personas/obtenerPerfil")
        Call<Persona> obtenerPerfil (@Header("Authorization") String token);


        @POST("Personas/actualizarPerfil")
        Call<Persona> actualizarPerfil (@Header("Authorization") String token,@Query("nombre") String nombre, @Query("apellido") String apellido, @Query("dni") String dni, @Query("email") String email);

        @GET("Registronotas/listarNotas")
        Call<List<MisNotasView>> listarNotas (@Header("Authorization") String token);

        @GET("Cuentacorriente/listarPagos")
        Call<List<MisCuentasView>> listarPagos (@Header("Authorization") String token);


        @GET("Personas/actualizarTokenFirebase")
        Call<String> actualizarTokenFirebase (@Header("Authorization") String token,  @Query("tokenFirebase") String tokenFirebase);



        /*para hacerlo FromForm


        //Inmuebles
        @GET("Inmuebles/ListarInmuebles")
        Call<List<Inmueble>> listarInmuebles (@Header("Authorization") String token);


        @POST("Inmuebles/Registrar")
        Call<Inmueble> registrarInmueble (@Header("Authorization") String token,
                                          @Body Inmueble i);


        @POST("Inmuebles/Actualizar")
        Call<Inmueble> actualizarInmueble (@Header("Authorization") String token,@Body Inmueble i);


        // Obtener Tipo de Inmueble
        @GET("TipoInmuebles/ListarTipoInmuebles")
        Call<List<TipoInmueble>> listarTipoInmueble (@Header("Authorization") String token);

        //Contratos
        @GET("Contratos/ListarContratoInmuebles")
        Call<List<Inmueble>> listarContratoInmuebles (@Header("Authorization") String token);

        @POST("Contratos/obtenerContrato")
        Call<Contrato> obtenerContrato (@Header("Authorization") String token, @Body Inmueble i);



        @FormUrlEncoded
        @POST("user/edit")
        Call<User> updateUser(@Field("campo1") String first, @Field("campo2") String last);
        */



    }

}
