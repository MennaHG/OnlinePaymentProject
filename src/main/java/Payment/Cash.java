package Payment;


public class Cash implements PaymentMethod{
	
	double amount;

	@Override
	public boolean pay(double amount) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void add(double amount) {
		this.amount += amount;		
	}


}
