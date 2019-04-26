package project2;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The MovieList class is used to store all Movie objects.
 * This class inherits all of its properties from an ArrayList<Color>.
 * It provides a default constructor that creates an empty MovieList.  
 * It has two methods for getting two different MovieLists. 
 * 
 * 
 * @author Christina Liu
 *
 */

public class MovieList extends ArrayList<Movie> {
	
	


	//default constructor 
	public MovieList() {
		super();
	}

	/**
	 * Returns a list of Movie objects whose titles contain the keyword as a substring
	 * @returns the MovieList representation of the matching movie title names for this MovieList object 
	 */

	public MovieList getMatchingTitles ( String keyword ) { 
		MovieList MatchingList = new MovieList();
		if (keyword != null && keyword.trim() != "") {

			for (Movie c: this) {
				String title = c.getTitle().toLowerCase();
				if (title == null)  {
					continue;
				}
				
				if(title == "") {
					continue;
				}
				
				if (title.contains(keyword.toLowerCase().trim())) {
					MatchingList.add(c);
			}
		}
			if (MatchingList.size() == 0 ) {
				return null;
			}
			else {
				Collections.sort(MatchingList);
				return MatchingList;
			}
		}
			return null;

	}
	
	/**
	 * Returns a list of Movie objects whose actors’ names contain the keyword as a substring
	 * @returns the MovieList representation of the matching actor names for this MovieList object 
	 */
	
	public MovieList getMatchingActor ( String keyword ) {
		MovieList MatchingList = new MovieList();
		
		if (keyword != null && keyword.trim() != "") {
			
		for (int i =0; i < this.size(); i++) {
			if (this.get(i).getActors() == null)  {
				continue;				
			}
			
			if (this.get(i).getActors().size() == 0) {
				continue;
			}
			
			for (int j =0; j < this.get(i).getActors().size(); j++) {
				String actorName = this.get(i).getActors().get(j).getName().toLowerCase();
				
				if (actorName.contains(keyword.toLowerCase().trim())) {
					MatchingList.add(this.get(i));
			}
		}
		}

		if (MatchingList.size() == 0 ) {
			return null;
		}
		else {
			Collections.sort(MatchingList);
			return MatchingList;
		}
		}
		return null;
	}
	

	
	@Override
	public String toString() { 
		String Block = "";
		for(int i=0; i < this.size(); i++) {
			Block += this.get(i).getTitle() + ";";
		}
		 return (Block + "\n");
		
	}
	
	

	
}
