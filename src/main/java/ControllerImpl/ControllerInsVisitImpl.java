package ControllerImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import ControllerInterface.ControllerHospitalObserver;
import ControllerInterface.ControllerInsVisit;
import ControllerInterface.ControllerInsVisitObserver;
import HospitalDb.ProviderDb;
import HospitalDb.Utils;
import ViewImpl.InsPatientViewImpl;
import ViewImpl.InsVisitViewImpl;
import ViewInterface.InsPatientView;
import ViewInterface.InsVisitView;

public class ControllerInsVisitImpl implements ControllerInsVisit, ControllerInsVisitObserver {

	
	final ControllerHospitalObserver observer;
	final InsVisitView view;
	public ControllerInsVisitImpl(ControllerHospitalObserver observer) {
		this.observer = observer;
		this.view = new InsVisitViewImpl(this);
		view.show();
	}
	@Override
	public void backToMenu() {
		this.observer.showMenu();
		
	}

	@Override
	public void register(String doctor, String patient, String ambulatory, Date date, String motivation, String result,String medicine) {
		/*System.out.println("Dottore: " + doctor);
		System.out.println("Paziente: " + patient);
		System.out.println("Ambulatorio: " + ambulatory);
		System.out.println("date: " + Utils.dateToSqlDate(date));
		System.out.println("Motivazione: " + motivation);
		System.out.println("Risultato: " + result);*/
		
		
		
		
		String dateSql = "'" + Utils.dateToSqlDate(date) + "'";
		doctor = Utils.stringSql(doctor);
		patient = Utils.stringSql(patient);
		//ambulatory = Utils.stringSql(ambulatory);
		motivation = Utils.stringSql(motivation);
		result = Utils.stringSql(result);
		System.out.println(result);
		
		
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn =  ProviderDb.getConnection().getMySQLConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			
			//QUERY REPORT
			String queryReport = "INSERT INTO referti(`DescMotivo`,`DescRisultato`) VALUES " + "(" + motivation + "," +
					result + ")";
			System.out.println("QUERY:" + queryReport);
			stmt.executeUpdate(queryReport,Statement.RETURN_GENERATED_KEYS);
			rs = stmt.getGeneratedKeys();
			String idReport = null;
			if(rs.next()) {
				System.out.println("ID REFERTO: " + rs.getString(1));
				idReport = rs.getString(1);
			}
			
			//QUERY MEDICINE REPORT
			String[] medArray = medicine.split("\n");
			System.out.println("MEDICINE");
			if(medicine.length() > 0) {
				for(int i = 0; i < medArray.length; i++ ) {
					String[] singleMed = medArray[i].split("-");
					String nameMed = Utils.stringSql(singleMed[0]);
					String qtnMed = Utils.stringSql(singleMed[1]);
					String perMed = Utils.stringSql(singleMed[2]);
					String queryMedicineReport = "INSERT INTO medicine_referto VALUES " + "(" + nameMed + "," +
							idReport + "," + qtnMed + "," + perMed + ")";
					
					System.out.println("Nome med:" + singleMed[0]);
					System.out.println("Qtn med:" + singleMed[1]);
					System.out.println("Periodo med:" + singleMed[2]);
					stmt.executeUpdate(queryMedicineReport);
				}
			}
			//QUERY VISITA
			String queryVisit= "INSERT INTO visite VALUES " + "(" + patient + "," +
					doctor + "," + dateSql + "," + idReport + "," + ambulatory + ")";
			stmt.executeUpdate(queryVisit);
			conn.commit();
			
			this.view.querySuccess();
		
		} catch (SQLException e) {
			try {
				conn.rollback();
				
			} catch (SQLException e1) {
				this.view.queryError(e);
			}
			this.view.queryError(e);
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
