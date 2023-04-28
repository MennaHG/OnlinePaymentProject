package services;
import Database.*;
import Payment.*;
import Transactions.*;
import Users.*;
public class DonationNGO extends Service{

	
	public DonationNGO() {
		this.type = "Donation";
		this.description = "Donation to a Non-Governmental Organization";
		this.url = "DonationNGO";
		this.discount  = new Discount();
		this.COD = true;
		this.form = new Form();
		
	}

	@Override
	public boolean handle(GeneralUser user, PaymentMethod method) {
		String amount = form.searchForField("AMOUNT");
		if(amount.matches("[0-9]+") && form.searchForField("PHONE").matches("[0-9]+"))
		{
			double x = Double.parseDouble(amount);
			Double value = x*discount.use();
			if (x > 0 && method.pay(x*discount.use()))
			{
            	String s = this.description + ": "+form.searchForField("NGO");
				Transaction t =  new PaymentTransaction(user,method,this.type,s,value);
            	TransactionsLog log = TransactionsLog.getLog();
            	log.addTransaction(t);
            	return true;
            }
		}
		return false;
	}

}
