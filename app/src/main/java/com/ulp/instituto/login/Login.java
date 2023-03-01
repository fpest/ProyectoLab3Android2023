package com.ulp.instituto.login;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ulp.instituto.R;

public class Login extends AppCompatActivity {

    private LoginViewModel viewModel;
    private TextView tvError;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private DetectarAgitar mDetectarAgitar;
    private Button btnNoRecuerdoPass;

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnNoRecuerdoPass = findViewById(R.id.btnNoRecuerdoPass);
        inicializarVista();


        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
                .create(LoginViewModel.class);
        viewModel.getErrorVisibility().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String mensaje) {

                Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG).show();


            }
        });










    }

    public void inicializarVista() {

        Button btLogin = findViewById(R.id.btLogin);
        EditText etUsuario = findViewById(R.id.etUsuario);
        EditText etPassword = findViewById(R.id.etPassword);

        etUsuario.setText("federico@ncdsanluis.com.ar");
        etPassword.setText("123" );

        tvError = findViewById(R.id.tvError);



        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.login(
                       etUsuario.getText().toString(),
                        etPassword.getText().toString()
                );
            }
        });


        btnNoRecuerdoPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.noRecueldoPass(
                        etUsuario.getText().toString()
                                        );
            }
        });










    }



}

