package ControllerImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ControllerInterface.ControllerHospitalObserver;
import ControllerInterface.ControllerVisDebtTotal;
import ControllerInterface.ControllerVisDebtTotalObserver;
import HospitalDb.ProviderDb;
import HospitalDb.Utils;
import ViewImpl.VisDebtPatientImpl;
import ViewImpl.VisDebtTotalViewImpl;
import ViewInterface.VisDebtPatient;
import ViewInterface.VisDebtTotalView;
import utilities.DebtPatient;
import utilitiesImpl.DebtPatientImpl;

public class ControllerVisDebtTotalImpl implements ControllerVisDebtTotal,ControllerVisDebtTotalObserver {
	private ControllerHospitalObserver observer;
	private VisDebtTotalView view;
	public ControllerVisDebtTotalImpl(ControllerHospitalObserver observer) {
		this.observer = observer;
		this.view = new VisDebtTotalViewImpl(this);
		this.view.show();
	}
	@Override
	public void backToMenu() {
		observer.showMenu();
		
	}

	@Override
	public void visualizeData(String patient) {
		
		int total = 0;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		patient = Utils.stringSql(patient);
		
		String queryTotalDebt = "SELECT DebitoTotale FROM pazienti WHERE CodiceFiscale=" + patient;

	    try {
			conn =  ProviderDb.getConnection().getMySQLConnection();
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(queryTotalDebt);
			if(rs.next()) {
				total = rs.getInt("DebitoTotale");
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
		
		
	
		this.view.updateView(Integer.toString(total));
		
	}

}
