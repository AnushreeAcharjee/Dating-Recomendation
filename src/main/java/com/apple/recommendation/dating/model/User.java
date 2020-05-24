package com.apple.recommendation.dating.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by anushree on 23 May, 2020
 */
@Data
@EqualsAndHashCode
public class User {
    private String name;
    private String gender;
    private int age;
    private List<String> interests;
    public User(String name){
        this.name = name;
    }
}
