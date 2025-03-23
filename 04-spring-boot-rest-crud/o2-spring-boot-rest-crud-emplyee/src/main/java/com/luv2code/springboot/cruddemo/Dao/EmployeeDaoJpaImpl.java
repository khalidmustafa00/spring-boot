package com.luv2code.springboot.cruddemo.Dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployeeDaoJpaImpl implements EmployeeDao {
    //define field for entity manager
    private EntityManager entityManager;
    public EmployeeDaoJpaImpl(EntityManager theEntityManager){
        entityManager=theEntityManager;
    }
    //set up constructor injection;
    @Override
    public List<Employee> findAll() {
        //create a query
        TypedQuery<Employee> theQuery=entityManager.createQuery("FROM Employee", Employee.class);
        //execute a query and get result list
        List<Employee> employees= theQuery.getResultList();
        //return the result
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        //get the employee
        Employee theEmployee=entityManager.find(Employee.class,theId);
        //return the employe;
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        //save employee
        Employee dbEmployee=entityManager.merge(theEmployee);
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {
        //find employee by id
        Employee theEmployee =entityManager.find(Employee.class,theId);
        entityManager.remove(theEmployee);


    }
}
