package ViewInterface;

import java.sql.SQLException;

public interface InsVisitView {
	void show();
	void querySuccess();
	void queryError(SQLException e);
}
