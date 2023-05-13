package ViewInterface;

import java.sql.SQLException;

public interface InsAssignEmploView {
	void show();
	void queryError(SQLException e);
	void querySuccess();
}
