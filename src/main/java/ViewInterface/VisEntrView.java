package ViewInterface;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import utilities.Entrance;
import utilities.Exit;

public interface VisEntrView {
	void show();
	void updateTableEntrance(List<Entrance> entraceList);
	void updateTableExit(List<Exit> exitList);
	void queryError(SQLException e);

	
}
