package modle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Appointment{
	
	//A common method to connect to the DB
		public Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", "");
		 
		//For testing
		 System.out.print("Successfully connected"); 
		 
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
		 }
		
		
		
		public String insertItem(String Number, String Date, String description, String Time, String Type)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for inserting."; }
		 // create a prepared statement
		 String query = " insert into appointment(`AppointmentID`,`Number`,`Date`,`description`,`Time`,`Type`)"
		 + " values (?, ?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, Number);
		 preparedStmt.setString(3, Date);
		 preparedStmt.setString(4, description);
		 preparedStmt.setString(5, Time);
		 preparedStmt.setString(6, Type);
		
		 
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
		
		
		
		public String readItems()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 // Prepare the html table to be displayed
		 output = "<table border=\"1\"><tr>Appointment ID<th> Number </th><th> Date </th><th> description </th><th> Time </th><th> Type </th><th>Update</th><th>Remove</th></tr>";
		 String query = "select * from appointment";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
			 String AppointmentID = Integer.toString(rs.getInt("AppointmentID"));
			 String Number = rs.getString("Number");
			 String Date = rs.getString("Date");
			 String description = rs.getString("description");		
			 String Time = rs.getString("Time");
			 String Type = rs.getString("Type");
			
		 // Add into the html table
			 output += "<tr><td>" + Number + "</td>";
			 output += "<td>" + Date + "</td>";
			 output += "<td>" + description + "</td>";
			 output += "<td>" + Time + "</td>";
			 output += "<td>" + Type + "</td>";
			 
		 // buttons
		 output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"items.jsp\">" + "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
		 + "<input name=\"AppointmentID\" type=\"hidden\" value=\"" + AppointmentID
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
		
		
		
		
		
		
		
		public String updateItem(String AppointmentID, String Number, String Date, String description, String Time, String Type)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE appointment SET Number=?,Date=?,description=?,Time=?,Type=?WHERE AppointmentID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, Number);
		 preparedStmt.setString(2, Date);
		 preparedStmt.setString(3, description);
		 preparedStmt.setString(4, Time);
		 preparedStmt.setString(5, Type);
		 preparedStmt.setInt(6,Integer.parseInt(AppointmentID));
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
		
		
		
		
		
		
		
		
		
		
		
		
		public String deleteItem(String AppointmentID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from appointment where AppointmentID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(AppointmentID));
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
