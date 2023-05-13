package ControllerImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import ControllerInterface.ControllerHospitalObserver;
import ControllerInterface.ControllerInsDebt;
import ControllerInterface.ControllerInsDebtObserver;
import HospitalDb.ProviderDb;
import HospitalDb.Utils;
import ViewImpl.InsAppoiViewImpl;
import ViewImpl.InsDebtViewImpl;
import ViewInterface.InsDebtView;

public class ControllerInsDebtImpl implements ControllerInsDebt, ControllerInsDebtObserver{
	final ControllerHospitalObserver observer;
	final InsDebtView view;
	
	public ControllerInsDebtImpl(ControllerHospitalObserver observer) {
		this.observer = observer;
		view = new InsDebtViewImpl(this);
		view.show();
	}

	@Override
	public void backToMenu() {
		this.observer.showMenu();
	}

	@Override
	public void register(String patient, Date date, String total, String description, String state) {
		System.out.println("Paziente:" + patient);
		System.out.println("Data:" + Utils.dateToSqlDate(date));
		System.out.println("Totale:" + total);
		System.out.println("Descrizione:" + description);
		System.out.println("Stato:" + state);
		
		patient = Utils.stringSql(patient);
		String dateSql = "'" + Utils.dateToSqlDate(date) + "'";
		description = Utils.stringSql(description);
		state = Utils.stringSql(state);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn =  ProviderDb.getConnection().getMySQLConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			
			//QUERY DEBT
			String queryInsertDebtPatient = "INSERT INTO debiti VALUES(" + patient + "," + dateSql + "," + description + "," + total + "," + state + ")" ;
			stmt.executeUpdate(queryInsertDebtPatient);	
			
			//QUERY DEBTPATIENT
			/*String queryDebtTotal = "SELECT DebitoTotale FROM pazienti WHERE CodiceFiscale=" + patient;
			rs = stmt.executeQuery(queryDebtTotal);	
			
			int debitoTotale = 0;
			
			while(rs.next()){
				debitoTotale = rs.getInt("DebitoTotale");
	         }
			System.out.print("DEBITO TOTALE:" + debitoTotale);
			debitoTotale = debitoTotale + Integer.parseInt(total);*/
			//QUERY
			
			String queryUpdateDebtPatient = "UPDATE pazienti SET DebitoTotale=DebitoTotale+" + total + " WHERE CodiceFiscale=" + patient ;
			stmt.executeUpdate(queryUpdateDebtPatient);	
			
			
			conn.commit();
			this.view.querySuccess();
		}catch (SQLException e) {
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
