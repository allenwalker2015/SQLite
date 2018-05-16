package com.sama.sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by uca on 05-16-18.
 */

public class ModificarActivity extends AppCompatActivity {
    EditText id,nombre;
    Button guardar,eliminar,clean,buscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        inicializarControles();
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = DBHelper.mydb.editUser(new Persona(id.getText().toString(),nombre.getText().toString()));
                if(flag){
                    Log.d("EDIT", nombre.getText().toString());
                }
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper.mydb.deleteUser(nombre.getText().toString());
                limpiar();
            }
        });

        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Persona p= DBHelper.mydb.findUser(id.getText().toString());
                if(p==null){
                    Toast.makeText(getApplicationContext(),"usuario no encontrado",Toast.LENGTH_SHORT);
                    limpiar();
                }else{
                    nombre.setText(p.getNombre());
                }
            }
        });

    }

    public void limpiar(){
        nombre.setText("");
        id.setText("");
    }
    public void inicializarControles(){
        id = findViewById(R.id.id_txt);
        nombre = findViewById(R.id.name_txt);
        guardar = findViewById(R.id.guardar);
        eliminar = findViewById(R.id.eliminar);
        clean = findViewById(R.id.limpiar);
        buscar = findViewById(R.id.buscar_boton);
    }
}
