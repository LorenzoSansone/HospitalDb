package ViewImpl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ControllerInterface.ControllerInsDebtObserver;
import HospitalDb.Utils;
import ViewInterface.InsDebtView;

public class InsDebtViewImpl implements InsDebtView{
	final ControllerInsDebtObserver observer;
	final JFrame frame = new JFrame();
	
	private static final String TITLE_FRAME = "Inserimento debito paziente";
	  
	public static final int SPACE = 5;
	public static final String TITLE = "Inserimento di una debito per un paziente";

	private final JLabel title = new JLabel(TITLE);
	    
	private final JTextField textPatient = new JTextField("", 12); //written that will be removed when clicked
	private final JTextField textTot = new JTextField("", 12); //written that will be removed when clicked
	private final JTextField textData =  new JTextField("dd-mm-yyyy", 12);
	private final JTextArea textDesc =  new JTextArea();
	private final JTextField textState  =  new JTextField("", 12);
	
	
	private final JButton registerBtn = new JButton("Registra");
	private final JButton homeBtn = new JButton("Home");
	  
	private static final double WIDTH_PERC_FRAME = 0.4;
	private static final double HEIGTH_PERC_FRAME = 0.4;
	private static final double WIDTH_MINIMUM_FRAME = WIDTH_PERC_FRAME / 0.7;
	private static final double HEIGTH_MINMUM_FRAME = HEIGTH_PERC_FRAME / 0.7;
	
	public InsDebtViewImpl(ControllerInsDebtObserver observer) {
		this.observer = observer;
		
		
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

        pWestInternal.add(new JLabel("Data"), cnst); 
        pWestInternal.add(textData, cnst);
        
        cnst.gridy++;
        
        pWestInternal.add(new JLabel("Totale"), cnst); 
        pWestInternal.add(textTot, cnst);

        cnst.gridy++;
        
        pWestInternal.add(new JLabel("Descrizione"), cnst); 
        pWestInternal.add(textDesc, cnst);
        
        textDesc.setPreferredSize(new Dimension(250, 50));

        cnst.gridy++;
        
        pWestInternal.add(new JLabel("Stato"), cnst); 
        pWestInternal.add(textState, cnst);

        cnst.gridy++;
        
        final JPanel pWest = new JPanel(new FlowLayout());
        pWest.add(pWestInternal);

        final JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
        pSouth.add(registerBtn);
     
        registerBtn.addActionListener(e ->{
        	
        	Optional<java.util.Date> date = Utils.buildDate(textData.getText());
    		
        	if(date.isEmpty()) {
        		JOptionPane.showMessageDialog(pWestInternal, "Wrong Data", "", JOptionPane.ERROR_MESSAGE);
        	}else {
        		String patient = textPatient.getText();
            	String tot = textTot.getText();
            	String desc = textDesc.getText();
            	String state = textState.getText();
        		observer.register(patient,date.get(),tot,desc,state);
        	}
        });
        homeBtn.addActionListener(e -> {
        	observer.backToMenu();
        	//frame.setVisible(false);
        	frame.dispose();
        });
        
        
        centerPanel.add(pWest, BorderLayout.CENTER);
        centerPanel.add(pNorth, BorderLayout.NORTH);
        centerPanel.add(pSouth, BorderLayout.SOUTH);
        
        mainPanel.add(menuPanel,BorderLayout.NORTH);
        mainPanel.add(centerPanel,BorderLayout.CENTER);
	 
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
	public void querySuccess() {
		JOptionPane.showMessageDialog(this.frame, "Query success", "", JOptionPane.INFORMATION_MESSAGE);
		
	}

	@Override
	public void queryError(SQLException e) {
		JOptionPane.showMessageDialog(this.frame,e.toString(), "", JOptionPane.ERROR_MESSAGE);
		
	}


}
