package Database;

import java.util.LinkedList;

import java.util.List;


import Transactions.*;


public class RefundRequestsLog {
	
	private List<Transaction> requests;
    private static RefundRequestsLog requestsLog;
    
    private RefundRequestsLog(){
        requests = new LinkedList<Transaction>();
    }

    public static RefundRequestsLog getRefundLog() {
        if(requestsLog == null){
        	requestsLog = new RefundRequestsLog();
        }
        return requestsLog;
    }
    
    public List<Transaction> getRequests(){
    	return requests;
    }
    
    public void addRequest(Transaction trans){
    	requests.add(trans);
    	trans.setRefundRequested(true);
    }
    
    public void removeRequest(Transaction trans) {
    	requests.remove(trans);
    }
    public boolean isEmpty()
    {
    	if(requests.isEmpty())
    		return true;
    	else
    		return false;
    }
}
