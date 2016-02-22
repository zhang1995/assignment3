package assignment3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class A3Driver 
	{

	
	  public static void main(String[] args) 
	  {	  
		//Stub for arraylist.
		ArrayList<Item> shoppingCart = new ArrayList<Item>(); 
		
		//Open file; file name specified in args (command line)
		String fileName = args[0];
		String line = null;
		
		//Parse input, take appropriate actions.
		try{
			// FileReader reads text files in the default encoding
			FileReader fileReader = new FileReader(fileName);
			// Always wrap FileReader in BufferedReader
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null){
				parseInput(line, shoppingCart);
			}
			// Always close file
			bufferedReader.close();
		}catch(FileNotFoundException ex){
			System.out.println("Unable to open file: " + fileName);
		}catch(IOException ex){
			System.out.println("Error reading file: " + fileName);
		}
		/*
		// General code example for how to iterate an array list. You will have to modify this heavily, to suit your needs.
		Iterator<Item> i = shoppingCart.iterator();
		while (i.hasNext()) 
		{
			Item temp = i.next();
			temp.calculatePrice(); 
			temp.printItemAttributes();
			//This (above) works because of polymorphism: a determination is made at runtime, 
			//based on the inherited class type, as to which method is to be invoked. Eg: If it is an instance
			// of Grocery, it will invoke the calculatePrice () method defined in Grocery.
		}
		*/		
	  }
	  
	private static void parseInput(String input, ArrayList<Item> shoppingCart){
		  ArrayList<String> inputs = new ArrayList<String>();
		  for(String word : input.split(" ")){
			  inputs.add(word);
		  }
		  int length = inputs.size();
		  
		  String operation = inputs.get(0);
		  if(operation.equals("insert")){
			  String category = inputs.get(1);
			  String name = inputs.get(2);
			  double price = Double.parseDouble(inputs.get(3));
			  int quantity = Integer.parseInt(inputs.get(4));
			  int weight = Integer.parseInt(inputs.get(5));
			  if(category.equals("clothing")){
				  insertClothing(shoppingCart, category, name, price, quantity, weight);
			  }else if(category.equals("electronics")){
				  String fragile = inputs.get(6); String state = inputs.get(7);
				  insertElectronics(shoppingCart, category, name, price, quantity, weight, fragile, state);
			  }else if(category.equals("grocery")){
				  String perishable = inputs.get(6);
				  insertGrocery(shoppingCart, category, name, price, quantity, weight, perishable);
			  }
		  }else if(operation.equals("delete")){
			  delete(shoppingCart, inputs.get(1));
		  }else if(operation.equals("search")){
			  search(shoppingCart, inputs.get(1));
		  }else if(operation.equals("update")){
			  update(shoppingCart, inputs.get(1), Integer.parseInt(inputs.get(2)));
		  }else if(operation.equals("print")){
			  print(shoppingCart);
		  }
	  }
	
	public static void insertClothing(ArrayList<Item> cart, String category, String name, 
			double price, int quantity, int weight)
	{
		Clothing temp = new Clothing(name, price, quantity, weight);
		cart.add(temp);
		System.out.format("Added to Cart: %s, $%f, amount:%d, weight: %d\n", name, price, quantity, weight);
	}
	public static void insertElectronics(ArrayList<Item> cart, String category, String name, 
			double price, int quantity, int weight, String fragile, String state)
	{
		boolean is_fragile;
		if(fragile.equals("F")){is_fragile = true;}
		else{is_fragile = false;}
		
		try {
			Electronics temp = new Electronics(name, price, quantity, weight, is_fragile, state);
			cart.add(temp);
		} catch (Exception e) {
			System.out.println("Incorrect state name");
		}	
		System.out.format("Added to Cart: %s, $%f, amount: %d, weight: %d, frigile: %s, state: %s\n", name, price, quantity, weight, fragile, state);	
	}
	public static void insertGrocery(ArrayList<Item> cart, String category, String name, 
			double price, int quantity, int weight, String perishable)
	{
		boolean is_perishable;
		if(perishable.equals("P")){is_perishable = true;}
		else{is_perishable = false;}
		
		Grocery temp = new Grocery(name, price, quantity, weight, is_perishable);
		cart.add(temp);
		System.out.format("Added to Cart: %s, $%f, amount:%d, weight: %d, perishable:%s\n", name, price, quantity, weight, perishable);
	}
	
	public static void delete(ArrayList<Item> cart, String name){
		int total = 0;
		Iterator<Item> i = cart.iterator();
		while (i.hasNext()) 
		{
			Item temp = i.next();
			if(name.equals(temp.getName())){
				i.remove(); total++;
			}
		}	
		System.out.println(total + " " + name + " deleted.");
	}
	
	public static void search(ArrayList<Item> cart, String name){
		int total = 0;
		Iterator<Item> i = cart.iterator();
		while (i.hasNext()) 
		{
			Item temp = i.next();
			if(name.equals(temp.getName())){
				total ++;
			}
		}	
		System.out.println(total + " " + name + " found.");
	}
	
	public static void update(ArrayList<Item> cart, String name, int quantity){
		Iterator<Item> i = cart.iterator();
		while (i.hasNext()) 
		{
			Item temp = i.next();
			if(name.equals(temp.getName())){
				temp.update_quantity(quantity);
			}
		}	
		System.out.println("Set "+name+"'s quantity to "+quantity+".");
	}
	
	public static void print(ArrayList<Item> cart){
		double sum = 0;
		Iterator<Item> i = cart.iterator();
		while (i.hasNext()) 
		{
			Item temp = i.next();
			sum += temp.calculatePrice(); 
			temp.printItemAttributes();
		}	
		System.out.println("The total amount of the items in shopping cart is: " + sum);
	}
}
