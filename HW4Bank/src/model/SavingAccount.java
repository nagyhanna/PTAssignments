package model;
/**
 * Savings accounts are generally for money that you don't intend to use for
 * daily expenses; The account is likely to have a limited number of free
 * transfers/transactions; In order to save more money, there is a limit of 4
 * transactions (withdraw) in a month and the maximum sum of money which can be
 * withdrawn is 100 Euro if and only if the sum in the bankAccount is minimum
 * 5times the max sum (5X100=500 euro)
 */

@SuppressWarnings("serial")
public class SavingAccount extends Account {
	
	private static final Double MAX_WITHDRAWN_SUM = 100.0;
	private static final Integer MAX_NR_OF_WITHDRAWS = 3;
	private int nrOfWithdraws = 0;
	private static final Double MAX_SUM_INACCOUNT = 500.0;
	

	public SavingAccount(double money) {
		super(money, "Saving Account");
		
	}
	public SavingAccount(int id,double money) {
		super(id,money, "Saving Account");
		
	}
	@Override
	public void addMoney(double sum) {
		this.money += sum;
		this.setChanged();
		this.notifyObservers(sum);
	}

	@Override
	public int withdrawMoney(double sum) {

		if (notExceededNrOfWithdraws()) {
			if ( money >= MAX_SUM_INACCOUNT) {
				if (sum <= MAX_WITHDRAWN_SUM) {
					money -= sum;
					 // successful withdraw 
					nrOfWithdraws++;
					this.setChanged();
					this.notifyObservers(sum);
					return 1;
				 }}}
		return -1; // unsuccessful withdraw - the nr of possible withdraws is greater then the limit (3 times a month);
		          // unsuccessful withdraw the wanted sum is greater than the limit(100 per withdraw);
		         // unsuccessful withdraw - the sum in acc. is less than the limit(500);
	}

	private boolean notExceededNrOfWithdraws() {
		return nrOfWithdraws <= MAX_NR_OF_WITHDRAWS;
	}

	public static Double getMaxSum() {
		return MAX_WITHDRAWN_SUM;
	}

	public static Integer getMaxnrofwithdraws() {
		return MAX_NR_OF_WITHDRAWS;
	}

	public int getNrOfWithdraws() {
		return nrOfWithdraws;
	}

	
	public static Double getMaxSumInaccount() {
		return MAX_SUM_INACCOUNT;
	}

	
	

}
