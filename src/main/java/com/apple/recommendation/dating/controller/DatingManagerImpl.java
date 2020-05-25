package com.apple.recommendation.dating.controller;

import com.apple.recommendation.dating.chain.*;
import com.apple.recommendation.dating.model.ApplicationContext;
import com.apple.recommendation.dating.model.Criteria;
import com.apple.recommendation.dating.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anushree on 23 May, 2020
 */
public class DatingManagerImpl implements DatingManager {

    List<User> list = new ArrayList<>();
    public void registerUser(User user) {
        list.add(user);
    }

    public List<String> getRecommendations(User user, int topMostCnt) {
        List<Filter> filters = new ArrayList<>();
        //IMPORTANT : Mind the order
        filters.add(new GenderFilter());
        filters.add(new AgeFilter());
        filters.add(new InterestsFilter());

        Chain chain = new Chain(filters);
        ApplicationContext appContext = new ApplicationContext(list,list,  new ArrayList<>(), new Criteria(user, topMostCnt));
        chain.start(appContext);

        return appContext.getRecommendations();
    }
}
