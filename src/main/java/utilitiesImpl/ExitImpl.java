package utilitiesImpl;

import java.util.Date;

import utilities.Exit;

public class ExitImpl implements Exit {
	private final String nameDoctor;
	private final String idDoctor;
	private final String dataExit;
	
	public ExitImpl(String nameDoctor, String idDoctor, String dataExit) {
		this.nameDoctor = nameDoctor;
		this.idDoctor = idDoctor;
		this.dataExit = dataExit;
	}
	@Override
	public String getNameDoctor() {
		// TODO Auto-generated method stub
		return nameDoctor;
	}
	@Override
	public String getIdDoctor() {
		// TODO Auto-generated method stub
		return idDoctor;
	}
	@Override
	public String getDate() {
		// TODO Auto-generated method stub
		return dataExit;
	}
}
