package ViewInterface;

import java.sql.SQLException;

public interface InsEmploView {
	void show();
	void querySuccess();
	void queryError(SQLException e);
}
