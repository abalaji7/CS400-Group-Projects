// --== CS400 File Header Information ==--
// Name: Arun Balaji
// Email: abalaji7@wisc.edi
// Team: nf
// TA: Daniel
// Lecturer: Gary
// Notes to Grader: <optional extra notes>


/*
 * This is the Movie object which stores the title, director, runtime, review, and whether or not the user watched the movie
 * It also allows for the review and watched fields to be updated
 */
public class Movie implements Comparable<Movie>{
	
	
	private String title;
	private double rating;
	private int year;
	private double runtime;
	private boolean watched;
	
	/*
	 * Constructor to initialize member fields
	 */
	public Movie(String title, double rating, int year, double runtime, boolean watched)
	{
		this.title = title;
		this.rating = rating;
		this.year = year;
		
		this.runtime = runtime;
		this.watched = watched;
		
	}

	/*
	 * return title
	 */
	public String getTitle() {
		return title;
	}

	/*
	 * set title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/*
	 * return rating
	 */
	public double getRating() {
		return rating;
	}

	/*
	 * set rating
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}

	/*
	 * return year
	 */
	public int getYear() {
		return year;
	}
	
	/*
	 * sets the year
	 */
	public void setYear(int year) {
		this.year = year;
	}
	

	/*
	 * return runtime
	 */
	public double getRuntime() {
		return runtime;
	}

	/*
	 * sets the runtime
	 */
	public void setRuntime(double runtime) {
		this.runtime = runtime;
	}

	/*
	 * returns whether or not the movie has been watched
	 */
	public boolean isWatched() {
		return watched;
	}

	/*
	 * updates watched status of the movie
	 */
	public void setWatched(boolean watched) {
		this.watched = watched;
	}
	
	/*
	 * String representation of all the information
	 */
	public String toString() {
		String info = "Title: " + title;
		info += "\nRating: " + rating;
		info += "\nYear: " + year;
		info += "\nRuntime: " + runtime;
		info += "\nIsWatched: " + watched;
		return info;
	}
	
	/*
	 * compares movies with one another
	 * @param Movie object to compare with
	 */
	@Override
	public int compareTo(Movie comp) {
		return Double.valueOf(rating).compareTo(Double.valueOf(comp.rating));
	}
}
