package com.ulp.instituto.ui.perfil;


import android.app.Person;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.ulp.instituto.R;
import com.ulp.instituto.databinding.FragmentPerfilBinding;
import com.ulp.instituto.modelo.Persona;
import com.ulp.instituto.databinding.FragmentPerfilBinding;


public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    private com.ulp.instituto.ui.perfil.PerfilViewModel vmPerfil;
    private EditText etCodigo, etNombre, etApellido, etDni, etEmail, etTelefono, etPassword;
    private Button btEnviarGuardar;
    private Button btnCambiarClave;
    private ImageView ivAvatar;
    private int avatar;
    private String claveOriginal;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        vmPerfil = new ViewModelProvider(this).get(com.ulp.instituto.ui.perfil.PerfilViewModel.class);

        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        btEnviarGuardar = view.findViewById(R.id.btEnviarGuardar);

        vmPerfil.getMutablePersona().observe(getViewLifecycleOwner(), new Observer<Persona>() {
            @Override
            public void onChanged(Persona persona) {
                binding.etCodigo.setText(persona.getId() + "");
                binding.etNombre.setText(persona.getNombre());
                binding.etApellido.setText(persona.getApellido());
                binding.etDni.setText(persona.getDni() + "");
                binding.etEmail.setText(persona.getEmail());
              //claveOriginal = persona.getClave();
                //etPassword.setText(propietario.getClave());
                //etTelefono.setText(persona.getTelefono() + "");
           //     avatar = propietario.getAvatar();
                //ivAvatar.setImageResource(avatar);

            }
        });

        vmPerfil.getMutableEditEnabled().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean enabled) {
                binding.etApellido.setEnabled(enabled);
                binding.etDni.setEnabled(enabled);
                binding.etEmail.setEnabled(enabled);
                binding.etNombre.setEnabled(enabled);
                //etPassword.setEnabled(enabled);
                //binding.etTelefono.setEnabled(enabled);

            }
        });

        vmPerfil.getMutableButtonText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String textBoton) {
                btEnviarGuardar.setText(textBoton);
            }
        });


        inicializarVista(view);


        btEnviarGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vmPerfil.EditarGuardar(btEnviarGuardar.getText().toString(), getPersona());
            }
        });

        btnCambiarClave = view.findViewById(R.id.btnCambioClave);
        btnCambiarClave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.cambioClave);
            }
        });


        return view;
    }

    public void inicializarVista(View view) {

        /*
        etCodigo = view.findViewById(R.id.etCodigo);
        etNombre = view.findViewById(R.id.etNombre);
        etApellido = view.findViewById(R.id.etApellido);
        etDni = view.findViewById(R.id.etDni);
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        etTelefono = view.findViewById(R.id.etTelfono);
        ivAvatar = view.findViewById(R.id.ivAvatar);
        btEnviarGuardar = view.findViewById(R.id.btEnviarGuardar);
        */

        vmPerfil.ObtenerUsuario();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    private Persona getPersona() {
        int id = Integer.parseInt(binding.etCodigo.getText().toString());
        String nombre = binding.etNombre.getText().toString();
        String apellido = binding.etApellido.getText().toString();
        String dni = binding.etDni.getText().toString();
        String email = binding.etEmail.getText().toString();
        String pass = binding.etPassword.getText().toString();
        Persona p = new Persona(id, nombre, apellido,dni, email,"" , "Alumno", "");

        return p;
    }
}
