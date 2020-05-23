package com.apple.recommendation.dating;

import com.apple.recommendation.dating.controller.DatingManagerImpl;
import com.apple.recommendation.dating.model.User;
import com.apple.recommendation.dating.utils.Utils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by anushree on 23 May, 2020
 */
public class Application {

    public static void main(String... arg){
        DatingManagerImpl datingManager = new DatingManagerImpl();
        Utils utils = new Utils();
        Set<User> users = utils.getUsers();

        for(User user: users){
            datingManager.registerUser(user);
        }

        User user = new User("UserB");
        user.setAge(27);
        user.setGender("Male");
        List<String> interests = new ArrayList<String>();
        interests.add("Cricket");
        interests.add("Football");
        interests.add("Movies");
        user.setInterests(interests);
        users.add(user);
        List<String> recommendations = datingManager.fetchMatches(user, 2);
        System.out.println(recommendations);
    }

}
