package com.grade.dao;

import com.grade.model.Classes;
import com.grade.model.Teacher;

import java.util.List;

public interface TeacherDao {

    public List<Teacher> getAllTeachers();
    public Teacher getTeacherById(int id);
    public Teacher getTeacherByEmail(String email);

    public boolean addTeacher(Teacher t);

    public boolean addTeacherClass(Teacher t, Classes c);

}
