package project6;

import java.util.Iterator;

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

public class MovieList extends BST<Movie>{

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
			Iterator<Movie> itr = this.iterator();
			while (itr.hasNext()) {
				Movie m = itr.next();
				String title = m.getTitle().toLowerCase();
				
				//verifying that the title is valid
				if (title == null)  {
					continue;
				}
				if(title == "") {
					continue;
				}
				
				//if the title matches the keyword then it added to a the new list
				if (title.contains(keyword.toLowerCase().trim())) {
					MatchingList.add(m);
			}
		}
			if (MatchingList.size() == 0 ) {
				return null;
			}
			else {
				
				//returning the new list
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
			Iterator<Movie> itr = this.iterator();
				
				//verifying that the actors are valid
				while (itr.hasNext()) {
					Movie m = itr.next();
				if (m.getActors() == null) continue;
				if (m.getActors().size() == 0) continue;
				else {
					
							
			//running through all the actors for each movie 
			for (int j =0; j < m.getActors().size(); j++) {
				String actorName = m.getActors().get(j).getName().toLowerCase();
				
				//if the actor name matches the keyword then it added to a the new list
				if (actorName.contains(keyword.toLowerCase().trim())) {
					MatchingList.add(m);
			}
			}
				}
		}
		if (MatchingList.size() == 0 ) {
			return null;
		}
		else {
			
			//returning the new list
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
		Iterator<Movie> itr = this.iterator();
		while (itr.hasNext()){
			Block += itr.next().getTitle() + ";";
		}
		 return (Block + "\n");
	}
}

