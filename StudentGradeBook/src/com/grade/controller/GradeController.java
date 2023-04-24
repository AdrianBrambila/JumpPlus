package com.grade.controller;

import com.grade.dao.ClassesDaoSQL;
import com.grade.dao.TeacherDaoSQL;
import com.grade.model.Classes;
import com.grade.model.Teacher;
import com.grade.utility.EmailRegex;

import java.util.List;
import java.util.Scanner;

public class GradeController {
    TeacherDaoSQL tdao = new TeacherDaoSQL();
    ClassesDaoSQL cdao = new ClassesDaoSQL();

    public Teacher register(Scanner s){

        Teacher t = new Teacher();
        System.out.println("Please enter your first name: ");
        String fname = s.nextLine();
        t.setFname(fname);
        System.out.println("Please enter your last name: ");
        String lname = s.nextLine();
        t.setLname(lname);

        boolean e = true;
        while(e){
            System.out.println("Please enter your email: ");
            String email = s.nextLine();
            if(!EmailRegex.emailCheck(email)){
                System.out.println("Email does not match pattern, try again!");
                //break;
            }
            else {
                t.setEmail(email);
                e = false;
            }
        }

        boolean p = true;
        while(p) {
            System.out.println("Please enter a secure password: ");
            String pass = s.nextLine();

            System.out.println("Please confirm password: ");
            String pass2 = s.nextLine();

            if (pass.equals(pass2)) {
                t.setPassword(pass);
                p = false;
            } else {
                System.out.println("Passwords do not match! Try again.");
            }
        }

        return t;
    };

    public boolean login(String email, String password){

        Teacher loggedTeacher = tdao.getTeacherByEmail(email);
        if(loggedTeacher.email == null || loggedTeacher.password == null){
            System.out.println("Email or password cannot be null, please try again!");
            return false;
        }

        if(loggedTeacher.email.equals(email)){
            if(loggedTeacher.password.equals(password)){
                return true;
            }
        }
        System.out.println("Invalid login, try again!");

        return false;
    }

    public boolean createClass(Scanner s, Teacher t){
//        Classes c = new Classes();
//        System.out.println("Please enter classname: ");
//        String name = s.nextLine();
//        c.setId(0);
//        c.setClassName(name);
//        cdao.addClass(c);
//        tdao.addTeacherClass(t, c);


        return true;
    };


}
