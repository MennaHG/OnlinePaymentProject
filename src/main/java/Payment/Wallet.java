package Payment;

public class Wallet implements PaymentMethod {
	
	private double balance;

	public Wallet() {
		balance = 0;
	}
	

	public Wallet(double balance) {
		this.balance = balance;
	}


	public boolean addFunds(CreditCard card, double amount) {
		if(card.pay(amount)) {
			this.balance += amount;
			return true;
		}
		return false;
	}

	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public boolean pay(double amount) {
		if (amount > this.balance)
			return false;
		balance = balance - amount;
		return true;
	}


	@Override
	public void add(double amount) {
		balance += amount;		
	}

}
