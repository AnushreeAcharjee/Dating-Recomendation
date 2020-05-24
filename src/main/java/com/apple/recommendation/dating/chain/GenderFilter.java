package com.apple.recommendation.dating.chain;

import com.apple.recommendation.dating.model.ApplicationContext;
import com.apple.recommendation.dating.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by anushree on 24 May, 2020
 */
public class GenderFilter implements Filter<ApplicationContext> {

    private Filter filter;

    @Override
    public void setNextFilter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public boolean doFilter(ApplicationContext appContext) {
        List<String> matches = new ArrayList<>();

        //by gender
        String gender = appContext.getCriteria().getUser().getGender();
        int topMostCnt = appContext.getCriteria().getTopMostCnt();

        Predicate<User> byGender = person -> !person.getGender().equalsIgnoreCase( gender);
        List<User> genderFiltered = appContext.getRequest().stream().filter(byGender)
                .collect(Collectors.toList());
       /* if(genderFiltered.size() == 0 || genderFiltered.size() < topMostCnt) {
            appContext.setRecommendations(matches);
            //return matches;//return empty list
        }*/
        if(genderFiltered.size() == topMostCnt){
            for(int i = 0; i < topMostCnt;i++){
                matches.add(genderFiltered.get(i).getName());
            }
            //return matches;
            appContext.setRecommendations(matches);
        }
        if(genderFiltered.size() > topMostCnt){
            //setting the request for next filter
            appContext.setRequest(genderFiltered);
        }
        //the response of this filter is genderFiltered
        appContext.setResponse(genderFiltered);
        return filter == null ? true: filter.doFilter(appContext);
    }
}
