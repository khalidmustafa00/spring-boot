package com.luv2code.springdemo.mvc;

import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {
    //add an intitBinder to convert ... trim input strings
    //remove leading and tailing spaces
    //resolve issue for validation
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        //true means trim to null if entirely is white space;
        //stringTrimmerEditor is a class difined in spring api its puspose to trime (leading and trailing white space)string
        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }
    @GetMapping("/")
    public String showForm(Model theModel){
        theModel.addAttribute("customer",new Customer());
        return "customer-form";
    }
    @PostMapping("/processform")
    public String processForm(@Valid @ModelAttribute("customer")Customer theCustomer, BindingResult theBindingResult){
       System.out.println("Last Name: |"+theCustomer.getLastName()+"|");
       System.out.println("Binding Result "+theBindingResult.toString());
       System.out.println("\n\n\n\n");
        if(theBindingResult.hasErrors()){
            return "customer-form";
        }
        else{
            return "customer-confirmation";
        }
    }

}
