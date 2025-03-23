package com.luv2code.springboot.demo.mycoolapp.Rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    //expose "/" that return "Hello World";
    @GetMapping("/")
    public String sayHello(){
        return "Hello World!";
    }
    //expose a new end point for "workout"
    @GetMapping("/workout")
    public String getDailyWorkout(){
            return "Run a hard 5K!";
    }
    //expose a new endpoint for "fortune"
    @GetMapping("/fortune")
    public String getDailyfortune(){
        return "Today is your lucky day. ";
    }
}
