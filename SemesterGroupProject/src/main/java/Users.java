import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Book;
import datamodel.User;
import util.BookUtil;
import util.UserUtil;

/**
 * Servlet implementation class Users
 */
@WebServlet("/Users")
public class Users extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	
	public Users() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String keyID = request.getParameter("userID"); //label it as whatever is designated in the HTML
		String keyName = request.getParameter("userName"); //label it as whatever is designated in the HTML
		String keyISBN = request.getParameter("bookList"); //label it as whatever is designated in the HTML
		
		String[] bookList = keyISBN.split(", ");
		/*try {
			//bookList.length() <= 5;
			int k = bookList.length;
		}
		catch (IndexOutOfBoundsException e){
			
		}*/
		Integer k = Integer.getInteger(keyID);
		System.out.println("=> " + k);
		if (userExist(k))
		{
			userCheckout(bookList);
		}
		
		/*try {
			
			
		}
		catch (NullPointerException e){
			bookList = null;
		}
		*/
		
		
		
	}
	
	public Boolean userExist(Integer keyID)
	{
		//UserUtil.checkUser(keyID);
		
		
		if (UserUtil.checkUser(keyID))
		{
			System.out.println("<li> The following user " + keyID + " exists and can check out books!!!");
			return UserUtil.checkUser(keyID);
		}
		else
		{
			System.out.println("<li> The following user bbb " + keyID + " does not exists, so go create a new user");
			return false;
		}
	}
	
	/**
	 * This is the user checkout starting point, where I create a list of the books that are available at the library and still have copies on shelves.
	 * bks is the list of books that meet the above condition.
	 * based on that, 
	 * @param bookList
	 */
	public void userCheckout(String[] bookList)
	{
		List<Book> bkz = null;
		List<Book> bks = null;
		for (int i = 0; i < bookList.length; i++)
		{
			for (int j = 1; j < 6; j++)
			{
				bkz = BookUtil.listBooks(null, null, bookList[i], "Please", j, 10000);
				bks.addAll(bkz);
			}		
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
