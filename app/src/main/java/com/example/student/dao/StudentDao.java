package com.example.student.dao;

import com.example.student.entity.Student;

import java.util.List;

public interface StudentDao {
    List<Student> selectAllStudent();
    void insert(Student student);
    void update(Student student);
    void delete(String studentName);
}
