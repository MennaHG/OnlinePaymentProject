package Payment;


public interface PaymentMethod {
	
	public boolean pay(double amount);
	public void add(double amount);
}
