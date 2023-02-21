package com.ulp.instituto;

import static android.content.ContentValues.TAG;

import static com.ulp.instituto.request.Token.ObtenerToken;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.messaging.FirebaseMessaging;
import com.ulp.instituto.databinding.ActivityPrincipalBinding;
import com.ulp.instituto.modelo.Persona;
import com.ulp.instituto.request.ApiRetrofit;
import com.ulp.instituto.request.Token;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import services.MyFirebaseMessagingService;

public class Principal extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityPrincipalBinding binding;
    private ApiRetrofit api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Task<String> token = FirebaseMessaging.getInstance().getToken();
        token.addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String s) {
                String token = ObtenerToken(getApplication());

                Log.d("Token Viejo", token);
                Log.d("Token Nuevo", s);


                Call<String> tokenPromesa = ApiRetrofit.getServiceInstituto().actualizarTokenFirebase(token, s);
                tokenPromesa.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        if (response.isSuccessful()) {

                            Log.d("salida", "Token Firebase ok");
                        } else {
                            Log.d("salida", "Token Firebase sin respuesta");

                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("salida ", t.getMessage());

                    }
                });

            }
        });

        binding = ActivityPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarPrincipal.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inscripciones)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_principal);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View headerView = navigationView.getHeaderView(0);

        configuararHeader(headerView);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_principal);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void getToken(Context context) {

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        String msg = "Token" + token;
                        Log.d(TAG, msg);

                        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                    }
                });
    }

public void configuararHeader(View headerView){

    TextView tv_nombre = headerView.findViewById(R.id.tv_nombre);
    TextView tv_email = headerView.findViewById(R.id.tv_email);



    Persona persona;

    SharedPreferences sp= this.getSharedPreferences("token",0);
    //  String token=sp.getString("token","-1");
    String token = Token.ObtenerToken(this);

    Call<Persona> tokenPromesa = ApiRetrofit.getServiceInstituto().obtenerPerfil(token);
    tokenPromesa.enqueue(new Callback<Persona>() {
        @Override
        public void onResponse(Call<Persona> call, Response<Persona> response) {
            if (response.isSuccessful()) {

                Persona persona = response.body();
                // dentro de un callback usar postvalue y no setvalue

                tv_nombre.setText("Bienvenido " + persona.getNombre());
                tv_email.setText(persona.getEmail());

            } else {
                Log.d("salida", "persona sin respuesta");

            }
        }

        @Override
        public void onFailure(Call<Persona> call, Throwable t) {
            Log.d("salida ", t.getMessage());

        }
    });












};

}

