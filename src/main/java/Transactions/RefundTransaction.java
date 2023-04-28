package Transactions;


public class RefundTransaction extends Transaction{
	
	private Transaction trans;

	public RefundTransaction(Transaction trans) {
		this.trans = trans; 
		trans.setRefunded(true);
	}


	public Transaction getTrans() {
		return trans;
	}


	public void setTrans(Transaction trans) {
		this.trans = trans;
	}


	@Override
	public String displayTransaction() {
		String s = trans.displayTransaction();
		return s;
	}

}
