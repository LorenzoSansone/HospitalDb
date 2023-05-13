package HospitalDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import ControllerImpl.ControllerHospitalImpl;


public class App {
	public static void main(String[] args) {
		
		new ControllerHospitalImpl();
		/*Optional<Date> data = Utils.buildDateTime("05-05-1999 20:02:02");
		System.out.println(data.get().getTime());
		System.out.println(Utils.dateToSqlDateTime(data.get()));
		
		Optional<Date> data1 = Utils.buildDate("05-05-1999");*/
		//System.out.print(data1.get().getTime());
		/*boolean test = LogAccount.getAccountLogged().checkPassword("admin", "123");
		System.out.println(test);
		System.out.println("account: " + LogAccount.getAccountLogged().getNameAccountLogged());
*/
		
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
	
	/*	String query = "UPDATE stanze_letto SET CodInfermiere=null WHERE CodId=1";
		try {
			conn =  ProviderDb.getConnection().getMySQLConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(query);		
			System.out.println("Query fatta");
		} catch (SQLException e) {
			System.out.println(e);
		}finally {
		    if (stmt != null) {
		        try {
		        	stmt.close();
		        } catch (SQLException e) { }
		    }
		    if (conn != null) {
		        try {
		            conn.close();
		        } catch (SQLException e) {}
		    }
		}
*/
			
	/*	
       try {
			conn =  ProviderDb.getConnection().getMySQLConnection();
			stmt = conn.createStatement();
			
			for(int i=1; i<10; i++) {
				Optional<java.util.Date> time = Utils.buildDateTime("10-"+ i + "-2010 21:20:30");
				String query = "INSERT INTO uscite VALUES('D1','" + Utils.dateToSqlDateTime(time.get()) + "')";
				//stmt.executeUpdate(query);
				System.out.println(time.get());
			}
			
			
			System.out.println("Query fatta");
		} catch (SQLException e) {
			System.out.println(e);
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
		*/
		
		String queryd = "";
		//Optional<Date> data = Utils.buildDateTime("05-05-1999 20:02:02");
		//String dataTimeSql = "'" + Utils.dateToSqlDateTime(data.get()) + "'";
		//System.out.println(dataTimeSql);
		
		/*try {
			conn =  ProviderDb.getConnection().getMySQLConnection();
			stmt = conn.createStatement();
			//String queryUpdateDebtPatient = "UPDATE pazienti SET DebitoTotale=DebitoTotale+" + 10 + " WHERE CodiceFiscale='P1'" ;
			String queryUpdateDebtPatient = "UPDATE pazienti SET DebitoTotale=150 WHERE CodiceFiscale='P1'" ;
			stmt.executeUpdate(queryUpdateDebtPatient);	
				
			System.out.println("Query fatta");
		} catch (SQLException e) {
			System.out.print(e);
		}finally {
			if (rs != null) {
		        try {
		        	rs.close();
		        } catch (SQLException e) { }
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
		*/
	}
}
