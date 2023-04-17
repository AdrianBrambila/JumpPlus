package com.rating.controller;

import com.rating.dao.TrackerDaoSQL;
import com.rating.dao.UserDaoSQL;
import com.rating.model.User;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class TrackerController {
    static TrackerDaoSQL tdao = new TrackerDaoSQL();


    public void listMovies() {
        List<String> movies = new ArrayList<String>();

        movies = tdao.getAllMovies();

        if (movies.isEmpty()){
            System.out.println("ERROR, no movies!");

        }
        else{
            int i = 1;
            for(String s : movies){
                System.out.println(i + "|  " + s);
                i++;
            }
        }




    };

}
