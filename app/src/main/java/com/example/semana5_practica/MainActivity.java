package com.example.semana5_practica;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void guardarPreferencia(View v) {
        SharedPreferences miPreferenciaCompartida = getSharedPreferences("MisDatosPersonales", MODE_PRIVATE);
        SharedPreferences.Editor editor = miPreferenciaCompartida.edit();
        EditText etNombre = findViewById(R.id.etNombre);
        EditText etCorreo = findViewById(R.id.etCorreo);

        String nombre = etNombre.getText().toString();
        String correo = etCorreo.getText().toString();

        editor.putString("nombre", nombre);
        editor.putString("correo", correo);
        
        editor.apply();

        Toast.makeText(this, "Se ha creado la preferencia compartida", Toast.LENGTH_SHORT).show();

        etNombre.setText("");
        etCorreo.setText("");
    }

    public void mostrarPreferencia(View view) {
        SharedPreferences miPreferenciaCompartidad = getSharedPreferences("MisDatosPersonales",MODE_PRIVATE);
        String nombre = miPreferenciaCompartidad.getString("nombre","No existe esa variable");
        String correo = miPreferenciaCompartidad.getString("correo","No existe esa variable");

        TextView tvPreferenciaCompartida = findViewById(R.id.tvPreferenciaCompartida);
        String preferencia = "\nNombre: " + nombre + "\nCorreo: " + correo;
        tvPreferenciaCompartida.setText(preferencia);
    }

    public void generarArchivo(View v) {
        try {

            EditText etNombre = findViewById(R.id.etNombre);
            String nombre = etNombre.getText().toString();

            FileOutputStream outputStream = null;
            outputStream = openFileOutput("MiArchivo.txt", MODE_PRIVATE);
            outputStream.write(nombre.getBytes());
            outputStream.close();
            etNombre.setText("");

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}