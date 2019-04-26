package project3;

import java.util.ArrayList;

import project3.Actor;
import project3.Location;
import project3.Movie;

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
	 * @param the index of the location desired
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
		StringBuilder movie = new StringBuilder();
		movie.append(String.format("%s (%d) \n", getTitle(), getYear()));
		movie.append("---------------------------------------------------------");
		movie.append("\n");
		movie.append(String.format("%-20s: %s \n", "director", getDirector()));
		movie.append(String.format("%-20s: %s\n", "writer", getWriter()));

		movie.append(String.format("%-20s: ", "starring"));
		for (Actor a : getActors()) {
			movie.append(String.format("%s, ", a.getName()));
		}
		movie.append("\n");
		
		movie.append(String.format("%-20s:\n",  "filmed on location at"));
		for (Location loc : getLocations()) {
			if (loc.hasFunFact()) {
				String funFact = loc.getFunFact();
				if (funFact == null || funFact.isEmpty()) {
					movie.append(String.format("\t%s\n", loc.getLocation()));
			}
				else
					movie.append(String.format("\t%s\t(%s) \n", loc.getLocation(), loc.getFunFact()));
			}
			else 
				movie.append(String.format("\t%s\n", loc.getLocation()));
		}
		movie.append("\n");
		return movie.toString();		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */	
	/**
	 * Returns the boolean if two movies are equal.
	 * @param the obj movie, or the other movie
	 * @returns the true if this movie and obj movie are equal, false if not 
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
	
	/**
	 * Returns an integer to see how comparable the two movies are
	 * @param the other movie
	 * @returns an integer 0, if both movies are the same, 
	 * 1 if the other movie was made before this movie, 
	 * and -1 if the other movie was made after this movie
	 */
	
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
