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
public class TestAgeFilter {
    AgeFilter ageFilter;
    ApplicationContext applicationContext;
    Utils utils;
    @BeforeEach
    public void init(){
        ageFilter = new AgeFilter();
        utils = new Utils();
        List<User> request = new ArrayList<>(utils.getUsers());
        List<User> response = new ArrayList<>(utils.getUsers());
        List<String> recommendations = new ArrayList<>();
        Criteria criteria = new Criteria(utils.getUser("UserB"), 1);
        applicationContext = new ApplicationContext(request, response, recommendations,criteria);
    }

    @Test
    public void testDoFilter(){
        ageFilter.doFilter(applicationContext);
        List<User> response = applicationContext.getResponse();
        assertEquals(5, response.size());
        //all the entries are sorted in closest age of the given user's age: CADE
        assertEquals("UserC", response.get(1).getName());
        assertEquals("UserA", response.get(2).getName());
        assertEquals("UserD", response.get(3).getName());
        assertEquals("UserE", response.get(4).getName());
    }
}
