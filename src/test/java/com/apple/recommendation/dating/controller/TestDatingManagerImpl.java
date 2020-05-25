package com.apple.recommendation.dating.controller;

import com.apple.recommendation.dating.model.User;
import com.apple.recommendation.dating.utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by anushree on 25 May, 2020
 */
public class TestDatingManagerImpl {
    DatingManagerImpl inst;
    Utils utils;
    @BeforeEach
    public void init(){
        inst = new DatingManagerImpl();
        utils = new Utils();
    }

    @Test
    public void testGetRecommendations(){
        Set<User> users = utils.getUsers();
        for(User user: users){
            inst.registerUser(user);
        }
        List<String> recommendations = inst.getRecommendations(utils.getUser("UserB"), 2);
        assertEquals(2, recommendations.size());
        assertEquals("UserD", recommendations.get(0));
        assertEquals("UserA", recommendations.get(1));
    }

    @Test
    public void testGetRecommendations_no_reccomendation_All_Female(){
        Set<User> users = utils.getUsers();
        User userB = utils.getUser("UserB");
        userB.setGender("Female");
        User userC = utils.getUser("UserC");
        userC.setGender("Female");
        users.add(userB);
        users.add(userC);
        for(User user: users){
            inst.registerUser(user);
        }
        List<String> recommendations = inst.getRecommendations(utils.getUser("UserA"), 2);
        assertEquals(0, recommendations.size());
    }
}
