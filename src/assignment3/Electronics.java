package assignment3;

import java.util.Arrays;

public class Electronics extends Item {

	String[] tax_free_states = new String[] { "TX", "NM", "VA", "AZ", "AK" };
	//this contain all the states and other territories a total of 
	String[] us_states = new String[] { "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "GU", "HI",
			"IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MH", "MI", "MN", "MO", "MS", "MT", "NC", "ND",
			"NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "PR", "PW", "RI", "SC", "SD", "TN", "TX", "UT",
			"VA", "VI", "VT", "WA", "WI", "WV", "WY" };
	protected boolean is_fragile;
	protected String state;

	// Variables, constructors etc. here.
	public Electronics(String name, double price, int quantity, int weight, boolean fragile, String state) throws Exception {
		super(name, price, quantity, weight);
		this.is_fragile = fragile;
		if (Arrays.asList(us_states).contains(state)) {
			this.state = state;
		} else {
			throw new Exception("Incorrect State Name!!!");
		}

	}

	// Implement calculate price/print methods as necessary
	public double state_Tax() {

		return Arrays.asList(tax_free_states).contains(state) ? 0 : 0.1;

	}

	public double calculatePrice() {
		double final_price = 0;
		// shipping cost
		double shipping_cost = (20 * weight) * quantity;
		if (is_fragile) {
			shipping_cost *= 1.2;
		}
		// the price including tax
		double tax = state_Tax();
		double price_after_tax = (price * quantity) * (1 + tax);
		final_price = price_after_tax + shipping_cost;
		return final_price;
	}

	void printItemAttributes() {
		
		System.out.format("Item: %s\n" + "Price: $%.2f\n" + "Quantity: %d\n" + "Weight: %d\n" +"Is_Fragile: %b\n"+"State: %s\n"+"Total Price: $%.2f\n\n", 
				name, price, quantity, weight, is_fragile, state, calculatePrice());
	}

}
