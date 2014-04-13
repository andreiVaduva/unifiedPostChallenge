package Backend;

public class Pair {
	String product;
	int votes;
	public Pair(String product,int votes) {
		this.product = product;
		this.votes = votes;
	}
	
	public String toString() {
		return product;
	}
}
