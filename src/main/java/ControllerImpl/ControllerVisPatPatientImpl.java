package ControllerImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import ControllerInterface.ControllerHospitalObserver;
import ControllerInterface.ControllerVisPatPatient;
import ControllerInterface.ControllerVisPatPatientObserver;
import HospitalDb.ProviderDb;
import HospitalDb.Utils;
import ViewImpl.VisEntrViewImpl;
import ViewImpl.VisPatPatientViewImpl;
import ViewInterface.VisPatPatientView;
import utilities.Entrance;
import utilities.PatPatient;
import utilitiesImpl.EntranceImpl;
import utilitiesImpl.PatPatientImpl;

public class ControllerVisPatPatientImpl implements  ControllerVisPatPatient,ControllerVisPatPatientObserver{
	
	private ControllerHospitalObserver observer;
	private VisPatPatientView view;
	public ControllerVisPatPatientImpl(ControllerHospitalObserver observer) {
		this.observer = observer;
		this.view = new VisPatPatientViewImpl(this);
		this.view.show();
	}
	@Override
	public void backToMenu() {
		observer.showMenu();
		
	}

	@Override
	public void visualizeData(String patient) {
		System.out.println("Paziente: " + patient );
		
		List<PatPatient> list = new ArrayList<>();
		
		Optional<Date> data = Utils.buildDate("05-05-2000");
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		patient = Utils.stringSql(patient);
		String query = "SELECT P.Nome,L.Data FROM patologie_pazienti L, patologie P WHERE L.CodPatologia=P.CodId AND L.CodPaziente=" + patient;

	    try {
			conn =  ProviderDb.getConnection().getMySQLConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()){
		          //Display values
		         // System.out.println("Nome: " + rs.getString("Nome"));
			     //  System.out.println("Data: " + rs.getDate("Data"));
			          
		          list.add(new PatPatientImpl(patient, rs.getDate("L.Data") ,rs.getString("P.Nome")));
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
		
		view.updateTablePatology(list);
		
	}

}
