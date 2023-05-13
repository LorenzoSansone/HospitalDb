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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ControllerInterface.ControllerAssignEmploObserver;
import HospitalDb.Utils;
import ViewInterface.InsAssignEmploView;

public class InsAssignEmploViewImpl implements InsAssignEmploView{
	
	ControllerAssignEmploObserver observer;
	JFrame frame = new JFrame();
	private static final String TITLE_FRAME = "Inserimento dipendente stanza";
	  
	public static final int SPACE = 5;
	public static final String TITLE = "Inserimento di un dipendente ad una stanza";

	String[] typeEmployee = new String[] {"Infermiera","Pulizie"};

	private final JLabel title = new JLabel(TITLE);
	    
	private final JTextField textEmployee = new JTextField("", 12); //written that will be removed when clicked
	private final JComboBox<String> comboTypeEmployee =  new JComboBox<>(typeEmployee);
	private final JTextField textRoom =  new JTextField("", 12);
	  
	private final JButton registerBtn = new JButton("Registra");
	private final JButton homeBtn = new JButton("Home");
	  
	private static final double WIDTH_PERC_FRAME = 0.4;
	private static final double HEIGTH_PERC_FRAME = 0.4;
	private static final double WIDTH_MINIMUM_FRAME = WIDTH_PERC_FRAME / 0.7;
	private static final double HEIGTH_MINMUM_FRAME = HEIGTH_PERC_FRAME / 0.7;
	
	public InsAssignEmploViewImpl(ControllerAssignEmploObserver observer) {
		this.observer = observer;
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
        
        pWestInternal.add(new JLabel("Dipendente"), cnst); 
        pWestInternal.add(textEmployee, cnst);
        
        cnst.gridy++;

        pWestInternal.add(new JLabel("Tipologia dipendente"), cnst); 
        pWestInternal.add(comboTypeEmployee, cnst);
        
        cnst.gridy++;
        
        pWestInternal.add(new JLabel("Stanza"), cnst); 
        pWestInternal.add(textRoom, cnst);

        cnst.gridy++;
        
      
        
        final JPanel pWest = new JPanel(new FlowLayout());
        pWest.add(pWestInternal);

        final JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
        pSouth.add(registerBtn);
     
        registerBtn.addActionListener(e ->{
        	String empl = textEmployee.getText();
        	String type = comboTypeEmployee.getSelectedItem().toString();
        	String room = textRoom.getText();

        	observer.register(empl,room,type);
        	
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
		this.frame.setVisible(true);
		
	}
	@Override
	public void queryError(SQLException e) {
		JOptionPane.showMessageDialog(this.frame,e.toString(), "", JOptionPane.ERROR_MESSAGE);
		
	}
	@Override
	public void querySuccess() {
		JOptionPane.showMessageDialog(this.frame, "Query success", "", JOptionPane.INFORMATION_MESSAGE);
		
	}

}
