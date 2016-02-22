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
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
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
		switch (transaction[0]) {

		case "insert":
			insert(transaction);
			break;
		case "delete":
			delete(transaction);
			break;
		case "serach":
			search(transaction);
			break;
		case "update":
			update(transaction);
			break;
		case "print":
			print(transaction);
			break;
		default:
			throw new Exception("Incorrect Operation Input");
		}
		return;
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
		String name = transaction[2];
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
		String name = transaction[2];
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
		String name = transaction[2];
		int quantity = 0;
		if (transaction[3].matches("[0-9]")) {
			quantity = Integer.parseInt(transaction[4]);
		} else {
			throw new Exception("Incorrect Quantity Input");
		}
		while (i.hasNext()) {
			Item temp = i.next();
			if (name.equals(temp.getName())) {
				temp.update_quantity(quantity);
			}
		}
		System.out.println("Set " + name + "'s quantity to " + quantity + ".");
	}

	public static void print(String[] transaction) throws Exception{
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
