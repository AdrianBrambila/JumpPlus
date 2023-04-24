package com.grade.application;

import com.grade.controller.GradeController;
import com.grade.dao.ClassesDaoSQL;
import com.grade.dao.StudentDaoSQL;
import com.grade.dao.TeacherDaoSQL;
import com.grade.model.Classes;
import com.grade.model.Student;
import com.grade.model.Teacher;
import com.grade.utility.Menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Menus menus = new Menus();

        GradeController gc = new GradeController();
        TeacherDaoSQL tdao = new TeacherDaoSQL();
        ClassesDaoSQL cdao = new ClassesDaoSQL();
        StudentDaoSQL sdao = new StudentDaoSQL();

        List<Teacher> teachers = new ArrayList<Teacher>();
        List<Classes> teacherClasses = new ArrayList<Classes>();
        List<Classes> classes = new ArrayList<Classes>();

        Scanner s = new Scanner(System.in);

        boolean b = true;
        while(b){
            menus.mainMenu();
            int choice = s.nextInt();
            s.nextLine();

            switch (choice){
                case 1 :{ //create account
                    Teacher t = gc.register(s);
                    tdao.addTeacher(t);
                    teachers = tdao.getAllTeachers();
                    break;

                }
                case 2:{ //login
                    teachers = tdao.getAllTeachers();
                    if (teachers.isEmpty()){
                        System.out.println("No teachers in the database, please create a new teacher!");
                        break;
                    }
                    System.out.println("Please Enter Your Email: ");
                    String email = s.nextLine();

                    System.out.println("Please Enter Your Password: ");
                    String password = s.nextLine();

                    if(gc.login(email, password)){
                        Teacher loggedTeacher = tdao.getTeacherByEmail(email);
                        boolean b2 = true;
                        while(b2){
                            menus.loggenMenu();
                            choice = s.nextInt();
                            s.nextLine();
                            switch (choice){
                                case 1:{//view classes
                                    teacherClasses = cdao.getClassesByTeacherId(loggedTeacher.getId());

                                    if(teacherClasses.isEmpty()){
                                        System.out.println("No classes!");

                                    }
                                    //int i = 1;
                                    for(Classes c : teacherClasses){
                                        System.out.println(c.getId() + "   " +c.getClassName());
                                    }

                                    System.out.println();
                                    System.out.println("Pick from the classes above to have more options, or press 0 to exit: ");
                                    choice = s.nextInt();
                                    s.nextLine();

                                    if (choice == 0){
                                        break;
                                    }
                                    Classes classChoice = cdao.getClassById(choice);

                                    System.out.println("You have selected : " + classChoice.getClassName() );
                                    System.out.println("What would you like to do?");

                                    menus.classMenu();

                                    choice = s.nextInt();
                                    s.nextLine();
                                    switch(choice){
                                        case 1:{ //list students
                                            List<Student> studentsInClass = cdao.getStudentsInClass(classChoice.getId());
                                            for(Student stu : studentsInClass){
                                                System.out.println(stu.getFname() + " " + stu.getLname() + " " + stu.getGrade());
                                            }
                                            break;
                                        }
                                        case 2:{ //alphabetic students
                                            List<Student> studAlpha = cdao.getStudentsInClassAlphabetically(classChoice.getId());
                                            for(Student stu : studAlpha){
                                                System.out.println(stu.getFname() + " " + stu.getLname() + " " + stu.getGrade());
                                            }
                                            break;
                                        }
                                        case 3:{ //list by grade
                                            List<Student> studGrades = cdao.getStudentsInClassByGrade(classChoice.getId());
                                            for(Student stu : studGrades){
                                                System.out.println(stu.getFname() + " " + stu.getLname() + " " + stu.getGrade());
                                            }
                                            break;

                                        }
                                        case 4:{//average grade
                                            System.out.println("Average grade of class " + classChoice.getClassName() + " is: " + cdao.getClassAverage(classChoice.getId()));
                                            break;

                                        }
                                        case 5: {// median grade
                                            System.out.println("Out of service.");
                                            break;

                                        }
                                        case 6:{//update grade
                                            List<Student> studentsToUpdate = cdao.getStudentsInClass(classChoice.getId());
                                            for(Student stu : studentsToUpdate){
                                                System.out.println(stu.getId() + "  " +stu.getFname() + " " + stu.getLname() + " " + stu.getGrade());
                                            }

                                            System.out.println("select the id of the student you would like to update.");
                                            int sid = s.nextInt();
                                            s.nextLine();
                                            Student upStu = sdao.getStudentById(sid);

                                            System.out.println("What is the new grade? ");
                                            double newGrade = s.nextDouble();
                                            s.nextLine();
                                            upStu.setGrade(newGrade);
                                            sdao.updateStudentGrade(upStu);


                                            break;

                                        }
                                        case 7: {//remove student
                                            List<Student> studentsToDelete = cdao.getStudentsInClass(classChoice.getId());
                                            for(Student stu : studentsToDelete){
                                                System.out.println(stu.getId() + "  " +stu.getFname() + " " + stu.getLname() + " " + stu.getGrade());
                                            }

                                            System.out.println("select the id of the student you would like to delete.");
                                            int sid = s.nextInt();
                                            s.nextLine();
                                            sdao.deleteStudent(sid);
                                            break;

                                        }
                                        case 8:{
                                            break;
                                        }

                                    }




                                    break;

                                }
                                case 2:{//choose class to teach
                                    classes = cdao.getAllClasses();
                                    for(Classes c : classes){
                                        System.out.println(c.getId() + "  " + c.getClassName());
                                    }
                                    choice = s.nextInt();
                                    s.nextLine();

                                    if(choice > classes.size()){
                                        System.out.println("Class does not exist, try again!");
                                        break;
                                    }
                                    else{
                                        Classes c = cdao.getClassById(choice);
                                        tdao.addTeacherClass(loggedTeacher, c);

                                    }

                                    break;

                                }
                                case 3:{ //create class
                                    //gc.createClass(s, loggedTeacher);
                                    Classes newClass = new Classes();
                                    System.out.println("Please enter classname: ");
                                    String name = s.nextLine();
                                    newClass.setId(0);
                                    newClass.setClassName(name);
                                    cdao.addClass(newClass);
                                    //tdao.addTeacherClass(loggedTeacher, newClass);
                                    classes = cdao.getAllClasses();
                                    break;


                                }
                                case 4:{ //exit
                                    b2 = false;
                                    break;
                                }
                            }
                        }
                    }


                    break;
                }
                case 3:{//exit
                    b = false;
                    break;
                }
            }

        }
        s.close();


    }
}