package com.sama.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button registrar,consultar,lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarControles();
        DBHelper.getInstance(this);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegistrarActivity.class);
                startActivity(intent);
            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ModificarActivity.class);
                startActivity(intent);
            }
        });
        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ListaActivity.class);
                startActivity(intent);
            }
        });
    }

    public void inicializarControles(){
        registrar = findViewById(R.id.registrar);
        consultar = findViewById(R.id.ver_notas);
        lista = findViewById(R.id.consultar);
    }
}
