package com.grade.dao;

import com.grade.model.Classes;
import com.grade.model.Student;

import java.util.List;

public interface ClassesDao {
    public List<Classes> getAllClasses();

    public Classes getClassById(int cid);
    public String getClassNameById(int cid);
    public List<Classes> getClassesByTeacherId(int tid);
    public List<Student> getStudentsInClass(int cid);
    public List<Student> getStudentsInClassAlphabetically(int cid);
    public List<Student> getStudentsInClassByGrade(int cid);

    public double getClassAverage(int cid);
    public double getClassMedian(int cid);

    public boolean addClass(Classes c);


}
