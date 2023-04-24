package com.grade.dao;

import com.grade.connection.ConnectionManager;
import com.grade.model.Classes;
import com.grade.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoSQL implements StudentDao{
    private Connection conn = ConnectionManager.getConnection();

    @Override
    public List<Student> getAllStudents() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM student");

            List<Student> studentList = new ArrayList<Student>();


            while (rs.next()) {
                int sid = rs.getInt("sid");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");

                Student s = new Student();
                s.setId(sid);
                s.setFname(fname);
                s.setLname(lname);
                s.setGrade(0);

                studentList.add(s);

            }
            return studentList;
        }catch (SQLException e) {
            System.out.println("Could not retrieve list of students");

        }
        return null;
    }


    @Override
    public Student getStudentById(int sid) {
        try (PreparedStatement pstmt = conn.prepareStatement("select * from student where sid = ?")){
            pstmt.setInt(1, sid);

            ResultSet rs = pstmt.executeQuery();
            Student s = new Student();

            if(rs.next()) {
                sid = rs.getInt("sid");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");

                s.setId(sid);
                s.setFname(fname);
                s.setLname(lname);
                return s;

            }
            rs.close();

        } catch (SQLException e) {
            System.out.println("Student with id = " + sid + " not found.");
        }

        return null;
    }

    @Override
    public double getStudentGrade(int id) {
        return 0;
    }

    @Override
    public boolean updateStudentGrade(Student s) {
        try (PreparedStatement pstmt = conn.prepareStatement("update student_classes set grade = ? where sid = ?")){
            // set up prepared statement to get a department using its id
            pstmt.setDouble(1, s.getGrade());
            pstmt.setInt(2, s.getId());

            int i = pstmt.executeUpdate();

            if (i > 0) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Could not update student grade");
        }
        return false;
    }

    @Override
    public boolean deleteStudent(int sid) {
        try{
            PreparedStatement pstmt = conn
                    .prepareStatement("delete from student_classes where sid = ?");
            pstmt.setInt(1, sid);


            int i = pstmt.executeUpdate();

            if (i > 0) {
                return true;
            }

        }
        catch (SQLException e){
            System.out.println("Failed to delete student");
        }
        return false;
    }
}
