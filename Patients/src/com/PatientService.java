package com;


import model.Patient;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Patients")
public class PatientService {
	Patient patientObj = new Patient();

	//get
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPatients() {
		return patientObj.readPatients();
	}
	//get
	@GET
	@Path("/PatientPayments/{patientID}")
	@Produces(MediaType.TEXT_HTML)
	public String readPatientsPaymentHistory(@PathParam("patientID") String patientID) {
		return patientObj.readPatientsPaymentHistory(patientID);
		//appointments details for selected user
	}
	//get
	@GET
	@Path("/PatientAppointments/{patientID}")
	@Produces(MediaType.TEXT_HTML)
	public String readPatientsAppointmentHistory(@PathParam("patientID") String patientID) {
		return patientObj.readPatientsAppointmentHistory(patientID);
		//appointments details for selected user
	}
	//post 
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPatient(@FormParam("patientName") String patientName, 
			@FormParam("patientAge") String patientAge,
			@FormParam("patientAddress") String patientAddress,
			@FormParam("patientPhone") String patientPhone,
			@FormParam("patientGender") String patientGender, 
			@FormParam("patientNotes") String patientNotes) {
		String output = patientObj.insertPatient(patientName, patientAge, patientAddress, patientPhone, patientGender, patientNotes);
		return output;
	}
	//put
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePatient(String PatientData) {
		// Convert the input string to a JSON object
		JsonObject PatientObject = new JsonParser().parse(PatientData).getAsJsonObject();
		// Read the values from the JSON object
		String patientID = PatientObject.get("patientID").getAsString();
		String patientName = PatientObject.get("patientName").getAsString();
		String patientAge = PatientObject.get("patientAge").getAsString();
		String patientAddress = PatientObject.get("patientAddress").getAsString();
		String patientPhone = PatientObject.get("patientPhone").getAsString();
		String patientGender = PatientObject.get("patientGender").getAsString();
		String patientNotes = PatientObject.get("patientNotes").getAsString();
		String output = patientObj.updatePatient(patientID, patientName, patientAge, patientAddress, patientPhone, patientGender, patientNotes);
		return output;
	}
	//delete
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePatient(String PatientData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(PatientData, "", Parser.xmlParser());

		// Read the value from the element <PatientID>
		String patientID = doc.select("patientID").text();
		String output = patientObj.deletePatient(patientID);
		return output;
	}

}

