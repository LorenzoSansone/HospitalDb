package ControllerImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import ControllerInterface.ControllerHospitalObserver;
import ControllerInterface.ControllerInsPatPatient;
import ControllerInterface.ControllerInsPatPatientObserver;
import HospitalDb.ProviderDb;
import HospitalDb.Utils;
import ViewImpl.InsPatPatientViewImpl;
import ViewInterface.InsPatPatientView;
import ViewInterface.InsPatientView;

public class ControllerInsPatPatientImpl implements ControllerInsPatPatient, ControllerInsPatPatientObserver {
	final ControllerHospitalObserver observer;
	final InsPatPatientView view;
	
	public ControllerInsPatPatientImpl(ControllerHospitalObserver observer) {
		this.observer = observer;
		this.view = new InsPatPatientViewImpl(this);
		this.view.show();
	}

	@Override
	public void backToMenu() {
		observer.showMenu();
	}

	@Override
	public void register(String patient, Date date, String patology) {
		System.out.println("Paziente: " + patient);
		System.out.println("Data: " + Utils.dateToSqlDate(date));
		System.out.println("Patology: " + patology);
		
		String dataSql = "'" + Utils.dateToSqlDate(date) + "'";
		//patology = Utils.stringSql(patology);
		patient = Utils.stringSql(patient);
		Connection conn = null;
		Statement stmt = null;
		String query = "INSERT INTO patologie_pazienti VALUES(" + patology + "," + patient + "," + dataSql + ")";
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
