package phase2;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Database.Accounts;
import Payment.CreditCard;
import Users.GeneralUser;

@RestController
public class AccountController {
	
	
	private Accounts accs;
	

	public AccountController() {
		this.accs = Accounts.getInstance(); 
		//test.FirstSpringAppApplication.main(null);
	}

	@GetMapping("/login")
	public String LogIn(@RequestBody GeneralUser user) {
		List<GeneralUser> copy = accs.getAccs();
		if (copy.isEmpty()) {
			return "\nUSER DOES NOT EXIST. TRY AGAIN\n";
		}
		else if(accs.getUser(user) != null){
			GeneralUser usr= accs.getUser(user);
			return "LOGIN SUCCESSFUL\n"+usr.to_String();
		}
		else {
			return("\nUSER DOES NOT EXIST. TRY AGAIN\n");
		}
	}
	

	@PostMapping("/signup")
	public String signUp(@RequestBody GeneralUser new_user)  {
		List<GeneralUser> copy = accs.getAccs();
		if ( copy.isEmpty()) {
			accs.addUser(new_user);			
			return ("SIGN UP SUCCESSFUL\n"+new_user.to_String());	
		}
		
		if (accs.userExists(new_user)) {
				return("\nUSERNAME/EMAIL ALREADY EXISTS. PLEASE TRY AGAIN\n");
			}
		
		accs.addUser(new_user); 
		return "SIGN UP SUCCESSFUL\n"+new_user.to_String();
	}
	
	@PutMapping("/addCard/{username}")
	@ResponseBody
	public String addCardInfo(@PathVariable String username,@RequestBody CreditCard card) {
		GeneralUser user = accs.getUser(username);
		if(user == null) {return "Invalid Username.Try Again";}
		user.setCard(card);
		return "CARD ADDED\n"+user.to_String();		
	}

}