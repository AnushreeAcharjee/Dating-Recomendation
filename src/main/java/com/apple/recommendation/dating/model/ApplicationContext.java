package com.apple.recommendation.dating.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by anushree on 24 May, 2020
 */
@Data
@AllArgsConstructor
public class ApplicationContext {
    List<User> request;
    List<User> response;
    List<String> recommendations;
    Criteria criteria;
}
