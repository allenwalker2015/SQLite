package com.sama.sqlite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by uca on 05-16-18.
 */

public class ModificarFragment extends Fragment {
    private EditText id,nombre,nota;
    private Button guardar,eliminar,clean,buscar;
    View v;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_modificar,container,false);
        inicializarControles();

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = DBHelper.mydb.editUser(new Estudiante(nombre.getText().toString(),id.getText().toString(),nota.getText().toString()));
                if(flag){
                    Log.d("EDITED", nombre.getText().toString());
                }
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper.mydb.deleteUser(id.getText().toString());
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
                Estudiante p= DBHelper.mydb.findUser(id.getText().toString());
                if(p==null){
                    Toast.makeText(getActivity(),"usuario no encontrado",Toast.LENGTH_SHORT);
                    limpiar();
                }else{

                    nombre.setText(p.getNombre());
                    nota.setText(p.getNota());
                }
            }
        });

        return v;
    }

    public void limpiar(){
        nombre.setText("");
        id.setText("");
        nota.setText("");
    }
    public void inicializarControles(){
        id = v.findViewById(R.id.id_txt);
        nombre = v.findViewById(R.id.name_txt);
        guardar = v.findViewById(R.id.guardar);
        eliminar = v.findViewById(R.id.eliminar);
        clean = v.findViewById(R.id.limpiar);
        buscar = v.findViewById(R.id.buscar_boton);
        nota = v.findViewById(R.id.nota_txt);
    }
}
