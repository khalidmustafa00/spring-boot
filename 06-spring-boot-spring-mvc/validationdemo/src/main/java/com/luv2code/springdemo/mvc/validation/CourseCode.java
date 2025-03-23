package com.luv2code.springdemo.mvc.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
//where can we apply this new annotaion
@Target({ElementType.METHOD,ElementType.FIELD})
//this basically says how long will the marked annotation be stored or used?
//so basically we say retain this annotation in the bytecode and also used it at
//runtime by JVM. so we sya this |_|
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {
    //define default courseCode
    public String value() default "LUV";
    //define default error message
    public String message() default "must start with LUV";
    //define default group
    public Class<?>[] groups() default {};
    //define default payloads
    public Class<? extends Payload>[] payload() default {};
}
