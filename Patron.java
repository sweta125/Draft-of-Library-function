import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A patron
 *
 * @author Ray Sun, Sweta Kotha
 */
public class Patron {
	public static final double FINE_AMOUNT = 1.25;
	
	private ArrayList<Book> books = new ArrayList<Book>();
	private String name;
	private double fines;
	private char[] password;
	private String username;
	
	/**
	 * Constructor
	 * @param n The name
	 */
	public Patron(String n, String u, char[] p) {
		name = n;
		fines = 0;
		username = u;
		password = p;
	}
	
	/**
	 * Adds a fine
	 * @param m The fine amount
	 */
	public void addFine(double m) {
		fines += m;
	}
	
	/**
	 * Returns the amount owed
	 * @return The amount owed
	 */
	public double getFine() {
		return fines;
	}
	
	/**
	 * Pays all fines
	 * @return The amount paid
	 */
	public double payFine() {
		double amount = fines;
		fines = 0;
		return amount;
	}
	
	/**
	 * Gets the name
	 * @return The name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * gets the password
	 * @return
	 */
	public char[] getPassword()
	{
		return password;
	}
	
	/**
	 * gets the String representation of password
	 * @return
	 */
	public String getStringPassword()
	{
		return String.valueOf(password);
	}
	
	/**
	 * gets the username
	 * @return
	 */
	public String getUsername()
	{
		return username;
	}
	
	/**
	 * sets the password
	 * @param p
	 */
	public void setPassword(char[] p)
	{
		password = p;
	}
	
	/**
	 * sets teh username
	 * @param i
	 */
	public void setUsername(String i)
	{
		username = i;
	}
	
	/**
	 * Borrows a book
	 * @param b The book
	 * @param d The due date
	 */
	public void borrowBook(Book b, Date d) {
		books.add(b);
		b.setDueDate(d);
	}
	
	/**
	 * Returns a book to the library
	 * @param b The book
	 * @param d The due date
	 * @return If the book is overdue
	 */
	public boolean returnBook(Book b, Date d) {
		boolean overdue = d.after(b.getDueDate());
		books.remove(b);
		b.returnBook();
		if(overdue) {
			addFine(FINE_AMOUNT);
			return true;
		}
		return false;
	}

	/**
	 * Finds a book
	 * @param title The title
	 * @return The book or null if not found
	 */
	public Book findBook(String title) {
		for(Book b : books) {
			if(b.getTitle().equalsIgnoreCase(title)) {
				return b;
			}
		}
		return null;
	}
	
	@Override
	/**
	 * Equality check
	 */
	public boolean equals(Object o) {
		if(o instanceof Patron) {
			return ((Patron) o).getName().equals(name);
		}
		return false;
	}
	
	@Override
	/**
	 * String representation
	 */
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.##");
		String output = name + " (username:  " + username + ", password: " + getStringPassword() + ") owes $" + df.format(fines) + " and has the following books: ";
		for(Book b : books) {
			output += "\n" + b.toString();
		}
		return output;
	}
}
