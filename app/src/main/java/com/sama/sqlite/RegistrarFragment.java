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

/**
 * Created by uca on 05-16-18.
 */

public class RegistrarFragment extends Fragment {
    EditText id,nombre;
    Button enviar;
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.activity_registrar,container,false);
        inicializarControles();
        DBHelper.getInstance(getActivity());
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = DBHelper.mydb.add(new Estudiante(nombre.getText().toString(),id.getText().toString(), "0"));
                if(flag){
                    Log.d("ADD", nombre.getText().toString());
                }
            }
        });

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void inicializarControles(){
        id = v.findViewById(R.id.id_txt);
        nombre = v.findViewById(R.id.name_txt);
        enviar = v.findViewById(R.id.enviar);
    }
}
