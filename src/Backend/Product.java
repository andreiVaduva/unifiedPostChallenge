package Backend;

import java.sql.Date;

public class Product {
	public int categoryId,cost;
	public String name;
	public Product(int categoryId,int cost,String name) {
		this.categoryId = categoryId;
		this.cost = cost;
		this.name = name;
		
	}
	
	public String toString() {
		return name + " with price " + cost  ;
	}
}
