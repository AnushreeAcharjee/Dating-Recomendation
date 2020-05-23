package com.apple.recommendation.dating.controller;

import com.apple.recommendation.dating.model.User;

import java.util.List;

/**
 * Created by anushree on 23 May, 2020
 */
public interface DatingManager {
    void registerUser(User user);
    List<String> fetchMatches(User user, int topMostCnt);
}
