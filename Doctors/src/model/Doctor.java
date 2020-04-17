package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Doctor{
	
	//A common method to connect to the DB
		public Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/doctors", "root", "");
		 
		//For testing
		 System.out.print("Successfully connected"); 
		 
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
		 }
		
		
		
		public String insertDocDetails(String Phone, String Address, String Specialization, String Email, String Name)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for inserting."; }
		 // create a prepared statement
		 String query = " insert into doctors(`DID`,`Phone`,`Address`,`Specialization`,`Email`,`Name`)"
		 + " values (?, ?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, Phone);
		 preparedStmt.setString(3, Address);
		 preparedStmt.setString(4, Specialization);
		 preparedStmt.setString(5, Email);
		 preparedStmt.setString(6, Name);
		
		 
		// execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while inserting the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		
		public String readDocDetails()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 // Prepare the html table to be displayed
		 output = "<table border=\"1\"><tr>Doctor ID<th>  Phone </th><th> Address </th><th> Specialization </th><th> Email </th><th> Name </th><th>Update</th><th>Remove</th></tr>";
		 String query = "select * from doctors";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
			 String DID = Integer.toString(rs.getInt("DID"));
			 String Phone = rs.getString("Phone");
			 String Address = rs.getString("Address");
			 String Specialization = rs.getString("Specialization");		
			 String Email = rs.getString("Email");
			 String Name = rs.getString("Name");
			
		 // Add into the html table
			 output += "<tr><td>" + Phone + "</td>";
			 output += "<td>" + Address + "</td>";
			 output += "<td>" + Specialization + "</td>";
			 output += "<td>" + Email + "</td>";
			 output += "<td>" + Name + "</td>";
			 
		 // buttons
		 output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"items.jsp\">" + "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
		 + "<input name=\"DID\" type=\"hidden\" value=\"" + DID
		 + "\">" + "</form></td></tr>";
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the items.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 
		
		
		
		
		
		
		
		public String update(String DID, String Phone, String Address, String Specialization, String Email, String Name)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE doctors SET Phone=?,Address=?,Specialization=?,Email=?,Name=?WHERE DID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, Phone);
		 preparedStmt.setString(2, Address);
		 preparedStmt.setString(3, Specialization);
		 preparedStmt.setString(4, Email);
		 preparedStmt.setString(5, Name);
		 preparedStmt.setInt(6,Integer.parseInt(DID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		
		
		
		
		
		
		
		
		
		
		public String deleteDocDetails(String DID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from doctors where DID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(DID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 


}