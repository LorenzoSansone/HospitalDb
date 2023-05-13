package ControllerImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import ControllerInterface.ControllerHospitalObserver;
import ControllerInterface.ControllerVisAppoi;
import ControllerInterface.ControllerVisAppoiObserver;
import HospitalDb.ProviderDb;
import HospitalDb.Utils;
import ViewImpl.VisAppoiViewImpl;
import ViewImpl.VisEntrViewImpl;
import ViewImpl.VisPatPatientViewImpl;
import ViewInterface.VisAppoiView;
import ViewInterface.VisEntrView;
import utilities.Appointment;
import utilitiesImpl.AppointmentImpl;
import utilitiesImpl.PatPatientImpl;

public class ControllerVisAppoiImpl implements ControllerVisAppoi, ControllerVisAppoiObserver{
	private ControllerHospitalObserver observer;
	private VisAppoiView view;
	public ControllerVisAppoiImpl(ControllerHospitalObserver observer) {
		this.observer = observer;
		this.view = new VisAppoiViewImpl(this);
		this.view.show();
	}
	@Override
	public void backToMenu() {
		observer.showMenu();
		
	}

	@Override
	public void visualizeData(Date date) {

		System.out.println("Data: " + date);
		System.out.println("Data: " + Utils.dateToSqlDate(date));
		Optional<Date> dateIns = Utils.buildDate("02-05-1966");
		Optional<Date> dateIns1 = Utils.buildDate("08-04-1986");
		List<Appointment> list = new ArrayList<>();
		//list.add(new AppointmentImpl("DottMario","PazLucrezio",dateIns.get(),"Ha sinotmi strani"));
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String dataSql = "'" + Utils.dateToSqlDate(date) + "'";
		
		String query = "SELECT Data, CodDottore, CodPaziente, Motivo FROM appuntamenti WHERE Data=" + dataSql;

	    try {
			conn =  ProviderDb.getConnection().getMySQLConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()){
		          //Display values
		         // System.out.println("Nome: " + rs.getString("Nome"));
			     //  System.out.println("Data: " + rs.getDate("Data"));
			      
		          list.add(new AppointmentImpl(rs.getString("CodDottore"),rs.getString("CodPaziente"), rs.getDate("Data") ,rs.getString("Motivo")));
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
		
		view.updateTableAppoitnment(list);
	}

}
