package project3;

/**
 * The Actor class is used to represent actors. 
 * It stores the name of an actor and it provides a one parameter constructor.
 * The constructor should throw an instance of IllegalArgumentException 
 * if it is called with a null parameter or an empty string parameter.
 * 
 * 
 * @author Christina Liu
 *
 */

public class Actor {

	private String s; 
	
	/**
	 * Constructs a new Actor object with the actor name as String s. 
	 * @param actorName String value should be used for this Actor; 
	 * @throws IllegalArgumentException  if actorName String value parameter is invalid 
	 */
	
	public Actor (String s) throws IllegalArgumentException{
		if (s == null || s == "") {
			throw new IllegalArgumentException ("There is no actor.");
		}
		this.s =s;
	}
	
	/**
	 * Returns the string of actor name.
	 * @returns the string representation of this Actor object 
	 */
	
	public String getName () {
		return s;
	}
}
