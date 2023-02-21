package com.ulp.instituto.ui.calificaciones;

import static com.ulp.instituto.request.Token.ObtenerToken;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ulp.instituto.modelo.MisMateriasView;
import com.ulp.instituto.modelo.MisNotasView;
import com.ulp.instituto.modelo.Registronotas;
import com.ulp.instituto.request.ApiRetrofit;
import com.ulp.instituto.request.Tabla;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalificacionesViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String[][]> calificacionesMutableLiveData;
    private ApiRetrofit api;

    private int posicion1;
    private String matriz[][] = new String[100][4];
    private String matrizfinal[][] = new String[8][4];
    private int max1;



    //
    public CalificacionesViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();

    }

    public MutableLiveData<String [][]> getCalificacionesMutableLiveData() {
        if (calificacionesMutableLiveData == null) {
            calificacionesMutableLiveData = new MutableLiveData<>();
        }
        listarCalificaciones();
        return calificacionesMutableLiveData;
    }

    public void listarCalificaciones() {


        String token = ObtenerToken(context);

        Call<List<MisNotasView>> tokenPromesa = ApiRetrofit.getServiceInstituto().listarNotas(token);
        tokenPromesa.enqueue(new Callback<List<MisNotasView>>() {
            @Override
            public void onResponse(Call<List<MisNotasView>> call, Response<List<MisNotasView>> response) {

                if (response.isSuccessful()) {

                    List<MisNotasView> notas = response.body();
                    int i = 0;
                    int j = 0;

                    for (MisNotasView object : notas) {

                        matriz[i][0] = String.valueOf(object.getFecha());
                        matriz[i][1] = String.valueOf(object.getDescripcion());
                        matriz[i][2] = String.valueOf(object.getNota());
                        i++;

                    }
                    max1 = i;

                    int t1 = 0;

                    for (int a = 0; a < 8; a++) {
                        matrizfinal[a][0] = matriz[t1][0];
                        matrizfinal[a][1] = matriz[t1][1];
                        matrizfinal[a][2] = matriz[t1][2];
                        t1++;
                    }


                    calificacionesMutableLiveData.setValue(matrizfinal);

                } else {
                    Log.d("salida", "Calificaciones sin respuesta");

                }
            }

            @Override
            public void onFailure(Call<List<MisNotasView>> call, Throwable t) {
                Log.d("salida ", t.getMessage());

            }
        });

    }

    public void botonMatriz(int scroll){
        if (posicion1 + scroll > -1 && posicion1 + scroll <= max1-8) {
            posicion1 = posicion1 + scroll;
            String[][] resultado = Tabla.ObtenerTabla(max1, posicion1, 8, matriz, matrizfinal);
            calificacionesMutableLiveData.setValue(resultado);
        }
    }

}



