package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.Dao.EmployeeDao;
import com.luv2code.springboot.cruddemo.Service.EmployeeService;
import com.luv2code.springboot.cruddemo.Service.EmployeeServiceImpl;
import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;
    //quick and dirty :inject employee dao
    public EmployeeRestController(EmployeeService theEmployeeService){
    employeeService=theEmployeeService;
    }
    //expose "/employees" and return a list of employees;
    @GetMapping("/employees")
    public List<Employee> fildAll(){
    return employeeService.findAll();
    }
    //add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee theEmployee=employeeService.findById(employeeId);
        if(theEmployee==null){
            throw new RuntimeException("Employee id is not found " + +employeeId);
        }
        return theEmployee;
    }
    //add mapping for post /employees - add new employee

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        //also just in case they pass an id in json ... set id to 0
        //this is to force a save of new item ... instead of update
        theEmployee.setId(0);
        Employee dbEmployee=employeeService.save(theEmployee);
        return dbEmployee;
    }
    //add mapping for PUT /employees - update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
            Employee dbemployee=employeeService.save(theEmployee);
            return dbemployee;
    }
    //add Mapping for delete /employees/{employeeId} - delete employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteById(@PathVariable int employeeId){
        Employee tempEmployee=employeeService.findById(employeeId);
        if(tempEmployee==null){
            throw new RuntimeException("Employee Id not found - "+employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Deleted Employee id - "+employeeId;
    }
}
