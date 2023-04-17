package com.rating.dao;

import com.rating.model.Movie;
import com.rating.model.Rating;
import com.rating.model.User;

import java.util.List;

public interface TrackerDao {
    public String getMovieById(int id);

    public boolean addTracker(Rating r);

    List<Rating> getRatingsByUser(User u);

    List<Rating> getAllAveRatings();

    List<String> getAllMovies();



}
