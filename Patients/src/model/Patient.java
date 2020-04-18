package model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Patient {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf?useTimezone=true&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	//insert
	public String insertPatient(String name, String age, String address, String phone, String gender, String notes) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into patients(`patientID`,`patientName`,`patientAge`,`patientAddress`,`patientPhone`,`patientGender`,`patientNotes`)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, age);
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, phone);
			preparedStmt.setString(6, gender);
			preparedStmt.setString(7, notes);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	//Read
	public String readPatients() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Patient Name</th><th>Patient Age</th><th>PatientAddress</th><th>PatientPhone</th><th>PatientGender</th><th>PatientNotes</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from patients";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String patientID = Integer.toString(rs.getInt("patientID"));
				String patientName = rs.getString("patientName");
				String patientAge = rs.getString("patientAge");
				String patientAddress = rs.getString("patientAddress");
				String patientPhone = rs.getString("patientPhone");
				String patientGender = rs.getString("patientGender");
				String patientNotes = rs.getString("patientNotes");
				// Add into the html table
				output += "<tr><td>" + patientName + "</td>";
				output += "<td>" + patientAge + "</td>";
				output += "<td>" + patientAddress + "</td>";
				output += "<td>" + patientPhone + "</td>";
				output += "<td>" + patientGender + "</td>";
				output += "<td>" + patientNotes + "</td>";
				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"patients.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
						+ "<input name=\"patientID\" type=\"hidden\" value=\"" + patientID + "\">" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the patients.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//Read the payment for the specified persons 
	public String readPatientsPaymentHistory(String patientID1) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Payment Amount</th><th>Payment Purpose</th><th>Patient ID</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from payments where patientID="+ patientID1;
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String paymentID = Integer.toString(rs.getInt("paymentID"));
				String paymentAmount = Integer.toString( rs.getInt("paymentAmount"));
				String paymentPurpose = rs.getString("paymentPurpose");
				String patientID = Integer.toString( rs.getInt("patientID"));
				
				// Add into the html table
				output += "<tr><td>" + paymentAmount + "</td>";
				output += "<td>" + paymentPurpose + "</td>";
				output += "<td>" + patientID1 + "</td>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the payments.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//Read the appointment for the specified person
	public String readPatientsAppointmentHistory(String patientID2)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border=\"1\"><tr>Appointment ID<th> Number </th><th> Date </th><th> description </th><th> Time </th><th> Type </th><th>Patient ID</th><th>Update</th><th>Remove</th></tr>";
	 String query = "select * from appointment where patientID="+ patientID2;
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
		 String patientID = Integer.toString( rs.getInt("patientID"));
	 // Add into the html table
		 output += "<tr><td>" + Number + "</td>";
		 output += "<td>" + Date + "</td>";
		 output += "<td>" + description + "</td>";
		 output += "<td>" + Time + "</td>";
		 output += "<td>" + Type + "</td>";
		 output += "<td>" + patientID2 + "</td>";

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

	//Update
	public String updatePatient(String ID,  String name, String age, String address, String phone, String gender, String notes) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE patients SET patientName=?,patientAge=?,patientAddress=?,patientPhone=?,patientGender=?,patientNotes=?WHERE patientID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, age);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, phone);
			preparedStmt.setString(5, gender);
			preparedStmt.setString(6, notes);
			preparedStmt.setInt(7, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the patient.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	//Delete
	public String deletePatient(String patientID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from patients where patientID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(patientID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the patient.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
