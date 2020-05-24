package com.apple.recommendation.dating.chain;

import com.apple.recommendation.dating.model.ApplicationContext;
import com.apple.recommendation.dating.model.Criteria;
import com.apple.recommendation.dating.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by anushree on 24 May, 2020
 */
public class AgeFilter implements Filter<ApplicationContext> {

    private Filter filter;

    @Override
    public void setNextFilter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public boolean doFilter(ApplicationContext appContext) {
        List<String> matches = new ArrayList<>();

        //by age
        Criteria criteria = appContext.getCriteria();
        int age = criteria.getUser().getAge();
        int topMostCnt = criteria.getTopMostCnt();
        if(appContext.getRecommendations().size() == 0){
            //no recommendations yet, continue age filter
            List<User> ageFiltered = appContext.getRequest();
            Collections.sort(ageFiltered, Comparator.comparingInt(a -> Math.abs(a.getAge() - age)));

            if(ageFiltered.size() == 0 || ageFiltered.size() < topMostCnt){
                //return from prev filter/ genderFiltered
                for(int i = 0; i < topMostCnt;i++){
                    matches.add(appContext.getResponse().get(i).getName());
                }
                appContext.setRecommendations(matches);
                //return matches;
            }
            if(ageFiltered.size() == topMostCnt){
                for(int i = 0; i < topMostCnt;i++){
                    matches.add(ageFiltered.get(i).getName());
                }
                appContext.setRecommendations(matches);
                // return matches;
            }
            if(ageFiltered.size() > topMostCnt){
                //setting the request for next filter
                appContext.setRequest(ageFiltered);
            }
            //the response of this filter is genderFiltered
            appContext.setResponse(ageFiltered);
        }
        return filter == null ? true: filter.doFilter(appContext);
    }
}
