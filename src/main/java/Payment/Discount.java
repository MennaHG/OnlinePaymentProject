package Payment;


public class Discount {
	
	double factor;
	int counter;
	

	public double getFactor() {
		return factor;
	}

	public int getCounter() {
		return counter;
	}

	
	
	public void setFactor(double factor) {
		this.factor = factor;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public Discount(double factor, int counter) {
		this.factor = factor;
		this.counter = counter;
	}
	
	public Discount() {
		this.factor = 1;
		this.counter = 0;
	}
	
	public double use() {
		if(--counter < 0) {
			return 1;
		}
		return factor;

	}
	
	

}
