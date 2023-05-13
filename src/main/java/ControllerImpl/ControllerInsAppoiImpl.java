package ControllerImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import ControllerInterface.ControllerHospitalObserver;
import ControllerInterface.ControllerInsAppoi;
import ControllerInterface.ControllerInsAppoiObserver;
import HospitalDb.ProviderDb;
import HospitalDb.Utils;
import ViewImpl.InsAppoiViewImpl;
import ViewImpl.InsEmploViewImpl;
import ViewInterface.InsAppoiView;
import ViewInterface.InsEmploView;

public class ControllerInsAppoiImpl implements  ControllerInsAppoi, ControllerInsAppoiObserver{
	final ControllerHospitalObserver observer;
	final InsAppoiView view;
	public ControllerInsAppoiImpl(ControllerHospitalObserver observer) {
		this.observer = observer;
		view = new InsAppoiViewImpl(this);
		view.show();
	}
	@Override
	public void backToMenu() {
		observer.showMenu();
		
	}
	@Override
	public void register(String patient, String doctor, Date date, String employee, String motivation) {
		System.out.println("Paziente: " + patient);
		System.out.println("Dottore: " + doctor);
		System.out.println("Data: " + Utils.dateToSqlDate(date));
		System.out.println("Impiegato: " + employee);
		System.out.println("Motivazione: " + motivation);
		
		patient = Utils.stringSql(patient);
		doctor = Utils.stringSql(doctor);
		employee = Utils.stringSql(employee);
		motivation = Utils.stringSql(motivation);
		
		String dateSql = "'" + Utils.dateToSqlDate(date) + "'";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn =  ProviderDb.getConnection().getMySQLConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			
			String queryDebtTotal = "SELECT COUNT(*) FROM appuntamenti WHERE Data=" + dateSql;
			rs = stmt.executeQuery(queryDebtTotal);	
			int totalAppointment = 0;
			if(rs.next()) {
				totalAppointment = rs.getInt(1);
				System.out.println("Total appuntamenti:" + totalAppointment);
			}
			if(totalAppointment < 10) {
				String query = "INSERT INTO appuntamenti VALUES(" + doctor + "," + patient + "," + dateSql + "," + motivation + "," + employee + ")";
				stmt.executeUpdate(query);	
				this.view.querySuccess();
			}else {
				this.view.appointmentDayComplete();
			}
			
			
			
			
			
			conn.commit();
		
		} catch (SQLException e) {
			this.view.queryError(e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				this.view.queryError(e);
			}
		}finally {
			if (rs != null) {
		        try {
		        	rs.close();
		        } catch (SQLException e) { /* Ignored */}
		    }
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
