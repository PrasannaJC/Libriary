

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/BookSearch")
public class BookSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String keyTitle = request.getParameter("booktitle"); //label it as whatever is designated in the HTML
		String keyAuthor = request.getParameter("writer"); //label it as whatever is designated in the HTML
		String keyISBN = request.getParameter("ISBN"); //label it as whatever is designated in the HTML
		String keyCategory = request.getParameter("category"); //label it as whatever is designated in the HTML

		String keyCopy = request.getParameter("copies"); //label it as whatever is designated in the HTML
		String keyYear = request.getParameter("date"); //label it as whatever is designated in the HTML
		
		try {
			masterSearch(response, keyTitle, keyAuthor, keyISBN, keyCategory, keyCopy, keyYear);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("null")
	/*
	 * The Master search method:
	 * Based upon when certain conditions are met, different lesser search methods will be called upon.
	 */
	void masterSearch(HttpServletResponse response, String keyTitle, String keyAuthor, String keyISBN, String keyCategory, String keyCopy, String keyYear) throws SQLException, IOException
	{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
		PrintWriter out = response.getWriter();
		
        String title = "Database Result";
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
              "transitional//en\">\n"; //
        out.println(docType + //
              "<html>\n" + //
              "<head><title>" + title + "</title></head>\n" + //
              "<body bgcolor=\"#f0f0f0\">\n" + //
              "<h1 align=\"center\">" + title + "</h1>\n");
        
        DBconnecter.getDBConnection(getServletContext());
        connection = DBconnecter.connection;
        
		String keys[] = {keyTitle, keyAuthor, keyISBN, keyCategory, keyCopy, keyYear};
		String keyNames[] = {"TITLE", "AUTHOR", "ISBN13", "CATEGORY", "COPIES", "PublicationYear"};
		String selectSQL = "SELECT * FROM BookTable";
		Boolean test[] = {false, false, false, false, false, false};
		

		
		for (int i = 0; i < keys.length; i++)
		{	
			if (keys[i] != "" || !keys[i].isEmpty()) // Just check if this is not accidently repeating the same command
			{
				test[i] = true;
			}
			System.out.println(keyNames[i] + " is " + test[i] + " with the input => " + keys[i] + " <=  ");
		}
		
		
		/** ORIGINAL
		for (int j = 0; j < 4; j++)
		{
			if (test[j])
			{
				selectSQL += " WHERE " + keyNames[j] + " LIKE ?";
				//keys[j] += "%";

				if (test[j+1] && j < 4)
				{
					selectSQL += " AND";
				}
			}
		}
		*/
		
		
		for (int j = 0; j < test.length; j++)
		{
			if (test[j] && j < 4)
			{
				if (String.valueOf(selectSQL.charAt(selectSQL.length() - 1)).equals("?"))
				{
					selectSQL += " AND";
				}
				
				selectSQL += " WHERE " + keyNames[j] + " LIKE ?";
				//keys[j] += "%";

				
				/*
				if (test[j+1] && j < 4)
				{
					if (test[j - 1])
					{
						selectSQL += " AND";
					}
					
					
				}
				*/
				
			}
			else if (test[j] && j == 4)
			{
				if (String.valueOf(selectSQL.charAt(selectSQL.length() - 1)).equals("?"))
				{
					selectSQL += " AND";
				}
				
				selectSQL += " WHERE " + keyNames[4] + " >= ?";
				
				
			}
			else if (test[j] && j == 5)
			{
				if (String.valueOf(selectSQL.charAt(selectSQL.length() - 1)).equals("?"))
				{
					selectSQL += " AND";
				}
				
				selectSQL += " WHERE " + keyNames[5] + " = ?";
			}
			
		}
	
		/**
		if (test[4]) // SOME ISSUE HERE!!! GETTING NULL POINTER EXCEPTION
		{
			selectSQL += " WHERE " + keyNames[4] + " >= ?";// + copyNumb;
		}
		if (test[5])
		{
			
			
			selectSQL += " WHERE " + keyNames[5] + " = ?";// + copyYear;
		}
		*/


		System.out.println("This is the selectSQL statement => " + selectSQL);
		
		response.setContentType("text/html");
		
		preparedStatement = connection.prepareStatement(selectSQL);
		int q = 1;
		String temp;
		for (int p = 0; p < test.length; p++)
		{
			if (test[p])
			{
				temp = keys[p] + "%";
				System.out.println("Temp is -> " + temp + " and p is -> " + p + " and q is -> " + q);
				preparedStatement.setString(q, temp);
				q++;
			}
		}
		
		System.out.println("This is the selectSQL statement for the second time => " + selectSQL);
		ResultSet rs = preparedStatement.executeQuery();

		
		
		while (rs.next()) {
            String titleTemp = rs.getString("TITLE").trim();
            String authorTemp = rs.getString("AUTHOR").trim();
            String bookNumb = rs.getString("ISBN13").trim();
            String group = rs.getString("CATEGORY").trim();
            
            int count = rs.getInt("COPIES");
            String describe = rs.getString("DESCRIPTION");
            int year = rs.getInt("PublicationYear");
            

            out.println("Book Title: " + titleTemp + ", ");
	        out.println("Author: " + authorTemp + ", ");
	        out.println("ISBN 13: " + bookNumb + ", ");
	        out.println("Category: " + group + ", ");
	        
	        out.println("Copies: " + count + ", ");
	        out.println("Description: " + describe + ", ");
	        out.println("Year Published: " + year + "<br>");
	        
		}
		out.println("<a href=/SemesterGroupProject/Book_Search.html>Search Data</a> <br>");
        out.println("</body></html>");
        rs.close();
        preparedStatement.close();
        connection.close();
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
