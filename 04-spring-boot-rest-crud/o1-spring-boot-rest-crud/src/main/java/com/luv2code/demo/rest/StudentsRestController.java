package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Students;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentsRestController {
    private List<Students> theStudents;
    //define the @PostConstruct to load the data ... only once;
    //We use @PostConstruct to run initialization logic
    // after a bean is fully constructed and dependencies are injected.
    // It keeps the code clean, ensures proper timing,
    // and avoids issues with dependency availability.
    @PostConstruct
    public void loadData(){
        theStudents =new ArrayList<>();
        theStudents.add(new Students("Poornima","Patel"));
        theStudents.add(new Students("Mario","Rossi"));
        theStudents.add(new Students("Mary","Smith"));
    }
    //define endpoint for "/students" -return a list of student
    @GetMapping("/students")
    public List<Students> getStudents(){

        return theStudents;
    }
    //define endpoint or "/students{studentsId}" - return students at index
    @GetMapping("/students/{studentsId}")
    public Students getStudent(@PathVariable int studentsId){
        //just index into the list ... keep it simple for now
        //check the student id against the student size;
        if(studentsId>=theStudents.size() || (studentsId<0)){
             throw new StudentNotFoundException("student id not found - "+studentsId);
        }
        return theStudents.get(studentsId);
    }
    //add an exception handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
        //create a students error response;
        StudentErrorResponse error=new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        //return ResponseEntity
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
    //Add another exception handler ... to catch any exception (catch all)
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc){
        //create a students error response;
        StudentErrorResponse error=new StudentErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        //return ResponseEntity
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
