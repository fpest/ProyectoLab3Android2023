package com.ulp.instituto.ui.perfil;

import android.app.Application;
import android.app.Person;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ulp.instituto.modelo.Persona;
import com.ulp.instituto.request.ApiRetrofit;
import com.ulp.instituto.modelo.Persona;
import com.ulp.instituto.request.ApiRetrofit;
import com.ulp.instituto.request.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class PerfilViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Persona> mPersona;
    private MutableLiveData<Boolean> mEditEnabled;
    private MutableLiveData<String> mButtonText;
    private Retrofit api;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        this.context= application.getApplicationContext();

    }
    public LiveData<Persona> getMutablePersona(){
        if(mPersona==null){
            mPersona=new MutableLiveData<>();
        }
        return mPersona;
    }
    public LiveData<String> getMutableButtonText(){
        if(mButtonText==null){
            mButtonText=new MutableLiveData<>();
        }
        return mButtonText;
    }
    public LiveData<Boolean> getMutableEditEnabled(){
        if(mEditEnabled==null){
            mEditEnabled=new MutableLiveData<>();
        }
        return mEditEnabled;
    }


    private String ObtenerToken(){
        SharedPreferences sp= context.getSharedPreferences("token",0);
        String token=sp.getString("token","-1");
        return token;

    };


    public void ObtenerUsuario(){
       // Propietario p= api.obtenerUsuarioActual();


        Persona persona;

        SharedPreferences sp= context.getSharedPreferences("token",0);
      //  String token=sp.getString("token","-1");
        String token = Token.ObtenerToken(context);

        Call<Persona> tokenPromesa = ApiRetrofit.getServiceInstituto().obtenerPerfil(token);
        tokenPromesa.enqueue(new Callback<Persona>() {
            @Override
            public void onResponse(Call<Persona> call, Response<Persona> response) {
                if (response.isSuccessful()) {

                    Persona persona = response.body();
                    // dentro de un callback usar postvalue y no setvalue
                    mPersona.postValue(persona);

                } else {
                    Log.d("salida", "persona sin respuesta");

                }
            }

            @Override
            public void onFailure(Call<Persona> call, Throwable t) {
                Log.d("salida ", t.getMessage());

            }
        });

    }
    public void EditarGuardar(String textoBoton, Persona p){

        if (textoBoton.equals("EDITAR")){
            mButtonText.setValue("GUARDAR");
            mEditEnabled.setValue(true);
        }else{
            mButtonText.setValue("EDITAR");
//


            Call tokenPromesa = ApiRetrofit.getServiceInstituto().actualizarPerfil(ObtenerToken(),p.getNombre(),p.getApellido(), p.getDni(), p.getEmail());
            tokenPromesa.enqueue(new Callback<Persona>() {
                @Override
                public void onResponse(Call<Persona> call, Response<Persona> response) {
                    if (response.isSuccessful()) {

                        Persona persona = response.body();

                        Toast.makeText(context.getApplicationContext(), "Datos Actualizada Correctamente.", Toast.LENGTH_SHORT).show();
                        mPersona.setValue(persona);

                    } else {
                        Log.d("salida", "persona sin respuesta en actualizar");

                    }
                }

                @Override
                public void onFailure(Call<Persona> call, Throwable t) {
                    Log.d("salida ", t.getMessage());

                }
            });














            mEditEnabled.setValue(false);
        }

    }



}