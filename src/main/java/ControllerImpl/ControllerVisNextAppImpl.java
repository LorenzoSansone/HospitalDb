package ControllerImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Optional;

import ControllerInterface.ControllerHospitalObserver;
import ControllerInterface.ControllerVisNextApp;
import ControllerInterface.ControllerVisNextAppObserver;
import HospitalDb.ProviderDb;
import HospitalDb.Utils;
import ViewImpl.VisNextAppViewImpl;
import ViewImpl.VisPatPatientViewImpl;
import ViewInterface.VisNextAppView;
import utilities.Appointment;
import utilitiesImpl.AppointmentImpl;
import utilitiesImpl.DebtPatientImpl;

public class ControllerVisNextAppImpl implements ControllerVisNextApp, ControllerVisNextAppObserver{
	
	private ControllerHospitalObserver observer;
	private VisNextAppView view;
	
	public ControllerVisNextAppImpl(ControllerHospitalObserver observer) {
		this.observer = observer;
		this.view = new VisNextAppViewImpl(this);
		this.view.show();
	}
	@Override
	public void backToMenu() {
		observer.showMenu();
		
	}

	@Override
	public void visualizeData(String patient) {
		System.out.println("Paziente: " + patient);
		
		Optional<Date> date = Utils.buildDate("01-01-1999");
		
		int total = 0;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		patient = Utils.stringSql(patient);
		String querySingleDebt = "SELECT Data,CodDottore,Motivo FROM appuntamenti WHERE Data>NOW() AND CodPaziente=" + patient + " ORDER BY Data LIMIT 1";
		
	    try {
			conn =  ProviderDb.getConnection().getMySQLConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(querySingleDebt);
			if(rs.next()){
		    	Appointment app = new AppointmentImpl(rs.getString("CodDottore"),patient,rs.getDate("Data"),rs.getString("Motivo"));
				this.view.updateTableNextApp(app);
		     }
			System.out.println("Query fatta");
		} catch (SQLException e) {
			this.view.queryError(e);
		}finally {
			if (rs != null) {
		        try {
		        	rs.close();
		        } catch (SQLException e) {}
		    }
		    if (stmt != null) {
		        try {
		        	stmt.close();
		        } catch (SQLException e) { }
		    }
		    if (conn != null) {
		        try {
		            conn.close();
		        } catch (SQLException e) { }
		    }
		}
		
		
	}

}
