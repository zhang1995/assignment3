package assignment3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class A3Driver {
	// Stub for ArrayList.
	static ArrayList<Item> shoppingCart = new ArrayList<Item>();

	public static void main(String[] args) {
		/*open file; given in command line*/
		String fileName = args[0];
		String line = null;
		// Parse input, take appropriate actions.
		try {
			// FileReader reads text files in the default encoding
			FileReader fileReader = new FileReader(fileName);
			// Always wrap FileReader in BufferedReader
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				parseInput(line);
			}
			// Always close file
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file: " + fileName);
		} catch (IOException ex) {
			System.out.println("Error reading file: " + fileName);
		}catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	/**
	 * direct the input base on operation type
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	private static void parseInput(String input) throws Exception {
		// convert the input to an String array
		String[] transaction = input.toLowerCase().split(" ");
		// process base on the operation
		try{
			switch (transaction[0]) {
	
			case "insert":
				insert(transaction);
				return;
			case "delete":
				delete(transaction);
				return;
			case "search":
				search(transaction);
				return;
			case "update":
				update(transaction);
				return;
			case "print":
				print(transaction);
				return;
			default:
				throw new Exception("Incorrect Operation Input");
			}
		} catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	/**
	 * 	insert the item 
	 * 
	 * @param transaction
	 * @throws Exception
	 */
	public static void insert(String[] transaction) throws Exception {
		Item new_item = CreatesNewItem.generate_new_item(transaction);
		shoppingCart.add(new_item);

		System.out.format("%d %s added to cart. $%f per item. Weight: %d.\n", 
				new_item.getQuantity(), new_item.name, new_item.getPrice(), new_item.getWeight());
	}

	/**
	 * 	delete a given item
	 * 
	 * @param transaction
	 * @throws Exception
	 */
	public static void delete(String[] transaction) throws Exception {
		if (transaction.length != 2) {
			throw new Exception("Incorrect transaction length");
		}
		int total = 0;
		String name = transaction[1];
		Iterator<Item> i = shoppingCart.iterator();
		while (i.hasNext()) {
			Item temp = i.next();
			if (name.equals(temp.name)) {
				total += temp.getQuantity();
				i.remove();
			}
		}
		System.out.format("%d %s deleted.\n", total, name);
	}
	/**	search for an item
	 * 
	 * @param transaction
	 * @throws Exception
	 */
	public static void search(String[] transaction) throws Exception {
		if (transaction.length != 2) {
			throw new Exception("Incorrect transaction length");
		}
		int total = 0;
		String name = transaction[1];
		Iterator<Item> i = shoppingCart.iterator();
		while (i.hasNext()) {
			Item temp = i.next();
			if (name.equals(temp.name)) {
				total += temp.getQuantity();
			}
		}
		System.out.println(total + " " + name + " found.");
	}
	/**	update the quantity of a given item
	 * 
	 * @param transaction
	 * 		- incorrect transactions length
	 * 		- incorrect quantity input
	 * @throws Exception
	 */
	public static void update(String[] transaction) throws Exception {
		if (transaction.length != 3) {
			throw new Exception("Incorrect Transaction Length");
		}
		Iterator<Item> i = shoppingCart.iterator();
		String name = transaction[1];
		int quantity = 0;
		if (transaction[2].matches("[0-9]+")) {
			quantity = Integer.parseInt(transaction[2]);
		} else {
			throw new Exception("Incorrect Quantity Input");
		}
		boolean succeed = false;
		while (i.hasNext()) {
			Item temp = i.next();
			if (name.equals(temp.name)) {
				temp.update_quantity(quantity);
				succeed = true;
			}
		}
		if(succeed){
			System.out.println("Set " + name + "'s quantity to " + quantity + ".");
		}else {
			System.out.println("Can't find the item!");
		}
	}
	
	/**	print out a summary for the shopping cart
	 * 
	 * @param transaction - the input transaction
	 * @throws Exception
	 */
	public static void print(String[] transaction) throws Exception {
		if (transaction.length != 1) {
			throw new Exception("Incorrect transaction length");
		}
		
		System.out.println("");	/*start a new line*/
		double sum = 0;
		Collections.sort(shoppingCart, Item.itemNameComparator);
		Iterator<Item> i = shoppingCart.iterator();
		while (i.hasNext()) {
			Item temp = i.next();
			sum += temp.calculatePrice();
			temp.printItemAttributes();
		}
		System.out.println("The total amount of the items in shopping cart is: " + sum);
	}
}
