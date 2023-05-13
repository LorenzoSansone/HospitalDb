package ViewImpl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ControllerInterface.ControllerVisEntrObserver;
import HospitalDb.Utils;
import ViewInterface.VisEntrView;
import utilities.Entrance;
import utilities.Exit;
import utilities.TableFactory;
import utilitiesImpl.TableFactoryImpl;


public class VisEntrViewImpl implements VisEntrView {
	final ControllerVisEntrObserver observer;
	final JFrame frame = new JFrame();
	private static final String TITLE_FRAME = "Visualizzazione entrata/uscita dottore";
	  
	public static final int SPACE = 5;
	public static final String TITLE = "Visualizzazione entrata/uscita dottore";
	String[] typeCombo = new String[] {"Entrata", "Uscita"};
	String[] titleTable = {"Data Ora"};
	
	private final JLabel title = new JLabel(TITLE);
	    
	private final JTextField textDoctor = new JTextField("", 12); //written that will be removed when clicked
	
	private final JComboBox<String> comboInOut =  new JComboBox<>(typeCombo);
	  
	private final JButton visualBtn = new JButton("Cerca");
	private final JButton homeBtn = new JButton("Home");
	
	private JTable table;
	final TableFactory factory;
	
	private static final double WIDTH_PERC_FRAME = 0.4;
	private static final double HEIGTH_PERC_FRAME = 0.4;
	private static final double WIDTH_MINIMUM_FRAME = WIDTH_PERC_FRAME / 0.7;
	private static final double HEIGTH_MINMUM_FRAME = HEIGTH_PERC_FRAME / 0.7;
	
	
	
	public VisEntrViewImpl(ControllerVisEntrObserver observer) {
		this.observer = observer;
		
		factory = new TableFactoryImpl(); 
		table = factory.getTable(titleTable);
		
		final JPanel centerPanel = new JPanel(new BorderLayout());
		final JPanel mainPanel = new JPanel(new BorderLayout());
		
		final JPanel menuPanel = new JPanel(new BorderLayout());
		menuPanel.add(homeBtn,BorderLayout.EAST);
		
		this.frame.setTitle(TITLE_FRAME);
		
        final JPanel pWestInternal = new JPanel(new GridBagLayout()); //flexible grid
        final GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0;
        cnst.insets = new Insets(SPACE, SPACE, SPACE, SPACE);
        cnst.fill = GridBagConstraints.HORIZONTAL;

        //I create the secondary panels for the various parts and add the components
        final JPanel pNorth = new JPanel(new FlowLayout());
        pNorth.add(title, cnst);
        cnst.gridy++;   //next line
        
        pWestInternal.add(new JLabel("Dottore"), cnst); 
        pWestInternal.add(textDoctor, cnst);
        
        cnst.gridy++;

        pWestInternal.add(new JLabel("Tipologia"), cnst);
        pWestInternal.add(comboInOut, cnst);
        
        
        
        
               
        
        final JPanel pWest = new JPanel(new FlowLayout());
        pWest.add(pWestInternal);

        final JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
        pSouth.add(visualBtn);
     
        visualBtn.addActionListener(e ->{
        	
        		String doctor = textDoctor.getText(); 	
        		String type = comboInOut.getSelectedItem().toString();
        		observer.visualizeData(doctor,type);
        });
        homeBtn.addActionListener(e -> {
        	observer.backToMenu();
        	//frame.setVisible(false);
        	frame.dispose();
        });
        
        JPanel tablePanel = new JPanel();
        tablePanel.add(table);
        centerPanel.add(pWest, BorderLayout.CENTER);
        centerPanel.add(pNorth, BorderLayout.NORTH);
        centerPanel.add(pSouth, BorderLayout.SOUTH);
        
        mainPanel.add(menuPanel,BorderLayout.NORTH);
        mainPanel.add(centerPanel,BorderLayout.CENTER);
        mainPanel.add(new JScrollPane(table),BorderLayout.EAST);
        frame.add(mainPanel);

		//mainPanel.add(panel,BorderLayout.CENTER);
		final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setMinimumSize(new Dimension((int) (screenSize.getWidth() * WIDTH_MINIMUM_FRAME), (int) (screenSize.getHeight() * HEIGTH_MINMUM_FRAME)));

        frame.getContentPane().add(mainPanel);
        frame.setLocationByPlatform(true);
		
	}
	@Override
	public void show() {
		frame.setVisible(true);
	}
	@Override
	public void updateTableEntrance(List<Entrance> entraceList) {
		
		final DefaultTableModel model = (DefaultTableModel) table.getModel();
	    table.setModel(this.getModelEntrance(entraceList));
	    model.fireTableDataChanged();
		
	}
	@Override
	public void updateTableExit(List<Exit> exitList) {
		
		final DefaultTableModel model = (DefaultTableModel) table.getModel();
	    table.setModel(this.getModelExit(exitList));
	    model.fireTableDataChanged();
		
	}
	
	private DefaultTableModel getModelEntrance(final Collection<Entrance> entrance) {
        final int row = entrance.size();
        final String[] columnNames = titleTable;
        Object[][] data = new Object[row][columnNames.length];
        int i = 0;
        for (final var elem : entrance) {
            //data[i][0] = elem.getIdDoctor();
            //data[i][1] = elem.getNameDoctor();
            data[i][0] = elem.getDate();
            i++;
        }
        return new DefaultTableModel(data, columnNames);
	}
	private DefaultTableModel getModelExit(final Collection<Exit> exit) {
        final int row = exit.size();
        final String[] columnNames = titleTable;
        Object[][] data = new Object[row][columnNames.length];
        int i = 0;
        for (final var elem : exit) {
            //data[i][0] = elem.getIdDoctor();
            //data[i][1] = elem.getNameDoctor();
            data[i][0] = elem.getDate();
            i++;
        }
        return new DefaultTableModel(data, columnNames);
	}
	@Override
	public void queryError(SQLException e) {
		JOptionPane.showMessageDialog(this.frame,e.toString(), "", JOptionPane.ERROR_MESSAGE);
		
	}


}
