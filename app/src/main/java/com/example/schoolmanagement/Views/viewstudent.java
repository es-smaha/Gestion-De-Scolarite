package com.example.schoolmanagement.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.schoolmanagement.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import com.example.schoolmanagement.Models.DataBaseHelper;
public class viewstudent extends AppCompatActivity {
    Spinner spin ;
    //ListView l ;
    GridView grid ;
    ArrayList<String> filiere;
    ArrayList<String> student;
    DataBaseHelper mydb;
    Button b ;
    ArrayAdapter<String> adapter;

    HashSet<String> j ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewstudent);
        spin = (Spinner)findViewById(R.id.spin);
        //l = (ListView) findViewById((R.id.list));
        b = (Button)findViewById(R.id.btn);
        //l = (ListView)findViewById(R.id.list);
        mydb = new DataBaseHelper(this);
        grid = (GridView)findViewById(R.id.grid);
        student = new ArrayList<String>();
        filiere = mydb.getbranche();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, filiere);
        spin.setAdapter(arrayAdapter);
        j = new HashSet<String>();

    }

    public void affiche(View v ){
        student = mydb.getstudent(spin.getSelectedItemPosition()+1);

        Toast.makeText(viewstudent.this,student.get(2), Toast.LENGTH_LONG).show();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, student);
        grid.setAdapter(adapter);

        //adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_gallery_item, student);
//        Cursor res = mydb.getAllstudent(spin.getSelectedItemPosition()+1);
//        res.moveToFirst();
//        if(res != null ){
//            while(!res.isAfterLast()){
//                student.add(res.getString(res.getColumnIndexOrThrow("LAST")));
//                student.add(res.getString(res.getColumnIndexOrThrow("AGE")));
//                student.add(res.getString(res.getColumnIndexOrThrow("CNE")));
//
//                res.moveToNext();
//
//            };
//            adapter = new ArrayAdapter<String>(viewstudent.this, android.R.layout.simple_list_item_1, student);
//            grid.setAdapter(adapter);
//
//        }else{
//            Toast.makeText(viewstudent.this,"no data", Toast.LENGTH_LONG).show();
//        }


//        res.close();
//        mydb.close();

    }
}