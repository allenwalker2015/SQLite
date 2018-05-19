package com.sama.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uca on 05-16-18.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "bd_usuarios";
    public static final String TABLA_USUARIO = "Estudiante";
    public static final String CAMPO_CARNET = "carnet";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_NOTA = "nota";
    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE " + TABLA_USUARIO
            + "(" + CAMPO_CARNET + " TEXT ," +
            CAMPO_NOMBRE + " TEXT,"+
            CAMPO_NOTA+" TEXT)";
    public static DBHelper mydb;
    private Context context;
    static SQLiteDatabase db;


    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
        db = this.getWritableDatabase();
    }

    public static DBHelper getInstance(Context context) {
        if (mydb == null) {
            mydb = new DBHelper(context.getApplicationContext());
        }
        return mydb;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_USUARIO);
        Log.d("DB", "onCreate: "+ CREAR_TABLA_USUARIO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CAMPO_NOMBRE);
    }




    public boolean add(Estudiante p){
        ContentValues values=new ContentValues();
        values.put(CAMPO_CARNET,p.getCarnet());
        values.put(CAMPO_NOMBRE,p.getNombre());
        values.put(CAMPO_NOTA,p.getNota());
        try {
            db.insert(TABLA_USUARIO, null,values);
        } catch (SQLiteConstraintException e) {
            Toast.makeText(context, "Error inserting record", Toast.LENGTH_SHORT).show();
            Log.d("ERROR",e.getMessage());
        } catch (Exception e) {
            // Just in case the above doesn't catch it
            Toast.makeText(context, "Error inserting record", Toast.LENGTH_SHORT).show();
            Log.d("ERROR",e.getMessage());
        }

        Toast.makeText(context,"Insertado con exito",Toast.LENGTH_SHORT).show();
        return true;
    }

    public boolean editUser(Estudiante p){
        String [] parametros={p.getCarnet()};
        String [] campos={CAMPO_NOMBRE};
        ContentValues values=new ContentValues();
        values.put(CAMPO_NOMBRE,p.getNombre());
        values.put(CAMPO_NOTA,p.getNota());
        db.update(TABLA_USUARIO,values, CAMPO_CARNET +"=?",parametros);
        Toast.makeText(context,"Actualizado con exito",Toast.LENGTH_SHORT).show();
        return true;
    }
    public boolean deleteUser(String dui){
        String [] parametros={dui};
        db.delete(TABLA_USUARIO, CAMPO_CARNET +"=?",parametros);
        Toast.makeText(context,"Eliminado con exito",Toast.LENGTH_SHORT).show();
        return true;
    }
    public Estudiante findUser(String dui) {
        Estudiante p=null;
        String[] parametros = {dui};
        String[] campos = {CAMPO_NOMBRE,CAMPO_NOTA};

        try {
            Cursor cursor = db.query(TABLA_USUARIO, campos, CAMPO_CARNET + "=?", parametros, null, null, null);
            while(cursor.moveToNext()) {
                Log.d("ENTRO", "findUser: ENTRO");
                p = new Estudiante( cursor.getString(0),dui, cursor.getString(1));
            }
        } catch (Exception e) {
            p = null;
        }
        return p;
    }

    public List<Estudiante> getAllUsers() {
        List<Estudiante> list=new ArrayList<>();
        Estudiante p=null;
        String[] campos = {CAMPO_CARNET,CAMPO_NOMBRE,CAMPO_NOTA};

        try {
            Cursor cursor = db.query(TABLA_USUARIO, campos, null, null, null, null, null);
            while(cursor.moveToNext()) {
                Log.d("ENTRO", "findUser: ENTRO");
                p = new Estudiante( cursor.getString(1),cursor.getString(0), cursor.getString(2));
                list.add(p);
            }
        } catch (Exception e) {
            list = null;
        }
        return list;
    }

    public float getAvg() {
        float avg=0;
        try {
            String query = "SELECT AVG("+CAMPO_NOTA+")" + " FROM " + TABLA_USUARIO;
            Cursor cursor = db.rawQuery(query,null);
            while(cursor.moveToNext()) {
                 avg = cursor.getFloat(0);
            }
        } catch (Exception e) {
            avg = 0;
        }
        return avg;
    }


}

