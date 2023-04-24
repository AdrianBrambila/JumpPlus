package com.grade.dao;

import com.grade.connection.ConnectionManager;
import com.grade.model.Classes;
import com.grade.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassesDaoSQL implements ClassesDao{
    private Connection conn = ConnectionManager.getConnection();

    @Override
    public List<Classes> getAllClasses() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM classes");

            List<Classes> classList = new ArrayList<Classes>();


            while (rs.next()) {
                int id = rs.getInt("cid");
                String name = rs.getString("cname");

                Classes c = new Classes();
                c.setId(id);
                c.setClassName(name);

                classList.add(c);

            }
            return classList;
        }catch (SQLException e) {
            System.out.println("Could not retrieve list of classes");

        }

        return null;
    }

    @Override
    public Classes getClassById(int cid) {
        try (PreparedStatement pstmt = conn.prepareStatement("select * from classes where cid = ?")){
            // set up prepared statement to get a department using its id
            pstmt.setInt(1, cid);

            ResultSet rs = pstmt.executeQuery();

            Classes c = new Classes();
            if(rs.next()) {
                cid = rs.getInt("cid");
                String name = rs.getString("cname");


                c.setId(cid);
                c.setClassName(name);
            }
            rs.close();
            return c;

        } catch (SQLException e) {
            System.out.println("Class with id = " + cid + " not found.");
        }
        return null;
    }

    @Override
    public String getClassNameById(int cid) {
        try (PreparedStatement pstmt = conn.prepareStatement("select * from classes where cid = ?")){
            // set up prepared statement to get a department using its id
            pstmt.setInt(1, cid);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                String name = rs.getString("cname");
                return name;
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println("Class with id = " + cid + " not found.");
        }
        return null;
    }


    @Override
    public List<Classes> getClassesByTeacherId(int tid) {
        try (PreparedStatement pstmt = conn.prepareStatement("select c.cid, t.fname, c.cname from teacher t join teacher_class tc on t.tid = tc.tid join classes c on c.cid = tc.cid where t.tid = ?")){
            pstmt.setInt(1, tid);
            ResultSet rs = pstmt.executeQuery();

            List<Classes> classList = new ArrayList<Classes>();

            while(rs.next()) {
                int cid = rs.getInt("cid");
                String name = rs.getString("cname");

                Classes c = new Classes();

                c.setId(cid);
                c.setClassName(name);
                classList.add(c);
            }
            rs.close();
            return classList;

        } catch (SQLException e) {
            System.out.println("Class with teacher id = " + tid + " not found.");
        }
        return null;
    }

    @Override
    public List<Student> getStudentsInClass(int cid) {
        try (PreparedStatement pstmt = conn.prepareStatement("select s.sid, s.fname, s.lname, sc.grade from student s join student_classes sc on s.sid = sc.sid join classes c on c.cid = sc.cid where c.cid = ?")){
            // set up prepared statement to get a department using its id
            pstmt.setInt(1, cid);

            ResultSet rs = pstmt.executeQuery();

            List<Student> studentList = new ArrayList<Student>();


            while(rs.next()) {
                int sid = rs.getInt("sid");
                String fname = rs.getString("lname");
                String lname = rs.getString("lname");
                double grade = rs.getDouble("grade");

                Student s = new Student();

                s.setId(sid);
                s.setFname(fname);
                s.setLname(lname);
                s.setGrade(grade);
                studentList.add(s);
            }
            rs.close();
            return studentList;

        } catch (SQLException e) {
            System.out.println("Can't get list dawg");
        }
        return null;
    }

    @Override
    public List<Student> getStudentsInClassAlphabetically(int cid) {
        try (PreparedStatement pstmt = conn.prepareStatement("select s.sid, s.fname, s.lname, sc.grade from student s join student_classes sc on s.sid = sc.sid join classes c on c.cid = sc.cid where c.cid = ? order by fname asc")){
            // set up prepared statement to get a department using its id
            pstmt.setInt(1, cid);

            ResultSet rs = pstmt.executeQuery();

            List<Student> studentList = new ArrayList<Student>();


            while(rs.next()) {
                int sid = rs.getInt("sid");
                String fname = rs.getString("lname");
                String lname = rs.getString("lname");
                double grade = rs.getDouble("grade");

                Student s = new Student();

                s.setId(sid);
                s.setFname(fname);
                s.setLname(lname);
                s.setGrade(grade);
                studentList.add(s);
            }
            rs.close();
            return studentList;

        } catch (SQLException e) {
            System.out.println("Can't get list dawg");
        }
        return null;
    }

    @Override
    public List<Student> getStudentsInClassByGrade(int cid) {
        try (PreparedStatement pstmt = conn.prepareStatement("select s.sid, s.fname, s.lname, sc.grade from student s join student_classes sc on s.sid = sc.sid join classes c on c.cid = sc.cid where c.cid = ? order by grade desc")){
            // set up prepared statement to get a department using its id
            pstmt.setInt(1, cid);

            ResultSet rs = pstmt.executeQuery();

            List<Student> studentList = new ArrayList<Student>();


            while(rs.next()) {
                int sid = rs.getInt("sid");
                String fname = rs.getString("lname");
                String lname = rs.getString("lname");
                double grade = rs.getDouble("grade");

                Student s = new Student();

                s.setId(sid);
                s.setFname(fname);
                s.setLname(lname);
                s.setGrade(grade);
                studentList.add(s);
            }
            rs.close();
            return studentList;

        } catch (SQLException e) {
            System.out.println("Can't get list dawg");
        }
        return null;
    }

    @Override
    public double getClassAverage(int cid) {
        try (PreparedStatement pstmt = conn.prepareStatement("select avg(grade) from student_classes where cid = ?")){
            // set up prepared statement to get a department using its id
            pstmt.setInt(1, cid);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                double average = rs.getDouble("avg(grade)");
                return average;
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println("Class average for class with id = " + cid + " not found.");
        }


        return 0;
    }

    @Override
    public double getClassMedian(int cid) {
        return 0;
    }

    @Override
    public boolean addClass(Classes c) {
        try{
            PreparedStatement pstmt = conn
                    .prepareStatement("INSERT into classes values(?, ?)");
            pstmt.setInt(1, 0);
            pstmt.setString(2, c.getClassName());



            int i = pstmt.executeUpdate();

            if (i > 0) {
                return true;
            }

        }
        catch (SQLException e){
            System.out.println("Failed to create class");
        }
        return false;
    }
}
