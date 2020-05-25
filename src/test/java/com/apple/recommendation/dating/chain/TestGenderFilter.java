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
public class TestGenderFilter {
    GenderFilter genderFilter;
    ApplicationContext applicationContext;
    Utils utils;

    @BeforeEach
    public void init(){
        genderFilter = new GenderFilter();
        utils = new Utils();
        List<User> request = new ArrayList<>(utils.getUsers());
        List<User> response = new ArrayList<>(utils.getUsers());
        List<String> recommendations = new ArrayList<>();
        Criteria criteria = new Criteria(utils.getUser("UserB"), 1);
        applicationContext = new ApplicationContext(request, response, recommendations,criteria);
    }

    @Test
    public void testDoFilter(){
        genderFilter.doFilter(applicationContext);
        List<User> response = applicationContext.getResponse();
        assertEquals(3, response.size());
        assertEquals("UserA", response.get(0).getName());
        assertEquals("UserD", response.get(1).getName());
        assertEquals("UserE", response.get(2).getName());
    }
}
