
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.BookUtil;
import util.UserUtil;

/**
 * Servlet implementation class returnBooks
 */
@WebServlet("/returnBooks")
public class returnBooks extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public returnBooks()
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
		
		String keyUserID = request.getParameter("userID");
		String ISBN1 = request.getParameter("isbn1");
		String ISBN2 = request.getParameter("isbn2");
		String ISBN3 = request.getParameter("isbn3");
		String ISBN4 = request.getParameter("isbn4");
		String ISBN5 = request.getParameter("isbn5");
		   
		if (keyUserID == null && ISBN1 == null && ISBN2 == null && ISBN3 == null
				&& ISBN4 == null && ISBN5 == null)
		{
			RequestDispatcher rd = request.getRequestDispatcher("Book_Return.html");  
			rd.include(request,response);
			out.println("<script type=\"text/javascript\">");
			out.println("alert('No Parameters Entered, Please Enter Parameters!');");
			out.println("</script>");
		}
		else if (keyUserID == null)
		{
			RequestDispatcher rd = request.getRequestDispatcher("Book_Return.html");  
			rd.include(request,response);
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Missing User ID Detected, Please Make Sure the User ID is Entered!');");
			out.println("</script>");
		}
		else if (ISBN1 == null && ISBN2 == null && ISBN3 == null
				&& ISBN4 == null && ISBN5 == null)
		{
			RequestDispatcher rd = request.getRequestDispatcher("Book_Return.html");  
			rd.include(request,response);
			out.println("<script type=\"text/javascript\">");
			out.println("alert('No Books were Entered, Please Make Sure to Return a Book!');");
			out.println("</script>");
		}
		else
		{
			String Books [] = new String [5];
			Books[0] = ISBN1;
			Books[1] = ISBN2;
			Books[2] = ISBN3;
			Books[3] = ISBN4;
			Books[4] = ISBN5;
			Integer userID;
			if(!keyUserID.equals("")) {
			 userID = Integer.valueOf(keyUserID);
			}else {
				 userID = null;
			}
			   
			for(String book: Books)
			{
				if(!book.isEmpty())
				{
					UserUtil.returnUserBook(userID, book);
					BookUtil.updateCopies(book, 1);
				}
			}
			   
			RequestDispatcher rd = request.getRequestDispatcher("Book_Return.html");  
			rd.include(request,response);
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Return Operation was Successful!');");
			out.println("</script>");
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
