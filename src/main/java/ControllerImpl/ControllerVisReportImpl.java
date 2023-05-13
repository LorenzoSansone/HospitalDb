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
import ControllerInterface.ControllerVisReport;
import ControllerInterface.ControllerVisReportObserver;
import HospitalDb.ProviderDb;
import HospitalDb.Utils;
import ViewInterface.VisReportView;
import utilities.Report;
import utilitiesImpl.DebtPatientImpl;
import utilitiesImpl.ReportImpl;
import ViewImpl.VisReportViewImpl;
public class ControllerVisReportImpl implements ControllerVisReport, ControllerVisReportObserver{
	private ControllerHospitalObserver observer;
	private VisReportView view;
	public ControllerVisReportImpl(ControllerHospitalObserver observer) {
		this.observer = observer;
		this.view = new VisReportViewImpl(this);
		this.view.show();
	}
	@Override
	public void backToMenu() {
		this.observer.showMenu();
		
	}

	@Override
	public void visualizeData(String patient) {
		System.out.println("Paziente: " + patient);
		List<Report> list = new ArrayList<>();
		
		Optional<Date> date = Utils.buildDate("01-05-1999");
		Optional<Date> date1 = Utils.buildDate("06-05-2020");
		
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		patient = Utils.stringSql(patient);
		String queryReport = "SELECT R.DescMotivo,R.DescRisultato, V.Data FROM visite V, referti R WHERE V.CodReferto=R.CodId AND V.CodPaziente=" + patient;
	

	    try {
			conn =  ProviderDb.getConnection().getMySQLConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(queryReport);
			while(rs.next()){
		
		          list.add(new ReportImpl(rs.getDate("V.Data"),rs.getString("R.DescMotivo"),rs.getString("R.DescRisultato")));
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
		
		this.view.updateTableReport(list);
		
	}

}
