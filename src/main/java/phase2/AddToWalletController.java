package phase2;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Database.Accounts;
import Database.TransactionsLog;
import Transactions.AddToWalletTransaction;
import Transactions.Transaction;
import Users.GeneralUser;
@RestController
public class AddToWalletController {
	
	@PutMapping("/addToWallet/{username}/{amount}")
	@ResponseBody
	public String addToWallet(@PathVariable String username,@PathVariable double amount) {
		GeneralUser user = Accounts.getInstance().getUser(username);
		if(user == null)
			return "Invalid username.Please Try Again.\n";
		if(user.getCard()==null)
			return "User has not added a credit card.Please add one first.\n";
		if(user.getWallet().addFunds(user.getCard(), amount)) {
			Transaction t = new AddToWalletTransaction(user, user.getCard(), amount);
			TransactionsLog log = TransactionsLog.getLog();
			log.addTransaction(t);
			return amount+" has been added to Wallet\n"+user.to_String();
		}
		else {
			return "No Sufficient Funds in card\n";
		}
	}

}
