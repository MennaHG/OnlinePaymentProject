package Transactions;
import Payment.*;
import Users.*;

public abstract class Transaction {
	
	protected User user;
	protected PaymentMethod method;
	protected boolean refunded;
	protected boolean refundRequested;
	protected double amount;


	public boolean isRefundRequested() {
		return refundRequested;
	}

	public void setRefundRequested(boolean refundRequested) {
		this.refundRequested = refundRequested;
	}

	public boolean isRefunded() {
		return refunded;
	}

	public void setRefunded(boolean refunded) {
		this.refunded = refunded;
	}
	public double getAmount() {
		return amount;
	}
	
	public String displayTransaction() {
		String s =  user.getUsername() +" "+user.getEmail()
		+" Method: "+method.getClass().getSimpleName()+" "+(refunded? "Refunded" : "");
		return s;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PaymentMethod getMethod() {
		return method;
	}

	public void setMethod(PaymentMethod method) {
		this.method = method;
	}
	
	
	
	

}
