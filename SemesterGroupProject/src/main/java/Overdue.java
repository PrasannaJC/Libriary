import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.User;
import util.UserUtil;
import util.Info;

import java.util.List;

@WebServlet("/Overdue")
public class Overdue extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    public Overdue()
    {
        super();       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String submit = request.getParameter("check");	

		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    String title = "Overdue Results";
	    String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
	    out.println(docType + //
	        "<html>\n" + //
	        "<head><title>" + title + "</title></head>\n" + //
	        "<body bgcolor=\"#b5a965\">\n" + //
	        "<h1 align=\"center\">" + title + "</h1>\n");
	    out.println("<ul>");
	
	    if (submit.contains("Search Overdue"))
		{
		    List<User> u = null;

			u = UserUtil.overdue();
			System.out.println("size of list => "+u.size());
			
			if (u.size() == 0)
			{
				out.println("There are no users with overdue books!!!");
			}
			else
			{
				out.println("The following users have overdue books!!!");
			    display(u, out);
			}
		}
	    else
		{
			out.println("Unknown Error...");
		}
	    
	    out.println("</ul>");
	    out.println("<a href=/" + Info.projectName + "/" + Info.homeWebName + ">Go Back to Home Page</a> <br>");
	    out.println("</body></html>");	
	}
	
	void display(List<User> u, PrintWriter out) 
	{
		for (User x : u)
		{
			System.out.println("[DBG] " + x.getUserID() + ", " //
					+ x.getUsername() + ", " //
					+ x.getBooks() + ", " //
					+ x.getCheckout() + ", " //
					+ x.getDue() + "</li>"); 

			out.println("<li>" + x.getUserID() + ", " //
					+ x.getUsername() + ", " //
					+ x.getBooks() + ", " //
					+ x.getCheckout() + ", " //
					+ x.getDue() + "</li>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}