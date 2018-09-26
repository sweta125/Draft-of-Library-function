/**
 * BookNotFound Exception - type of exception thrown when book is currently not in library
 * @author Ray and Sweta
 *
 */
public class BookNotFoundException extends Exception{

	/**
	 * General Error message
	 */
	public BookNotFoundException() {
		super("Book is not in the library");
	}
	
	/**
	 * specific message
	 * @param error
	 */
	public BookNotFoundException(String error)
	{
		super("Book is not in the library: "+error);
	}

}
