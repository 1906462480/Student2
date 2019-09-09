package com.example.student.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.DhcpInfo;

import com.example.student.entity.Student;
import com.example.student.utils.DHelper;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl  implements  StudentDao{
    private DHelper dHelper;
    private SQLiteDatabase sqLiteDatabase;
    public StudentDaoImpl(Context context){
        dHelper = new DHelper(context);
    }
    @Override
    public List<Student> selectAllStudent() {
        String sql = "select * from t_student_info";
        List<Student> students = null;
        sqLiteDatabase=dHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor !=null &&cursor.getCount()>0){
           students = new ArrayList<>();
            while (cursor.moveToNext()){
                Student student = new Student();
                student.setId(cursor.getInt(cursor.getColumnIndex("id")));
                student.setName(cursor.getString(cursor.getColumnIndex("student_name")));
                student.setClassmate(cursor.getString(cursor.getColumnIndex("student_classmate")));
                student.setAge(cursor.getInt(cursor.getColumnIndex("student_age")));
                students.add(student);
            }
            cursor.close();
        }
        sqLiteDatabase.close();
        return students;
    }

    @Override
    public void insert(Student student) {
        String sql = "insert into t_student_info values(null,?,?,?)";
        sqLiteDatabase=dHelper.getWritableDatabase();
        sqLiteDatabase.execSQL(sql,new Object[]{
                student.getName(),
                student.getClassmate(),
                student.getAge()
        });
        sqLiteDatabase.close();
    }

    @Override
    public void update(Student student) {
        String sql = "update t_student_info set student_name=? ,student_classmate=? ,student_age=? where id=?";
        sqLiteDatabase = dHelper.getWritableDatabase();
        sqLiteDatabase.execSQL(sql,new Object[]{
                student.getName(),
                student.getClassmate(),
                student.getAge(),
                student.getId()
        });
        sqLiteDatabase.close();

    }

    @Override
    public void delete(String studentName) {
        sqLiteDatabase = dHelper.getWritableDatabase();
        String sql = "delete from t_student_info where student_name=?";
        sqLiteDatabase.execSQL(sql,new Object[]{studentName});

    }
}
