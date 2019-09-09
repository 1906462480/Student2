package com.example.student.activity;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.student.R;
import com.example.student.dao.StudentDaoImpl;
import com.example.student.entity.Student;


import java.util.Arrays;
import java.util.List;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSave, btnCancel;
    private EditText etName, etAge;
    private Spinner spClassmate;
    private StudentDaoImpl studentDaoImpl;
    private List<String> classmates;
    private Student student;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        etName = findViewById(R.id.name);
        etAge = findViewById(R.id.old);
        btnSave = findViewById(R.id.ok);
        btnCancel = findViewById(R.id.break1);
        spClassmate = findViewById(R.id.spinner);
        initData();

        studentDaoImpl = new StudentDaoImpl(this);
        etName.setOnClickListener(this);
        etAge.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        spClassmate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        student = (Student) bundle.getSerializable("update");
        if (student != null){
            SpinnerAdapter spinnerAdapter = spClassmate.getAdapter();
            for (int i=0;i<spinnerAdapter.getCount();i++){
                if (student.getClassmate().equals(spinnerAdapter.getItem(i).toString())){
                    spClassmate.setSelection(i);
                }
            }
            flag= student.getId();
            etName.setText(student.getName());
            etAge.setText(String.valueOf(student.getAge()));
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ok:
                addStudent();
                break;
            case R.id.break1:
                break;
        }

    }

    private void addStudent() {
        Student student = new Student();
        student.setName(etName.getText().toString());
        student.setClassmate(spClassmate.getSelectedItem().toString());
        student.setAge(Integer.parseInt(etAge.getText().toString()));

        studentDaoImpl.update(student);

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("1",student);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}
