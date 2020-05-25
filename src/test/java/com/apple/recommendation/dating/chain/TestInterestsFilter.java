package com.apple.recommendation.dating.chain;

import com.apple.recommendation.dating.model.ApplicationContext;
import com.apple.recommendation.dating.model.Criteria;
import com.apple.recommendation.dating.model.User;
import com.apple.recommendation.dating.utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by dell on 25 May, 2020
 */
public class TestInterestsFilter {
    InterestsFilter interestsFilter;
    Utils utils;
    ApplicationContext applicationContext;

    @BeforeEach
    public void init(){
        interestsFilter = new InterestsFilter();
        utils = new Utils();
        List<User> request = new ArrayList<>(utils.getUsers());
        List<User> response = new ArrayList<>(utils.getUsers());
        List<String> recommendations = new ArrayList<>();
        Criteria criteria = new Criteria(utils.getUser("UserB"), 1);
        applicationContext = new ApplicationContext(request, response, recommendations,criteria);
    }

    @Test
    public void testDoFilter(){
        interestsFilter.doFilter(applicationContext);
        List<User> response = applicationContext.getResponse();//dacbe
        assertEquals(5, response.size());
        assertEquals("UserD", response.get(0).getName());
        assertEquals("UserA", response.get(1).getName());
        assertEquals("UserC", response.get(2).getName());
        assertEquals("UserB", response.get(3).getName());
        assertEquals("UserE", response.get(4).getName());
    }
}
