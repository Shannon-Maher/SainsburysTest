package webscrap;

import java.util.ArrayList;

public class Products {
	ArrayList<Product> results = new ArrayList<Product>();
	double total = 0.00;
	
	/*
	 *Get an Array List that holds all product info
	 * @return ArrayList<Product> An Array List that holds all product info
	 */
	public ArrayList<Product> getResults() {
		return results;
	}
	
	/*
	 * Set an Array List that holds all product info
	 * @ArrayList<Product> results An Array List that holds all product info
	 */
	
	public void setResults(ArrayList<Product> results) {
		this.results = results;
	}
	
	/*
	 * Adds a new product into the Array List
	 * @param aProduct An object containing a product
	 */
	public void addProduct(Product aProduct)
	{
		results.add(aProduct);
		total = total + aProduct.getUnit_price();
		total = round(total,2);
	
	}
	
	/*
	 * Sets a double to an x number of decimal places
	 * @param value The current value before correction of decimal place
	 * @param places the number of decimal places
	 * @return double the corrected double to x decimal places
	 */
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	/*
	 * Returns the total unit price of all products
	 * @return double total of unit prices
	 */
	public double getTotal() {
		return total;
	}
	
	/*
	 * Sets the total unit price of all products
	 * @param total the total unit price of all products
	 */
	public void setTotal(double total) {
		this.total = total;
	} 
}
