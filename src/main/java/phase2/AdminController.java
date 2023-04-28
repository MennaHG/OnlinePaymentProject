package phase2;

import java.util.List;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Database.*;
import Payment.*;
import services.*;
import Transactions.*;
import Users.*;


@RestController
@Component
@RequestMapping("/Admin")
public class AdminController {
	
  
	private static Accounts accounts;
	private static Catalog catalog;
	private static TransactionsLog translog;
	private static RefundRequestsLog rlog;
	public AdminController() {
			accounts=Accounts.getInstance();
			translog=TransactionsLog.getLog();
			rlog=RefundRequestsLog.getRefundLog();
			catalog=new Catalog();
		
	}
	
	///TODO change return data type if needed
	
	
	
	/// case 1 -> add discount
	/// case 1.1->add overall discount
	/*working web service*/
	@PutMapping("/addOverallDiscount")
	@ResponseBody
	public String addOverallDiscount( @RequestBody Discount overallDiscount )  {
		
		List<GeneralUser> accountsModify=accounts.getAccsTomodify();
		if(accountsModify.isEmpty())
			return "no users";
		for(GeneralUser U:accountsModify)
		{
			U.setDiscount(overallDiscount);
		}
		return "discount added";
		
		
	}
	//// case 1.2 -> add specific discount for a certain service
	/// throws exception if entered a wrong services description
	/*works*/
	@PutMapping("/addSpecificDiscount/{serviceNumber}")
	@ResponseBody
	public String addSpecificDiscount(@RequestBody Discount specficDiscount , @PathVariable int serviceNumber ) 
	{
		
		Service[] servicesProviders=catalog.getServicesProviders();
		if(serviceNumber<=0 || serviceNumber>servicesProviders.length)
			return "Please Enter a Valid Service Number";
		servicesProviders[serviceNumber-1].setDiscount(specficDiscount);
		return "Discount added to "+servicesProviders[serviceNumber-1].getClass().getSimpleName()+" service";

	}
	
	/// case 2 -> list user transactions if list empty should be handled in boundary
	/*working*/
	@GetMapping("/listUserTransactions/{Username}")
	@ResponseBody
	public List<Transaction> listUserTransactions(@PathVariable String Username){
		List<Transaction> usertranscations=translog.listAllUserTrans(Username);
		return usertranscations;
	}
	///case 3.1->list all refund requests and approve or reject
	/*working*/
	@GetMapping("/listRefundRequests")
	@ResponseBody
	public List<Transaction> listRefundRequests(){
		
		return rlog.getRequests();
	}
	
	///case 3.2 ->accept or reject a request
	@PutMapping("/accept/{transnumber}")
	@ResponseBody
	public String accept(@PathVariable int transnumber) {
	
		if(rlog.isEmpty())
			return "NO refund Requests at the moment";
		
		if(transnumber<=0 || transnumber>rlog.getRequests().size())
			return"Please Enter a valid number";
			

		
		Transaction t=rlog.getRequests().get(transnumber-1);
		
		
		t.getMethod().add(t.getAmount());
		t.setRefunded(true);
		rlog.removeRequest(t);
		return "accepted";
		
	}
	
	@PutMapping("/reject/{transnumber}")
	@ResponseBody
	public String reject(@PathVariable  int transnumber) {
		
		if(rlog.isEmpty())
			return "NO refund Requests at the moment";
		
		if(transnumber<=0 || transnumber>rlog.getRequests().size())
			return"Please Enter a valid number";
		
	
	
		Transaction t=rlog.getRequests().get(transnumber-1);
		
		
		t.setRefunded(false);
		rlog.removeRequest(t);
		return "Rejected";
		
	}
	
	
	
	
	
	
	

}
