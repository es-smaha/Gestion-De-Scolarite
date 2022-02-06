package com.example.schoolmanagement.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schoolmanagement.Models.DataBaseHelper;
import com.example.schoolmanagement.R;

public class branche extends AppCompatActivity {
    DataBaseHelper mydb;
    EditText b , m  ;
    Button addb , addm ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branche);
           mydb = new DataBaseHelper(this);
           b =(EditText)findViewById(R.id.b2);
           m =(EditText)findViewById(R.id.filliere);
           addb =(Button)findViewById(R.id.addb);
           addm = (Button)findViewById(R.id.addm);



    }
    public void insert(View v){
        Boolean bl =  mydb.insertbranche(b.getText().toString());
        if(bl = true) Toast.makeText(branche.this,"data inserted" , Toast.LENGTH_LONG).show();
        else
            Toast.makeText(branche.this,"not inserted" , Toast.LENGTH_LONG).show();
    }
    public void insertmodule(View v){
        Boolean bl =  mydb.insertmodule(m.getText().toString());
        if(bl = true) Toast.makeText(branche.this,"module inserted" , Toast.LENGTH_LONG).show();
        else
            Toast.makeText(branche.this,"not inserted" , Toast.LENGTH_LONG).show();
    }

}