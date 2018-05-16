package com.sama.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by uca on 05-16-18.
 */

public class RegistrarActivity extends AppCompatActivity {
    EditText id,nombre;
    Button enviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        inicializarControles();
        DBHelper.getInstance(this);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = DBHelper.mydb.add(new Persona(id.getText().toString(),nombre.getText().toString()));
                if(flag){
                    Log.d("ADD", nombre.getText().toString());
                }
            }
        });



    }

    public void inicializarControles(){
        id = findViewById(R.id.id_txt);
        nombre = findViewById(R.id.name_txt);
        enviar = findViewById(R.id.enviar);
    }
}
