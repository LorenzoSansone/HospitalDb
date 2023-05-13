package ViewInterface;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import utilities.Appointment;
import utilities.Entrance;

public interface VisAppoiView {
	void show();
	void updateTableAppoitnment(List<Appointment> appointList);
	void queryError(SQLException e);
}
