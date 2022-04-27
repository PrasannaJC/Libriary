package datamodel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @since J2SE-1.8
 CREATE TABLE employee (
  id INT NOT NULL AUTO_INCREMENT,    
  name VARCHAR(30) NOT NULL,   
  age INT NOT NULL,    
  PRIMARY KEY (id));
 */
@Entity
@Table(name = "UserTable")
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USERID")
	private Integer userID;

	@Column(name = "USERNAME")
	private String username;
   
	@Column(name = "BOOKS")
	private String books;
   
	@Column(name = "CHECKOUT")
	private java.sql.Date checkout;
   
	@Column(name = "DUE")
	private java.sql.Date due;
      
	public User()
	{}

	// for an existing user, need conversion to java.sql.Date and due date calculation to happen in the Util; need to UPDATE not create user
	// as such, there is no create user command with the ID.
   
	// for a new user, need conversion to java.sql.Date and due date calculation to happen in the Util
	public User(String username, String books, java.sql.Date checkout, java.sql.Date due)
	{
		this.username = username;
		this.books = books;
		this.checkout = checkout;
		this.due = due;
	}
  
	public Integer getUserID()
	{
		return userID;
	}

	public void setUserID(Integer userID)
	{
		this.userID = userID;
	}

	public String getUsername()
	{
	   	return username;
	}
   
	public void setUsername(String username)
	{
		this.username = username;
	}
   
	public String getBooks()
	{
		return books;
	}

	public void setBooks(String books)
	{
		this.books = books;
	}
   
	public java.sql.Date getCheckout()
	{
		return checkout;
	}
   
	public void setCheckout(java.sql.Date checkout)
	{
		this.checkout = checkout;
	}
   
	public java.sql.Date getDue()
	{
		return due;
	}
   
	public void setDue(java.sql.Date due)
	{
		this.due = due;
	}
   
	@Override
   	public String toString()
	{
		return "User: " + this.userID + ", " + this.username + ", " + this.books + ", " + this.checkout + ", " + this.due;
	}
}