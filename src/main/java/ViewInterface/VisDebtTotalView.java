package ViewInterface;

import java.sql.SQLException;
import java.util.List;

import utilities.DebtPatient;

public interface VisDebtTotalView {
	void show();
	void updateView(String total);
	void queryError(SQLException e);
}
