package com.grade.dao;

import com.grade.connection.ConnectionManager;
import com.grade.model.Classes;
import com.grade.model.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoSQL implements TeacherDao{
    private Connection conn = ConnectionManager.getConnection();

    @Override
    public List<Teacher> getAllTeachers() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM teacher");

            List<Teacher> teacherList = new ArrayList<Teacher>();


            while (rs.next()) {
                int id = rs.getInt("tid");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String email = rs.getString("email");
                String password = rs.getString("password");

                Teacher t = new Teacher();
                t.setId(id);
                t.setFname(fname);
                t.setLname(lname);
                t.setEmail(email);
                t.setPassword(password);

                teacherList.add(t);
            }
            return teacherList;
        }catch (SQLException e) {
            System.out.println("Could not retrieve list of teachers");

        }
        return null;
    }

    @Override
    public Teacher getTeacherById(int tid) {
        try (PreparedStatement pstmt = conn.prepareStatement("select * from teacher where tid = ?")){
            pstmt.setInt(1, tid);

            ResultSet rs = pstmt.executeQuery();
            Teacher t = new Teacher();
            if(rs.next()) {
                tid = rs.getInt("tid");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String email = rs.getString("email");
                String password = rs.getString("password");
                t.setId(tid);
                t.setFname(fname);
                t.setLname(lname);
                t.setEmail(email);
                t.setPassword(password);

                return t;

            }
            rs.close();

        } catch (SQLException e) {
            System.out.println("Teacher with id = " + tid + " not found.");
        }
        return null;
    }

    @Override
    public Teacher getTeacherByEmail(String email) {
        try (PreparedStatement pstmt = conn.prepareStatement("select * from teacher where email = ?")){
            // set up prepared statement to get a department using its id
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            Teacher t = new Teacher();
            if(rs.next()) {
                int tid = rs.getInt("tid");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                email = rs.getString("email");
                String password = rs.getString("password");
                t.setId(tid);
                t.setFname(fname);
                t.setLname(lname);
                t.setEmail(email);
                t.setPassword(password);

                return t;

            }
            rs.close();

        } catch (SQLException e) {
            System.out.println("Teacher with email = " + email + " not found.");
        }
        return null;
    }

    @Override
    public boolean addTeacher(Teacher t) {
        try{
            PreparedStatement pstmt = conn
                    .prepareStatement("INSERT into teacher values(?, ?, ?, ?, ?)");
            pstmt.setInt(1, 0);
            pstmt.setString(2, t.getFname());
            pstmt.setString(3, t.getLname());
            pstmt.setString(4, t.getEmail());
            pstmt.setString(5, t.getPassword());


            int i = pstmt.executeUpdate();

            if (i > 0) {
                return true;
            }

        }
        catch (SQLException e){
            System.out.println("Failed to create teacher");
        }
        return false;
    }

    @Override
    public boolean addTeacherClass(Teacher t, Classes c) {
        try (PreparedStatement pstmt = conn.prepareStatement("insert into teacher_class values(?, ?)")){
            pstmt.setInt(1, t.getId());
            pstmt.setInt(2, c.getId());

            int i = pstmt.executeUpdate();

            if (i > 0) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Failed to add class to teacher list.");
        }


        return false;
    }
}
