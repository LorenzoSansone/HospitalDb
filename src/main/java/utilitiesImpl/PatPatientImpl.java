package utilitiesImpl;

import java.util.Date;

import utilities.PatPatient;

public class PatPatientImpl implements PatPatient{
	private final String patient;
	private final Date date;
	private final String patology;
	
	public PatPatientImpl(String patient, Date date, String patology) {
		this.patient = patient;
		this.date = date;
		this.patology = patology;
	}

	@Override
	public String getPatient() {
		// TODO Auto-generated method stub
		return this.patient;
	}

	@Override
	public String getDate() {
		// TODO Auto-generated method stub
		return this.date.toString();
	}

	@Override
	public String getPatology() {
		return this.patology;
	}
}
