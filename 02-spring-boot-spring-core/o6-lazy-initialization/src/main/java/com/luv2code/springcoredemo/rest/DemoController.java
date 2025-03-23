package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    //define private field for dependency
   private Coach mycoach;
   @Autowired
   public void DemoController(@Qualifier("cricketCoach") Coach thecoach){
       System.out.println("In Constructor: "+getClass().getSimpleName());
       mycoach=thecoach;
   }
    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return mycoach.getDailyWorkout();
    }
}
