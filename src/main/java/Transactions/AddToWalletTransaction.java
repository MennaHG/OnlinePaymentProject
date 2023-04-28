package Transactions;
import Payment.*;
import Users.*;

public class AddToWalletTransaction extends Transaction{
	
	

	public AddToWalletTransaction(User user, CreditCard card, double amount) {
		this.user = user;
		this.method = card;
		this.amount = amount;
		this.refunded = false;
		this.refundRequested = false;
	}



	@Override
	public String displayTransaction() {
		String s = super.displayTransaction()+" "+ amount+" added to wallet\n";
		return s;
	}

}
