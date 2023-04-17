package com.rating.dao;

import com.rating.connection.ConnectionManager;
import com.rating.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoSQL implements UserDao {

    private Connection conn = ConnectionManager.getConnection();

    @Override
    public List<User> getAllUsers() {

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            List<User> userList = new ArrayList<User>();


            while (rs.next()) {
                int id = rs.getInt("uid");
                String email = rs.getString("email");
                String password = rs.getString("password");

                User u = new User();
                u.setId(id);
                u.setEmail(email);
                u.setPassword(password);

                userList.add(u);

            }
            return userList;
        }catch (SQLException e) {
            System.out.println("Could not retrieve list of users");

        }

        return null;
    }



    @Override
    public User getUserById(int id) {
        try (PreparedStatement pstmt = conn.prepareStatement("select * from users where uid = ?")){
            // set up prepared statement to get a department using its id
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            //rs.first();
            User u = new User();
            if(rs.next()) {
                id = rs.getInt("uid");
                String email = rs.getString("email");
                String password = rs.getString("password");


                u.setId(id);
                u.setEmail(email);
                u.setPassword(password);
            }
            rs.close();
            return u;

        } catch (SQLException e) {
            System.out.println("User with id = " + id + " not found.");
        }

        return null;
    }

    @Override
    public User getuserByEmail(String email) {
        try(PreparedStatement pstmt = conn.prepareStatement("select * from users where email = ?")) {

            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            //rs.first();
            User u = new User();
            if(rs.next()) {
                int id = rs.getInt("uid");
                // email = rs.getString("email");
                String password = rs.getString("password");


                u.setId(id);
                u.setEmail(email);
                u.setPassword(password);
            }
            rs.close();
            return u;

        } catch (SQLException e) {
            System.out.println("User with email = " + email + " not found.");
        }

        return null;
    }

    @Override
    public boolean addUser(User u) {
        try{
            PreparedStatement pstmt = conn
                    .prepareStatement("INSERT into users(uid, email, password) values(?, ?, ?)");
            pstmt.setInt(1, 0);
            pstmt.setString(2, u.getEmail());
            pstmt.setString(3, u.getPassword());

            int i = pstmt.executeUpdate();

            if (i > 0) {
                return true;
            }

        }
        catch (SQLException e){
            System.out.println("Failed to create user");
        }
        return false;
    }
}
