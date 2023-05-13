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
import ControllerInterface.ControllerVisEntr;
import ControllerInterface.ControllerVisEntrObserver;
import HospitalDb.ProviderDb;
import HospitalDb.Utils;
import ViewImpl.LoginViewImpl;
import ViewImpl.VisEntrViewImpl;
import ViewInterface.VisEntrView;
import utilities.Entrance;
import utilities.Exit;
import utilitiesImpl.EntranceImpl;
import utilitiesImpl.ExitImpl;

public class ControllerVisEntrImpl implements ControllerVisEntr, ControllerVisEntrObserver {
	ControllerHospitalObserver observer;
	VisEntrView view;
	public ControllerVisEntrImpl(ControllerHospitalObserver observer) {
		this.observer = observer;
		this.view = new VisEntrViewImpl(this);
		this.view.show();
	}
	@Override
	public void backToMenu() {
		observer.showMenu();
	}
	@Override
	public void visualizeData(String doctor, String type) {
		System.out.println("Dottore" + doctor);
		System.out.println("type" + type);
		//QUERY
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		if("Entrata".equals(type)) {
			String doctorName = Utils.stringSql(doctor);
			List<Entrance> list = new ArrayList<>();
			String querySelect = "SELECT DataOra FROM entrate WHERE CodDottore=" + doctorName;
			
	       try {
				conn =  ProviderDb.getConnection().getMySQLConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(querySelect);
				while(rs.next()){
			          //Display values
			          System.out.println("Data: " + rs.getTimestamp(1));
			          list.add(new EntranceImpl(doctor,"", rs.getTimestamp(1).toString()));
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
			view.updateTableEntrance(list);
		}else {
			List<Exit> list = new ArrayList<>();
			String doctorName = Utils.stringSql(doctor);
		
			String querySelect = "SELECT DataOra FROM uscite WHERE CodDottore=" + doctorName;
			
	       try {
				conn =  ProviderDb.getConnection().getMySQLConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(querySelect);
				while(rs.next()){
			          //Display values
			          System.out.println("Data: " + rs.getTimestamp(1));
			          list.add(new ExitImpl(doctor,"", rs.getTimestamp(1).toString()));
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
			view.updateTableExit(list);
		}
	}
}
