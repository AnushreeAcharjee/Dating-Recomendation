package com.apple.recommendation.dating.controller;

import com.apple.recommendation.dating.model.User;
import lombok.var;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by anushree on 23 May, 2020
 */
public class DatingManagerImpl implements DatingManager {
    PriorityQueue<User> pq = new PriorityQueue<>();
    List<User> list = new ArrayList<>();
    public void registerUser(User user) {
        list.add(user);
    }

    public List<String> fetchMatches(User user, int topMostCnt) {
        List<String> matches = new ArrayList<>();
        //applyFilters(user, topMostCnt);

        String gender = user.getGender();
        Predicate<User> byGender = person -> !person.getGender().equalsIgnoreCase( gender);
        List<User> genderFiltered = list.stream().filter(byGender)
                .collect(Collectors.toList());
        if(genderFiltered.size() == 0 || genderFiltered.size() < topMostCnt) {
            return matches;//return empty list
        }
        if(genderFiltered.size() == topMostCnt){
            for(int i = 0; i < topMostCnt;i++){
                matches.add(genderFiltered.get(i).getName());
            }
            return matches;
        }

        //by age
        int given = user.getAge();
        List<User> ageFiltered = new ArrayList<>(genderFiltered);
        Collections.sort(ageFiltered, Comparator.comparingInt(a -> Math.abs(a.getAge() - given)));
        //ageFiltered[0] = given user
       // ageFiltered = ageFiltered.subList(1, ageFiltered.size());
        if(ageFiltered.size() == 0 || ageFiltered.size() < topMostCnt){
            //return from genderFiltered
            for(int i = 0; i < topMostCnt;i++){
                matches.add(genderFiltered.get(i).getName());
            }
            return matches;
        }
        if(ageFiltered.size() == topMostCnt){
            for(int i = 0; i < topMostCnt;i++){
                matches.add(ageFiltered.get(i).getName());
            }
            return matches;
        }

        //by interest : max number of common interests
        List<User> interestFiltered = new ArrayList<>(ageFiltered);
        List<String> givenInterests = user.getInterests();
        Collections.sort(interestFiltered, Comparator.comparingInt(a -> findMatchCount(a.getInterests(), givenInterests)));

        if(interestFiltered.size() == 0 || interestFiltered.size() < topMostCnt){
            //return from ageFiltered
            for(int i = 0; i < topMostCnt;i++){
                matches.add(ageFiltered.get(i).getName());
            }
            return matches;
        }
        for(int i = 0; i < topMostCnt;i++){
            matches.add(interestFiltered.get(i).getName());
        }
        return matches;
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
