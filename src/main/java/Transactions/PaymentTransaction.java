package Transactions;
import Payment.*;
import Users.*;

public class PaymentTransaction extends Transaction {
	
	private String serviceType;
	private String serviceDescription;
	
	public PaymentTransaction(User user, PaymentMethod method, String serviceType, String serviceDescription, double amount) {
		
		this.user = user;
		this.method = method;
		this.serviceType = serviceType;
		this.serviceDescription = serviceDescription;
		this.amount = amount;
		this.refunded = false;
		this.refundRequested = false;
	}

	public String getServiceType() {
		return serviceType;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	
	PaymentTransaction(PaymentTransaction t){
		this.user = t.user;
		this.method = t.method;
		this.serviceType = t.serviceType;
		this.serviceDescription = t.serviceDescription;
		this.amount = t.amount;
	}

	@Override
	public String displayTransaction() {
		String s = super.displayTransaction()+" "+this.serviceDescription + " "+ amount+'\n';
		return s;
	}
	
	

}
