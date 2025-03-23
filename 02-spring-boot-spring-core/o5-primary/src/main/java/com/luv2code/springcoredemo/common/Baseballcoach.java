package com.luv2code.springcoredemo.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class Baseballcoach implements Coach{

    @Override
    public String getDailyWorkout() {
        return "spend 30 minutes in batting practice";
    }
}
