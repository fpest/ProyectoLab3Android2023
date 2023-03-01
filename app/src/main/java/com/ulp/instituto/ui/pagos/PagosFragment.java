package com.ulp.instituto.ui.pagos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ulp.instituto.databinding.FragmentPagosBinding;
import com.ulp.instituto.modelo.Registronotas;
import com.ulp.instituto.ui.pagos.PagosViewModel;

import java.util.List;

public class PagosFragment extends Fragment {

    private PagosViewModel mViewModel;

    public static PagosFragment newInstance() {
        return new PagosFragment();
    }
    private FragmentPagosBinding binding;
    List<Registronotas> listaMaterias;
    private String matriz[][] = new String[100][100];



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PagosViewModel mCalViewModel =
                new ViewModelProvider(this).get(PagosViewModel.class);

        binding = FragmentPagosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mCalViewModel.getPagosMutableLiveData().observe(getViewLifecycleOwner(), new Observer<String[][]>() {

            @Override
            public void onChanged(String[][] matriz) {
                inicializarVista(root, inflater, matriz);

            }
        });

        binding.btnMatrizsubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalViewModel.botonMatriz(-1);
            }
        });

        binding.btnMatrizbajar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalViewModel.botonMatriz(1);
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

        binding.tv11.setText(matriz[0][0]);
        binding.tv12.setText(matriz[0][1]);
        binding.tv13.setText(matriz[0][2]);
        binding.tv21.setText(matriz[1][0]);
        binding.tv22.setText(matriz[1][1]);
        binding.tv23.setText(matriz[1][2]);
        binding.tv31.setText(matriz[2][0]);
        binding.tv32.setText(matriz[2][1]);
        binding.tv33.setText(matriz[2][2]);
        binding.tv41.setText(matriz[3][0]);
        binding.tv42.setText(matriz[3][1]);
        binding.tv43.setText(matriz[3][2]);
        binding.tv51.setText(matriz[4][0]);
        binding.tv52.setText(matriz[4][1]);
        binding.tv53.setText(matriz[4][2]);
        binding.tv61.setText(matriz[5][0]);
        binding.tv62.setText(matriz[5][1]);
        binding.tv63.setText(matriz[5][2]);
        binding.tv71.setText(matriz[6][0]);
        binding.tv72.setText(matriz[6][1]);
        binding.tv73.setText(matriz[6][2]);
        binding.tv81.setText(matriz[7][0]);
        binding.tv82.setText(matriz[7][1]);
        binding.tv83.setText(matriz[7][2]);







    }





}