package com.ulp.instituto.ui.inscripciones;

import static com.ulp.instituto.request.Token.ObtenerToken;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ulp.instituto.modelo.Materia;
import com.ulp.instituto.modelo.MisMateriasView;
import com.ulp.instituto.modelo.Persona;
import com.ulp.instituto.request.ApiRetrofit;
import com.ulp.instituto.request.Tabla;
import com.ulp.instituto.request.Token;

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



        Call<List<MisMateriasView>> tokenPromesa1 = ApiRetrofit.getServiceInstituto().listarMaterias(token);
        tokenPromesa1.enqueue(new Callback<List<MisMateriasView>>() {
            @Override
            public void onResponse(Call<List<MisMateriasView>> call, Response<List<MisMateriasView>> response) {

                if (response.isSuccessful()) {

                    for(int b=0; b<8 ; b++) {
                        matrizfinal[b][0] = "";
                        matrizfinal[b][1] = "";
                        matrizfinal[b][2] = "";

                        matriz1[b][0] = "";
                        matriz1[b][1] = "";
                        matriz1[b][2] = "";

                        matriz2[b][0] = "";
                        matriz2[b][1] = "";
                        matriz2[b][2] = "";

                    }


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
    public void pedidoinscripcionmateria(View view, String materia, String ciclolectivo, String estado) {


        if (estado.equals("Disponible")) {

            new AlertDialog.Builder(view.getContext())
                    .setTitle("Pedido de Inscripción")
                    .setMessage("Está seguro que desea inscribirse en la carrera: " + materia + "?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface di, int i) {
                            // Actualización de estado


                            Persona persona;

                            SharedPreferences sp= context.getSharedPreferences("token",0);
                            //  String token=sp.getString("token","-1");
                            String token = Token.ObtenerToken(context);

                            Call<String> tokenPromesa = ApiRetrofit.getServiceInstituto().pedidoInscripcionMaterias(token, materia, estado, ciclolectivo);
                            tokenPromesa.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    if (response.isSuccessful()) {

                                        // Persona persona = response.body();




                                        // dentro de un callback usar postvalue y no setvalue

                                    } else {
                                        Log.d("salida", "persona sin respuesta");

                                    }
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {
                                    Log.d("salida ", t.getMessage());

                                }
                            });

                        }


                    })

                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface di, int i) {
                            //Navigation.findNavController(view).navigate(R.id.nav_inicio);
                        }
                    }).show();

        } else {

            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setMessage("No es posible inscribirse en una carrera que no esté disponible.")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do things
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();


        }
    }


    public void prepararbase(Activity actividad){

        String token2 = ObtenerToken(actividad);
        Call<String> tokenPromesa = ApiRetrofit.getServiceInstituto().preparadoBase(token2);
        tokenPromesa.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful()) {

                    Log.d("salida", "Preparado ok");
                } else {
                    Log.d("salida", "Preparado sin respuesta");

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("salida ", t.getMessage());

            }
        });




    }


}



;



