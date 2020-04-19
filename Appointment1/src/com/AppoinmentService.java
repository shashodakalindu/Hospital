package com;

import modle.Appointment;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Appoint")
public class AppoinmentService {
	
	Appointment appointmentObj = new Appointment();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
	 return appointmentObj.readItems();
	 }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("Number") String Number,
	 @FormParam("Date") String Date,
	 @FormParam("description") String description,
	 @FormParam("Time") String Time,
	 @FormParam("Type") String Type,
	@FormParam("patientID") String patientID,
	@FormParam("DID") Integer DID,
	@FormParam("HospitalID") Integer HospitalID)
	
	
	{
	 String output = appointmentObj.insertItem(Number, Date, description, Time, Type,patientID,DID,HospitalID);
	return output;
	}
	
	
	
	
	 
	
	
	
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData)
	{
	//Convert the input string to a JSON object
	 JsonObject appointmentObject = new  JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	 String AppointmentID = appointmentObject.get("AppointmentID").getAsString();
	 String Number = appointmentObject.get("Number").getAsString();
	 String Date = appointmentObject.get("Date").getAsString();
	 String description = appointmentObject.get("description").getAsString();
	 String Time = appointmentObject.get("Time").getAsString();
	 String Type = appointmentObject.get("Type").getAsString();
	 String patientID = appointmentObject.get("patientID").getAsString();
	 int DID = appointmentObject.get("DID").getAsInt();
	 int HospitalID = appointmentObject.get("HospitalID").getAsInt();
	
	 
	 String output = appointmentObj.updateItem(AppointmentID,Number,Date,description,Time,Type,patientID,DID,HospitalID);
	return output;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, " ", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String AppointmentID = doc.select("AppointmentID").text();
	 String output = appointmentObj.deleteItem(AppointmentID);
	return output;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
