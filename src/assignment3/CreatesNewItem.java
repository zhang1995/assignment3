package assignment3;

import java.util.regex.Pattern;

public class CreatesNewItem {
	static String doublenumber = "([0-9]*)\\.([0-9]*)";
	static String wholenumber = "([0-9]*)\\.([0]*)";

	/**
	 * this method will creates a new item according to the command line
	 * 
	 * 
	 * @param transaction
	 *            exception
	 * @return
	 */
	public Item generate_new_item(String[] transaction) throws Exception {
		// Declare varibles
		String name = null;
		double price = 0;
		int quantity = 0;
		int weight = 0;

		// check the validility of the transaction
		if (transaction.length < 6) {
			throw new Exception("Incorrect Number of Elements In The Transaction");
		}
		name = transaction[2].toLowerCase();
		// check price
		if (Pattern.matches(doublenumber, transaction[3]) || transaction[3].matches("[0-9]")) {
			price = (double) Double.parseDouble(transaction[3]);
		} else {
			throw new Exception("Incorrect Price Input");
		}
		// check quantity
		if (transaction[4].matches("[0-9]")) {
			quantity = Integer.parseInt(transaction[4]);
		} else {
			throw new Exception("Incorrect Quantity Input");
		}
		// check weight
		if (Pattern.matches(wholenumber, transaction[5]) || transaction[5].matches("[0-9]")) {
			weight = Integer.parseInt(transaction[5]);
		} else {
			throw new Exception("Incorrect Weight Input");
		}
		String category = transaction[1].toLowerCase();
		//implement item base on category
		switch(category) {
			
		case "clothing":
			//clothing transaction contains 6 elements
			if(transaction.length != 7) {
				throw new Exception("Incorrect Transaction for Clothing");
			}
			return new Clothing(name, price, quantity, weight);
			
		case "groceries":
			//clothing transaction contains 7 elements
			
			
		case "electronics":
		
			
		default:
			throw new Exception("Incorrect Category Name");
		}
	}

}
