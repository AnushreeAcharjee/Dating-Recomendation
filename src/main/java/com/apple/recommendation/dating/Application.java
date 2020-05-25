package com.apple.recommendation.dating;

import com.apple.recommendation.dating.controller.DatingManagerImpl;
import com.apple.recommendation.dating.model.User;
import com.apple.recommendation.dating.utils.Utils;

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

        List<String> recommendations = datingManager.getRecommendations(utils.getUser("UserB"), 2);
        System.out.println(recommendations);
    }

}
