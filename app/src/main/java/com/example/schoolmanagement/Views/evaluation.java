package com.example.schoolmanagement.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.schoolmanagement.Models.DataBaseHelper;
import com.example.schoolmanagement.R;

import java.util.ArrayList;

public class evaluation extends AppCompatActivity {
    Spinner fil , mod , student ;
    Button btn ;
    GridView grid ;
    EditText note;
    EditText total;

    ArrayList<String> filiere;
    ArrayList<String> stud;
    ArrayList<String> res;
    ArrayList<Double> Notet;
    DataBaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);
        fil = (Spinner)findViewById(R.id.filliere);
        mod= (Spinner)findViewById(R.id.module);
        student= (Spinner)findViewById(R.id.student);
        note = (EditText)findViewById(R.id.note);
        total =note = (EditText)findViewById(R.id.id);
        grid = (GridView)findViewById(R.id.grid);
        mydb = new DataBaseHelper(this);
        filiere = mydb.getbranche();
        Notet = new ArrayList<Double>();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, filiere);
        fil.setAdapter(arrayAdapter);
        res =  mydb.getPlanning(fil.getSelectedItemPosition()+1);
        stud = mydb.getstudentm(fil.getSelectedItemPosition()+1);

//



    }
    public void showm(View v ){
        res =  mydb.getPlanning(fil.getSelectedItemPosition()+1);

//
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, res);
        mod.setAdapter(adapter);

        stud = mydb.getstudentm(fil.getSelectedItemPosition()+1);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, stud);
        student.setAdapter(adapter1);

    }
    public void insert(View v ){
//        Toast.makeText(evaluation.this,student.getSelectedItemPosition()+1 , Toast.LENGTH_LONG).show();

        Boolean bl =  mydb.insertEvaluation(Double.parseDouble(note.getText().toString()),student.getSelectedItemPosition()+1,mod.getSelectedItemPosition()+1);

        if(bl = true) Toast.makeText(evaluation.this,"data inserted" , Toast.LENGTH_LONG).show();
        else
            Toast.makeText(evaluation.this,"not inserted" , Toast.LENGTH_LONG).show();

    }
    public void buletin(View v ){
//        Toast.makeText(evaluation.this,student.getSelectedItemPosition()+1 , Toast.LENGTH_LONG).show();

        stud = mydb.geteval(student.getSelectedItemPosition()+1);
        Notet.add(Double.parseDouble(stud.get(1)));
        Double sum =0.0;
        Double moy =0.0;

        for (int i = 0; i < Notet.size(); i++)
            sum += Notet.get(i);
        moy = sum / Notet.size();
        total.setText(moy.toString());
        //Toast.makeText(evaluation.this,stud.get(2), Toast.LENGTH_LONG).show();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, stud);
        grid.setAdapter(adapter);
    }
}