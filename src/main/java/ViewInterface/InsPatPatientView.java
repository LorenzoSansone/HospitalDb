package ViewInterface;

import java.sql.SQLException;

public interface InsPatPatientView {
	void show();
	void querySuccess();
	void queryError(SQLException e);
	
}
