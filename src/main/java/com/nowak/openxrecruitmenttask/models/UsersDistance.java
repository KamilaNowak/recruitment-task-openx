package com.nowak.openxrecruitmenttask.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsersDistance {

    private Integer from;
    private Integer to;
    private Double distance;
}
