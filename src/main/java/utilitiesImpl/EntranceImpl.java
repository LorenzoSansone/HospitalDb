package utilitiesImpl;

import java.util.Date;

import utilities.Entrance;

public class EntranceImpl implements Entrance{
	private final String nameDoctor;
	private final String idDoctor;
	private final String dataEnter;
	public EntranceImpl(String nameDoctor, String idDoctor, String dataEnter) {
		this.nameDoctor = nameDoctor;
		this.dataEnter = dataEnter;
		this.idDoctor = idDoctor;
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
		return dataEnter;
	}
}
