package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Bank implements BankInterface {

	private Map<Person, Set<Account>> client;
	private Serialization serialized_clients;

	public Bank() {
		client = new HashMap<Person, Set<Account>>();

		serialized_clients = new Serialization();
		client = serialized_clients.deserialization();

		for (Map.Entry<Person, Set<Account>> entry : client.entrySet()) {
			Set<Account> acc = entry.getValue();
			for (Account account : acc) {
				account.addObserver(entry.getKey());
			}
		}
	}

	public Bank(int a) {
		client = new HashMap<Person, Set<Account>>();
	}

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
	 */
	public void addAccforPers(Person p, Account acc) {
		assert isWellFormed(client);
		assert p != null && acc != null;

		int nrOfPreAccounts = nrOfAccounts(p);
		System.out.println("nr of PREEaccounts" + p.getName() + nrOfPreAccounts);

		acc.addObserver(p);
		if (client.containsKey(p)) {
			Set<Account> accounts = client.get(p);
			for (Account account : accounts) {
				if (account.equals(acc)) {
					return;
				}
			}
			accounts.add(acc);
			serialization();
			deserialization();
			assert isWellFormed(client);
			System.out.println("nr of Postaccounts" + p.getName() + nrOfAccounts(p));
			assert (nrOfPreAccounts + 1) == nrOfAccounts(p);
			assert containsAccount(p, acc);
			return;
		}
		// account/s does not exists
		Set<Account> newAcc = new HashSet<Account>();
		newAcc.add(acc);
		client.put(p, newAcc);
		serialization();
		deserialization();
		assert isWellFormed(client);
		System.out.println("nr of Postaccounts" + p.getName() + nrOfAccounts(p));
		assert (nrOfPreAccounts + 1) == nrOfAccounts(p);
		assert containsAccount(p, acc);

	}

	/**
	 * Delete a person/client
	 * 
	 * @param p
	 * @pre p!= null
	 * @pre p is a client
	 * @post numberOfClients@pre -1 = numberOfAccounts
	 * @post p is not a client
	 * @invariant isWellFormed
	 */
	public void deletePerson(Person person) {
		assert isWellFormed(client);
		assert person != null;
		assert containsPerson(person);
		int nrOfClients = getnrOfClients();

		client.remove(person);
		serialization();
		deserialization();
		assert isWellFormed(client);
		assert nrOfClients - 1 == getnrOfClients();
		assert !containsPerson(person);

	}

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
	 */
	public void deleteAccount(Person person, Account account) {
		assert isWellFormed(client);
		assert person != null && account != null;
		assert containsPerson(person);
		assert containsAccount(person, account);
		int nrOfPreAccounts = nrOfAccounts(person);

		Set<Account> accounts = client.get(person);
		accounts.remove(account);
		if (nrOfAccounts(person) == 0) {
			deletePerson(person);
		}
		serialization();
		deserialization();

		assert nrOfPreAccounts - 1 == nrOfAccounts(person);
		assert isWellFormed(client);

	}

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
	 */

	public void depositMoney(int accId, double sum, Person p) {
		assert isWellFormed(client);
		assert p != null && sum > 0.0;
		assert containsPerson(p);
		assert containsAccountId(p, accId);
		double totalsum = getSumAccountId(p, accId);
		Set<Account> accounts = client.get(p);
		for (Account acc : accounts) {
			if (acc.getAccId() == accId) {
				acc.addMoney(sum);
				serialization();
				deserialization();
				assert isWellFormed(client);
				assert totalsum < getSumAccountId(p, accId);
			}
		}

	}

	
	public void serialization() {
		serialized_clients.serialization(client);
	}

	public void deserialization() {
		serialized_clients.deserialization();
	}

	public Map<Person, Set<Account>> getClient() {
		return this.client;
	}

	public void setClient(Map<Person, Set<Account>> client) {
		this.client = client;
	}

	/**
	 * @param client
	 *            There is no person without at least one account There is no
	 *            account with negative balance There is no Account without
	 *            person person without name
	 */
	@Override
	public boolean isWellFormed(Map<Person, Set<Account>> client) {

		if (client.isEmpty()) {
			return true;
		}
		for (Map.Entry<Person, Set<Account>> entry : client.entrySet()) {
			if (entry.getKey() == null) {
				return false;
			}
			for (Account account : entry.getValue()) {
				if (account == null) {
					// System.out.println("aici"+ entry.getKey().getName());
					return false;
				}
				if (account.getMoney() < 0.0) {
					// System.out.println("aici"+ entry.getKey().getName());
					return false;
				}
			}
			if (entry.getKey().getName().equals(""))
				return false;
		}
		return true;

	}

	private int nrOfAccounts(Person p) {
		int nrOfAccounts = 0;
		for (Map.Entry<Person, Set<Account>> entry : client.entrySet()) {
			if (entry.getKey().equals(p)) {
				for (Account account : entry.getValue()) {
					nrOfAccounts++;
				}
			}
		}
		System.out.println("nr of accounts......" + p.getName() + "..." + nrOfAccounts);
		return nrOfAccounts;
	}

	private int getnrOfClients() {
		int nrOfClients = 0;
		for (Map.Entry<Person, Set<Account>> entry : client.entrySet()) {
			nrOfClients++;
		}
		return nrOfClients;
	}

	private boolean containsAccount(Person p, Account acc) {

		for (Map.Entry<Person, Set<Account>> entry : client.entrySet()) {
			if (entry.getKey().equals(p)) {
				for (Account account : entry.getValue()) {
					if (account.equals(acc)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean containsAccountId(Person p, int accId) {

		for (Map.Entry<Person, Set<Account>> entry : client.entrySet()) {
			if (entry.getKey().equals(p)) {
				for (Account account : entry.getValue()) {
					if (account.getAccId() == accId) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private double getSumAccountId(Person p, int accId) {

		for (Map.Entry<Person, Set<Account>> entry : client.entrySet()) {
			if (entry.getKey().equals(p)) {
				for (Account account : entry.getValue()) {
					if (account.getAccId() == accId) {
						return account.getMoney();
					}
				}
			}
		}
		return 0.0;
	}

	public boolean containsPerson(Person p) {

		for (Map.Entry<Person, Set<Account>> entry : client.entrySet()) {
			if (entry.getKey().equals(p)) {
				return true;
			}
		}
		return false;
	}

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
	 */
	@Override
	public int withdrawMoney(int accId, double sum, Person p) {
		assert isWellFormed(client);
		assert p != null && sum > 0.0;
		assert containsPerson(p);
		assert containsAccountId(p, accId);
		double totalsum = getSumAccountId(p, accId);
		Set<Account> accounts = client.get(p);
		for (Account acc : accounts) {
			if (acc.getAccId() == accId) {
				if (acc.withdrawMoney(sum) == 1) {
					serialization();
					deserialization();
					assert isWellFormed(client);
					assert totalsum > getSumAccountId(p, accId);
					return 1;
				} else
					return -1;
			}
		}
		return -1;
	}

	public int withdrawMoneyForTest(int accId, double sum, Person p) {
		assert isWellFormed(client);
		assert p != null && sum > 0.0;
		assert containsPerson(p);
		assert containsAccountId(p, accId);
		double totalsum = getSumAccountId(p, accId);
		Set<Account> accounts = client.get(p);
		for (Account acc : accounts) {
			if (acc.getAccId() == accId) {
				if (acc.withdrawMoney(sum) == 1) {
					serialization();
					deserialization();
					assert isWellFormed(client);
					assert totalsum > getSumAccountId(p, accId);
					return 1;
				} else
					return -1;
			}
		}
		return -1;
	}

}
