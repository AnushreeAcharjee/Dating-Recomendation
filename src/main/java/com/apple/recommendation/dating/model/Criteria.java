package com.apple.recommendation.dating.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by anushree on 24 May, 2020
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
public class Criteria {
    User user;
    int topMostCnt;
}
