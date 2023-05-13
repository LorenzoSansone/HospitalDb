package ViewInterface;

import java.sql.SQLException;
import java.util.List;

import utilities.Appointment;
import utilities.PatPatient;

public interface VisNextAppView {
	void show();
	void updateTableNextApp(Appointment appointment);
	void queryError(SQLException e);

}
