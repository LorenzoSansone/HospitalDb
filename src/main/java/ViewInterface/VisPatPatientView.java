package ViewInterface;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import utilities.Entrance;
import utilities.PatPatient;

public interface VisPatPatientView {
	void show();
	void updateTablePatology(List<PatPatient> patPatientList);
	void queryError(SQLException e);

}
