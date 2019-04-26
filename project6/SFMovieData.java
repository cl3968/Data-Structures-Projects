package project6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The SFMovieData class is responsible for opening and reading the data files, obtaining user input, 
 * performing some data validation and handling all errors that may occur.
 * The program is interactive. 
 * When the program is executed the name of the input file containing the list of all the film locations 
 * in San Francisco is provided as the program's single command line argument. The data in this file 
 * serves as a database of all the movies. 
 * In the interactive part, the user enters a keyword string representation of a title or actor. The program 
 * responds by printing the movie descriptions matching the keyword.
 * 
 * 
 * 
 * @author Christina Liu adapted from @author Joanna Klukowska's ColorConverted code
 *
 */

public class SFMovieData {
	
	public static void main(String[] args) {
		
			//verify that the command line argument exists 
			if (args.length == 0 ) {
				System.err.println("Usage Error: the program expects file name as an argument.\n");
				System.exit(1);
			}
			
			//verify that command line argument contains a name of an existing file 
			File FilmLocations = new File(args[0]); 
			if (!FilmLocations.exists()){
				System.err.println("Error: the file "+FilmLocations.getAbsolutePath()+" does not exist.\n");
				System.exit(1);
			}
			
			//verify that command line argument can be read
			if (!FilmLocations.canRead()){
				System.err.println("Error: the file "+FilmLocations.getAbsolutePath()+
												" cannot be opened for reading.\n");
				System.exit(1);
			}
			
			//open the file for reading 
			Scanner inMovies = null; 
			
			try {
				inMovies = new Scanner (FilmLocations ) ;
			
			//verifying that the command line argument can be opened 
			} catch (FileNotFoundException e) {
				System.err.println("Error: the file "+FilmLocations.getAbsolutePath()+
												" cannot be opened for reading.\n");
				System.exit(1);
			}
			
			//read the content of the file and save the data in an ArrayList of separate Movie objects
			MovieList list = new MovieList(); 
			
			String line = null; 
			
			//for each line in the command line argument document, there will be one movie object created 
			//associated to that line 
			while (inMovies.hasNextLine()) {	
				try { 
					line = inMovies.nextLine(); 
					ArrayList<String> splitLine = splitCSVLine(line);
					
					//this is to prevent a null pointer exception due to the arrays being different sizes depending 
					//on the number of actors 
					//the tutors helped me write this part of the code
					if (splitLine.size() == 9) {
						Actor a1 = new Actor(splitLine.get(8));
						Actor a2 = null;
						Actor a3 = null; 
						Movie movie = new Movie(splitLine.get(0), Integer.parseInt(splitLine.get(1)), splitLine.get(6), splitLine.get(7), a1,a2, a3);
						
						//this is to check for repetitive movies and adding a new location instead of creating a new movie object
						if (list.contains(movie)) {
							Location newLoc = new Location(splitLine.get(2), splitLine.get(3));

							//add locations to duplicate movie 
							Iterator<Movie> itr = list.iterator();
							while (itr.hasNext()) {
								if (itr.next().equals(movie)) 
									itr.next().addLocation(newLoc);
							}
							continue;		
						}
						else {
							Location newLoc = new Location(splitLine.get(2),splitLine.get(3));
							movie.addLocation( newLoc);
							list.add(movie);
						}
					}
					else if (splitLine.size() == 10) {
						Actor a1 = new Actor(splitLine.get(8));
						Actor a2 = new Actor(splitLine.get(9));
						Actor a3 = null; 	
						Movie movie = new Movie(splitLine.get(0), Integer.parseInt(splitLine.get(1)), splitLine.get(6), splitLine.get(7), a1,a2, a3);
						
						//this is to check for repetitive movies and adding a new location instead of creating a new movie object
						if (list.contains(movie)) {
							Location newLoc = new Location(splitLine.get(2), splitLine.get(3));

							//add locations to duplicate movies
							Iterator<Movie> itr = list.iterator();
							while (itr.hasNext()) {
								if (itr.next().equals(movie)) 
									itr.next().addLocation(newLoc);
							}
							continue;		
						}
						else {
							Location newLoc = new Location(splitLine.get(2),splitLine.get(3));
							movie.addLocation( newLoc);
							list.add(movie);
						}
					}
					else if (splitLine.size() == 11) {
						Actor a1 = new Actor(splitLine.get(8));
						Actor a2 = new Actor(splitLine.get(9));
						Actor a3 = new Actor(splitLine.get(10));
						Movie movie = new Movie(splitLine.get(0), Integer.parseInt(splitLine.get(1)), splitLine.get(6), splitLine.get(7), a1,a2, a3);

						//this is to check for repetitive movies and adding a new location instead of creating a new movie object
						if (list.contains(movie)) {
							Location newLoc = new Location(splitLine.get(2), splitLine.get(3));
							
							//add location to duplicate movies
							Iterator<Movie> itr = list.iterator();
							while (itr.hasNext()) {
								if (itr.next().equals(movie)) 
									itr.next().addLocation(newLoc);
							}
							continue;		
						}
						else {
							Location newLoc = new Location(splitLine.get(2),splitLine.get(3));
							movie.addLocation( newLoc);
							list.add(movie);
						}
					}	
				}
				
				catch (NoSuchElementException ex ) {
					//caused by an incomplete or miss-formatted line in the input file
					System.err.println(line);
					continue; 	
				}
				
				catch (NumberFormatException E) {
					//ignore this exception and skip to the next line because it is the first line 
				}
				
				catch (IllegalArgumentException ex ) {
					//ignore this exception and skip to the next line 
				}
				catch(NullPointerException ex) {
					//ignore
				}
			}
			
			
			//interactive mode: 
			
			Scanner userInput  = new Scanner (System.in ); 
			String userValue = "";
			String matching = "";
			String search = "";
			
			
			do {			
				System.out.println("Search the database by matching keywords to titles or actor names.\n" + 
								   "To search for matching titles, enter\n" + 
								   "title KEYWORKD\n" + 
								   "To search for matching actor names, enter\n" + 
								   "actor KEYWORD\n" + 
								   "To finish the program, enter\n" + 
									"quit\n" +
									"Enter your search query:");

				//read line and check first five characters and get substring and then trim it 
				userValue = userInput.nextLine().trim();
				if (userValue.equalsIgnoreCase("quit")) {
					userInput.close();
					return;
				}
				matching = userValue.substring(0,5);
				search = userValue.substring(6);
				
				//verifying the user did not choose quit
				if (!matching.equalsIgnoreCase("quit") && !search.equalsIgnoreCase("quit")) { 
					
					//verifying the user did not input an incorrect input
					if (search == null || !(search instanceof String)) {
						System.out.println("This is not a valid query. Try again."); 
					}
						
						//checking for matching titles
						if ( matching.equalsIgnoreCase("title") ) {

							try {
								if (list.getMatchingTitles(search) == null) {
									System.out.println("No matches found. Try again.");
								}
								else {
									
									//printing out matching movie titles
									Iterator<Movie> itrSearch = list.getMatchingTitles(search).iterator();
									while (itrSearch.hasNext()) {
										System.out.print (itrSearch.next().toString());
									}

								} 
						}
							catch (IllegalArgumentException ex ) {
								System.out.println("Error: This is not a valid movie specification."); 
								continue;
						}	
					}
					
					//checking for matching actors
					else if (matching.equalsIgnoreCase("actor")) {
						try {
							if (list.getMatchingActor(search) == null) {
								System.out.println("No matches found. Try again.");
							}
							else {
								
								//printing out matching actor names
								Iterator<Movie> itrSearch = list.getMatchingActor(search).iterator();
								while (itrSearch.hasNext()) {
									System.out.print (itrSearch.next().toString());
								}
							}
						
						}
						catch (IllegalArgumentException ex ) {
							System.out.println("Error: This is not a valid movie specification."); 
							continue;
						}
					}
					else System.out.println( "This is not a valid query. Try again.");
				}				
			} while (!matching.equalsIgnoreCase("quit") );     
			
			userInput.close();	
	}

	/**
	 * Splits the given line of a CSV file according to commas and double quotes
	 * (double quotes are used to surround multi-word entries so that they may contain commas)
	 * @author Joanna Klukowska
	 * @param textLine a line of text to be passed
	 * @return a LinkedList object containing all individual entries found on that line
	 */
	 public static ArrayList<String> splitCSVLine(String textLine){

	 if (textLine == null ) return null;

	 	ArrayList<String> entries = new ArrayList<String>();
	 	int lineLength = textLine.length();
	 	StringBuffer nextWord = new StringBuffer();
	 	char nextChar;
	 	boolean insideQuotes = false;
	 	boolean insideEntry= false;

	 // iterate over all characters in the textLine
	 for (int i = 0; i < lineLength; i++) {
		 nextChar = textLine.charAt(i);

		 // handle smart quotes as well as regular quotes
		 if (nextChar == '"' || nextChar == '\u201C' || nextChar == '\u201D') {

			 	// change insideQuotes flag when nextChar is a quote
			 	if (insideQuotes) {
			 		insideQuotes = false;
			 		insideEntry = false;
			 	}else {
			 		insideQuotes = true;
			 		insideEntry = true;
			 	}
		 } else if (Character.isWhitespace(nextChar)) {
			 if ( insideQuotes || insideEntry ) {
				 // add it to the current entry
				 nextWord.append( nextChar );
			 }else { // skip all spaces between entries
				 continue;
			 }
		 } else if ( nextChar == ',') {
			 if (insideQuotes){ // comma inside an entry
				 nextWord.append(nextChar);
			 } else { // end of entry found
				 insideEntry = false;
				 entries.add(nextWord.toString());
				 nextWord = new StringBuffer();
			 }
		 } else {
			 // add all other characters to the nextWord
			 nextWord.append(nextChar);
			 insideEntry = true;
		 }

	 }
	 	// add the last word ( assuming not empty )
	 	// trim the white space before adding to the list
	 	if (!nextWord.toString().equals("")) {
	 		entries.add(nextWord.toString().trim());
	 	}

	 	return entries;
	}
}



