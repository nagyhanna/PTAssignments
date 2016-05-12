package main;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Account;
import model.Bank;
import model.Person;
import model.SavingAccount;
import model.SpendingAccount;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Bank bank= new Bank();
		Person p11= new Person("AAA");
		Person p22= new Person("BBB");
		Account acc= new SavingAccount(12.0);
		Account acc1= new SpendingAccount(22.0);
		Account acc2= new SpendingAccount(220.0);
		Account acc3= new SavingAccount(1220.0);
		
		//bank.addAccforPers(p11, acc);
		//bank.addAccforPers(p11, acc1);
		//bank.addAccforPers(p22, acc2);
		//bank.addAccforPers(p22, acc3);
		//System.out.println(bank.getClient().toString());
		
		
		//bank.depositMoney(200, 1000, p11);
		//System.out.println(bank.getClient().toString());
		
		Person p0= new Person("John Smith");
		Person p1= new Person("Will Garner");
		Person p2= new Person("Alicia Florick");
		Person p3= new Person("Nagy Hanna");
		Person p4= new Person("Nagy Ilona");
		Person p5= new Person("Cary Agos");
		Account a0= new SavingAccount(10.0);
		Account a1= new SpendingAccount(20.0);
		Account a2= new SavingAccount(30.0);
		Account a3= new SpendingAccount(40.0);
		Account a4= new SpendingAccount(50.0);
		Account a5= new SavingAccount(60.0);
		Account a6= new SavingAccount(70.0);
		/*
			bank.addAccforPers(p0,a0);
			bank.addAccforPers(p1,a1);
			bank.addAccforPers(p2,a2);
			bank.addAccforPers(p3,a3);
			bank.addAccforPers(p4,a4);
			
			bank.addAccforPers(p5,a5);
			bank.addAccforPers(p5,a6);
*/
		
		BankControl bankC= new BankControl(bank);
		
		

	}

}
