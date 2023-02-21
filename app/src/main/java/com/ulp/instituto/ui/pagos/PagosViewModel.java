package com.ulp.instituto.ui.pagos;

import static com.ulp.instituto.request.Token.ObtenerToken;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ulp.instituto.modelo.MisCuentasView;
import com.ulp.instituto.request.ApiRetrofit;
import com.ulp.instituto.request.Tabla;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagosViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String[][]> pagosMutableLiveData;
    private ApiRetrofit api;

    private int posicion1;
    private String matriz[][] = new String[100][4];
    private String matrizfinal[][] = new String[8][4];
    private int max1;



    //
    public PagosViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();

    }

    public MutableLiveData<String [][]> getPagosMutableLiveData() {
        if (pagosMutableLiveData == null) {
            pagosMutableLiveData = new MutableLiveData<>();
        }
        listarPagos();
        return pagosMutableLiveData;
    }

    public void listarPagos() {


        String token = ObtenerToken(context);

        Call<List<MisCuentasView>> tokenPromesa = ApiRetrofit.getServiceInstituto().listarPagos(token);
        tokenPromesa.enqueue(new Callback<List<MisCuentasView>>() {
            @Override
            public void onResponse(Call<List<MisCuentasView>> call, Response<List<MisCuentasView>> response) {

                if (response.isSuccessful()) {

                    List<MisCuentasView> notas = response.body();
                    int i = 0;
                    int j = 0;

                    for (MisCuentasView object : notas) {

                        matriz[i][0] = String.valueOf(object.getFechahora());
                        matriz[i][1] = String.valueOf(object.getDescripcion());
                        matriz[i][2] = String.valueOf(object.getMonto());
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


                    pagosMutableLiveData.setValue(matrizfinal);

                } else {
                    Log.d("salida", "Pagos sin respuesta");

                }
            }

            @Override
            public void onFailure(Call<List<MisCuentasView>> call, Throwable t) {
                Log.d("salida ", t.getMessage());

            }
        });

    }

    public void botonMatriz(int scroll){
        if (posicion1 + scroll > -1 && posicion1 + scroll <= max1-8) {
            posicion1 = posicion1 + scroll;
            String[][] resultado = Tabla.ObtenerTabla(max1, posicion1, 8, matriz, matrizfinal);
            pagosMutableLiveData.setValue(resultado);
        }
    }

}



