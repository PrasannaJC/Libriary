import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.BookUtil;
import util.Info;

/**
 * Servlet implementation class BookInsert
 */
@WebServlet("/BookInsert")
public class BookInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookInsert() {
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
		Integer keyCopy = Integer.valueOf(request.getParameter("copies")); //label it as whatever is designated in the HTML
		//String keyDescription = request.getParameter("description"); 
		Integer keyYear = Integer.valueOf(request.getParameter("date")); //label it as whatever is designated in the HTML// TODO Auto-generated method stub
		
		BookUtil.createBook(keyTitle, keyAuthor, keyISBN, keyCategory, keyCopy, keyYear);
		
	      response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      String title = "Book Inserted!";
	      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
	      out.println(docType + //
	            "<html>\n" + //
	            "<head><title>" + title + "</title></head>\n" + //
	            "<body bgcolor=\"#f0f0f0\">\n" + //
	            "<h1 align=\"center\">" + title + "</h1>\n");
	      out.println("<ul>");
	      out.println("<li> Book Title: " + keyTitle);
	      out.println("<li> Author: " + keyAuthor);
	      out.println("<li> ISBN: " + keyISBN);
	      out.println("<li> Category " + keyCategory);
	      out.println("<li> Number of Copies: " + keyCopy);
	      //out.println("<li> Description: " + keyDescription);
	      out.println("<li> Publication Year: " + keyYear);
	      out.println("</ul>");
	      out.println("<a href=/" + Info.projectName + "/" + Info.searchWebName + ">Insert Another Book</a> <br>");
	      out.println("<a href=/" + Info.projectName + "/" + Info.homeWebName + ">Back to Home Page</a> <br>");
	      out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
