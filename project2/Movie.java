package project2;

import java.util.ArrayList;

/**
 * The Movie class is used to store the following information:
 * title, year of release, list of locations in which the movie was filmed, director, writer, and up to three actors.
 * It has two constructors that throw an instance of the IllegalArgumentException, 
 * if it is called with a null or empty title, an invalid year, or a null or empty first actor. 
 * This class implements the Comparable<Movie> interface.
 * 
 * 
 * @author Christina Liu
 *
 */

public class Movie implements Comparable<Movie> {

	private String title; 
	private int year;
	private String director;
	private String writer = "";
	private ArrayList<Actor> actors;
	private ArrayList<Location> loc;
	
	/**
	 * Constructs a new Movie object with the movie title as a String value and year of release as an integer value. 
	 * @param Title String value and year int value should be used for this Movie; 
	 * @throws IllegalArgumentException if title String value parameter or year int value parameter are invalid 
	 */
	
	public Movie(String title, int year) throws IllegalArgumentException{
		if (title == null || title == "") {
			throw new IllegalArgumentException ("There is no title.");
		}
		if (year < 1900 || year > 2020) {
			throw new IllegalArgumentException("The year is invalid.");
		}
		this.title = title;
		this.year = year;
		loc = new ArrayList <Location>();
		
	}
	
	/**
	 * Constructs a new Movie object with the movie title as a String value, year of release as an integer value,
	 * director name as a String value, and first, second, and third actors as Actor values.
	 * @param Title String value, year int value, director String value, writer String value, 
	 * actor1 Actor value, actor2 Actor value, and actor3 Actor value should be used for this Movie; 
	 * @throws IllegalArgumentException if title String value parameter, year int value parameter, and actor1 Actor value parameter are invalid 
	 */
	
	public Movie(String title, int year, String director, String writer, Actor actor1, Actor actor2, Actor actor3) throws IllegalArgumentException{
		if (title == null || title == "") {
			throw new IllegalArgumentException ("There is no title.");
		}
		if (year <= 1900 || year >= 2020) {
			throw new IllegalArgumentException("The year is invalid.");
		}
		if(actor1 == null) {
			throw new IllegalArgumentException("Must have first actor.");
		}
		
		this.title = title;
		this.year = year;

		
			this.director = director;			
			this.writer = writer;
		
			actors = new ArrayList<Actor>();
			actors.add((actor1));
			if (actor2 != null && actor2.getName() != "") {
				actors.add((actor2));
			}
			if (actor3 != null && actor3.getName() != "") {
				actors.add((actor3));
			}
			
			loc = new ArrayList <Location>();

		
	}

	/**
	 * Returns the string representation of the movie title name.
	 * @returns the string representation of the movie title name for this Movie object 
	 */	
	
	public String getTitle() {
		return title;
	}
	
	/**
	 * Returns the integer representation of the year of release.
	 * @returns the integer representation of the year for this Movie object 
	 */
	
	public int getYear() {
		return  year;
	}
	
	/**
	 * Returns the string representation of the director name.
	 * @returns the string representation of the director name for this Movie object 
	 */
	
	public String getDirector() {
		return director;
	}
	
	/**
	 * Returns the string representation of the writer name.
	 * @returns the string representation of the writer name for this Movie object 
	 */
	
	public String getWriter() {
		return writer;
	}
	
	/**
	 * Returns the ArrayList of Actors representation of the actors' name.
	 * @returns the ArrayList of Actors representation of the actors' name for this Movie object 
	 */
	
	public ArrayList<Actor> getActors() {
		return actors;
	}

	/**
	 * Returns the ArrayList of Locations representation of all the locations filmed.
	 * @returns the ArrayList of Locations representation of all the locations filmed for this Movie object 
	 */

	public ArrayList<Location> getLocations() {
		return loc;
	}
	
	/**
	 * Returns the Location representation of the location of a specific index.
	 * @returns the Location representation of the specific location for this Movie object 
	 */
	
	public Location getLocation(int i) {
		return loc.get(i);
	}
	
	/**
	 * Validates and sets new Location for this Movie object. 
	 * @param new Location value to be examined and set. 
	 * @throws IllegalArgumentException if the Location is invalid 
	 */
	
	public void addLocation(Location newLoc) throws IllegalArgumentException{ 
		
		if (newLoc == null) {
			throw new IllegalArgumentException("Location is null.");
		}
		loc.add(newLoc);
	
	}
	
	/**
	 * Returns the string representation of this Movie.
	 * @returns the string representation of this Movie object 
	 */
	
	public String toString () {
		String movie = (getTitle().toUpperCase() + " (" + getYear() + ")" + "\n" + "--------------------------" + 
	"\n" + "director: " + getDirector().toUpperCase() + "\n" + "writer: " + getWriter().toUpperCase() + "\n" );
			
				for (int i = 0; i < getActors().size(); i++) {
					
					movie += ( "starring: " + getActors().get(i).getName().toUpperCase() + "\n" );
				
				}
				if (loc.size()!=0) {	
					movie += ("filmed on location at: " + "\n");
										
					for (int i = 0; i < loc.size(); i++) {
				
						movie += ( loc.get(i).toString().toUpperCase());
					
					if (loc.get(i).hasFunFact()) { 
						
						movie += ("(" + loc.get(i).getFunFact().toUpperCase() + ")" + "\n");
					
					}
					else 
						movie += "\n"+ "\n" +"\n";
				}
				}
				
		return movie;
			
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Movie))
			return false;
		Movie other = (Movie) obj;
		 if (!title.equalsIgnoreCase(other.title) || !(year==other.year))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Movie arg0) {
		
		if (this.getYear() > (arg0.getYear())) {
			return 1;
		};
		if (this.getYear() < (arg0.getYear())) {
			return -1;
		}
		return  (this.getTitle().compareToIgnoreCase(arg0.getTitle()));
	}
	
}
