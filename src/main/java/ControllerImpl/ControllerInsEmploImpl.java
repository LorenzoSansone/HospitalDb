package ControllerImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ControllerInterface.ControllerHospitalObserver;
import ControllerInterface.ControllerInsEmplo;
import ControllerInterface.ControllerInsEmploObserver;
import HospitalDb.ProviderDb;
import HospitalDb.Utils;
import ViewImpl.InsEmploViewImpl;
import ViewInterface.InsEmploView;
import ViewInterface.InsPatientView;

public class ControllerInsEmploImpl implements ControllerInsEmplo, ControllerInsEmploObserver{
	final ControllerHospitalObserver observer;
	final InsEmploView view;
	
	public ControllerInsEmploImpl(ControllerHospitalObserver observer) {
		this.observer = observer;
		view = new InsEmploViewImpl(this);
		view.show();
	}
	public void backToMenu() {
		observer.showMenu();
	}
	
	public void register(String cf, String name, String surname, java.util.Date date, String gender, String tel, String mail, String type, String iban, String department) {
		/*System.out.println("CF: " +cf);
		System.out.println("Nome: " + name);
		System.out.println("Cognome: " + surname);
		System.out.println("Data: " + Utils.dateToSqlDate(date));
		System.out.println("Genere: " + gender);
		System.out.println("Tel: " + tel);
		System.out.println("Mail: " + mail);
		System.out.println("Tipologia: " + type);
		System.out.println("Iban: " + iban);
		System.out.println("Dipartimento: " + department);*/
		
		cf = Utils.stringSql(cf);
		name = Utils.stringSql(name);
		surname = Utils.stringSql(surname);
		gender = Utils.stringSql(gender);
		tel = Utils.stringSql(tel);
		mail = Utils.stringSql(mail);
		iban = Utils.stringSql(iban);
		department = Utils.stringSql(department); 
		String dateSql = "'" +  Utils.dateToSqlDate(date) + "'";
		
		String sqlTable = "";
		if(type.equals("Dottore")) {
			sqlTable = "dottori";
		}else if(type.equals("Ufficio")) {
			sqlTable = "impiegati_ufficio";
		}else if(type.equals("Infermiere")) {
			sqlTable = "infermieri";
		}else if(type.equals("Pulizie")) {
			sqlTable = "dipendenti_pulizie";
		}
		String query = "";
		if(type.equals("Dottore")) {
			query = "INSERT INTO "+ sqlTable + " VALUES " + "(" + cf + "," +
					 name + "," +
					 surname + "," +
					 dateSql + "," +
					 gender + "," +
					 tel + "," +
					 iban + "," +
					 mail + "," +
					 department + ")";
		}else {
			query = "INSERT INTO "+ sqlTable + " VALUES " + "(" + cf + "," +
					 name + "," +
					 surname + "," +
					 dateSql + "," +
					 gender + "," +
					 tel + "," +
					 iban + "," +
					 mail + ")";
		}
		
		
		if(!sqlTable.equals("")) {
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
}
