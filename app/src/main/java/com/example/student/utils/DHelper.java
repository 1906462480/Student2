package com.example.student.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.DhcpInfo;

import com.example.student.entity.Student;

public class DHelper extends SQLiteOpenHelper {

    public DHelper(Context context){
        super(context,"student.db",null,3);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Student.TBL_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists t_student_info");
        onCreate(db);
    }
}
