package ControllerImpl;


import ControllerInterface.ControllerHospital;
import ControllerInterface.ControllerHospitalObserver;
import ControllerInterface.ControllerInsPatient;
import ControllerInterface.ControllerInsPatientObserver;
import HospitalDb.ProviderDb;
import HospitalDb.Utils;
import ViewImpl.InsPatientViewImpl;
import ViewInterface.InsPatientView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ControllerInsPatientImpl implements ControllerInsPatient, ControllerInsPatientObserver {
	final ControllerHospitalObserver observer;
	final InsPatientView view;
	public ControllerInsPatientImpl(ControllerHospitalObserver observer) {
		this.observer = observer;
		this.view = new InsPatientViewImpl(this);
		view.show();
	}
	public void show() {
		//view.show();
	}
	public void backToMenu() {
		observer.showMenu();
	}
	@Override
	public void register(String cf, String name, String surname, java.util.Date date, String gender, String tel, String room, String desc, String debt) {
		System.out.println("CF: " +cf);
		System.out.println("Nome: " + name);
		System.out.println("Cognome: " + surname);
		System.out.println("Data: " + Utils.dateToSqlDate(date));
		System.out.println("Genere: " + gender);
		System.out.println("Tel: " + tel);
		System.out.println("Stanza: " + room);
		System.out.println("Descrizione salute: " + desc);
		System.out.println("Debito: " + debt);
		String dateSql = "'" + Utils.dateToSqlDate(date) + "'";
		cf = Utils.stringSql(cf);
		name = Utils.stringSql(name);
		surname = Utils.stringSql(surname);
		gender = Utils.stringSql(gender);
		tel = Utils.stringSql(tel);
		desc = Utils.stringSql(desc);
		debt = Utils.stringSql(debt);
		room = Utils.stringSql(room);

		String query = "INSERT INTO pazienti VALUES " + "(" + cf + "," +
				 name + "," +
				 surname + "," +
				 dateSql + "," +
				 gender + "," +
				 tel + "," +
				 desc + "," +
				 debt + "," +
				 room + ")";
		Connection conn = null;
		Statement stmt = null;
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
