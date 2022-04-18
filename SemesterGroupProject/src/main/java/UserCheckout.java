

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.*;
import datamodel.*;
/**
 * Servlet implementation class UserCheckout
 */
@WebServlet("/UserCheckout")
public class UserCheckout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCheckout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
		   java.sql.Date date = null;
		   PrintWriter out = response.getWriter();
		   String keyNewUser = request.getParameter("userName");
		   String keyUserID = request.getParameter("userID");
		   String keyBooks = request.getParameter("bookList");
		   String keyDate = request.getParameter("currentDate");
           Integer userID = Integer.getInteger(keyUserID);
           boolean updated = false;
           boolean avaibleCopies = true;
           String arr1[] = keyBooks.split(",");
           for (String book : arr1) {
        	  if (avaibleCopies) {
        		  avaibleCopies =  BookUtil.checkCopies(book);
        	  }
        	  else {
        		  break;
        	  }
           }
		   if(keyNewUser != null) {
			   UserUtil.createUser(keyNewUser, keyBooks,  date.valueOf(keyDate));
		   }
		   else if(keyUserID != null) {
			   if(avaibleCopies) {
				   updated = UserUtil.updateUser(userID, keyBooks, date.valueOf(keyDate));
				   for (String book : arr1) { 
			            BookUtil.updateCopies(book, -1);
			           }
			   }
			   if(!updated) {
				   out.print("Sorry could not update due either because user does not exist "
				   		+ "or because the user has books already checked out "
				   		+ "or there are no more avaible copies for a book");  
			        RequestDispatcher rd=request.getRequestDispatcher("user_Checkout.html");  
			        rd.include(request,response);
			   }else {
				   
				   out.print("updated, user checked out!");  
				        RequestDispatcher rd=request.getRequestDispatcher("user_Checkout.html");  
				        rd.include(request,response);
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
