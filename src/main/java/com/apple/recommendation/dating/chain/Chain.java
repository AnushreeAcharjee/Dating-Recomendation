package com.apple.recommendation.dating.chain;

import com.apple.recommendation.dating.model.ApplicationContext;

import java.util.List;

public class Chain {
    public List<Filter> filters;

    public Chain(List<Filter> input){
        filters = input;
    }

    public void start(ApplicationContext appContext){
        for(int i = 0; i < filters.size() - 1; i++){
            filters.get(i).setNextFilter(filters.get(i + 1));
        }
        filters.get(0).doFilter(appContext);
    }
}