package project2;

/**
 * The Location class is used to represent the filming locations and fun facts that may be 
 * associated with them. It stores the name of the location itself and the optional fun fact.
 * The constructor should throw an instance of IllegalArgumentException
 * if it is called with the location string that is either null or empty.
 * The fun fact is allowed to be null or empty (this will happen when there is no
 * fun fact associated with the given location, i.e., the corresponding column in the data set is empty).
 * 
 * 
 * @author Christina Liu
 *
 */


public class Location {

	private String location;
	private String fun_fact;

	/**
	 * Constructs a new Location object with the location name as a String value and fun fact as a String value. 
	 * @param Location String value and fun fact String value should be used for this Location; 
	 * @throws IllegalArgumentException  if location String value parameter is invalid 
	 */
	
	public Location(String location, String fun_fact) throws IllegalArgumentException{
		if (location == null || location == "") {
			throw new IllegalArgumentException ("There is no location.");
		}
		this.location = location;
		this.fun_fact = fun_fact;
		
	}
	
	/**
	 * Returns the string representation of the location name.
	 * @returns the string representation of the location name for this Location object 
	 */
	
	public String getLocation () {
		return location;
	}
	
	/**
	 * Returns the string representation of the fun fact.
	 * @returns the string representation of the fun fact for this Location object 
	 */
	
	public String getFunFact () {
		if (fun_fact == null || fun_fact == "") {
			return "";
		}
		else {
			return fun_fact;
		}
	}
	
	/**
	 * Returns the boolean representation of the fun fact.
	 * @returns the boolean representation of the fun fact for this Location object 
	 */
	
	public boolean hasFunFact() {
		if (getFunFact() == null || getFunFact() == "") {
			return false;
		}
		else 
			return true;
	}
	
	/**
	 * Returns the string representation of this Location.
	 * @returns the string representation of this Location object 
	 */
	
	public String toString() {
		if (hasFunFact()) {
			return String.format("%s (%s)", getLocation(), getFunFact());
	}
		else {
			return String.format("%s", getLocation());
		}
	}
	
}
