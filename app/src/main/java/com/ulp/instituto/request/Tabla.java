package com.ulp.instituto.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.ulp.instituto.modelo.MisMateriasView;

public class Tabla {


    public static String[][] ObtenerTabla(int filasMatriz, int pos, int filasTabla, String matriz[][], String matrizOriginal[][]){
        String  matrizfinal[][] = new String[8][4];

        matrizfinal = matrizOriginal;
            int t1=pos;

            for (int a=0; a<filasTabla ; a++){
                matrizfinal[a][0]=matriz[t1][0];
                matrizfinal[a][1]=matriz[t1][1];
                matrizfinal[a][2]=matriz[t1][2];
                t1++;
            }

        return matrizfinal;

    };


    public static String[][] ObtenerTabla2(int filasMatriz, int pos, int filasTabla, String matriz[][], String matrizOriginal[][]){
        String  matrizfinal[][] = new String[8][4];

        matrizfinal = matrizOriginal;
        int t1=pos;

        for (int a=4; a<8 ; a++){
            matrizfinal[a][0]=matriz[t1][0];
            matrizfinal[a][1]=matriz[t1][1];
            matrizfinal[a][2]=matriz[t1][2];
            t1++;
        }

        return matrizfinal;

    };
}
