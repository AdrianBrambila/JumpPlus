package com.rating.dao;


import com.rating.connection.ConnectionManager;
import com.rating.model.Movie;
import com.rating.model.Rating;
import com.rating.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrackerDaoSQL implements TrackerDao {

    private Connection conn = ConnectionManager.getConnection();
    @Override
    public String getMovieById(int id) {
        try (PreparedStatement pstmt = conn.prepareStatement("select movie_name from movies where movie_id = ?")){
            // set up prepared statement to get a department using its id

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            //rs.first();
            if(rs.next()) {
                String movieName = rs.getString("movie_name");
                return movieName;

            }

        } catch (SQLException e) {
            System.out.println("Movie with id = " + id + " not found.");
        }
        return null;
    }
    @Override
    public boolean addTracker(Rating r) {
        try{

            PreparedStatement pstmt = conn
                    .prepareStatement("INSERT into tracker(tid, uid, movie_name, rating) values(?, ?, ?, ?)");
            pstmt.setInt(1, 0);
            pstmt.setInt(2, r.getUid());
            pstmt.setString(3, r.getFilm());
            pstmt.setDouble(4, r.getRating());
            int i = pstmt.executeUpdate();

            if (i > 0) {
                return true;
            }

        }
        catch (SQLException e){
            System.out.println("Failed add tracked movie");
        }
        return false;
    }




    @Override
    public List<Rating> getRatingsByUser(User u) {
        try ( PreparedStatement pstmt = conn.prepareStatement("select * from tracker where uid = ?")){
            pstmt.setInt(1, u.getId());

            ResultSet rs = pstmt.executeQuery();

            List<Rating> userRatings = new ArrayList<Rating>();
            while(rs.next()) {
                int tid = rs.getInt("tid");
                int uid = rs.getInt("uid");
                String movie = rs.getString("movie_name");
                double rating = rs.getDouble("rating");

                //String film = getMovieById(movieId);


                Rating r = new Rating();
                r.setId(tid);
                r.setUid(uid);
                r.setFilm(movie);
                r.setRating(rating);

                userRatings.add(r);

            }
            rs.close();
            return userRatings;

        } catch (SQLException e) {
            System.out.println("Could not access user ratings");
        }
        return null;
    }

    @Override
    public List<Rating> getAllAveRatings() {

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select movie_name, AVG(rating) as average from tracker group by movie_name");

            List<Rating> aveRatings = new ArrayList<Rating>();


            while (rs.next()) {
                // int id = rs.getInt("movie_id");
                String movie_name = rs.getString("movie_name");
                double average = rs.getDouble("average");

                Rating r = new Rating();
                r.setFilm(movie_name);
                r.setRating(average);

                aveRatings.add(r);

            }
            return aveRatings;
        }catch (SQLException e) {
            System.out.println("Could not retrieve list of Movies");

        }

        return null;
    }


    @Override
    public List<String> getAllMovies() {

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM movies");

            List<String> movies = new ArrayList<String>();


            while (rs.next()) {
               // int id = rs.getInt("movie_id");
                String movie_name = rs.getString("movie_name");

                movies.add(movie_name);

            }
            return movies;
        }catch (SQLException e) {
            System.out.println("Could not retrieve list of Movies");

        }

        return null;
    }
}
