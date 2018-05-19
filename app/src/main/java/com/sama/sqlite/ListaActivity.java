package com.sama.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class ListaActivity extends AppCompatActivity {
    TextView promedio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        RecyclerView rv = findViewById(R.id.recycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new Adapter(DBHelper.mydb.getAllUsers()));
        promedio = findViewById(R.id.promedio);
        promedio.setText(String.format("%.1f", DBHelper.mydb.getAvg()));
    }
}
