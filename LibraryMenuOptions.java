import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * Shows menu options for a library
 * @author Student
 *
 */
public class LibraryMenuOptions{
	private static JPanel mainPanel = new JPanel();
	private static final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH);
	private static final DecimalFormat df = new DecimalFormat("#.##");
	private static Library library;

	/**
	 * Makes a menu given a library
	 * @param library
	 */
	public LibraryMenuOptions(Library library)
	{
		this.library = library;
	}
	
	/**
	 * add a book from administrator mode
	 */
	public void administratorAddBook() {
		try{
			String title = askQuestion("Enter title:");
			String author = askQuestion("Enter author:");
			String genreString = askQuestion("Enter genre number (1-Nonfiction 2-Mystery 3-Science Fiction 4-Fantasy 5-Thriller):");
			Genre genre = Genre.values()[Integer.parseInt(genreString)-1];
			String locationString = askQuestion("Enter shelf location (1-Children 2-Adult 3-Reference):");
			Location location = Location.values()[Integer.parseInt(locationString)-1];
			int copies = Integer.parseInt(askQuestion("Type in the number of copies: "));
			for (int i=0; i<=copies; i++)
			{library.includeBook(new Book(title, author, genre, location));}
			showMessage("Book added, returning to menu.");
		}
			catch (Exception e)
			{showMessage("Book was not added");}
	}
	
	/**
	 * gives the library's status from administrator mode
	 */
	public void administratorLibraryStatus(){
			showMessage(library.toString());
	}
	
	/**
	 * search the catalog for a book (given name, ID, or author) in administration mode
	 */
	public void administratorSearchBookCatalog(){
		try 
		{
			String input = askQuestion("Enter either the title or author of the book:");
			ArrayList<Book> list = library.findBook(input);
			
			String message = "Here are all the books currently checked into the library that match that search:\n";
			for (Book b: list) {
				if (b.isCheckedOut() == false)
				{message += b.toString() + "\n";
				 showMessage(message);
				}
			}
		}
		catch (BookNotFoundException e){
			showMessage(e.getMessage());
		}
	  }
	
	/**
	 * gives patron info from administration mode
	 */
	public void administratorPatronInfo(){
		ArrayList<Patron> list = library.getPatronList();
		for (Patron p: list)
		{showMessage("\n"+p.toString());}
		
		if (list.size() == 0)
			showMessage("There are currently no patrons, try signing up as one in patron mode.");
	}
	
	/**
	 * gives current status of specific patron
	 * @param p
	 */
	public void patronCurrentStatus(Patron p){
		showMessage(p.toString());
	}
	
	/**
	 * checks out book for patron
	 * @param p
	 */
	public void patronCheckOutBook(Patron p)
	{
		try 
		{
			String input = askQuestion("Enter either the title or author of the book:");
			ArrayList<Book> list = library.findBook(input);
			
			String message = "Here are all the books currently checked into the library that match that search:\n";
			for (Book b: list) {
				if (b.isCheckedOut() == false)
				message += b.toString() + "\n";
			}
			
			String ID = askQuestion(message + "\nType the exact ID of the book that you want");
			Book request = null;
			boolean found = false;
			for (Book b: list)
				{
					if (b.getIdentification().equalsIgnoreCase(ID))
						{
							request = b;
							Date dueDate = dateFormat.parse(askQuestion("Enter due date (mm/dd/yy):"));
							library.borrowBook(p, request, dueDate);
							showMessage("Book borrowed, returning to menu.");
							found = true;
						}
				}
			if (found == false)
				throw new BookNotFoundException(ID + " not found.");
		} 
		
		catch (BookNotFoundException e){
			showMessage(e.getMessage());
		}
		catch (ParseException e) {
			showMessage("Invalid date, returning to menu.");
		}
	  }
	
	/**
	 * return book (and tell fines) for patron
	 * @param p
	 */
	public void patronReturnBook(Patron p){
		String title = askQuestion("Enter title of book:");
		Book book = p.findBook(title);
		if(book == null) {
			showMessage("This patron does not have that book, returning to menu.");
			return;
		}
		try {
			Date date = dateFormat.parse(askQuestion("Enter current date (mm/dd/yy):"));
			if(library.returnBook(p, book, date)) {
				showMessage("Book returned late, the patron now owes $" + df.format(p.getFine()) + ". ");
			}
			else {
				showMessage("Book returned on time, returning to menu.");
			}
		} catch (ParseException e) {
			showMessage("Invalid date, returning to menu.");
		}
	}
	
	/**
	 * Gets the library
	 * @return Library
	 */
	public Library getLibrary()
	{
		return library;
	}
	
	/**
	 * Uses a dialog box to ask a question
	 * @param question The question
	 * @return The answer
	 */
	private static String askQuestion(String question) {
		return JOptionPane.showInputDialog(question);
	}
	
	/**
	 * Uses a dialog box to show a message
	 * @param message
	 */
	private static void showMessage(String message) {
		JOptionPane.showMessageDialog(mainPanel, message);
	}

}
