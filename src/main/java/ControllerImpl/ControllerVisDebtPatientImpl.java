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
import ControllerInterface.ControllerVisDebtPatient;
import ControllerInterface.ControllerVisDebtPatientObserver;
import HospitalDb.ProviderDb;
import HospitalDb.Utils;
import ViewImpl.VisDebtPatientImpl;
import ViewImpl.VisPatPatientViewImpl;
import ViewInterface.VisDebtPatient;
import utilities.DebtPatient;
import utilitiesImpl.DebtPatientImpl;
import utilitiesImpl.PatPatientImpl;

public class ControllerVisDebtPatientImpl implements ControllerVisDebtPatient, ControllerVisDebtPatientObserver{
	private ControllerHospitalObserver observer;
	private VisDebtPatient view;
	public ControllerVisDebtPatientImpl(ControllerHospitalObserver observer) {
		this.observer = observer;
		this.view = new VisDebtPatientImpl(this);
		this.view.show();
	}
	@Override
	public void backToMenu() {
		observer.showMenu();
		
	}

	@Override
	public void visualizeData(String patient) {
		System.out.println("Paziente: " + patient);
		List<DebtPatient> list = new ArrayList<>();
		int total = 0;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		patient = Utils.stringSql(patient);
		String querySingleDebt = "SELECT Data,DescDebito,Totale,Stato FROM debiti WHERE CodPaziente=" + patient;

	    try {
			conn =  ProviderDb.getConnection().getMySQLConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(querySingleDebt);
			while(rs.next()){
		          //Display values
		         // System.out.println("Nome: " + rs.getString("Nome"));
			     //  System.out.println("Data: " + rs.getDate("Data"));
			          
		          list.add(new DebtPatientImpl(patient,rs.getString("Totale"),rs.getString("DescDebito"),rs.getString("Stato"),rs.getDate("Data")));
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
		
		
	
		this.view.updateTableDebt(list);
		
	}

}
