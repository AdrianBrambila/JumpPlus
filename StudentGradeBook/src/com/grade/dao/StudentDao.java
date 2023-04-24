package com.grade.dao;

import com.grade.model.Student;

import java.util.List;

public interface StudentDao {

    public List<Student> getAllStudents();
    public Student getStudentById(int sid);
    public double getStudentGrade(int id);
    public boolean updateStudentGrade(Student s);
    public boolean deleteStudent(int sid);




}
