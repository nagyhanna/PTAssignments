package model;
public class SpendingAccount extends Account {

	//private static final String ACCOUNT_TYPE="Spending Account";
	

	public SpendingAccount(double money) {
		super( money,"Spending Account");

	}
	public SpendingAccount(int id,double money) {
		super(id,money, "Spending Account");
		
	}
	
	@Override
	public int withdrawMoney(double sum) {

		if (sum <= money) {
			// successful withdraw
			money -= sum;
			this.setChanged();
			this.notifyObservers(sum);
			return 1;
		} else
			return -1; // unsuccessful withdraw - sum is greater then the sum in the account;
	}

	@Override
	public void addMoney(double sum) {
		this.money += sum;
		this.setChanged();
		this.notifyObservers(sum);
	}

	

	

}
