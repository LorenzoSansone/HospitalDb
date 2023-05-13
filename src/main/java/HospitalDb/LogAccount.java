package HospitalDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LogAccount {

	private static final LogAccount SINGLETON = new LogAccount();
	private String nameAccountLogged = "";
	private Map<String, String> accounts = Map.of(
		    "admin", "123",
		    "doctor", "1234",
		    "employee", "12345"
		);
	private LogAccount(){};
	
	public static LogAccount getAccountLogged() {
		return SINGLETON;
	}
	public String getNameAccountLogged() {
		return nameAccountLogged;
	}
	public boolean checkPassword(String username, String password) {
		//return true;
		if(this.accounts.containsKey(username) && (this.accounts.get(username).equals(password)) ) {
			this.nameAccountLogged = username;
			return true;
		}else {
			return false;
		}
	
		
		
	}
}
