package com.luv2code.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//- Use @RestController when you want to identify a class for REST APIs
//- Use @RequestMapping when you want to define a base mapping or expose a specific endpoint
@RestController
@RequestMapping("/test")
public class DemoRestController {
    //add code for "/hello" endpoint
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World!";
    }
}
