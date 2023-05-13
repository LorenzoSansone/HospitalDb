package HospitalDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProviderDb {
	
	private static final ProviderDb SINGLETON = new ProviderDb();
	
	private ProviderDb(){};
	
	public static ProviderDb getConnection() {
		return SINGLETON;
	}
	public Connection getMySQLConnection() {
		 final String username = "root";
		 final String password = "passwordDb";
		 final String dbName = "hospitaldb";
	
        final String dbUri = "jdbc:mysql://localhost:3306/" + dbName;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            // Thanks to the JDBC DriverManager we can get a connection to the database
            return DriverManager.getConnection(dbUri, username, password);
        } catch (final SQLException | ClassNotFoundException e) {
            throw new IllegalStateException("Could not establish a connection with db", e);
        }
    }
}
