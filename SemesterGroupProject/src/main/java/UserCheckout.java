
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.*;

/**
 * Servlet implementation class UserCheckout
 */
@WebServlet("/UserCheckout")
public class UserCheckout extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserCheckout()
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();
		String keyNewUser = request.getParameter("userName");
		String keyUserID = request.getParameter("userID");
		String ISBN1 = request.getParameter("isbn1");
		String ISBN2 = request.getParameter("isbn2");
		String ISBN3 = request.getParameter("isbn3");
		String ISBN4 = request.getParameter("isbn4");
		String ISBN5 = request.getParameter("isbn5");
		Integer userID = null;
		
		if(keyUserID != null )
		{
			userID = Integer.valueOf(keyUserID);
		}
		
		boolean updated = false;
		String keyBooks = "";
		String Books [] = new String [5];
		Books[0] = ISBN1;
		Books[1] = ISBN2;
		Books[2] = ISBN3;
        Books[3] = ISBN4;
        Books[4] = ISBN5;
        
        for (String book : Books)
        {
        	if (book.isEmpty())
        	{
        		book = "";
        	} 
        	else if (!book.equals(null) && keyBooks.equals(""))
        	{
        		keyBooks =  book;
        	}
        	else if (!book.equals(null) )
        	{
        		keyBooks = keyBooks + ", " + book;
        	}
        }
        
        boolean avaibleCopies = true;
        String arr1[] = keyBooks.split(", ");
        
        for (String book : arr1)
        {
        	if (avaibleCopies)
        	{
        		avaibleCopies =  BookUtil.checkCopies(book);
        	}
        	else
        	{
        		break;
        	}
        }
        
        if(keyNewUser != null)
        {
        	if(avaibleCopies)
        	{
        		updated = UserUtil.createUser(keyNewUser, keyBooks);
        		
        		for (String book : arr1)
        		{ 
        			BookUtil.updateCopies(book, -1);
        		}
        	}
        	
        	if(!updated)
        	{
        		RequestDispatcher rd=request.getRequestDispatcher("New_User_Checkout.html");  
        		rd.include(request,response);
        		out.println("<script type=\"text/javascript\">");
        		out.println("alert('Unable to Update Request,"
        				+ "Please Verify that the User Properties are Correct"
        				+ "and that there are Available Copies of the Desired Book(s)!');");
        		out.println("</script>");
        	}
        	else
        	{  
        		RequestDispatcher rd=request.getRequestDispatcher("New_User_Checkout.html");  
        		rd.include(request,response);
        		out.println("<script type=\"text/javascript\">");
        		out.println("alert('Request has been Processed, User Checkout was Successful!');");
        		out.println("</script>");     
        	}
        }
        else if(keyUserID != null)
        {
        	if(avaibleCopies)
        	{
        		updated = UserUtil.updateUser(userID, keyBooks);
        		
        		for (String book : arr1)
        		{ 
        			BookUtil.updateCopies(book, -1);
        		}
        	}
        	
        	if(!updated)
        	{
        		RequestDispatcher rd=request.getRequestDispatcher("User_Checkout.html");  
        		rd.include(request,response);
        		out.println("<script type=\"text/javascript\">");
        		out.println("alert('Unable to Update Request,"
        				+ "Please Verify that the User Properties are Correct"
        				+ "and that there are Available Copies of the Desired Book(s)!');");
        		out.println("</script>");
        	}
        	else
        	{  
        		RequestDispatcher rd=request.getRequestDispatcher("User_Checkout.html");  
        		rd.include(request,response);
        		out.println("<script type=\"text/javascript\">");
        		out.println("alert('Request has been Processed, User Checkout was Successful!');");
        		out.println("</script>");     
        	}
        }   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
