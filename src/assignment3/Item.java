package assignment3;

public class Item {
	
	private double tax = 0.1;
	
	// Declare variables for this class. Think about its type: public, protected
	// or private?
	String name;
	protected double price;
	protected int quantity;
	protected double weight;

	// You will need a constructor (Why?). Create it here.
	public Item() {
		name = "";
		price = 0;
		quantity = 0;
		weight = 0;
	}

	public Item(String n, double p, int q, double w) {
		name = n;
		price = p;
		quantity = q;
		weight = w;
	}

	//
	public void update_quantity(int new_quantity) {
		
		this.quantity = new_quantity;
	}

	public double calculatePrice() {
		double final_price = 0;
		// Insert price calculation here
		double standard_shipping = (20*weight)*quantity;
		double price_after_tax = (price*quantity)*(1+tax);
		final_price = price_after_tax + standard_shipping;
		return final_price;
	}

	void printItemAttributes() {
		// Print all applicable attributes of this class
	}

}
