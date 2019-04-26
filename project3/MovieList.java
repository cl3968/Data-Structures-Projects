package project3;

import project3.Movie;
import project3.LinkedList;

/**
 * The MovieList class is used to store all Movie objects.
 * This class inherits all of its properties from a LinkedList<Movie>.
 * It provides a default constructor that creates an empty MovieList.  
 * It has two methods for getting two different MovieLists. 
 * 
 * 
 * @author Christina Liu
 *
 */

public class MovieList extends LinkedList<Movie>{

	//default constructor 
	public MovieList() {
		super();
	}

	/**
	 * Returns a list of Movie objects whose titles contain the keyword as a substring
	 * @param a keyword to search for matching movie titles
	 * @returns the MovieList representation of the matching movie title names for this MovieList object 
	 */

	public MovieList getMatchingTitles ( String keyword ) { 
		
		//making a new list
		MovieList MatchingList = new MovieList();
		
		//verifying that the keyword is valid
		if (keyword != null && keyword.trim() != "") {
			
			//running through each movie in the movielist
			for (Movie c: this) {
				String title = c.getTitle().toLowerCase();
				
				//verifying that the title is valid
				if (title == null)  {
					continue;
				}
				if(title == "") {
					continue;
				}
				
				//if the title matches the keyword then it added to a the new list
				if (title.contains(keyword.toLowerCase().trim())) {
					MatchingList.add(c);
			}
		}
			if (MatchingList.size() == 0 ) {
				return null;
			}
			else {
				
				//sorting and returning the new list
				this.sort();
				return MatchingList;
			}
		}
			return null;
	}
	
	/**
	 * Returns a list of Movie objects whose actors’ names contain the keyword as a substring
	 * @param a keyword to search for matching actor names
	 * @returns the MovieList representation of the matching actor names for this MovieList object 
	 */
	
	public MovieList getMatchingActor ( String keyword ) {
		
		//making a new list
		MovieList MatchingList = new MovieList();
		
		//verifying that the keyword is valid
		if (keyword != null && keyword.trim() != "") {
			
			//running through all the movies in movielist
			for (int i =0; i < this.size(); i++) {
				
				//verifying that the actors are valid
				if (this.get(i).getActors() == null)  {
					continue;				
			}
				if (this.get(i).getActors().size() == 0) {
					continue;
			}
				
			//running through all the actors for each movie 
			for (int j =0; j < this.get(i).getActors().size(); j++) {
				String actorName = this.get(i).getActors().get(j).getName().toLowerCase();
				
				//if the actor name matches the keyword then it added to a the new list
				if (actorName.contains(keyword.toLowerCase().trim())) {
					MatchingList.add(this.get(i));
			}
		}
		}
		if (MatchingList.size() == 0 ) {
			return null;
		}
		else {
			
			//sorting and returning the new list
			this.sort();
			return MatchingList;
		}
		}
		return null;
	}

	/**
	 * Returns the string representation of this MovieList.
	 * @returns the string representation of this MovieList object 
	 */
	
	@Override
	public String toString() { 
		String Block = "";
		for(int i=0; i < this.size(); i++) {
			Block += this.get(i).getTitle() + ";";
		}
		 return (Block + "\n");
	}
}
