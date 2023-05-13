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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ControllerInterface.ControllerVisDebtPatientObserver;
import ControllerInterface.ControllerVisPatPatientObserver;
import ViewInterface.VisDebtPatient;
import utilities.DebtPatient;
import utilities.PatPatient;
import utilities.TableFactory;
import utilitiesImpl.TableFactoryImpl;

public class VisDebtPatientImpl implements VisDebtPatient {
	
	final ControllerVisDebtPatientObserver observer;
	final JFrame frame = new JFrame();
	private static final String TITLE_FRAME = "Visualizzazione debiti paziente";
	  
	public static final int SPACE = 5;
	public static final String TITLE = "Visualizzazione i debito di un paziente";

	String[] titleTable = {"Data", "Totale", "Descrizione", "Stato"};
	
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
	
	public VisDebtPatientImpl(ControllerVisDebtPatientObserver observer) {
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
	public void updateTableDebt(List<DebtPatient> debtList) {
		final DefaultTableModel model = (DefaultTableModel) table.getModel();
	    table.setModel(this.getModel(debtList));
	    model.fireTableDataChanged();
	   
	    
	}

	private DefaultTableModel getModel(final Collection<DebtPatient> debtList) {
        final int row = debtList.size();
        final String[] columnNames = titleTable;
        Object[][] data = new Object[row][columnNames.length];
        int i = 0;
        for (final var elem : debtList) {
            data[i][0] = elem.getDate();
            data[i][1] = elem.getTotal();
            data[i][2] = elem.getDesc();
            data[i][3] = elem.getState();
            i++;
        }
        return new DefaultTableModel(data, columnNames);
	}
	@Override
	public void queryError(SQLException e) {
		JOptionPane.showMessageDialog(this.frame,e.toString(), "", JOptionPane.ERROR_MESSAGE);
		
	}


}
