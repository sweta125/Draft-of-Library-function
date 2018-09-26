import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Driver class to test IMSA IRC
 * @author Ray, Sweta
 *
 */
public class Driver {
	private static JPanel mainPanel = new JPanel();
	private static Library library = new Library();
	private static LibraryMenuOptions libMenu = new LibraryMenuOptions(library);
	private static LibraryGUI IMSAIRC = new LibraryGUI(libMenu);

	/**
	 * main method for testing
	 * @param args
	 * @throws BookNotFoundException
	 */
	public static void main(String[] args) throws BookNotFoundException{
		char [] pass1= {'a', 'b', 'c'};
		library.addPatron(new Patron("Sweta Kotha", "skotha", pass1));
		char [] pass2= {'c', 'a', 't'};
		library.addPatron(new Patron("Omar Aldawud", "oaldy", pass2));
		char [] pass3= {'d', 'o', 'g'};
		library.addPatron(new Patron("John Smith", "jsmith", pass3));
		
		Genre g1 = Genre.valueOf("THRILLER");
		Location l1 = Location.valueOf("ADULT");
		Book B = new Book("Sherlock", "Sherlock", g1, l1);
		library.includeBook(new Book("Sherlock", "Sherlock", g1, l1));
		
		Genre g2 = Genre.valueOf("MYSTERY".toUpperCase());
		Location l2 = Location.valueOf("CHILDREN".toUpperCase());
		library.includeBook(new Book("Nancy Drew", "Apply Lockett", g2, l2));
		
		String response = askQuestion("Choose whether you would like to access the patron or administration mode: Type P or A, respectively.");
		if (response.equalsIgnoreCase("A"))
		{
			IMSAIRC.administratorMenu();
		}
				
		if (response.equalsIgnoreCase("P"))
		{
			showMessage("If you are a returning patron, LOGIN, or if not SIGN UP for a new account.");
			IMSAIRC.patronIntroScreen();
		}
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
