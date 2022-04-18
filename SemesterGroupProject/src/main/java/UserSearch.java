import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import datamodel.Book;
//import datamodel.Book;
import datamodel.User;
//import util.BookUtil;
import util.Info;
//import util.BookUtil;
import util.UserUtil;

/**
 * Servlet implementation class Users
 */
@WebServlet("/UserSearch")
public class UserSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	
	public UserSearch() {
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
		try { 
			k = Integer.valueOf(request.getParameter("userID")); //label it as whatever is designated in the HTML
		}
		catch (NumberFormatException e) {
			k = null;
		}
		/*
		System.out.println("=> " + k);
		System.out.println("=> " + keyID);
		System.out.println("=> " + keyName);
		System.out.println("=> " + keyISBN);
		*/
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    String title = "Database Result";
	    String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
	    out.println(docType + //
	        "<html>\n" + //
	        "<head><title>" + title + "</title></head>\n" + //
	        "<body bgcolor=\"#f0f0f0\">\n" + //
	        "<h1 align=\"center\">" + title + "</h1>\n");
	    out.println("<ul>");
	    
	    
		List<User> u = null;
		List<User> v = null;
		//int m = Integer.parseInt(keyID);
		
		u = UserUtil.listUsers();		
		v = UserUtil.listUsers(k, keyName, keyISBN);
		
		display(v, out); // you can change v for u if you wish to display all users.
		
		/*if (userExist(k))
		{
			System.out.println("=> " + k);
			userCheckout(bookList);
		}*/
		if (userExist(keyID))
		{
			System.out.println("=> " + keyID);
			//userCheckout(bookList);
		}
		/*try {
			
			
		}
		catch (NullPointerException e){
			bookList = null;
		}
		*/
		out.println("<a href=/" + Info.projectName + "/" + Info.searchUserName + ">Search for Another User</a> <br>");
		
		
	}
	
	public Boolean userExist(String keyID)
	{
		//UserUtil.checkUser(keyID);
		//System.out.println("Number 1");
		Integer k = Integer.getInteger(keyID);
		Boolean out = false;
		//int m = 0;
		/*if (k.equals(null))
		{
			
		}
		else
		{
			m = Integer.parseInt(keyID);
		}*/
		
		System.out.println(k + " ");
		if (keyID == null || keyID.equals(""))
		{
			System.out.println("<li> The following user " + keyID + " does not exists, so go create a new user");
			//System.out.println("Number 3");
			out = false;
		}
		else if (UserUtil.checkUser(keyID))
		{

			System.out.println("<li> The following user " + keyID + " exists and can check out books!!!");
			//System.out.println("Number 2");
			out = UserUtil.checkUser(keyID);
		}
		return out;
	}
	
	
	void display(List<User> u, PrintWriter out) 
	{
	      for (User x : u) {
	    	  
	         System.out.println("[DBG] " + x.getUsername() + ", " //
	        		 + x.getUserID() + ", " //
	        		 + x.getBooks() + ", " //
	        		 + x.getCheckout() + ", " //
	        		 + x.getDue() + ", " //
	        		 + x.getOverdue());

	         out.println("<li> " + x.getUsername() + ", " //
	        		 + x.getUserID() + ", " //
	        		 + x.getBooks() + ", " //
	        		 + x.getCheckout() + ", " //
	        		 + x.getDue() + ", " //
	        		 + x.getOverdue() + "</li>");

	      }
	   }
	
	
	
	/**
	 * This is the user checkout starting point, where I create a list of the books that are available at the library and still have copies on shelves.
	 * bks is the list of books that meet the above condition.
	 * based on that, 
	 * @param bookList
	 */
	/*
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
*/

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
