package assignment3;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Item {
	
	private double tax = 0.1;
	static NumberFormat df = new DecimalFormat("#0.00");
	
	// Declare variables for this class. Think about its type: public, protected
	// or private?
	String name;
	protected double price;
	protected int quantity;
	protected int weight;

	// You will need a constructor (Why?). Create it here.
	public Item() {
		name = "";
		price = 0;
		quantity = 0;
		weight = 0;
	}

	public Item(String name, double price, int quantity, int weight) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.weight = weight;
	}
	// get functions
	public String getName(){return name;}
	public double getPrice(){return price;}
	public int getQuantity(){return quantity;}
	public int getWeight(){return weight;}


	//
	public void update_quantity(int new_quantity) {
		this.quantity = new_quantity;
	}

	public double calculatePrice() {
		double final_price = 0;
		// Insert price calculation here
		double standard_shipping = (20*weight)*quantity;
		double item_price = (price*quantity)*(1+tax);
		final_price = item_price + standard_shipping;
		return final_price;
	}

	void printItemAttributes() {
		// Print all applicable attributes of this class
		System.out.format("Item: %s\nPrice: $%f\nQuantity: %d\nWeight: %d\nTotal Price: $%f\n", 
				name, price, quantity, weight, calculatePrice());
	}

}
