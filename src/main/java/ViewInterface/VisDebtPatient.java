package ViewInterface;

import java.sql.SQLException;
import java.util.List;

import utilities.Appointment;
import utilities.DebtPatient;

public interface VisDebtPatient {
	void show();
	void updateTableDebt(List<DebtPatient> debtList);
	void queryError(SQLException e);
}
