package com.example.student.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.student.R;
import com.example.student.entity.Student;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private List<Student> students;
    private int selectItem=-1;

    public StudentAdapter(List<Student> students) {
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student,parent,false);
        Student student = students.get(position);
        TextView name = convertView.findViewById(R.id.btn_name);
        TextView classmate = convertView.findViewById(R.id.btn_class);
        TextView age = convertView.findViewById(R.id.btn_old);

        name.setText(student.getName());
        classmate.setText(student.getClassmate());
        age.setText(String.valueOf(student.getAge()));

        if (selectItem == position){
            convertView.setBackgroundColor(Color.YELLOW);
        }
        else {
            convertView.setBackgroundColor(Color.TRANSPARENT);
        }
        return convertView;
    }

    public void setSelectItem(int selectItem){
        this.selectItem=selectItem;
    }
}
