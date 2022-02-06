package com.example.schoolmanagement.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.schoolmanagement.Models.DataBaseHelper;
import com.example.schoolmanagement.R;

import java.util.ArrayList;

public class inscription extends AppCompatActivity {
    Spinner spin ;
    DataBaseHelper mydb;
    EditText  nom , last , cne , age , sexe;
    ArrayList<String> filiere;

    ArrayList<String> student;
    CheckBox F , M ;
    Button b ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        spin = (Spinner)findViewById(R.id.spin);
        mydb = new DataBaseHelper(this);
        filiere = mydb.getbranche();
        nom = (EditText)findViewById(R.id.nom);
        last = (EditText)findViewById(R.id.last);
        cne = (EditText)findViewById(R.id.cne);
        age = (EditText)findViewById(R.id.age);
        F=(CheckBox)findViewById(R.id.F);
        M= (CheckBox)findViewById(R.id.M);
        spin=(Spinner)findViewById(R.id.spin);
        b = (Button)findViewById(R.id.button);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, filiere);
        spin.setAdapter(arrayAdapter);

    }

    public void addc(View v ){
        String sexe = "";
        if(F.isChecked()){
            sexe ="female";

        }else if (M.isChecked()){
            sexe = "male";
        }
        Boolean bl =  mydb.insertInsription(nom.getText().toString(),last.getText().toString(),
                cne.getText().toString(),Integer.parseInt(age.getText().toString()),sexe ,spin.getSelectedItemPosition()+1);
        if(bl = true) Toast.makeText(inscription.this,"data inserted" , Toast.LENGTH_LONG).show();
        else
            Toast.makeText(inscription.this,"not inserted" , Toast.LENGTH_LONG).show();

    }

    public void go(View v){
        startActivity(new Intent(inscription.this, viewstudent.class));
        finish();

    }

}