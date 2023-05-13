package ViewInterface;

import java.sql.SQLException;

public interface InsDebtView {
	void show();
	void querySuccess();
	void queryError(SQLException e);
}
