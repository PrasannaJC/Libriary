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

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * Servlet implementation class Overdue
 */
@WebServlet("/Overdue")
public class Overdue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Overdue() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

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

		u = UserUtil.overdue();

	    display(u, out);
	    out.println("</ul>");
	    out.println("<a href=/" + Info.projectName + "/" + Info.homeWebName + ">Go Back to Home Page</a> <br>");
	    //out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Data</a> <br>");
	    out.println("</body></html>");
		
	}
	
	public void overdue(java.sql.Date date)
	{
		java.time.LocalDate checkout = date.toLocalDate();
		java.time.LocalDate today = LocalDate.now();
		
		int diff = checkout.compareTo(today);
		if (diff > 14)
		{
			System.out.println("You have overdue books!!! Go return the existing ones before you checkout new ones!!!");
		}
	}
	
	void display(List<User> u, PrintWriter out) 
	{
	      for (User x : u) {
	         System.out.println("[DBG] " + x.getUserID() + ", " //
	        		 + x.getUsername() + ", " //
	        		 + x.getBooks() + ", " //
	        		 + x.getCheckout() + ", " //
	        		 + x.getDue() + ", " //
	        		 + x.getOverdue()); 

	         out.println("<li>" + x.getUserID() + ", " //
	        		 + x.getUsername() + ", " //
	        		 + x.getBooks() + ", " //
	        		 + x.getCheckout() + ", " //
	        		 + x.getDue() + ", " //
	        		 + x.getOverdue() + "</li>");
	      }
	}
	
	
	public void process()
	{
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
