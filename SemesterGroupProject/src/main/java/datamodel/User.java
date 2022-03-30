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
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "USERID")
   private Integer userID;

   @Column(name = "BOOKS")
   private String books;

   @Column(name = "OVERDUE")
   private String overdue;
      
   public User() {
   }

   public User(Integer userID, String books, String overdue) {
      this.userID = userID;
      this.books = books;
      this.overdue = overdue;
   }

   /*
    * I am including this to be used as a librarian insert of userID, book ISBN13
    * UtilDB will need to have a function to set overdue to False with timer going until true 
    * I envision this will be something along the lines of [current date] + [2 weeks?] set to '' (true)
    */
   
   public User(Integer userID, String books) {
	  this.userID = userID;
	  this.books = books;
   }
  

   public Integer getUserID() {
      return userID;
   }

   public void setUserID(Integer userID) {
      this.userID = userID;
   }

   public String getBooks() {
      return books;
   }

   public void setBooks(String books) {
      this.books = books;
   }

   public String getOverdue() {
      return overdue;
   }

   public void setOverdue(String overdue) {
      this.overdue = overdue;
   }
   
   @Override
   public String toString() {
      return "Book: " + this.userID + ", " + this.books + ", " + this.overdue;
   }
}