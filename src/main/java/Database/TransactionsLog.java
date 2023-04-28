package Database;

import java.util.LinkedList;

import java.util.List;

import Transactions.*;
import Users.*;

public class TransactionsLog {

    private List<Transaction> log;
    private static TransactionsLog transactionsLog;
    private TransactionsLog(){
        log = new LinkedList<Transaction>();
    }

    public static TransactionsLog getLog() {
        if(transactionsLog == null){
            transactionsLog = new TransactionsLog();
        }
        return transactionsLog;
    }

    public List<Transaction> getList(){
        return log;  
    }
    public List<Transaction> listUserTrans(GeneralUser user){
        List<Transaction> res= new LinkedList<Transaction>();
        for(Transaction trans : log){
            if(trans.getUser().equals(user)){ res.add(trans); }
        }
        return res;
    }
    
    
    public List<Transaction> listAllUserTrans(String username){
        List<Transaction> res= new LinkedList<Transaction>();
        for(Transaction trans : log){
            if(trans.getUser().getUsername().equals(username)){ res.add(trans); }
        }
        return res;
    }

    public void addTransaction(Transaction trans){
        log.add(trans);
    }


}
