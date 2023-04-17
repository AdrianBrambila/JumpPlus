package com.rating.dao;

import com.rating.model.User;

import java.util.List;

public interface UserDao {

    public List<User> getAllUsers();


    public User getUserById(int id);

    public User getuserByEmail(String email);

    public boolean addUser(User u);



}
