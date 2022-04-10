import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Book;
import util.Info;
import util.BookUtil;

/**
 * Servlet implementation class SearchBook
 */
@WebServlet("/SearchBook")
public class SearchBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyTitle = request.getParameter("booktitle"); //label it as whatever is designated in the HTML
		String keyAuthor = request.getParameter("writer"); //label it as whatever is designated in the HTML
		String keyISBN = request.getParameter("ISBN"); //label it as whatever is designated in the HTML
		String keyCategory = request.getParameter("category"); //label it as whatever is designated in the HTML

		Integer keyCopy;
		try { 
			keyCopy = Integer.valueOf(request.getParameter("copies")); //label it as whatever is designated in the HTML
		}
		catch (NumberFormatException e) {
			keyCopy = null;
		}
		Integer keyYear;
		try {
			keyYear = Integer.valueOf(request.getParameter("date")); //label it as whatever is designated in the HTML
		}
		catch (NumberFormatException e) {
			keyYear = null;
		}
		
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
	
	    List<Book> bkz = null;

		bkz = BookUtil.listBooks(keyTitle, keyAuthor, keyISBN, keyCategory, keyCopy, keyYear);

	    display(bkz, out);
	    out.println("</ul>");
	    out.println("<a href=/" + Info.projectName + "/" + Info.searchWebName + ">Search for Another Book</a> <br>");
	    //out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Data</a> <br>");
	    out.println("</body></html>");
			
		
	}
	
	
	void display(List<Book> listBooks, PrintWriter out) 
	{
	      for (Book b : listBooks) {
	         System.out.println("[DBG] " + b.getTitle() + ", " //
	        		 + b.getAuthor() + ", " //
	        		 + b.getISBN13() + ", " //
	        		 + b.getCategory() + ", " //
	        		 + b.getCopies() + ", " //
	        		 + b.getDescription() + ", " //
	        		 + b.getPublicationYear());

	         out.println("<li>" + b.getTitle() + ", " //
	        		 + b.getAuthor() + ", " //
	        		 + b.getISBN13() + ", " //
	        		 + b.getCategory() + ", " //
	        		 + b.getCopies() + ", " //
	        		 + b.getDescription() + ", " //
	        		 + b.getPublicationYear() + "</li>");
	      }
	   }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
