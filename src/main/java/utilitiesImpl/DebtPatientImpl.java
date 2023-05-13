package utilitiesImpl;

import java.util.Date;

import utilities.DebtPatient;

public class DebtPatientImpl implements DebtPatient {
	private Date date;
	private String patient;
	private String total;
	private String desc;
	private String state;
	public DebtPatientImpl(String patient, String total, String desc, String state, Date date) {
		this.date = date;
		this.desc = desc;
		this.patient = patient;
		this.total = total;
		this.state = state;
	}
	
	public String getPatient() {
		return this.patient;
	}
	public String getTotal() {
		return this.total;
	}
	public String getDesc() {
		return this.desc;
	}
	public String getState() {
		return this.state;
	}
	public String getDate() {
		return this.date.toString();
	}
}
