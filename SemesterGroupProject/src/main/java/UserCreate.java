import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Info;
import util.UserUtil;

/**
 * Servlet implementation class CreateUser
 */
@WebServlet("/UserCreate")
public class UserCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UserCreate() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String keyDate = request.getParameter("currentDate"); //label it as whatever is designated in the HTML
		String keyName = request.getParameter("userName"); //label it as whatever is designated in the HTML
		String keyISBN = request.getParameter("bookList"); //label it as whatever is designated in the HTML
		
		java.sql.Date d = Date.valueOf(keyDate);
		
		UserUtil.createUser(keyName, keyISBN, d);
		
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
	      out.println("<li> Name: " + keyName);
	      out.println("<li> ISBN: " + keyISBN);
	      out.println("<li> Date: " + d);
	      out.println("</ul>");
	      out.println("<a href=/" + Info.projectName + "/" + Info.searchWebName + ">Insert Another Book</a> <br>");
	      out.println("<a href=/" + Info.projectName + "/" + Info.homeWebName + ">Back to Home Page</a> <br>");
	      out.println("</body></html>");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
