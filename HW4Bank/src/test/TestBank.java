package test;
import static org.junit.Assert.*;

import org.junit.Test;

import model.Account;
import model.Bank;
import model.Person;
import model.SavingAccount;
import model.SpendingAccount;

public class TestBank {

	Bank bank;
	Person p0;
	Person p1;
	Account a0;
	Account a1;
	Account a2;
	
	public  TestBank() {
	//	fail("Not yet implemented");
		bank= new Bank();
		p0= new Person("Nagy Ilona test");
		p1= new Person("Cary Agos test");
		a0= new SavingAccount(10.0);
		a1= new SpendingAccount(20.0);
		a2= new SavingAccount(30.0);
	}

	@Test
	public void testAdd(){
		bank.addAccforPers(p1, a0);
		bank.addAccforPers(p0, a1);
		bank.depositMoney(a0.getAccId(),500, p1);
		bank.withdrawMoney(a1.getAccId(), 50, p0);
		bank.deletePerson(p0);
		bank.deleteAccount(p1,a0);
	}
	
}
