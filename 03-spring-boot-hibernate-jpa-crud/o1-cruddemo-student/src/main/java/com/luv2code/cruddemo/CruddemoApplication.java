package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDao;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;
@SpringBootApplication
public class CruddemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(StudentDao studentDao){
		return runner ->{
			//createStudent(studentDao);
			createMultipleStudent(studentDao);
			//readStudent(studentDao);
			//queryForStudent(studentDao);
			//queryByLastName(studentDao);
			//updateStudent(studentDao);
			//deleteStudent(studentDao);
			//deletAllStudent(studentDao);
		};
	}

	private void deletAllStudent(StudentDao studentDao) {
		System.out.println("Deleting All Student..");
		int numRowsDeleted=studentDao.deleteAll();
		System.out.println("Deleted Row Count: "+numRowsDeleted);
	}

	private void deleteStudent(StudentDao studentDao) {
		int studentId=3;
		System.out.println("deleting the studen Id: "+studentId);
		studentDao.delete(studentId);

	}

	private void updateStudent(StudentDao studentDao) {
		//retrieve student based on the id: Primary key
		int studentId=1;
		System.out.println("Getting student with id: "+studentId);
		Student myStudent=studentDao.findById(studentId);
		//change first name to "Scooby"
		System.out.println("Updating the student...");
		myStudent.setFirstName("Scooby");
		//update the student
		studentDao.update(myStudent);
		//display the updated student
		System.out.println("Updated Student: "+ myStudent);
	}

	private void queryByLastName(StudentDao studentDao) {
		//get a List of student
		List<Student>theStudent=studentDao.findByLastName("Duck");
		//display a list of student;
		for(Student tempStudent:theStudent){
			System.out.println(tempStudent);
		}
	}

	private void queryForStudent(StudentDao studentDao) {
		//get a list of Student;
		List<Student> theStudent=studentDao.findAll();
		//display list of student;
		for(Student s:theStudent){
			System.out.println(s);
		}
	}

	//Method for Reading the student data vai id;
	private void readStudent(StudentDao studentDao) {
		//create a student object
		System.out.println("Creating new student object.. ");
		Student tempStudent=new Student("Daffy","Duck","daffy@gamil.com");
		//save the student
		System.out.println("saving the student ...");
		studentDao.save(tempStudent);
		//display the id of the saved student
		int theId=tempStudent.getId();
		System.out.println("saved Student. Generated id: "+theId);
		//retrieve the student based on the id: primary key
		System.out.println("Retrieving student with id: "+theId);
		Student myStudent=studentDao.findById(theId);
		//display student
		System.out.println("Found the student: "+myStudent);
	}
//creating multiple student (Method)
	private void createMultipleStudent(StudentDao studentDao) {
		//create multiple student
		System.out.println("Creating 3 new student object...");
		Student tempStudent1=new Student("John","Doe","john@gmail.com");
		Student tempStudent2=new Student("Mary","Public","mary@gmail.com");
		Student tempStudent3=new Student("Bonita","Applebum","bonita@gmail.com");
		//save the student object
		System.out.println("Saving the Student...");
		studentDao.save(tempStudent1);
		studentDao.save(tempStudent2);
		studentDao.save(tempStudent3);

	}
//Method for creating single student;
	private void createStudent(StudentDao studentDao) {
		//create the student object
		System.out.println("Creating new student object...");
		Student tempStudent=new Student("khalid","Mustafa","km865014@gmail.com");
		//save the student object
		System.out.println("Saving the Student...");
		studentDao.save(tempStudent);
		//display id of the saved student
		System.out.println("saved Student. Generated id: "+tempStudent.getId());
	}
}
