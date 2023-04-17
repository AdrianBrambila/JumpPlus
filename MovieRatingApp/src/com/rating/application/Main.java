package com.rating.application;

import com.rating.dao.TrackerDaoSQL;
import com.rating.dao.UserDaoSQL;
import com.rating.model.Movie;
import com.rating.model.Rating;
import com.rating.model.User;
import com.rating.controller.TrackerController;
import com.rating.utility.EmailRegex;
import com.rating.utility.Menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Menus menus = new Menus();
        TrackerController controller = new TrackerController();
        UserDaoSQL udao = new UserDaoSQL();
        TrackerDaoSQL tdao = new TrackerDaoSQL();
        List<User> users = new ArrayList<User>();
        List<Rating> userRatings = new ArrayList<Rating>();

        Scanner s = new Scanner(System.in);

        boolean b = true;
        while(b){
            menus.mainMenu();
            int choice = s.nextInt();
            s.nextLine();

            switch(choice){
                case 1: {//register accunt
                    User newUser = new User();
                    System.out.println("Please enter your email: ");
                    String email = s.nextLine();
                    if(!EmailRegex.emailCheck(email)){
                        System.out.println("Email does not match pattern!");
                        break;
                    }


                    newUser.setEmail(email);

                    boolean p = true;
                    while(p) {
                        System.out.println("Please enter a secure password: ");
                        String pass = s.nextLine();

                        System.out.println("Please confirm password: ");
                        String pass2 = s.nextLine();

                        if (pass.equals(pass2)){
                            newUser.setPassword(pass);
                            p = false;
                        }
                        else{
                            System.out.println("Passwords do not match! Try again.");
                        }
                    }

                    udao.addUser(newUser);
                    users = udao.getAllUsers();
                    break;
                }
                case 2:{ //Login
                    users = udao.getAllUsers();

                    if (users.isEmpty()){
                        System.out.println("No users in the database, please create a new user!");
                        break;
                    }

                    System.out.println("Please Enter Your Email: ");
                    String email = s.nextLine();


                    System.out.println("Please Enter Your Password: ");
                    String password = s.nextLine();

                    User logUser = udao.getuserByEmail(email);
                    //User logUser = udao.getUserById(id);
                    if(logUser.email == null || logUser.password == null){
                        System.out.println("Please try again!");
                        break;
                    }

                    //System.out.println(logUser);

                    if (logUser.email.equals(email)){
                  //  if (logUser.getId() == id){
                        if(logUser.password.equals(password)){

                            boolean b2 = true;
                            while(b2){
                                menus.loggedMenu();
                                choice = s.nextInt();
                                switch (choice) {
                                    case 1: { //view tracked movies
                                            userRatings = tdao.getRatingsByUser(logUser);
                                            if(userRatings.isEmpty()){
                                                System.out.println("No movies tracked!");
                                            }

                                            for(Rating r : userRatings){
                                                System.out.println(r.getFilm() + "    " + r.getRating());
                                            }

                                        break;
                                    }
                                    case 2: { // Add Movie Rating
                                        System.out.println("Please Select from the movies below:");
                                        controller.listMovies();
                                        int movie_id = s.nextInt();
                                        s.nextLine();
                                        if(choice > 5 || choice < 1){
                                            System.out.println("Not a valid choice, please try again!");
                                            break;
                                        }


                                        System.out.println("Please enter a rating between 1 and 5: ");
                                        double score = s.nextDouble();
                                        s.nextLine();
                                        if(score < 1 || score > 5){
                                            System.out.println("Invalid rating!");
                                            break;
                                        }

                                        String movie_name = tdao.getMovieById(movie_id);
                                        Rating rating = new Rating();
                                        rating.setId(0);
                                        rating.setUid(logUser.getId());
                                        rating.setFilm(movie_name);
                                        rating.setRating(score);

                                        tdao.addTracker(rating);


                                        break;

                                    }
                                    case 3: {//view average of all movies
                                        List<Rating> aves = tdao.getAllAveRatings();
                                        for(Rating r : aves){
                                            System.out.println(r.getFilm() + "    " + r.getRating());
                                        }

                                        break;
                                    }
                                    case 4:{//exit
                                        b2 = false;
                                        break;

                                    }
                                }
                            }

                        }

                    }
                    break;

                }
                case 3:{ //View Movies
                    controller.listMovies();
                    break;
                }
                case 4:{ //Exit
                    b = false;
                    break;

                }

            }




        }

        s.close();


    }
}