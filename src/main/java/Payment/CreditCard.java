package Payment;


public class CreditCard implements PaymentMethod{
	
	int cardNumber;
	int pin;
	double balance;
	
	public CreditCard(int cardNumber, int pin, double balance) {
		this.cardNumber = cardNumber;
		this.pin = pin;
		this.balance = balance;
		
	}

	@Override
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

	public double getBalance() {
		return balance;
	}

	 
}
