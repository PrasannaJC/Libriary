package datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @since J2SE-1.8
  CREATE TABLE LibrarianTable (
  id INT NOT NULL AUTO_INCREMENT,
  branch VARCHAR(30) NOT NULL, 
  pin VARCHAR(30) NOT NULL,   
  PRIMARY KEY (id));
 */
@Entity
@Table(name = "LibrarianLogin")
public class LibrarianTable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;
   
   @Column(name = "branch")
   private String branch;

   @Column(name = "pin")
   private String pin;
   
   public LibrarianTable(Integer id, String branch, String pin) {
      this.id = id;
      this.branch = branch;
      this.pin = pin;
   }
   public LibrarianTable(String branch, String pin) {
	  this.branch = branch; 
      this.pin = pin;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }
   public String getbranch() {
	      return this.branch;
	   }

   public void setPartyName(String branch) {
	      this.branch = branch;
	   }
   public String getPin() {
      return pin;
   }

   public void setPin(String pin) {
      this.pin = pin;
   }
     
   @Override
   public String toString() {
      return "PARTY: " + this.branch + ", " + this.pin;
   }
}

