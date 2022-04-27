import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import datamodel.Book;
//import datamodel.Book;
import datamodel.User;
//import util.BookUtil;
import util.Info;
//import util.BookUtil;
import util.UserUtil;

/**
 * Servlet implementation class Users
 */
@WebServlet("/UserSearch")
public class UserSearch extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
	public UserSearch()
	{
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		String keyID = request.getParameter("userID"); //label it as whatever is designated in the HTML
		String keyName = request.getParameter("userName"); //label it as whatever is designated in the HTML
		String keyISBN = request.getParameter("isbn"); //label it as whatever is designated in the HTML
		
		Integer k = Integer.getInteger(keyID);
		try
		{ 
			k = Integer.valueOf(request.getParameter("userID")); //label it as whatever is designated in the HTML
		}
		catch (NumberFormatException e)
		{
			k = null;
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Search Results";
	    String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
	    out.println(docType + //
	    		"<html>\n" + //
	    		"<head><title>" + title + "</title></head>\n" + //
	    		"<body bgcolor=\"#b5a965\">\n" + //
	    		"<h1 align=\"center\">" + title + "</h1>\n");
	    out.println("<ul>");  
	    
		List<User> v = null;	
		v = UserUtil.listUsers(k, keyName, keyISBN);
		
		display(v, out);
		
		if (userExist(keyID))
		{
			System.out.println("=> " + keyID);
		}
		
		out.println("<a href=/" + Info.projectName + "/" + Info.searchUserName + ">Search for Another User</a> <br>");
	}
	
	public Boolean userExist(String keyID)
	{
		Integer k = Integer.getInteger(keyID);
		Boolean out = false;
		
		System.out.println(k + " ");
		
		if (keyID == null || keyID.equals(""))
		{
			System.out.println("<li> The following user " + keyID + " does not exists, so go create a new user");
			out = false;
		}
		else if (UserUtil.checkUser(keyID))
		{
			System.out.println("<li> The following user " + keyID + " exists and can check out books!!!");
			out = UserUtil.checkUser(keyID);
		}
		
		return out;
	}
	
	void display(List<User> u, PrintWriter out) 
	{
		for (User x : u)
		{	  
			System.out.println("[DBG] " + x.getUsername() + ", " //
					+ x.getUserID() + ", " //
					+ x.getBooks() + ", " //
					+ x.getCheckout() + ", " //
					+ x.getDue() + "</li>");

			out.println("<li> " + x.getUsername() + ", " //
					+ x.getUserID() + ", " //
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
