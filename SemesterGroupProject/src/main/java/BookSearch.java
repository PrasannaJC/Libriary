

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
		String keyTitle = request.getParameter("bookTitle"); //label it as whatever is designated in the HTML
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
        
		String keys[] = {keyTitle, keyAuthor, keyISBN, keyCategory, keyCopy, keyYear};
		String keyNames[] = {"TITLE", "AUTHOR", "ISBN13", "CATEGORY", "COPIES", "PublicationYear"};
		String selectSQL = "Select * FROM BookTable";
		Boolean test[] = new Boolean[6];
		
		for (int i = 0; i < keys.length; i++)
		{	
			if (keys[i] != "" && !keys[i].isEmpty()) // Just check if this is not accidently repeating the same command
			{
				test[i] = true;
			}
		}
		
		if (test[4]) // SOME ISSUE HERE!!! GETTING NULL POINTER EXCEPTION
		{
			int copyNumb = Integer.parseInt(keyCopy);
			selectSQL += " Where COPIES >= " + copyNumb;
		}
		if (test[5])
		{
			int copyYear = Integer.parseInt(keyYear);
			selectSQL += " Where PublicationYear = " + copyYear;
		}
		
		for (int j = 0; j < 4; j++)
		{
			if (test[j])
			{
				selectSQL += " WHERE" + keyNames[j] + "LIKE ?";
			}
		}
		/*
		if (!test[0] && !test[1] && !test[2] && !test[3] && !test[4] && !test[5])
		{
			selectSQL = "Select * FROM BookTable";
		}
        */
		response.setContentType("text/html");
		preparedStatement = connection.prepareStatement(selectSQL);
		ResultSet rs = preparedStatement.executeQuery();

		
		while (rs.next()) {
            String titleTemp = rs.getString("booktitle").trim();
            String authorTemp = rs.getString("writer").trim();
            String bookNumb = rs.getString("ISBN").trim();
            String group = rs.getString("category").trim();
            int count = rs.getInt("copies");
            int year = rs.getInt("date");

            out.println("Book Title: " + titleTemp + ", ");
	        out.println("Author: " + authorTemp + ", ");
	        out.println("ISBN 13: " + bookNumb + ", ");
	        out.println("Category: " + group + ", ");
	        out.println("Copies: " + count + ", ");
	        out.println("Year Published: " + year + "<br>");
		}
		out.println("<a href=/SemesterGroupProject/BookSearch.html>Search Data</a> <br>");
        out.println("</body></html>");
        rs.close();
        preparedStatement.close();
        connection.close();
	}
	
	/*
	void titleSearch()
	{
		
	}
	
	void authorSearch()
	{
		
	}
	
	void ISBNsearch()
	{
		
	}
	
	void categorySearch()
	{
		
	}
	
	//searches based on if there are more than a set number of copies of a book available. 
	void copySearch()
	{
		
	}
	
	// filters out books by the matching year
	// maybe set it up as a drop down where a user can search based on a certain year 
	// (keep it within a range of say forty or fifty years)
	 
	void yearSearch()
	{
		
	}

	*/
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
