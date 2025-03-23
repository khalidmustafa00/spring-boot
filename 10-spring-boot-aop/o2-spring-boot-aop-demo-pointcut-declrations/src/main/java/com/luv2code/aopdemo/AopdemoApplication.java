package com.luv2code.aopdemo;
import com.luv2code.aopdemo.dao.AccountDao;
import com.luv2code.aopdemo.dao.MembershipDAO;
import com.luv2code.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
@SpringBootApplication
public class AopdemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(AccountDao theAccountDao,
											   MembershipDAO theMembershipDAO,
											   TrafficFortuneService theTrafficFortuneService){
		return runner ->{
			//demoTheBeforeAdvice(theAccountDao,theMembershipDAO);
			//demoTheAfterReturningAdvice(theAccountDao);
			//demoTheAfterThrowingAdvice(theAccountDao);
			//demoTheAfterAdvice(theAccountDao);
			//demoTheAroundAdvice(theTrafficFortuneService);
			//demoTheAroundAdviceHandleTheException(theTrafficFortuneService);
			demoTheAroundAdviceRethrewException(theTrafficFortuneService);
		};
	}

	private void demoTheAroundAdviceRethrewException(TrafficFortuneService theTrafficFortuneService) {
		System.out.println("\nMain Program: demoTheAroundAdviceRethrewException");
		System.out.println("calling getFortunes()");
		boolean tripWire=true;
		String data=theTrafficFortuneService.getFortune(tripWire);
		System.out.println("My Fortune is : "+data);
		System.out.println("Finished");
	}

	private void demoTheAroundAdviceHandleTheException(TrafficFortuneService theTrafficFortuneService) {
		System.out.println("\nMain Program: theTrafficFortuneService");
		System.out.println("calling getFortunes()");
		boolean tripWire=true;
		String data=theTrafficFortuneService.getFortune(tripWire);
		System.out.println("My Fortune is : "+data);
		System.out.println("Finished");
	}

	private void demoTheAroundAdvice(TrafficFortuneService theTrafficFortuneService) {
		System.out.println("\nMain Program: demoTheAroundAdvice");
		System.out.println("calling getFortunes()");

		String data=theTrafficFortuneService.getFortune();
		System.out.println("My Fortune is : "+data);
		System.out.println("Finished");
	}

	private void demoTheAfterAdvice(AccountDao theAccountDao) {
		//call method to find the accounts
		List<Account> theAccounts=null;
		try{
			//add a boolean falg to simulate exceptions
			boolean tripWire=false;
			theAccounts=theAccountDao.findAccount(tripWire);
		}catch (Exception exc){
			System.out.println("\n\nMain Programs: ... caught exception: "+exc);
		}
		//display the account
		System.out.println("\n\nMain Programs: demoTheAfterThrowingAdvice");
		System.out.println("---");
		System.out.println(theAccounts);
		System.out.println("\n");
	}

	private void demoTheAfterThrowingAdvice(AccountDao theAccountDao) {
		//call method to find the accounts
		List<Account> theAccounts=null;
		try{
			//add a boolean falg to simulate exceptions
			boolean tripWire=true;
			theAccounts=theAccountDao.findAccount(tripWire);
		}catch (Exception exc){
			System.out.println("\n\nMain Programs: ... caught exception: "+exc);
		}
		//display the account
		System.out.println("\n\nMain Programs: demoTheAfterThrowingAdvice");
		System.out.println("---");
		System.out.println(theAccounts);
		System.out.println("\n");
	}


	private void demoTheAfterReturningAdvice(AccountDao theAccountDao) {
		//call method to find the accounts
		List<Account> theAccounts=theAccountDao.findAccount();
		//display the account
		System.out.println("\n\nMain Programs: demoTheAfterReturningAdvice");
		System.out.println("---");
		System.out.println(theAccounts);
		System.out.println("\n");

	}

	private void demoTheBeforeAdvice(AccountDao theAccountDao, MembershipDAO theMembershipDAO) {
		//call the business method
		Account myAccount=new Account();
		myAccount.setName("Madhu");
		myAccount.setLevel("Platinums");
		theAccountDao.addAccount(myAccount,true);
		theAccountDao.doWork();
		//call the AccountDao getter/setter methods
		theAccountDao.setName("foobar");
		theAccountDao.setServiceCode("code");

		String name=theAccountDao.getName();
		String code=theAccountDao.getServiceCode();
		//call the membership business method
		theMembershipDAO.addSillyMember();
		theMembershipDAO.goToSleep();
	}

}
