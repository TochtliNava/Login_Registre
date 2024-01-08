package com.example.login_registre;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //Definir Vistas
    private EditText emailEditText, passwordEditText;

    // Definir credenciales validas
    private static final String VALID_EMAIL= "email";
    private static final String VALID_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inicializar vistas
        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        Button loginButton = findViewById(R.id.button);
        loginButton.setEnabled(false);

        // Agregar u listener al boton de inicio de sesion
        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            // Verificar si las credenciales son validas
            if (email.equals(VALID_EMAIL) && password.equals(VALID_PASSWORD)){
                // Credenciales validas, Mostrar un cuadro de dialogo de exito
                showSuccessDialog();
            } else {
                // Credenciales ivalidas mostrar un cuadro de dialogo de error
                showErrorDialog();
            }
        });


        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(emailEditText.getText()) && !TextUtils.isEmpty(passwordEditText.getText())) {
                    loginButton.setEnabled(true);
                } else {
                    loginButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(emailEditText.getText()) && !TextUtils.isEmpty(passwordEditText.getText())) {
                    loginButton.setEnabled(true);
                } else {
                    loginButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    private void showSuccessDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Inicio de Sesion Exitoso");
        builder.setIcon(R.drawable.exito);
        builder.setMessage("Has Iniciado session con exito");
        builder.setPositiveButton("Aceptar", (dialog, which) -> {
            Intent intent = new Intent(this, user.class);
            startActivity(intent);
        });
        builder.setCancelable(false);
        builder.show();
    }
    private void showErrorDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Inicio de Sesion Fallido");
        builder.setIcon(R.drawable.falla);
        builder.setMessage("Las credenciales Ingresadas son Incorrectas.");
        builder.setPositiveButton("Aceptar", null);
        builder.setCancelable(false);
        builder.show();
        emailEditText.setText(null);
        passwordEditText.setText(null);
    }
}