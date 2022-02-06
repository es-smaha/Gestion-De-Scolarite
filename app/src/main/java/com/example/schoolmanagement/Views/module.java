package com.example.schoolmanagement.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.schoolmanagement.Models.DataBaseHelper;
import com.example.schoolmanagement.R;

import java.util.ArrayList;

public class module extends AppCompatActivity {
    Spinner module , filliere  , showm , spin2 ;
    Button add;
    ArrayList<String> filiere;
    ArrayList<String> mod;
    DataBaseHelper mydb;
    EditText niv ;
    ArrayList<String> res;
    ListView l ;

    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
        filliere  = (Spinner)findViewById(R.id.spinf);
        module    = (Spinner)findViewById(R.id.spinm) ;
        //showm    = (Spinner)findViewById(R.id.showm) ;
        spin2 =(Spinner)findViewById(R.id.spinf2);
         l = (ListView) findViewById(R.id.list);
        niv = (EditText)findViewById(R.id.niv);
        mydb = new DataBaseHelper(this);
        // show fields
        filiere = mydb.getbranche();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, filiere);
        filliere.setAdapter(arrayAdapter);
        // show modules
        mod = mydb.getModule();
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mod);
        module.setAdapter(arrayAdapter2);
        spin2.setAdapter(arrayAdapter);

    }

    public void add(View v ){
        Boolean bl =  mydb.insertplanning(niv.getText().toString() ,filliere.getSelectedItemPosition()+1, module.getSelectedItemPosition()+1);
        if(bl = true) Toast.makeText(module.this,"data inserted" , Toast.LENGTH_LONG).show();
        else
            Toast.makeText(module.this,"not inserted" , Toast.LENGTH_LONG).show();

    }
    public void show(View v ){
        res =  mydb.getPlanning(filliere.getSelectedItemPosition()+1);
//        if( res != null){
//            ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, res);
//            showm.setAdapter(arrayAdapter3);
//        }
//
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, res);
        l.setAdapter(adapter);


    }
}