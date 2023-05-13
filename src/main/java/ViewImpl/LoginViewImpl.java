package ViewImpl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import ControllerInterface.ControllerLoginObserver;
import HospitalDb.LogAccount;
import ViewInterface.LoginView;

public class LoginViewImpl implements LoginView{
	
	ControllerLoginObserver observer;
	private final JFrame frame = new JFrame();
	

    private static final String TITLE_FRAME = "Login";
    private static final int HORIZONTAL = 350;
    private static final int VERTICAL = 200;
    public static final int SPACE = 5;
    public static final String TITLE = "Login Account";
    public static final String USERNAME_STRING = "Username:";
    public static final String PASSWORD_STRING = "Password:";
	
    private final JLabel title = new JLabel(TITLE);
    private final JLabel username = new JLabel(USERNAME_STRING);
    private final TextField textUsername = new TextField("Username", 12); //written that will be removed when clicked
    private final JLabel password = new JLabel(PASSWORD_STRING);
    private final JPasswordField textPassword = new JPasswordField("Password", 12); //password field + written that will be removed when clicked
    private final JButton login = new JButton("Login");

    
	private static final double WIDTH_PERC_FRAME = 0.2;
	private static final double HEIGTH_PERC_FRAME = 0.2;
	private static final double WIDTH_MINIMUM_FRAME = WIDTH_PERC_FRAME / 0.7;
	private static final double HEIGTH_MINMUM_FRAME = HEIGTH_PERC_FRAME / 0.7;
	
	public LoginViewImpl(ControllerLoginObserver observer) {
		
		this.observer = observer;
		
		final JPanel mainPanel = new JPanel(new BorderLayout());
		
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

        pWestInternal.add(username, cnst); 
        pWestInternal.add(textUsername, cnst);
        cnst.gridy++;

        pWestInternal.add(password, cnst);
        pWestInternal.add(textPassword, cnst);
        cnst.gridy++;

        final JPanel pWest = new JPanel(new FlowLayout());
        pWest.add(pWestInternal);

        final JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
        pSouth.add(login);
     
        login.addActionListener(e ->{
        	String user = textUsername.getText();
        	String pass = textPassword.getText();
        	System.out.print(user);
        	System.out.print(pass);
        	if(LogAccount.getAccountLogged().checkPassword(user, pass)) {
        		observer.showMenu();
        		//frame.setVisible(false);
        		frame.dispose();
        	}else {
        		JOptionPane.showMessageDialog(pWestInternal, "Wrong credential", "", JOptionPane.ERROR_MESSAGE);
        	}
        });

        mainPanel.add(pWest, BorderLayout.CENTER);
        mainPanel.add(pNorth, BorderLayout.NORTH);
        mainPanel.add(pSouth, BorderLayout.SOUTH);
        
        frame.add(mainPanel);

		//mainPanel.add(panel,BorderLayout.CENTER);
		final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setMinimumSize(new Dimension((int) (screenSize.getWidth() * WIDTH_MINIMUM_FRAME), (int) (screenSize.getHeight() * HEIGTH_MINMUM_FRAME)));

        frame.getContentPane().add(mainPanel);
        frame.setLocationByPlatform(true);
	}
	public void showLogin() {
		frame.setVisible(true);
	}
}
