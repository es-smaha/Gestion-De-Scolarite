package com.example.schoolmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.schoolmanagement.Models.DataBaseHelper;
import com.example.schoolmanagement.Views.branche;
import com.example.schoolmanagement.Views.evaluation;
import com.example.schoolmanagement.Views.inscription;
import com.example.schoolmanagement.Views.module;


public class MainActivity extends AppCompatActivity{
    // declare card view ids
    private CardView ins , mod , br , ev;
    DataBaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mydb = new DataBaseHelper(this);
        ins = (CardView) findViewById(R.id.inscription);
        mod = (CardView) findViewById(R.id.filliere);
        br = (CardView) findViewById(R.id.branche);
        ev = (CardView) findViewById(R.id.evaluation);

    }

    public void cards(View v ){
        Intent i;
        switch(v.getId()){
            case R.id.inscription:
                i = new Intent(this, inscription.class);
                startActivity(i);
                break;
            case R.id.filliere:
                i = new Intent(this, module.class);
                startActivity(i);
                break;

            case R.id.branche:
                i = new Intent(this, branche.class);
                startActivity(i);
                break;

            case R.id.evaluation:
                i = new Intent(this, evaluation.class);
                startActivity(i);
                break;

        }
    }
}