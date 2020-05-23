package com.apple.recommendation.dating.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by anushree on 23 May, 2020
 */
@Data
public class User {
    private String name;
    private String gender;
    private int age;
    private List<String> interests;
    public User(String name){
        this.name = name;
    }
    @Override public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        User other = (User) o;
        if (!other.canEqual((Object)this)) return false;
        if (!super.equals(o)) return false;
        if (this.name != other.name) return false;
        if (this.gender != other.gender) return false;
        if (this.age != other.age) return false;
        if (this.interests != other.interests) return false;
        return true;
    }
    protected boolean canEqual(Object other) {
        return other instanceof User;
    }

    @Override public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = (result*PRIME) + super.hashCode();
        result = (result*PRIME) + this.age;
        return result;
    }
}
