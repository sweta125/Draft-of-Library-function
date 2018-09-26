import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Models a typical Library GUI with various Components
 *
 * @author Ray Sun, Sweta Kotha
 */
public class LibraryGUI{
	private static JFrame frame = new JFrame();
	private static JPanel contentPane = new JPanel();
	private static JPanel mainPanel = new JPanel();
	private static Color color=new Color(200, 102, 204);
	private static Font font=new Font("Tahoma", 1, 11);

	 private static JLabel name=null;
	 private static JLabel username=null;
     private static JLabel password=null;
	 private static JTextField usernameField=null;
	 private static JPasswordField passwordField=null;
	 private static JTextField nameField=null;
	 
	 private static LibraryMenuOptions libMenu;
	
	/**
	 * Sets the basic JFrame structure
	 * @param libMenu
	 */
	public LibraryGUI(LibraryMenuOptions libMenu)  {
			
		frame.setContentPane(contentPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		this.libMenu = libMenu;

	}
		
	/**
	 * Creates JPanel contentPane
	 */
	public static void createContentPane()
	{  
        contentPane.setBorder(BorderFactory.createTitledBorder("IRC Book Catalog"));
        contentPane.setBackground(color);
        contentPane.setLayout(new GridLayout(2, 2, 2, 2));
	}
	
	/**
	 * Intro screen for patron with login/sign up buttons
	 */
	public static void patronIntroScreen()
	{
		removeContentPane();
		createContentPane();
		JButton login = new JButton("Login");
		login.addActionListener(
				ae -> {loginScreen();});
		
		JButton signUp = new JButton("Sign Up");
		signUp.addActionListener(
			ae -> {signUpScreen();});
		
		contentPane.add(login);
		contentPane.add(signUp);
		
		frame.setSize(200, 130);
		frame.setTitle("IMSA IRC");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/**
	 * removes the JPanel contentPane
	 */
	public static void removeContentPane()
	{
		contentPane.removeAll();
	}
	
	/**
	 * login screen for patron
	 */
	public static void loginScreen()
	{
		removeContentPane();
		createContentPane();
		
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		
		username=new JLabel("Username");
        username.setLabelFor(usernameField);
        username.setFont(font);
        username.setForeground(Color.white);
        password=new JLabel("Password");
        password.setLabelFor(passwordField);
        password.setFont(font);
        password.setForeground(Color.white);
        usernameField=new JTextField(10);
        usernameField.setBorder(new LineBorder(null, 0, false));
        passwordField=new JPasswordField(10);
        passwordField.setBorder(new LineBorder(null, 0, false));

        JButton done = new JButton("Done");
        		
        contentPane.add(username);
        contentPane.add(usernameField);
        contentPane.add(password);
        contentPane.add(passwordField);
        contentPane.add(done);
        
        frame.setTitle("Login Information");
        frame.setSize(500, 150);
    	frame.setVisible(true);
    	
    	done.addActionListener(
				ae -> {
					if (doesPatronExist() != null)
					{
						Patron p = doesPatronExist();
			    		patronMenu(p);
					}
					
					else
					{
						showMessage("Patron Information is incorrect or patron does not exist (try sign up)");
			    		patronIntroScreen();
					}
				});
	}
	
	/**
	 * gets the login password
	 * @return
	 */
	public static char[] getLoginPassword()
	{
		return passwordField.getPassword();
	}
	
	/**
	 * gets the username from the JTextfield
	 * @return username
	 */
	public static String getUsername()
	{
		return usernameField.getText();
	}
	
	/**
	 * gets the name from the JTextfield
	 * @return name
	 */
	public static String getName()
	{
		return nameField.getText();
	}
	
	/**
	 * check if a patron exists in the library used for menu/GUI
	 * @return patron 
	 */
	public static Patron doesPatronExist()
	{
		if (libMenu.getLibrary().findPatron(getUsername()) == null)
		{
			return null;
		}
		
		else
		{
			Patron p = libMenu.getLibrary().findPatron(getUsername());
			//if (p.getPassword().equals(getLoginPassword()))//don't need bc unique usernames
			{return p;}
		}
	}
	
	/**
	 * sign up screen for patrons
	 */
	public static void signUpScreen()
	{
		removeContentPane();
		createContentPane();
		
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		
		username=new JLabel("Username");
        username.setLabelFor(usernameField);
        username.setFont(font);
        username.setForeground(Color.white);
        password=new JLabel("Password");
        password.setLabelFor(passwordField);
        password.setFont(font);
        password.setForeground(Color.white);
        name=new JLabel("Name");
        name.setLabelFor(nameField);
        name.setFont(font);
        name.setForeground(Color.white);
        usernameField=new JTextField(10);
        usernameField.setBorder(new LineBorder(null, 0, false));
        passwordField=new JPasswordField(10);
        passwordField.setBorder(new LineBorder(null, 0, false));
        nameField=new JTextField(10);
        nameField.setBorder(new LineBorder(null, 0, false));
        
        JButton done = new JButton("Done");
        done.addActionListener(
				ae -> {
					if (doesPatronExist() != null)
					{
						showMessage("Username is already taken, please sign up again with a unique username.");
						signUpScreen();
					}
					
					else
					{
						Patron p = new Patron(getName(), getUsername(), getLoginPassword());
						libMenu.getLibrary().addPatron(p);
						patronMenu(p);
					}
				});

        contentPane.add(name);
        contentPane.add(nameField);
        contentPane.add(username);
        contentPane.add(usernameField);
        contentPane.add(password);
        contentPane.add(passwordField);
        contentPane.add(done);
        
        frame.setTitle("Sign Up Information");
        frame.setSize(500, 250);
    	frame.setVisible(true);		
	}
	/**
	 * administration menu for administrators
	 */
	public static void administratorMenu()
	{
		removeContentPane();
		createContentPane();
		
		JButton catalog = new JButton("Search Book Catalog");
		catalog.addActionListener(
				ae -> {
					libMenu.administratorSearchBookCatalog();
				});
		
		JButton patronInfo = new JButton("Patron Information");
		patronInfo.addActionListener(
				ae -> {
					libMenu.administratorPatronInfo();
				});
		
		JButton addBooks = new JButton("Add Books to the Library");
		addBooks.addActionListener(
				ae -> {
					libMenu.administratorAddBook();
				});
		
		JButton libStatus = new JButton("Library Status");
		libStatus.addActionListener(
				ae -> {
					libMenu.administratorLibraryStatus();
				});
		
		JButton b6 = new JButton("Exit");
		b6.addActionListener(
			ae -> {System.exit(0);});
		
		contentPane.add(catalog);
		contentPane.add(patronInfo);
		contentPane.add(addBooks);
		contentPane.add(libStatus);
		contentPane.add(b6);
		
		frame.setSize(700, 600);
		frame.setTitle("Administrator Menu");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/**
	 * patron menu for patrons
	 * @param p
	 */
	public static void patronMenu(Patron p)
	{
		removeContentPane();
		createContentPane();
		
		JButton status = new JButton("Check Your Current Status");
		status.addActionListener(
				ae -> {
					libMenu.patronCurrentStatus(p);
				});
		
		JButton checkOut = new JButton("Check out a Book");
		checkOut.addActionListener(
				ae -> {
					libMenu.patronCheckOutBook(p);
				});
		
		JButton returnBook = new JButton("Return a Book");
		returnBook.addActionListener(
				ae -> {
					libMenu.patronReturnBook(p);
				});
		
		JButton b6 = new JButton("Exit");
		b6.addActionListener(
			ae -> {System.exit(0);});
		
		contentPane.add(status);
		contentPane.add(checkOut);
		contentPane.add(returnBook);
		contentPane.add(b6);
		
		frame.setSize(400, 400);
		frame.setTitle(p.getName()+" - Patron Menu");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/**
	 * Uses dialog box to show a message
	 * @param message
	 */
	private static void showMessage(String message) {
		JOptionPane.showMessageDialog(mainPanel, message);
	}
}
