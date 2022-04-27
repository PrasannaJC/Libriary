import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.LibrarianTable;
import util.LibrarianLoginUtil;

@WebServlet("/HibernateLibrarianDB")
public class HibernateLibrarianDB extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public HibernateLibrarianDB()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");

	    // #1
	    LibrarianLoginUtil.createlibrarian( "Branch1", "12345" );
	    LibrarianLoginUtil.createlibrarian( "Branch2", "34562");
	    LibrarianLoginUtil.createlibrarian( "Branch1", "97865" );
	    LibrarianLoginUtil.createlibrarian( "Branch3", "45676" );
	      
	    // #2
	    retrieveDisplayData(response.getWriter());
	}

	void retrieveDisplayData(PrintWriter out)
	{
		String title = "Database Result";
	    String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
	    		"transitional//en\">\n"; //
	    out.println(docType + //
	    		"<html>\n" + //
	            "<head><title>" + title + "</title></head>\n" + //
	            "<body bgcolor=\"#f0f0f0\">\n" + //
	            "<h1 align=\"center\">" + title + "</h1>\n");
	    out.println("<ul>");
	     
	    List<LibrarianTable> listLibrarian = LibrarianLoginUtil.listLibrarian();
	      
	    for (LibrarianTable ll : listLibrarian)
	    {
	    	System.out.println("[DBG] " + ll.getId() + ", " //
	    			+ ll.getbranch() + ", " //
	    			+ ll.getPin());

	    	out.println("<li>" + ll.getId() + ", " //
	    			+ ll.getbranch() + ", " //
	    			+ ll.getPin());
	    }
	    
	    out.println("</ul>");
	    out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}