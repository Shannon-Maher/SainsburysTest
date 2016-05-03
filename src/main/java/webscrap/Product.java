package webscrap;

public class Product {
	private String title = "";
	private String size = "";
	private double unit_price = 0.00;
	private String description = "";
	
	/*
	 * The Title
	 * @return String the title of the product
	 */
	public String getTitle() {
		return title;
	}
	
	/*
	 * The Title
	 * @param title the title of the product
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/*
	 * The size of the image
	 * @return String the size of the image
	 */
	public String getSize() {
		return size;
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
	 * The size of the image
	 * @param size the size of the image
	 */
	public void setSize(String size) {
		Double dbSize = new Double(size);
		
		dbSize = (dbSize / 1024); 
		dbSize = round(dbSize,2);
		this.size = dbSize.toString()+"kb";
	}
	
	/*The unit price of the product
	 * @Return double The unit price
	 */
	public double getUnit_price() {
		return unit_price;
	}
	
	/*The unit price of the product
	 * @param unit_price The unit price
	 */
	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}
	
	/*
	 * The description of the product
	 * @return String the Description
	 */
	public String getDescription() {
		return description;
	}
	
	/*
	 * The description of the product
	 * @param description the Description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
