package ViewInterface;

import java.sql.SQLException;

public interface InsPatientView {
	void show();
	void querySuccess();
	void queryError(SQLException e);
}
