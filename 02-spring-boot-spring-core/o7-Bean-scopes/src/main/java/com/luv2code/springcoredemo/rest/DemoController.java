package com.luv2code.springcoredemo.rest;
import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class DemoController {
    //define private field for the dependency
   private Coach mycoach;
   private Coach anothercoach;
   @Autowired
   public void DemoController(@Qualifier("cricketCoach") Coach thecoach,
                              @Qualifier("cricketCoach") Coach theanothercoach){
       System.out.println("In Constructor: "+getClass().getSimpleName());
       mycoach=thecoach;
       anothercoach=theanothercoach;
   }
    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return mycoach.getDailyWorkout();
    }

    @GetMapping("/check")
    public String check(){
       return "Comparing beans: mycoach==anothercoach, "+ (mycoach==anothercoach);
    }
}
