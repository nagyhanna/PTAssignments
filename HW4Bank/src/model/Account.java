package model;
import java.io.Serializable;
import java.util.Observable;

@SuppressWarnings("serial")
public abstract class Account extends Observable implements Serializable{
	
	private final int accId;
	private static int id=200;
	protected double money;
	private  String accountType;
	
	public Account(double money, String type) {
		super();
		this.accId = id++;
		this.money = money;
		this.accountType=type;
		
	}
	
	public Account(int id,double money, String type) {
		super();
		this.accId = id;
		this.money = money;
		this.accountType=type;
		
	}
	public int getAccId() {
		return accId;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}


	public String getType(){
		return this.accountType;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accId;
		return result;
	}
	
	
	@Override
	public String toString() {
		return "Account [accId=" + accId + ", money=" + money + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accId != other.accId)
			return false;
		return true;
	}
	
	
	public abstract void addMoney(double sum);
	public abstract int withdrawMoney(double sum);
	

}
