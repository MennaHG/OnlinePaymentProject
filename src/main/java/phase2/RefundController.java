package phase2;

import java.util.LinkedList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Database.Accounts;
import Database.RefundRequestsLog;
import Database.TransactionsLog;
import Transactions.RefundTransaction;
import Transactions.Transaction;
import Users.GeneralUser;

@RestController
public class RefundController {
	private static TransactionsLog Translog;
	private static RefundRequestsLog refunds;

	public RefundController() {
		Translog = TransactionsLog.getLog();
		refunds = RefundRequestsLog.getRefundLog();
	}

	@GetMapping("/getTransactions/{username}")
	@ResponseBody
	public String addRequest(@PathVariable String username) {
		GeneralUser user = (Accounts.getInstance()).getUser(username);
		if(user == null) {return "Invalid username";}
		List<Transaction> transactions = Translog.listUserTrans(user);
		String res ="All Refundable User Transaction\n"; int num=1;
		for (Transaction trans : transactions) {
			if (trans instanceof RefundTransaction || trans.isRefunded() || trans.isRefundRequested())
				continue;

			res+=(num++)+"- "+trans.displayTransaction()+'\n';
		}
		return res;
	}
	
	@PostMapping("/addRequest/{username}/{transNo}")
	@ResponseBody
	public String refundTrans(@PathVariable String username,@PathVariable int transNo) {
		GeneralUser user = (Accounts.getInstance()).getUser(username);
		if(user == null) {return "Invalid username";}
		List<Transaction> transactions = Translog.listUserTrans(user);
		if(transactions.isEmpty() || transNo<=0 || transNo>transactions.size())
			return "Transaction Number doesn't exist\nPlease Choose one of the following\n"+addRequest(username);
		
		List<Transaction> res = new LinkedList<>();
		for (Transaction trans : transactions) {
			if (trans instanceof RefundTransaction || trans.isRefunded() || trans.isRefundRequested())
				continue;
			res.add(trans);
		}
		
		if(res.size() <= transNo-1)
		  return "Transaction Number doesn't exist\nPlease Choose one of the following\n"+addRequest(username);
		
		refunds.addRequest(res.get(transNo-1));return "Refund Request has been added";
		
				
	}
}