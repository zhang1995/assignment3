package assignment3;

public class Electronics extends Item {

	protected boolean is_fragile;
	protected String state;

	// Variables, constructors etc. here.
	public Electronics(String n, double p, int q, double w, boolean f, String s) {
		super(n,p,q,w);
		this.is_fragile = f;
		
		this.state = s;
		
	}
	// Implement calculate price/print methods as necessary

}
