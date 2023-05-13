package utilitiesImpl;

import java.util.Date;

import utilities.Appointment;

public class AppointmentImpl implements Appointment{
	private String doctor;
	private String patient;
	private Date date;
	private String motivation;
	public AppointmentImpl(String doctor, String patient, Date date, String motivation) {
		this.doctor = doctor;
		this.patient = patient;
		this.date = date;
		this.motivation = motivation;
	}
	
	public String getDoctor() {
		return this.doctor;
	}
	public String getPatient() {
		return this.patient;
	}
	public String getDate() {
		return this.date.toString();
	}
	public String getMotivation() {
		return this.motivation;
	}
	
}
