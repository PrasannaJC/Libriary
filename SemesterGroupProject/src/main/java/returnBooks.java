

import java.io.IOException;
import java.io.PrintWriter;

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
public class returnBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public returnBooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");  
		   String keyUserID = request.getParameter("userID");
		   String ISBN1 = request.getParameter("isbn1");
		   String ISBN2 = request.getParameter("isbn2");
		   String ISBN3 = request.getParameter("isbn3");
		   String ISBN4 = request.getParameter("isbn4");
		   String ISBN5 = request.getParameter("isbn5");
           String Books [] = new String [5];
           Books[0] = ISBN1;
           Books[1] = ISBN2;
           Books[2] = ISBN3;
           Books[3] = ISBN4;
           Books[4] = ISBN5;
		   Integer userID = Integer.valueOf(keyUserID);
		   for(String book: Books) {
			   if(!book.isEmpty()) {
				   UserUtil.returnUserBook(userID, book);
				   BookUtil.updateCopies(book, 1);
			   }
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
