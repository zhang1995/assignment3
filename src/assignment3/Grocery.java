package assignment3;

public class Grocery extends Item {

	protected boolean is_perishable;

	// variables, constructor here

	// override calculatePrice() if necessary; Implement print methods as
	// necessary
	public double calculatePrice() {
		//no tax for grocery
		return price*weight*quantity;
	}

	// Only re-implement stuff you cannot get from the superclass (Item)

}
