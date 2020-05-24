package com.apple.recommendation.dating.chain;

/**
 * Created by anushree on 24 May, 2020
 */
public interface Filter<T> {
    void setNextFilter(Filter<T> filter);
    boolean doFilter(T context);
}
