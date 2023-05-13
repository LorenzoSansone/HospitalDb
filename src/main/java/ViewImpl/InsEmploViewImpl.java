package ViewImpl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ControllerInterface.ControllerInsEmplo;
import ControllerInterface.ControllerInsEmploObserver;
import HospitalDb.Utils;
import ViewInterface.InsEmploView;

public class InsEmploViewImpl implements InsEmploView {
	final ControllerInsEmploObserver observer;
	
	JFrame frame = new JFrame();
	private static final String TITLE_FRAME = "Inserimento dipendenti";
	private static final int HORIZONTAL = 350;
	private static final int VERTICAL = 200;
	public static final int SPACE = 5;
	public static final String TITLE = "Inserire i dati del nuovo dipendente";
 	String[] gender = new String[] {"Maschio","Femmina"};
 	String[] typeEmployee = new String[] {"Ufficio","Dottore","Infermiere","Pulizie"};
	   
	    
	private final JLabel title = new JLabel(TITLE);
	
	private final JTextField textName = new JTextField("", 12); //written that will be removed when clicked
	private final JTextField textSurname = new JTextField("", 12); //written that will be removed when clicked
	private final JTextField textCF = new JTextField("", 12); //written that will be removed when clicked
	private final JTextField textData =  new JTextField("dd-mm-yyyy", 12);
	private final JTextField textTel =  new JTextField("", 12);
	private final JTextField textMail =  new JTextField("", 12);
	private final JTextField textIban =  new JTextField("", 12);
	private final JTextField textGender =  new JTextField("M/F", 12);
	private final JTextField textDepartment =  new JTextField("", 12);
	
	private final JComboBox<String> comboEmployee =  new JComboBox<>(typeEmployee);
	    
	private final JButton registerBtn = new JButton("Registra");
	private final JButton homeBtn = new JButton("Home");
	    
	private static final double WIDTH_PERC_FRAME = 0.4;
	private static final double HEIGTH_PERC_FRAME = 0.4;
	private static final double WIDTH_MINIMUM_FRAME = WIDTH_PERC_FRAME / 0.7;
	private static final double HEIGTH_MINMUM_FRAME = HEIGTH_PERC_FRAME / 0.7;
	
	public InsEmploViewImpl(ControllerInsEmploObserver observer) {
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
        
        pWestInternal.add(new JLabel("CodiceFiscale"), cnst); 
        pWestInternal.add(textCF, cnst);
        
        cnst.gridy++;

        pWestInternal.add(new JLabel("Nome"), cnst); 
        pWestInternal.add(textName, cnst);
        
        cnst.gridy++;
        
        pWestInternal.add(new JLabel("Cognome"), cnst); 
        pWestInternal.add(textSurname, cnst);

        cnst.gridy++;
        
     
        
        pWestInternal.add(new JLabel("Data di nascita"), cnst);
        pWestInternal.add(textData, cnst);
        
        
        cnst.gridy++;
        
        pWestInternal.add(new JLabel("Genere"), cnst);
        pWestInternal.add(textGender, cnst);
         
        
        cnst.gridy++;
        
        pWestInternal.add(new JLabel("Telefono *"), cnst);
        pWestInternal.add(textTel, cnst);
         
        
        cnst.gridy++;
        
        pWestInternal.add(new JLabel("Mail *"), cnst);
        pWestInternal.add(textMail, cnst);

        
        cnst.gridy++;
        
        pWestInternal.add(new JLabel("Tipologia impiegato"), cnst);
        pWestInternal.add(comboEmployee, cnst);
        
         
        cnst.gridy++;
        
        pWestInternal.add(new JLabel("Iban"), cnst);
        pWestInternal.add(textIban, cnst);
         
        cnst.gridy++;
        
        pWestInternal.add(new JLabel("Reparto"), cnst);
        pWestInternal.add(textDepartment, cnst);
        textDepartment.setEnabled(false);
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
        		String cf = textCF.getText();
            	String name = textName.getText();
            	String surname = textSurname.getText();
            	String gender = textGender.getText();
            	String tel = textTel.getText();
            	String mail = textMail.getText();
            	String typeEmplo = comboEmployee.getSelectedItem().toString();
            	String iban = textIban.getText();
            	String department = textDepartment.getText();
        		
        		observer.register(cf,name,surname,date.get(),gender,tel,mail,typeEmplo,iban, department);
        	}
        });
        homeBtn.addActionListener(e -> {
        	observer.backToMenu();
        	//frame.setVisible(false);
        	frame.dispose();
        });
        
        comboEmployee.addItemListener(event -> {
            if (event.getStateChange() == ItemEvent.SELECTED) {
            	if(comboEmployee.getSelectedItem().toString().equals("Dottore")) {
            		textDepartment.setEnabled(true);
            	}else {
            		textDepartment.setEnabled(false);
            	}
            }
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
		
		this.frame.setVisible(true);
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
