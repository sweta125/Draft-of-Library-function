import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * A library
 *
 * @author Ray Sun, Sweta Kotha
 */
public class Library {
	private ArrayList<Patron> patrons = new ArrayList<>();
	private ArrayList<Book> books = new ArrayList<>();
	private double money;
	
	/**
	 * Constructor
	 */
	public Library() {
		money = 0;
	}
	
	/**
	 * Adds the funds
	 * @param m The amount to add
	 */
	public void addMoney(double m) {
		money += m;
	}
	
	/**
	 * Gets the amount of funds
	 * @return The amount
	 */
	public double getMoney() {
		return money;
	}
	
	public ArrayList<Patron> getPatronList(){
		return patrons;
	}
	
	/**
	 * Pays the fines of a patron
	 * @param p The patron
	 * @return The amount paid
	 */
	public double payFine(Patron p) {
		double paid = p.payFine();
		addMoney(paid);
		return paid;
	}
	
	/**
	 * Adds a book to the catalog and sets a books Username number
	 * @param b The book
	 */
	public void includeBook(Book b) {
		books.add(b);
		b.setIdentification(createBookIdentification(b));
	}

	/**
	 * Borrows a book
	 * @param p The patron
	 * @param b The book
	 * @param d The due date
	 * @throws BookNotFoundException 
	 */
	public void borrowBook(Patron p, Book b, Date d) throws BookNotFoundException {
		if (b.isCheckedOut() == false)
			p.borrowBook(b, d);
		else
			throw new BookNotFoundException("checked out");
	}
	
	/**
	 * Returns a book to the library
	 * @param p The patron
	 * @param b The book
	 * @param d The due date
	 * @return If the book is overdue
	 */
	public boolean returnBook(Patron p, Book b, Date d) {
		return p.returnBook(b, d);
	}
	
	/**
	 * Adds a patron
	 * @param p The patron
	 */
	public void addPatron(Patron p) {
		patrons.add(p);
	}
	
	/**
	 * Finds a patron
	 * @param ID
	 * @return The patron or null if not found
	 */
	public Patron findPatron(String username) {
		for(Patron p : patrons) {
			if(p.getUsername().equalsIgnoreCase(username)) {
				return p;
			}
		}
		return null;
	}
	
	/**
	 * Finds if library contains specific book with same title, author, or ID
	 * @param title The title
	 * @return The book or null if not found
	 * @throws BookNotFoundException 
	 */
	public ArrayList<Book> findBook(String s) throws BookNotFoundException {
		ArrayList<Book> list = new ArrayList<>();
		for(Book b : books)
		{
			if(b.getTitle().equalsIgnoreCase(s))
				list.add(b);
			else if(b.getIdentification().equals(s))
				list.add(b);
			else if(b.getAuthor().equalsIgnoreCase(s))
				list.add(b);
		}
		if (list.size() == 0)
			throw new BookNotFoundException("no matches in author, title, or ID");
		return list;
	}
	
	/**
	 * checks if specific book in library is checked out
	 * @param ID
	 * @return if book is checked out
	 */
	public boolean canCheckOut(String ID)
	{
		for(Book b : books) {
			if(b.getIdentification().equalsIgnoreCase(ID) && b.isCheckedOut() == false) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * creates ID for book
	 * @param b
	 * @return created ID
	 */
	/**
	 * creates ID for book
	 * @param b
	 * @return created ID
	 */
	public String createBookIdentification(Book b)
	{
		String identification = "";
		
		Location l = b.getLocation();
		if (l == Location.CHILDREN)
			identification += "100.";
		else if (l == Location.ADULT)
			identification += "200.";
		else if (l == Location.REFERENCE)
			identification += "300.";
		
		Genre g = b.getGenre();
		if (g == Genre.NONFICTION)
			identification += "N.";
		else if (g == Genre.MYSTERY)
			identification += "M.";
		else if (g == Genre.SCIENCE_FICTION)
			identification += "S.";
		else if (g == Genre.THRILLER)
			identification += "T.";
		else if (g == Genre.FANTASY)
			identification += "F.";
		
		String[] s = b.getAuthor().split(" ");
		String firstInitial = s[0].substring(0, 1).toUpperCase();
		String lastInitial = s[s.length-1].substring(0,  1).toUpperCase();
		identification +=  firstInitial+lastInitial+".";
		
		int num = 1;
		identification += num;
		
		while (containsIdentification(identification) == true)
		{
			identification = identification.substring(0, 9) + num++;
		}
		
		return identification;
	}

	/**
	 * checks if ID is taken
	 * @param ID
	 * @return if ID is taken
	 */
	public boolean containsIdentification(String ID)
	{
		for (Book b: books)
		{
			if (b.getIdentification().equals(ID))
				{
					return true;
				}
		}
		return false;
	}
	
	/**
	 * checks if the Patron's username is taken by a patron
	 * @param p1
	 * @return true if patron username is taken
	 */
	public boolean containsUsername(Patron p1)
	{
		for (Patron p: patrons)
		{
			if (p.getUsername().equals(p1))
				{
					return true;
				}
		}
		return false;
	}
	
	@Override
	/**
	 * String representation
	 */
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.##");
		String output = "Funds: $" + df.format(money);
		output += "\nBooks:";
		for(Book b : books) {
			output += "\n" + b.toString();
		}
		output += "\n\nPatrons:";
		for(Patron p : patrons) {
			output += "\n" + p.toString();
		}
		return output;
	}
}
