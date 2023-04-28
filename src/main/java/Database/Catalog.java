package Database;

import java.util.ArrayList;


import services.*;



public class Catalog {
	
	private Service[] servicesProviders =  new Service[13];
	private String[] services = {"Mobile Recharge", "Internet Payment", "Landline", "Donation"};
	
	public String[] getServices() {
		return services;
	}

	public void setServices(String[] services) {
		this.services = services;
	}

	public Catalog() {
		servicesProviders[0] = new VodafoneMobile();
		servicesProviders[1] = new EtisalatMobile();
		servicesProviders[2] = new OrangeMobile();
		servicesProviders[3] = new WeMobile();
		servicesProviders[4] = new VodafoneInternet();
		servicesProviders[5] = new EtisalatInternet();
		servicesProviders[6] = new OrangeInternet();
		servicesProviders[7] = new WeInternet();
		servicesProviders[8] = new LandlineMonthly();
		servicesProviders[9] = new LandlineQuarterly();
		servicesProviders[10] = new DonationCancerHospital();
		servicesProviders[11] = new DonationNGO();
		servicesProviders[12] = new DonationSchools();
	}
	
	//TODO move to UI
	public void displayServices() {
		System.out.println("1: Mobile Recharge");
		System.out.println("2: Internet Payment");
		System.out.println("3: Landline");
		System.out.println("4: Donation");
	}
	
	public ArrayList<Service> findProviders(String service) {
		String s =  service.toLowerCase();
		ArrayList<Service> list =  new ArrayList<Service>();
		for (int i = 0 ; i<servicesProviders.length; i++) {
			if(servicesProviders[i].getType().toLowerCase().matches(s) ) {
				list.add(servicesProviders[i]);
			}
		}
		return list;

	}
	
	public ArrayList<Service> searchCatalog(String word){
		String s =  word.toLowerCase();
		ArrayList<Service> list =  new ArrayList<Service>();
		for (int i = 0 ; i<servicesProviders.length; i++) {
			if(servicesProviders[i].getDescription().toLowerCase().contains(s)) {
				list.add(servicesProviders[i]);
			}
		}
		return list;
	}
	
	public Service findExactMatch(String word){

		for (int i = 0 ; i<servicesProviders.length; i++) {
			if(servicesProviders[i].getUrl().equals(word)) {
				return servicesProviders[i];
			}
		}
		return null;
	}

	public Service[] getServicesProviders() {
		return servicesProviders;
	}
	

}
