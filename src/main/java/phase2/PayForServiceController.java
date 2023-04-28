package phase2;

import java.util.ArrayList;



import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Database.*;

import services.*;
import Users.*;
import Payment.*;

@SpringBootApplication
@RestController
public class PayForServiceController {
	
	Catalog c = new Catalog();

	@PostMapping(value = "/{username}/{serviceSelection}/{method}")
	public String payForService(@PathVariable("username") String username, @PathVariable("serviceSelection") String serviceSelection, @PathVariable("method") String method, @RequestBody FormDeco form) {
		Accounts accs = Accounts.getInstance();
		GeneralUser user = accs.getUser(username);
		if (user == null) {
			return "User does not exist";
		}
		
		Service service = c.findExactMatch(serviceSelection);
		if (service == null) {
			return "The service you are trying to access does not exist";
		}
		
		
		service.setForm(form);
		if(form == null ) {
			return "the input is incorrect";
		}
		
		method =  method.toLowerCase();
		if(method.equals("creditcard")) {
			if(service.handle(user, user.getCard())) {
				return "Transaction Successful";
			}
			return "Transaction Failed";
		}
		else if (method.equals("wallet")) {
			if(service.handle(user, user.getWallet())) {
				return "Transaction Successful";
			}
			return "Transaction Failed";
		}
		else if (method.equals("cod") && service.isCOD()) {
			Cash cod = new Cash();
			if(service.handle(user, cod)) {
				return "Transaction Successful";
			}
			return "Transaction Failed";
		}
		
		return "Transaction Failed";

		
	}
	
	@GetMapping(value = "/services/search/{word}")
	public ArrayList<Service> searchForService(@PathVariable("word") String word) {
		
		return c.searchCatalog(word);
		
	}
	
	@GetMapping(value = "/services")
	public String[] showServices() {
		
		return c.getServices();
		
	}
	
	@GetMapping(value = "/servicesCatalog")
	public Service[] showProviders() {
		
		return c.getServicesProviders();
		
	}
}
