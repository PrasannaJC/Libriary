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
@Table(name = "BookTable")
public class Book
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOOKID")
	private String bookid;
   
	
	@Column(name = "TITLE")
	private String title;

	@Column(name = "AUTHOR")
	private String author;
   
	@Column(name = "ISBN13")
	private String isbn13;
   
	@Column(name = "CATEGORY")
	private String category;
   
	@Column(name = "COPIES")
	private Integer copies;
   
	@Column(name = "PublicationYear")
	private Integer publicationyear;
   
	public Book()
	{}

	public Book(String title, String author, String isbn13, String category, Integer copies, Integer publicationyear)
	{
		this.title = title;
		this.author = author;
		this.isbn13 = isbn13;
		this.category = category;
		this.copies = copies;
		this.publicationyear = publicationyear;
	}
  

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
   	{
		this.author = author;
   	}

	public String getISBN13()
	{
		return isbn13;
	}

	public void setISBN13(String isbn13)
	{
		this.isbn13 = isbn13;
	}
   
	public String getCategory()
	{
		return category;
	}
   
	public void setCategory(String category)
	{
		this.category = category;
	}

	public Integer getCopies()
	{
		return copies;
	}
   
	public void setCopies(Integer copies)
	{
		this.copies = copies;
	}
   
	public Integer getPublicationYear()
	{
		return publicationyear;
	}
   
	public void setPublicationYear(Integer publicationyear)
	{
		this.publicationyear = publicationyear;
	}
   
	@Override
	public String toString()
	{
		return "Book: " + this.title + ", " + this.author + ", " + this.isbn13 + ", " + this.category + ", " + this.copies + ", " + this.publicationyear;
	}
}