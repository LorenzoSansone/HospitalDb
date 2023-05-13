package ViewImpl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ControllerInterface.ControllerInsPatientObserver;
import HospitalDb.LogAccount;
import HospitalDb.Utils;
import ViewInterface.InsPatientView;

public class InsPatientViewImpl implements InsPatientView{
	ControllerInsPatientObserver observer;
	JFrame frame = new JFrame();
	 private static final String TITLE_FRAME = "Inserimento paziente";
	    private static final int HORIZONTAL = 350;
	    private static final int VERTICAL = 200;
	    public static final int SPACE = 5;
	    public static final String TITLE = "Inserire i dati del nuovo paziente";
	    public static final String NAME_STRING = "Nome:";
	    public static final String PASSWORD_STRING = "Password:";
		String[] gender = new String[] {"Maschio","Femmina"};

	   
	    
	    private final JLabel title = new JLabel(TITLE);
	    
	    private final JTextField textName = new JTextField("", 12); //written that will be removed when clicked
	    private final JTextField textSurname = new JTextField("", 12); //written that will be removed when clicked
	    private final JTextField textCF = new JTextField("", 12); //written that will be removed when clicked
	    private final JTextField textData =  new JTextField("dd-mm-yyyy", 12);
	    private final JTextField textTel =  new JTextField("", 12);
	    private final JTextArea textDesc =  new JTextArea();
	    private final JTextField textDebt =  new JTextField("", 12);
	    private final JTextField textGender =  new JTextField("M/F", 12);
	    private final JTextField textRoom =  new JTextField("", 12);
	    
	    private final JButton registerBtn = new JButton("Registra");
	    private final JButton homeBtn = new JButton("Home");
	    
		private static final double WIDTH_PERC_FRAME = 0.4;
		private static final double HEIGTH_PERC_FRAME = 0.4;
		private static final double WIDTH_MINIMUM_FRAME = WIDTH_PERC_FRAME / 0.7;
		private static final double HEIGTH_MINMUM_FRAME = HEIGTH_PERC_FRAME / 0.7;
	
	
	public InsPatientViewImpl(ControllerInsPatientObserver observer) {
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
        
        pWestInternal.add(new JLabel("Telefono"), cnst);
        pWestInternal.add(textTel, cnst);
         
        
        cnst.gridy++;
        
        pWestInternal.add(new JLabel("Descrizione salute"), cnst);
        pWestInternal.add(textDesc, cnst);

        textDesc.setPreferredSize(new Dimension(250, 50));
         
        cnst.gridy++;
        
        pWestInternal.add(new JLabel("Debito paziente"), cnst);
        pWestInternal.add(textDebt, cnst);
         
        cnst.gridy++;
        
        pWestInternal.add(new JLabel("Stanza ricovero *"), cnst);
        pWestInternal.add(textRoom, cnst);
         
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
        		String name = textName.getText();
            	String surname = textSurname.getText();
            	String cf = textCF.getText();
            	String tel = textTel.getText();
            	String gender = textGender.getText();
            	String room = textRoom.getText();
            	String debt = textDebt.getText();
            	String desc = textDesc.getText();
        		observer.register(cf,name,surname,date.get(),gender,tel,room,desc,debt);
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
