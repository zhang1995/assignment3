package assignment3;

public class Grocery extends Item {

	// variables, constructor here
	protected boolean is_perishable;

	public Grocery(String name, double price, int quantity, double weight, boolean is_perishable) {
		super(name, price, quantity, weight);
		this.is_perishable = is_perishable;
	}

	// override calculatePrice() if necessary; Implement print methods as
	// necessary
	public double calculatePrice() {
		double final_price = 0;
		// shipping cost
		double shipping_cost = (20 * weight) * quantity;
		if (is_perishable) {
			shipping_cost *= 1.2;
		}
		// the price including tax
		double taxfree_price = price * quantity;
		final_price = taxfree_price + shipping_cost;
		return final_price;
	}
	
void printItemAttributes() {
		
		System.out.format("Item: %s\n" + "Price: %d\n" + "Quantity: %d\n" + "Weight: %d\n" +"Is_Perishable: %b\n" + "Total Price: %d\n", 
				name, price, quantity, weight, is_perishable, calculatePrice());
	}

	// Only re-implement stuff you cannot get from the superclass (Item)

}
