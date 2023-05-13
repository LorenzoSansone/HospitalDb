package utilitiesImpl;

import java.util.Date;

import utilities.Report;

public class ReportImpl implements Report{
	private Date date;
	private String descMotivo;
	private String descRisultato;
	
	
	public ReportImpl(Date date, String descMotivo, String descRisultato) {
		this.date = date;
		this.descMotivo = descMotivo;
		this.descRisultato = descRisultato;
		
	}
	public String getDate() {
		return date.toString();
	}
	public String getMotivo() {
		return descMotivo;
	}
	public String getRisultato() {
		return descRisultato;
	}

}
