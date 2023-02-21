package com.ulp.instituto.ui.inscripciones;

import static com.ulp.instituto.request.Token.ObtenerToken;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ulp.instituto.modelo.Materia;
import com.ulp.instituto.modelo.MisMateriasView;
import com.ulp.instituto.request.ApiRetrofit;
import com.ulp.instituto.request.Tabla;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InscripcionesViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<String[][]> materiasMutableLiveData;
    private ApiRetrofit api;

    private int posicion1, posicion2;
    private String matriz1[][] = new String[100][4];
    private String matriz2[][] = new String[100][4];
    private String matrizfinal[][] = new String[8][4];
    private int max1, max2;



    //
    public InscripcionesViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();

    }

    public MutableLiveData<String [][]> getMateriasMutableLiveData() {
        if (materiasMutableLiveData == null) {
            materiasMutableLiveData = new MutableLiveData<>();
        }
        listarMaterias();
        return materiasMutableLiveData;
    }

    public void listarMaterias() {


        String token = ObtenerToken(context);

        Call<List<MisMateriasView>> tokenPromesa = ApiRetrofit.getServiceInstituto().listarMaterias(token);
        tokenPromesa.enqueue(new Callback<List<MisMateriasView>>() {
            @Override
            public void onResponse(Call<List<MisMateriasView>> call, Response<List<MisMateriasView>> response) {

                if (response.isSuccessful()) {

                    List<MisMateriasView> materias = response.body();
                    int i = 0;
                    int j = 0;
                    for (MisMateriasView object : materias) {

                        if(object.getEstado().equals("Vigente")){
                            matriz1[i][0] = String.valueOf(object.getCiclolectivo());
                            matriz1[i][1] = String.valueOf(object.getDescripcion());
                            matriz1[i][2] = String.valueOf(object.getEstado());
                            i++;}
                        else{
                            matriz2[j][0] = String.valueOf(object.getCiclolectivo());
                            matriz2[j][1] = String.valueOf(object.getDescripcion());
                            matriz2[j][2] = String.valueOf(object.getEstado());
                            j++;
                        }
                    }
                    max1 = i;
                    max2 = j;
                    int t1=0;
                    int t2=0;
                    for (int a=0; a<4 ; a++){
                        matrizfinal[a][0]=matriz1[t1][0];
                        matrizfinal[a][1]=matriz1[t1][1];
                        matrizfinal[a][2]=matriz1[t1][2];
                        t1++;
                    }
                    for (int a=4; a<8 ; a++){
                        matrizfinal[a][0]=matriz2[t2][0];
                        matrizfinal[a][1]=matriz2[t2][1];
                        matrizfinal[a][2]=matriz2[t2][2];
                        t2++;
                    }

                    materiasMutableLiveData.setValue(matrizfinal);

                } else {
                    Log.d("salida", "inmueble sin respuesta");

                }
            }

            @Override
            public void onFailure(Call<List<MisMateriasView>> call, Throwable t) {
                Log.d("salida ", t.getMessage());

            }
        });



    }



    public void botonMatriz1(int scroll){
        if (posicion1 + scroll > -1 && posicion1 + scroll <= max1-4) {
            posicion1 = posicion1 + scroll;
            String[][] resultado = Tabla.ObtenerTabla(max1, posicion1, 4, matriz1, matrizfinal);
            materiasMutableLiveData.setValue(resultado);
        }
    }

    public void botonMatriz2(int scroll){
        if (posicion2 + scroll > -1 && posicion2 + scroll <= max2-4) {
            posicion2 = posicion2 + scroll;
            String[][] resultado = Tabla.ObtenerTabla2(max2, posicion2, 4, matriz2, matrizfinal);
            materiasMutableLiveData.setValue(resultado);
        }
    }






}



