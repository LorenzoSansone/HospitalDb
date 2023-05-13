package utilitiesImpl;

import java.util.Collection;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import utilities.Entrance;
import utilities.Exit;
import utilities.TableFactory;


public class TableFactoryImpl implements TableFactory{

	@Override
	public JTable getTable(String[] title) {
		   final int row = 0;
	        final String[] columnNames = title;
	        Object[][] data = new Object[row][columnNames.length];  
	        final DefaultTableModel model = new DefaultTableModel(data, columnNames);
	        final JTable table = new JTable(model) {
	            private static final long serialVersionUID = 1L;
	            public boolean isCellEditable(final int row, final int column) {
	                return false;
	            }
	        };
	        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        return table;
	}

	

	public DefaultTableModel getModelEntrance(final Collection<Entrance> entrance) {
	        final int row = entrance.size();
	        final String[] columnNames = {"Id", "Nome", "Data Ora"};
	        Object[][] data = new Object[row][columnNames.length];
	        int i = 0;
	        for (final var elem : entrance) {
	            data[i][0] = elem.getIdDoctor();
	            data[i][1] = elem.getNameDoctor();
	            data[i][2] = elem.getDate();
	            i++;
	        }
	        return new DefaultTableModel(data, columnNames);
	}
}
