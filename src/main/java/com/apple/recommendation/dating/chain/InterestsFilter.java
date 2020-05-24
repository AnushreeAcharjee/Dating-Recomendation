package com.apple.recommendation.dating.chain;

import com.apple.recommendation.dating.model.ApplicationContext;
import com.apple.recommendation.dating.model.Criteria;
import com.apple.recommendation.dating.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by anushree on 24 May, 2020
 */
public class InterestsFilter implements Filter<ApplicationContext> {

    private Filter filter;

    @Override
    public void setNextFilter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public boolean doFilter(ApplicationContext appContext) {
        List<String> matches = new ArrayList<>();

        //by interest : max number of common interests
        Criteria criteria = appContext.getCriteria();
        int topMostCnt = criteria.getTopMostCnt();
        List<String> givenInterests = appContext.getCriteria().getUser().getInterests();
        if(appContext.getRecommendations().size() == 0){
            //no recommendations yet, continue interests filter
            List<User> interestFiltered = appContext.getRequest();
            Collections.sort(interestFiltered, Comparator.comparingInt(a -> findMatchCount(a.getInterests(), givenInterests)));

            if(interestFiltered.size() == 0 || interestFiltered.size() < topMostCnt){
                //return from prev filter/ageFiltered
                for(int i = 0; i < topMostCnt;i++){
                    matches.add(appContext.getResponse().get(i).getName());
                }
                appContext.setRecommendations(matches);
                //return matches;
            }
            for(int i = 0; i < topMostCnt;i++){
                matches.add(interestFiltered.get(i).getName());
                appContext.setRecommendations(matches);
            }
            if(interestFiltered.size() > topMostCnt){
                //setting the request for next filter
                appContext.setRequest(interestFiltered);
            }
            //the response of this filter is genderFiltered
            appContext.setResponse(interestFiltered);
        }
        return filter == null ? true: filter.doFilter(appContext);
    }

    private int findMatchCount(List<String> a,List<String> b){
        int matchCount = 0;

        for(int i = 0, j = 0;i < a.size() && j < b.size();){
            int res = a.get(i).compareTo(b.get(j));
            if(res == 0){
                matchCount++;
                i++;
                j++;
            }else if(res < 0){
                i++;
            }else{
                j++;
            }
        }
        return matchCount;
    }
}
