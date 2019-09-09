package com.example.student.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.student.R;
import com.example.student.adapter.StudentAdapter;
import com.example.student.dao.StudentDaoImpl;
import com.example.student.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static  final  int ADD=103;
    private static final int UPDATE=104;
    private Student selectStudent;
    private StudentDaoImpl studentDaoImpl;
    private int selectPos;
    private StudentAdapter studentAdapter;
    private List<Student> students;
private ListView listView;
private Button etAdd;
private Button etFix;
private Button etDel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);

        etAdd = findViewById(R.id.add);
        etFix = findViewById(R.id.fix);
        etDel = findViewById(R.id.del);


        etAdd.setOnClickListener(this);
        etFix.setOnClickListener(this);
        etDel.setOnClickListener(this);
        initData();
        studentAdapter=new StudentAdapter(students);
        listView.setAdapter(studentAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                studentAdapter.setSelectItem(position);
                selectPos=position;
                selectStudent = (Student) parent.getItemAtPosition(position);
                studentAdapter.notifyDataSetInvalidated();
            }
        });
    }

    private void initData() {
        studentDaoImpl = new StudentDaoImpl(this);
        students=studentDaoImpl.selectAllStudent();

        if (students == null){
            students = new ArrayList<>();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add:
                Intent intent = new Intent(MainActivity.this,ADDActivity.class);
                startActivityForResult(intent,ADD);
                break;
            case R.id.fix:
                if (selectStudent != null){
                    Intent intent1 = new Intent(MainActivity.this ,UpdateActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("update",selectStudent);
                    intent1.putExtras(bundle);
                    startActivityForResult(intent1,UPDATE);
                }
                break;
            case R.id.del:
                if (selectStudent != null){
                    studentDaoImpl.delete(selectStudent.getName());
                    students.remove(selectPos);
                    studentAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK ){
            return;
        }
        if (data !=null){
            Bundle bundle = data.getExtras();
            if (bundle== null){
                return;
            }
            selectStudent= (Student) bundle.get("1");
            if (requestCode == UPDATE){
                students.set(selectPos,selectStudent);
            }
            if (requestCode == ADD){
                students.add(selectStudent);
            }
            studentAdapter.notifyDataSetChanged();
        }
    }
}
