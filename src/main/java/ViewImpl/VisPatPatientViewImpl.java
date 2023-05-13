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
import ControllerInterface.ControllerVisPatPatientObserver;
import ViewInterface.VisPatPatientView;
import utilities.Entrance;
import utilities.PatPatient;
import utilities.TableFactory;
import utilitiesImpl.TableFactoryImpl;

public class VisPatPatientViewImpl implements VisPatPatientView {
	
	final ControllerVisPatPatientObserver observer;
	final JFrame frame = new JFrame();
	private static final String TITLE_FRAME = "Visualizzazione patologie";
	  
	public static final int SPACE = 5;
	public static final String TITLE = "Visualizzazione patologie pazienti";

	String[] titleTable = {"Data", "Nome patologia"};
	
	private final JLabel title = new JLabel(TITLE);
	    
	private final JTextField textPatient = new JTextField("", 12); //written that will be removed when clicked
	

	  
	private final JButton visualBtn = new JButton("Cerca");
	private final JButton homeBtn = new JButton("Home");
	
	private JTable table;
	private TableFactory factory;
	
	private static final double WIDTH_PERC_FRAME = 0.4;
	private static final double HEIGTH_PERC_FRAME = 0.4;
	private static final double WIDTH_MINIMUM_FRAME = WIDTH_PERC_FRAME / 0.7;
	private static final double HEIGTH_MINMUM_FRAME = HEIGTH_PERC_FRAME / 0.7;
	
	
	public VisPatPatientViewImpl(ControllerVisPatPatientObserver observer) {
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
        
        pWestInternal.add(new JLabel("Paziente"), cnst); 
        pWestInternal.add(textPatient, cnst);
        
        cnst.gridy++;

    
        
        
        
               
        
        final JPanel pWest = new JPanel(new FlowLayout());
        pWest.add(pWestInternal);

        final JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
        pSouth.add(visualBtn);
     
        visualBtn.addActionListener(e ->{
        	
        		String patient = textPatient.getText(); 	
        	
        		observer.visualizeData(patient);
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
		this.frame.setVisible(true);
		
	}

	@Override
	public void updateTablePatology(List<PatPatient> patPatientList) {
		final DefaultTableModel model = (DefaultTableModel) table.getModel();
	    table.setModel(this.getModel(patPatientList));
	    model.fireTableDataChanged();
		
	}
	private DefaultTableModel getModel(final Collection<PatPatient> patPatientSet) {
        final int row = patPatientSet.size();
        final String[] columnNames = titleTable;
        Object[][] data = new Object[row][columnNames.length];
        int i = 0;
        for (final var elem : patPatientSet) {
            data[i][0] = elem.getDate();
            data[i][1] = elem.getPatology();
            i++;
        }
        return new DefaultTableModel(data, columnNames);
	}
	@Override
	public void queryError(SQLException e) {
		JOptionPane.showMessageDialog(this.frame,e.toString(), "", JOptionPane.ERROR_MESSAGE);
		
	}
	

}
