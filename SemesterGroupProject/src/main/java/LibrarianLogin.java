
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.LibrarianLoginUtil;

/**
* Servlet implementation class LibrarianLogin
*/
@WebServlet("/LibrarianLogin")
public class LibrarianLogin extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public LibrarianLogin()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		String keyBranch = request.getParameter("branchName");
		String keyPin = request.getParameter("pinCode");
		boolean librarian = false;
		librarian = LibrarianLoginUtil.LibrarianLogin(keyBranch, keyPin);
		
		if (librarian)
		{
			RequestDispatcher rd = request.getRequestDispatcher("Home.html");  
				rd.forward(request,response);  
		}
		else
		{
			RequestDispatcher rd = request.getRequestDispatcher("LoginPage.html");  
			rd.include(request,response);
			out.println("<script type=\"text/javascript\">");
			out.println("alert('The Branch Name or Pin Code is Invalid');");
			out.println("</script>");
		}
		
		out.close();  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
