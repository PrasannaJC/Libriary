

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
public class LibrarianLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	   public LibrarianLogin() {
	      super();
	   }

	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   String keyBranch = request.getParameter("branchName");
		      String keyPin = request.getParameter("PinCode");
		      boolean librarian = false;
			  librarian = LibrarianLoginUtil.LibrarianLogin(keyBranch, keyPin);
			  if (librarian == true) {
				  response.setContentType("html");
         	      PrintWriter out = response.getWriter();
	       	      out.println("hello");
	            }
			  else {
				  PrintWriter out = response.getWriter();
	       	      out.println("incorrect branch or password");
			  }
	   }

	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
//	   void search(String keyBranch, String keyPin, HttpServletResponse response) throws IOException {
//	     
//	      Connection connection = null;
//	      PreparedStatement preparedStatement = null;
//	      
//	      try {
//	         DBconnecter.getDBConnection(getServletContext());
//	         
//	         connection = DBconnecter.connection;
//	         
//	         if(!keyBranch.isEmpty() && !keyPin.isEmpty()) {
//	        	 String selectSQL = "SELECT * FROM  LibrarianLogin WHERE BRANCH LIKE ? AND PIN LIKE ?";
//		            String branch = keyBranch + "%";
//		            String pin = keyPin + "%";
//		            preparedStatement = connection.prepareStatement(selectSQL);
//		            preparedStatement.setString(1, branch);
//		            preparedStatement.setString(2, pin);
//	         }
//	         
//	         ResultSet rs = preparedStatement.executeQuery();
//
//	         while (rs != null && rs.next()) {
//	            String branch = rs.getString("branch").trim();
//	            String pin = rs.getString("pin").trim();
//	            if(branch.contains(keyBranch) && pin.contains(keyPin)){
//	            	 response.setContentType("html");
//	       	      PrintWriter out = response.getWriter();
//	       	      out.println("/SemesterGroupProject/Home.html");
//	            }
//	            else if(!branch.contains(keyBranch) || !pin.contains(keyPin)){
//	            	 PrintWriter out = response.getWriter();
//		       	      out.println("incorrect branch or password");
//		       	      
//	            }
//	         }
//	         
//	         rs.close();
//	         preparedStatement.close();
//	         connection.close();
//	      
//	      } 
//	      catch (SQLException se) {
//	         se.printStackTrace();
//	      } 
//	      catch (Exception e) {
//	         e.printStackTrace();
//	      } 
//	      finally {
//	         try {
//	            if (preparedStatement != null)
//	               preparedStatement.close();
//	         } 
//	         catch (SQLException se2) {
//	         }
//	         try {
//	            if (connection != null)
//	               connection.close();
//	         } 
//	         catch (SQLException se) {
//	            se.printStackTrace();
//	         }
//	      }
//	   }
//
//	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	      doGet(request, response);
//	   }

}
