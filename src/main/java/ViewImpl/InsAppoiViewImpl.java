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

import ControllerInterface.ControllerInsAppoi;
import ControllerInterface.ControllerInsAppoiObserver;
import HospitalDb.Utils;
import ViewInterface.InsAppoiView;

public class InsAppoiViewImpl implements InsAppoiView{
	ControllerInsAppoiObserver observer;
	private JFrame frame = new JFrame();
	private static final String TITLE_FRAME = "Inserimento appuntamento";

	public static final int SPACE = 5;
	public static final String TITLE = "Inserire i dati per un appuntamento";
	
	String[] gender = new String[] {"Maschio","Femmina"};
    
	private final JLabel title = new JLabel(TITLE);
	    
	private final JTextField textEmployee = new JTextField("", 12); //written that will be removed when clicked
	private final JTextField textDoctor = new JTextField("", 12); //written that will be removed when clicked
	private final JTextField textPatient = new JTextField("", 12); //written that will be removed when clicked
	private final JTextField textData =  new JTextField("dd-mm-yyyy", 12);
	private final JTextArea textMotivation =  new JTextArea();
	    
	private final JButton registerBtn = new JButton("Registra");
	private final JButton homeBtn = new JButton("Home");
	    
	private static final double WIDTH_PERC_FRAME = 0.4;
	private static final double HEIGTH_PERC_FRAME = 0.4;
	private static final double WIDTH_MINIMUM_FRAME = WIDTH_PERC_FRAME / 0.7;
	private static final double HEIGTH_MINMUM_FRAME = HEIGTH_PERC_FRAME / 0.7;
	
	
	public InsAppoiViewImpl(ControllerInsAppoiObserver observer) {
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
        
        pWestInternal.add(new JLabel("Impiegato d'ufficio"), cnst); 
        pWestInternal.add(textEmployee, cnst);
        
        cnst.gridy++;

        pWestInternal.add(new JLabel("Dottore"), cnst); 
        pWestInternal.add(textDoctor, cnst);
        
        cnst.gridy++;
        
        pWestInternal.add(new JLabel("Paziente"), cnst); 
        pWestInternal.add(textPatient, cnst);

        cnst.gridy++;
        
        
       
        pWestInternal.add(new JLabel("Data di nascita"), cnst);
        pWestInternal.add(textData, cnst);
        
        
        cnst.gridy++;
        
        pWestInternal.add(new JLabel("Motivazione"), cnst);
        pWestInternal.add(textMotivation, cnst);
        textMotivation.setPreferredSize(new Dimension(250, 50));
        
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
            	String doctor = textDoctor.getText();
            	String motivation = textMotivation.getText();
            	String employee = textEmployee.getText();
       
        		observer.register(patient,doctor,date.get(),employee, motivation);
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
		this.frame.show();
		
	}
	@Override
	public void querySuccess() {
		JOptionPane.showMessageDialog(this.frame, "Query success", "", JOptionPane.INFORMATION_MESSAGE);
		
	}

	@Override
	public void queryError(SQLException e) {
		JOptionPane.showMessageDialog(this.frame,e.toString(), "", JOptionPane.ERROR_MESSAGE);
		
	}


	@Override
	public void appointmentDayComplete() {
		JOptionPane.showMessageDialog(this.frame, "Non puoi prenotare più di 10 appuntamenti per giorno", "", JOptionPane.INFORMATION_MESSAGE);
		
	}

}
