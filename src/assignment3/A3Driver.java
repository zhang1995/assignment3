package assignment3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class A3Driver {

	// Stub for arraylist.
	static ArrayList<Item> shoppingCart = new ArrayList<Item>();

	public static void main(String[] args) {

		// Open file; file name specified in args (command line)
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
		/*
		 * // General code example for how to iterate an array list. You will
		 * have to modify this heavily, to suit your needs. Iterator<Item> i =
		 * shoppingCart.iterator(); while (i.hasNext()) { Item temp = i.next();
		 * temp.calculatePrice(); temp.printItemAttributes(); //This (above)
		 * works because of polymorphism: a determination is made at runtime,
		 * //based on the inherited class type, as to which method is to be
		 * invoked. Eg: If it is an instance // of Grocery, it will invoke the
		 * calculatePrice () method defined in Grocery. }
		 */
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

	public static void insert(String[] transaction) throws Exception {
		Item new_item = CreatesNewItem.generate_new_item(transaction);
		shoppingCart.add(new_item);

		System.out.println("Inserted " + new_item.name + " into Shopping Cart.");
	}

	public static void delete(String[] transaction) throws Exception {
		int total = 0;
		if (transaction.length != 2) {
			throw new Exception("Incorrect transaction length");
		}
		String name = transaction[1];
		Iterator<Item> i = shoppingCart.iterator();
		while (i.hasNext()) {
			Item temp = i.next();
			if (name.equals(temp.getName())) {
				i.remove();
				total++;
			}
		}
		System.out.println(total + " " + name + " deleted.");
	}

	public static void search(String[] transaction) throws Exception {
		int total = 0;
		if (transaction.length != 2) {
			throw new Exception("Incorrect transaction length");
		}
		String name = transaction[1];
		Iterator<Item> i = shoppingCart.iterator();
		while (i.hasNext()) {
			Item temp = i.next();
			if (name.equals(temp.getName())) {
				total++;
			}
		}
		System.out.println(total + " " + name + " found.");
	}

	public static void update(String[] transaction) throws Exception {
		Iterator<Item> i = shoppingCart.iterator();
		if (transaction.length != 3) {
			throw new Exception("Incorrect transaction length");
		}
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
			if (name.equals(temp.getName())) {
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

	public static void print(String[] transaction) throws Exception {
		double sum = 0;
		Iterator<Item> i = shoppingCart.iterator();
		while (i.hasNext()) {
			Item temp = i.next();
			sum += temp.calculatePrice();
			temp.printItemAttributes();
		}
		System.out.println("The total amount of the items in shopping cart is: " + sum);
	}
}
