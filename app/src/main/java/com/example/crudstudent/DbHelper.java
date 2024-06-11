package com.example.crudstudent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME= "Crud";
    private static final int VERSION=1;
    public DbHelper(Context context) {
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table data (id integer primary key autoincrement , Username text , Class text , RollNo text)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists data");

    }

    public boolean insert_record(String name,String classs, String roll){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Username",name);
        values.put("Class",classs);
        values.put("RollNo",roll);


        long add=db.insert("data",null,values);
        if (add==-1){
            return false;
        }else {
            return true ;
        }

    }
/*
    public Cursor getinfo(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from data ",null);
        return cursor;
    }*/


    public ArrayList<StudentModel> getAllRecords() {
        ArrayList<StudentModel> studentList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM data", null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("Username"));
                String classs = cursor.getString(cursor.getColumnIndexOrThrow("Class"));
                String roll = cursor.getString(cursor.getColumnIndexOrThrow("RollNo"));
                studentList.add(new StudentModel(name, classs, roll));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return studentList;
    }

    public boolean updateRecord(StudentModel student) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Username", student.getName());
        values.put("Class", student.getStudentClass());
        values.put("RollNo", student.getRollNo());

        int updatedRows = db.update("data", values, "Username=?", new String[]{String.valueOf(student.getName())});
        return updatedRows > 0;
    }

    public boolean deleteRecord(String name) {
        SQLiteDatabase db = getWritableDatabase();
        int deletedRows = db.delete("data", "Username=?", new String[]{name});
        return deletedRows > 0;
    }

}


