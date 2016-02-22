package assignment3;

public class Clothing extends Item {

	// variables, constructors as necessary
	public Clothing() {
		super();
	}

	public Clothing(String name, double price, int quantity, int weight) {
		super(name,price,quantity,weight);
	}
	
	void printItemAttributes() {	
		System.out.format("Item: %s\n"+"Price: $%f\n"+"Quantity: %d\n"+"Weight: %d\n"+"Total Price: $%f\n\n", 
				name, price, quantity, weight, calculatePrice());
	}

}
