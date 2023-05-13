package ControllerImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import ControllerInterface.ControllerAssignEmplo;
import ControllerInterface.ControllerAssignEmploObserver;
import ControllerInterface.ControllerHospitalObserver;
import HospitalDb.ProviderDb;
import HospitalDb.Utils;
import ViewImpl.InsAssignEmploViewImpl;
import ViewImpl.VisPatPatientViewImpl;
import ViewInterface.InsAssignEmploView;

public class ControllerAssignEmploImpl implements ControllerAssignEmplo, ControllerAssignEmploObserver {
	private ControllerHospitalObserver observer;
	private InsAssignEmploView view;
	public ControllerAssignEmploImpl(ControllerHospitalObserver observer) {
		this.observer = observer;
		this.view = new InsAssignEmploViewImpl(this);
		this.view.show();
	}
	@Override
	public void backToMenu() {
		this.observer.showMenu();
		
	}

	@Override
	public void register(String employee, String room, String typeEmployee) {
		System.out.println("Dipendente: " + employee);
		System.out.println("Stanza: " + room);
		System.out.println("Tipologia: " + typeEmployee);
		String type = "";
		employee = Utils.stringSql(employee);
		
		if(typeEmployee.equals("Infermiera")) {
			type = "CodInfermiere";
		}else {
			type = "CodPulizie";
		}
		String query = "UPDATE stanze_letto SET "+ type + "=" + employee + " WHERE CodId=" + room;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn =  ProviderDb.getConnection().getMySQLConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(query);		
			this.view.querySuccess();
		} catch (SQLException e) {
			this.view.queryError(e);
		}finally {
		    if (stmt != null) {
		        try {
		        	stmt.close();
		        } catch (SQLException e) { /* Ignored */}
		    }
		    if (conn != null) {
		        try {
		            conn.close();
		        } catch (SQLException e) { /* Ignored */}
		    }
		}
		
		
		
		
	}

}
