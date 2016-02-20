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

	// Only re-implement stuff you cannot get from the superclass (Item)

}
