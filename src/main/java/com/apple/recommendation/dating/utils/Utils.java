package com.apple.recommendation.dating.utils;

import com.apple.recommendation.dating.model.User;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dell on 23 May, 2020
 */
public class Utils {
    Set<User> users = new LinkedHashSet<>();
    public Set<User> getUsers(){
        User user = new User("UserA");
        user.setAge(25);
        user.setGender("Female");
        List<String> interests = new ArrayList<>();
        interests.add("Cricket");
        user.setInterests(interests);
        users.add(user);

        user = new User("UserB");
        user.setAge(27);
        user.setGender("Male");
        interests = new ArrayList<String>();
        interests.add("Cricket");
        interests.add("Football");
        interests.add("Movies");
        user.setInterests(interests);
        users.add(user);

        user = new User("UserC");
        user.setAge(26);
        user.setGender("Male");
        interests = new ArrayList<String>();
        interests.add("Movies");
        interests.add("Tennis");
        interests.add("Football");
        interests.add("Cricket");
        user.setInterests(interests);
        users.add(user);

        user = new User("UserD");
        user.setAge(24);
        user.setGender("Female");
        interests = new ArrayList<String>();
        interests.add("Tennis");
        interests.add("Football");
        interests.add("Badminton");
        user.setInterests(interests);
        users.add(user);

        user = new User("UserE");
        user.setAge(32);
        user.setGender("Female");
        interests = new ArrayList<String>();
        interests.add("Cricket");
        interests.add("Football");
        interests.add("Movies");
        interests.add("Badminton");
        user.setInterests(interests);
        users.add(user);

        return users;
    }

    public User getUser(String given) {
        for(User user : users){
            if(user.equals(given)){
                return user;
            }
        }
        return null;
    }
}
