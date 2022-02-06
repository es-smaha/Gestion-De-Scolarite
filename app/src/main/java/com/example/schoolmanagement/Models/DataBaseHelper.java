package com.example.schoolmanagement.Models;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DataBaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "SchoolM.db";
    public static final String TABLE1= "branche";
    public static final String TABLE2= "module";
    public static final String TABLE3= "student";
    public static final String PLANNING = "planning";
    public static final String EVALUATION = "evaluation";

    // branches branche
    public static final String col_id = "ID";
    public static final String col_br = "NAME";
    // branches module
    public static final String col_idm = "ID";
    public static final String col_m = "NAME_M";
    // branches the relationship between branch
    public static final String planning_id= "ID";
    public static final String module_id= "module_id";
    public static final String filliere_id = "branch_id";
    public static final String niveau = "NIVEAU";
    // student columns
    public static final String ids = "ID";
    public static final String name = "NAME";
    public static final String last= "LAST";
    public static final String cne = "CNE";
    public static final String age= "AGE";
    public static final String sexe = "SEXE";
    public static final String branche_id= "branche_id";

    // student evauations

    public static final String eval_id = "ID";
    public static final String note = "NOTE";
    public static final String student_id = "student_id";





    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "CREATE TABLE " + TABLE1 + "("
                +col_id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + col_br + " TEXT)";

        String query2 = "CREATE TABLE " + TABLE3 + "("
                +ids+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +name+" TEXT ,"
                +last + " TEXT ,"
                +cne+ " TEXT,"
                +age +" INTEGER,"
                +sexe+" TEXT,"
                +branche_id+" INTEGER ,"
                +" FOREIGN KEY ("+branche_id+") REFERENCES "+" branche(id)"+
                ")";
        String query3 = "CREATE TABLE " + TABLE2+ "("
                +col_idm+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + col_m + " TEXT)";

        String query4 = "CREATE TABLE " + PLANNING+ " ("
                +planning_id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + niveau + " TEXT ,  "
                + filliere_id+" INTEGER ,"
                + module_id+ " INTEGER ,"
                +" FOREIGN KEY ("+filliere_id+") REFERENCES "+" branche(id) , "

                +" FOREIGN KEY ("+module_id+") REFERENCES "+" module(id) "+

                ")";

        String query5 = "CREATE TABLE " + EVALUATION+ " ("
                + eval_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + note + " DOUBLE ,  "
                + student_id + " INTEGER ,"
                + module_id+ " INTEGER ,"
                +" FOREIGN KEY ("+student_id+") REFERENCES "+" student(id) , "

                +" FOREIGN KEY ("+module_id+") REFERENCES "+" module(id) "+

                ")";

        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        db.execSQL(query5);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query1 = "DROP TABLE IF EXISTS " + TABLE1;
        String query2 = "DROP TABLE IF EXISTS " + TABLE3;
        String query3 = "DROP TABLE IF EXISTS " + TABLE2;
        String query4 = "DROP TABLE IF EXISTS " + PLANNING;
        String query5 = "DROP TABLE IF EXISTS " + EVALUATION;


        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        db.execSQL(query5);
        onCreate(db);
    }
    public boolean insertbranche(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(col_br, name);
        long res = db.insert(TABLE1, null, contentValue);
        if (res == -1)
            return false;
        else
            return true;

    }

    public boolean insertmodule(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(col_m, name);
        long res = db.insert(TABLE2, null, contentValue);
        if (res == -1)
            return false;
        else
            return true;

    }
    public boolean insertplanning (String name , Integer B_id , Integer M_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(niveau, name);
        contentValue.put(student_id, B_id);
        contentValue.put(module_id, M_id);
        long res = db.insert(PLANNING, null, contentValue);
        if (res == -1)
            return false;
        else
            return true;

    }
    public boolean insertEvaluation (double note1 , Integer stud_id , Integer mod_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(String.valueOf(note), note1);
        contentValue.put(student_id, stud_id);
        contentValue.put(module_id, mod_id);
        long res = db.insert(EVALUATION, null, contentValue);
        if (res == -1)
            return false;
        else
            return true;

    }


    public ArrayList getPlanning(Integer id ){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "SELECT * from module where id IN" +
                "(select module_id from planning " +
                "where planning.branch_id = "+ id +
                " );" ,  null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            list.add(cursor.getString(cursor.getColumnIndexOrThrow("NAME_M")));
            cursor.moveToNext();
        }


        return list;

    }

//    SELECT DISTINCT branche.NAME , module.NAME_M  from module , planning , branche
//    where  planning.module_id = module.ID
//    AND branche.id = 1


    // insert inscription
    public boolean insertInsription(String n, String l , String Cne , Integer Age, String Sexe , Integer b_Id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(name, n);
        contentValue.put(last, l);
        contentValue.put(cne, Cne);
        contentValue.put(age, Age);
        contentValue.put(sexe, Sexe);
        contentValue.put(branche_id, b_Id);

        long res = db.insert(TABLE3, null, contentValue);
        if (res == -1)
            return false;
        else
            return true;

    }
    public Cursor getAllbranche(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select " + TABLE1, null);
        return res;

    }
    public ArrayList getbranche(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE1 , null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            list.add(cursor.getString(cursor.getColumnIndexOrThrow("NAME")));
            cursor.moveToNext();
        }
        return list;

    }
    // get all modules
    public ArrayList getModule(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT DISTINCT * FROM "+ TABLE2 , null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            list.add(cursor.getString(cursor.getColumnIndexOrThrow("NAME_M")));
            cursor.moveToNext();
        }
        return list;

    }
    public ArrayList getstudent(Integer id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT DISTINCT * FROM " + TABLE3 + " INNER JOIN " + TABLE1 + " ON " + TABLE3 + ".branche_id = " + TABLE1 +
                ".id " + "AND "+ TABLE1 + ".ID = "+id , null);
        //cursor.moveToFirst();
        while(cursor.moveToNext()){
           // list.add(cursor.getString(cursor.getColumnIndexOrThrow("NAME")));
            list.add(cursor.getString(cursor.getColumnIndexOrThrow("LAST")));
            list.add(cursor.getString(cursor.getColumnIndexOrThrow("STUDENT.NAME")));
            list.add(cursor.getString(cursor.getColumnIndexOrThrow("CNE")));
            list.add(cursor.getString(cursor.getColumnIndexOrThrow("AGE")));
           // cursor.moveToNext();
        }
        // SELECT student.last , branche.name from student
        //INNER JOIN branche ON student.branche_id= branche.ID;
        return list;

    }
    public ArrayList getstudentm(Integer id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT DISTINCT * FROM " + TABLE3 + " INNER JOIN " + TABLE1 + " ON " + TABLE3 + ".branche_id = " + TABLE1 +
                ".id " + "AND "+ TABLE1 + ".ID = "+id , null);
        //cursor.moveToFirst();
        while(cursor.moveToNext()){
            // list.add(cursor.getString(cursor.getColumnIndexOrThrow("NAME")));
            list.add(cursor.getString(cursor.getColumnIndexOrThrow("LAST")));

            // cursor.moveToNext();
        }

        return list;

    }
    public Cursor getAllstudent(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT DISTINCT * FROM " + TABLE3 + " INNER JOIN " + TABLE1 + " ON " + TABLE3 + ".branche_id = " + TABLE1 +
                ".id " + "AND "+ TABLE1 + ".ID = "+id, null);
        return res;
    }

    public ArrayList geteval(Integer id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT  *  from module , evaluation , student " +
                "where  evaluation.module_id = module.ID " +
                "AND evaluation.student_id = student.ID " +
                "AND student.id = " + id, null);
        //cursor.moveToFirst();
        while(cursor.moveToNext()){
//            list.add(cursor.getString(cursor.getColumnIndexOrThrow("NAME")));
//            list.add(cursor.getString(cursor.getColumnIndexOrThrow("LAST")));
            //list.add(cursor.getString(cursor.getColumnIndexOrThrow("SEXE")));
            list.add(cursor.getString(cursor.getColumnIndexOrThrow("NAME_M")));
            list.add(cursor.getString(cursor.getColumnIndexOrThrow("NOTE")));
            // cursor.moveToNext();
        }
        // SELECT student.last , branche.name from student
        //INNER JOIN branche ON student.branche_id= branche.ID;
        return list;
}}
