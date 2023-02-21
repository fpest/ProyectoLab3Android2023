package com.ulp.instituto.ui.inscripciones;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.ulp.instituto.R;
import com.ulp.instituto.databinding.FragmentInscripcionesBinding;
import com.ulp.instituto.modelo.Materia;

import org.w3c.dom.Text;

import java.util.List;

public class InscripcionesFragment extends Fragment {



    private FragmentInscripcionesBinding binding;
    List<Materia> listaMaterias;
    private String matriz[][] = new String[100][100];

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InscripcionesViewModel mInmViewModel =
                new ViewModelProvider(this).get(InscripcionesViewModel.class);

        binding = FragmentInscripcionesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mInmViewModel.getMateriasMutableLiveData().observe(getViewLifecycleOwner(), new Observer<String[][]>() {

            @Override
            public void onChanged(String[][] matriz) {
                inicializarVista(root, inflater, matriz);

            }
        });

        binding.btnMatriz1subir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInmViewModel.botonMatriz1(-1);
            }
        });

        binding.btnMatriz1bajar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInmViewModel.botonMatriz1(1);
            }
        });

        binding.btnMatriz2subir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInmViewModel.botonMatriz2(-1);
            }
        });

        binding.btnMatriz2bajar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInmViewModel.botonMatriz2(1);
            }
        });




        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void inicializarVista(View view, LayoutInflater layoutInflater, String[][] matriz) {

      this.matriz = matriz ;

        binding.tv111.setText(matriz[0][0]);
        binding.tv112.setText(matriz[0][1]);
        binding.tv113.setText(matriz[0][2]);
        binding.tv121.setText(matriz[1][0]);
        binding.tv122.setText(matriz[1][1]);
        binding.tv123.setText(matriz[1][2]);
        binding.tv131.setText(matriz[2][0]);
        binding.tv132.setText(matriz[2][1]);
        binding.tv133.setText(matriz[2][2]);
        binding.tv141.setText(matriz[3][0]);
        binding.tv142.setText(matriz[3][1]);
        binding.tv143.setText(matriz[3][2]);

        binding.tv11.setText(matriz[4][0]);
        binding.tv12.setText(matriz[4][1]);
        binding.tv13.setText(matriz[4][2]);
        binding.tv21.setText(matriz[5][0]);
        binding.tv22.setText(matriz[5][1]);
        binding.tv23.setText(matriz[5][2]);
        binding.tv31.setText(matriz[6][0]);
        binding.tv32.setText(matriz[6][1]);
        binding.tv33.setText(matriz[6][2]);
        binding.tv41.setText(matriz[7][0]);
        binding.tv42.setText(matriz[7][1]);
        binding.tv43.setText(matriz[7][2]);







    }





}