package ViewInterface;

import java.sql.SQLException;
import java.util.List;

import utilities.PatPatient;
import utilities.Report;

public interface VisReportView {
	void show();
	void updateTableReport(List<Report> reportList);
	void queryError(SQLException e);


}
