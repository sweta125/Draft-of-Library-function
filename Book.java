import java.util.Date;

/**
 * A book
 *
 * @author Ray Sun, Sweta Kotha
 */
public class Book {
	
	private String title;
	private String author;
	private String identification;
	private Genre genre;
	private Location location;
	private Date dueDate;
	
	/**
	 * Constructor
	 * @param t The title
	 * @param a The author
	 * @param g The genre
	 * @param l The location
	 */
	public Book(String t, String a, Genre g, Location l) {
		title = t;
		author = a;
		genre = g;
		location = l;
		dueDate = null;
		identification = "";
	}
	
	/**
	 * Gets the identification
	 * @return the identification
	 */
	public String getIdentification()
	{
		return identification;
	}
	/**
	 * Gets the title
	 * @return The title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Gets the author
	 * @return The author
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * Gets the genre
	 * @return The genre
	 */
	public Genre getGenre() {
		return genre;
	}
	
	/**
	 * Gets the location
	 * @return The location
	 */
	public Location getLocation() {
		return location;
	}
	
	/**
	 * Checks whether the book is checked out
	 * @return If it is checked out
	 */
	public boolean isCheckedOut() {
		return dueDate != null;
	}
	
	/**
	 * Gets the due date
	 * @return The due date
	 */
	public Date getDueDate() {
		return dueDate;
	}
	
	/**
	 * Creates a unique identification
	 */
	public void setIdentification(String iD)
	{
		identification = iD;
	}
	
	/**
	 * Sets the due date
	 * @param d The due date
	 */
	public void setDueDate(Date d) {
		dueDate = d;
	}
	
	/**
	 * Returns the book to the library
	 */
	public void returnBook() {
		dueDate = null;
	}
	
	@Override
	/**
	 * Equality check
	 */
	public boolean equals(Object o) {
		if(o instanceof Book) {
			return ((Book) o).getAuthor().equals(author) && ((Book) o).getTitle().equals(title);
		}
		return false;
	}
	
	@Override
	/**
	 * String representation
	 */
	public String toString() {
		String output =  title + " by " + author + " (" + genre.toString() + ") in the " + location.toString() + " (Book ID: "+ identification + ") ";
		if(isCheckedOut()) {
			output += ", due on " + dueDate.toString();
		}
		return output;
	}
}
