package ViewInterface;

import java.sql.SQLException;

public interface InsAppoiView {
	void show();
	void querySuccess();
	void queryError(SQLException e);
	void appointmentDayComplete();
}
