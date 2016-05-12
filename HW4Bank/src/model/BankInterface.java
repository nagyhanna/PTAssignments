package model;
import java.util.Map;
import java.util.Set;

public interface BankInterface {
	
	
	
	/**
	 * Adds an account to a person
	 * 
	 * @param p 
	 * @param acc
	 * @pre acc != null
	 * @pre p!= null
	 * @post numberOfAccounts@pre +1 = numberOfAccounts
	 * @post person's accounts contains (account) true
	 * @invariant isWellFormed
	 * */
	public void addAccforPers(Person p, Account acc);
	
	/**
	 * Delete an account from a person
	 * 
	 * @param p 
	 * @param acc
	 * @pre acc != null
	 * @pre p!= null
	 * @pre p is a client
	 * @pre p contains(account)
	 * @post numberOfAccounts@pre -1 = numberOfAccounts
	 * @post person's accounts does not contains (account) false
	 * @invariant isWellFormed
	 * */
	public void deleteAccount(Person person, Account account);
	
	/**
	 * Delet a person/client
	 * 
	 * @param p
	 * @pre p!= null
	 * @pre p is a client
	 * @post numberOfClients@pre -1 = numberOfAccounts
	 * @post p is not a client
	 * @invariant isWellFormed
	 * */
	public void deletePerson(Person person);
	
	/**
	 * Deposit money to an account of a client
	 * 
	 * @param p
	 * @param accId
	 * @param sum
	 * @pre p!= null
	 * @pre sum > 0.0 
	 * @pre p is a client
	 * @pre p has account with accId
	 * @post sum > sum@pre
	 * @invariant isWellFormed
	 * */
	public void depositMoney(int accId, double sum, Person p);
	
	
	/**
	 * Withdraw money from an account of a client
	 * 
	 * @param p
	 * @param accId
	 * @param sum
	 * @pre p!= null
	 * @pre sum > 0.0
	 * @pre p is a client
	 * @pre p has account with accId
	 * @post sum < sum@pre
	 * @invariant isWellFormed
	 * */
	public int withdrawMoney(int accId, double sum, Person p);
	
	
	
	/**
	 * @param client
	 * There is no person without at least one account
	 * There is no account with negative balance
	 * There is no Account without person
	 * */
	public boolean isWellFormed(Map<Person, Set<Account>> client);
	
	


}
