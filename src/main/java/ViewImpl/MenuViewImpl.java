package ViewImpl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import ControllerInterface.ControllerHospitalObserver;
import HospitalDb.LogAccount;
import ViewInterface.MenuView;


public class MenuViewImpl implements MenuView {
	
	 private ControllerHospitalObserver observer;
	 private final JFrame frame = new JFrame();
	 //INSERIMENTI
	 private static final String BTN_CONTROLLER_INSERT_EMPLOYEE = "INSERIMENTO PERSONALE";
	 private static final String BTN_CONTROLLER_INSERT_PATIENT = "INSERIMENTO PAZIENTE";
	 private static final String BTN_CONTROLLER_INSERT_VISIT = "INSERIMENTO VISITA";
	 private static final String BTN_CONTROLLER_INSERT_PATOLOGY_PATIENT = "INSERIMENTO PATOLOGIA PAZIENTE";
	 private static final String BTN_CONTROLLER_INSERT_APPOINTMENT = "INSERIMENTO APPUNTAMENTO";
	 private static final String BTN_CONTROLLER_INSERT_BILL_PATIENT = "INSERIMENTO DEBITO PAZIENTE";;
	 
	 //VISUALIZZA
	 private static final String BTN_CONTROLLER_VIEW_ENTRY_EXIT_DOCTOR = "VISUALIZZA INGRESSI/USCITE DOTTORE";
	 private static final String BTN_CONTROLLER_VIEW_EXIT_DOCTOR = "VISUALIZZA USCITE DOTTORE";
	 private static final String BTN_CONTROLLER_VIEW_PATOLOGY_PATIENT = "VISUALIZZA PATOLOGIE PAZIENTI";
	 private static final String BTN_CONTROLLER_VIEW_APPOINTMENT = "VISUALIZZA APPUNTAMENTI";
	 private static final String BTN_CONTROLLER_VIEW_DEBT_PATIENT = "VISUALIZZA DEBITO PAZIENTE";
	 private static final String BTN_CONTROLLER_VIEW_REPORT_PATIENT = "VISUALIZZA REFERTO PAZIENTE";
	 private static final String BTN_CONTROLLER_VIEW_NEXT_APPOINTMENT = "VISUALIZZA PROSSIMO APPUNTAMENTO";
	 private static final String BTN_CONTROLLER_VIEW_DEBT_TOTAL = "VISUALIZZA DEBITO TOTALE";
	 
	 //ASSEGNAZIONE
	 private static final String BTN_CONTROLLER_ASSIGN = "ASSEGNAZIONE DIPENDENTE STANZA";

	 private static final String TITLE_FRAME = "Menu ospedale";
	 private static final String ACCOUNT_LOGGED_STRING = "Username logged: ";

	 private static final Color COLOR_BORDER_NORTH_PANEL = Color.BLACK;
	 private static final String MENU_STRING = "MENU";
	 private static final double PROPORTION_BUTTON = 1.5;

	 private static final double WIDTH_PERC_FRAME = 0.5;
	 private static final double HEIGTH_PERC_FRAME = 0.5;
	 private static final double WIDTH_MINIMUM_FRAME = WIDTH_PERC_FRAME / 0.7;
	 private static final double HEIGTH_MINMUM_FRAME = HEIGTH_PERC_FRAME / 0.7;

	 private final JButton btnControllerEmployee = new JButton(BTN_CONTROLLER_INSERT_EMPLOYEE);
	 private final JButton btnControllerPatient = new JButton(BTN_CONTROLLER_INSERT_PATIENT);
	 private final JButton btnControllerVisit = new JButton(BTN_CONTROLLER_INSERT_VISIT);
	 private final JButton btnControllerPatologyPatient = new JButton(BTN_CONTROLLER_INSERT_PATOLOGY_PATIENT);
	 private final JButton btnControllerAppointment = new JButton(BTN_CONTROLLER_INSERT_APPOINTMENT);
	 private final JButton btnControllerBillPatient = new JButton(BTN_CONTROLLER_INSERT_BILL_PATIENT);

	 private final JButton btnControllerViewEntryExit = new JButton(BTN_CONTROLLER_VIEW_ENTRY_EXIT_DOCTOR);
	 private final JButton btnControllerViewPatoPatie = new JButton(BTN_CONTROLLER_VIEW_PATOLOGY_PATIENT);
	 private final JButton btnControllerViewAppointment = new JButton(BTN_CONTROLLER_VIEW_APPOINTMENT);
	 private final JButton btnControllerViewDebtPat = new JButton(BTN_CONTROLLER_VIEW_DEBT_PATIENT);
	 private final JButton btnControllerViewReport = new JButton(BTN_CONTROLLER_VIEW_REPORT_PATIENT);
	 private final JButton btnControllerViewNextAppointment = new JButton(BTN_CONTROLLER_VIEW_NEXT_APPOINTMENT);
	 private final JButton btnControllerViewDebtTotal = new JButton(BTN_CONTROLLER_VIEW_DEBT_TOTAL);

	 
	 
	 
	 private final JButton btnControllerAssign = new JButton(BTN_CONTROLLER_ASSIGN);
	
	 
	 
	 private final JLabel labelAccount;
	
	 public MenuViewImpl() {
	
		 	String accountLogged = LogAccount.getAccountLogged().getNameAccountLogged();
		 
			 
			final JPanel mainPanel = new JPanel(new BorderLayout());
		    final JPanel centerPanel = new JPanel();
		    centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.Y_AXIS));
		    
		    final JPanel northPanel = new JPanel(new BorderLayout());
		    
		    JPanel insertPanel = new JPanel(new FlowLayout());
		    insertPanel.setBorder(new TitledBorder("INSERISCI"));
		    insertPanel.add(btnControllerEmployee);
		    insertPanel.add(btnControllerPatient);
		    insertPanel.add(btnControllerVisit);
		    insertPanel.add(btnControllerPatologyPatient);
		    insertPanel.add(btnControllerAppointment);
		    insertPanel.add(btnControllerBillPatient);
		    
		    JPanel viewPanel = new JPanel(new FlowLayout());
		    viewPanel.setBorder(new TitledBorder("VISUALIZZA"));
		    
		    viewPanel.add(btnControllerViewEntryExit);
		    viewPanel.add(btnControllerViewPatoPatie);
		    viewPanel.add(btnControllerViewAppointment);
		    viewPanel.add(btnControllerViewDebtPat);
		    viewPanel.add(btnControllerViewReport);
		    viewPanel.add(btnControllerViewNextAppointment);
		    viewPanel.add(btnControllerViewDebtTotal);
		    
		    JPanel assignPanel = new JPanel(new FlowLayout());
		    assignPanel.setBorder(new TitledBorder("ASSEGNAZIONE"));
		    

		    assignPanel.add(btnControllerAssign);

		    
		    centerPanel.add(insertPanel);
		    centerPanel.add(viewPanel);
		    centerPanel.add(assignPanel);
		    
		   
	       
	        
	        this.frame.setTitle(TITLE_FRAME);

	        btnControllerEmployee.setPreferredSize(this.increaseSize(btnControllerEmployee.getPreferredSize()));
	        btnControllerPatient.setPreferredSize(this.increaseSize(btnControllerPatient.getPreferredSize()));
	        btnControllerVisit.setPreferredSize(this.increaseSize(btnControllerVisit.getPreferredSize()));
	        btnControllerPatologyPatient.setPreferredSize(this.increaseSize(btnControllerPatologyPatient.getPreferredSize()));
	        btnControllerAppointment.setPreferredSize(this.increaseSize(btnControllerAppointment.getPreferredSize()));
	        btnControllerBillPatient.setPreferredSize(this.increaseSize(btnControllerBillPatient.getPreferredSize()));
	        
	        btnControllerViewEntryExit.setPreferredSize(this.increaseSize(btnControllerViewEntryExit.getPreferredSize()));
	    
	        btnControllerViewPatoPatie.setPreferredSize(this.increaseSize(btnControllerViewPatoPatie.getPreferredSize()));
	        btnControllerViewAppointment.setPreferredSize(this.increaseSize(btnControllerViewAppointment.getPreferredSize()));
	        btnControllerViewDebtPat.setPreferredSize(this.increaseSize(btnControllerViewDebtPat.getPreferredSize()));
	        btnControllerViewReport.setPreferredSize(this.increaseSize(btnControllerViewReport.getPreferredSize()));
	        btnControllerViewDebtTotal.setPreferredSize(this.increaseSize(btnControllerViewDebtTotal.getPreferredSize()));
	        
	        btnControllerViewNextAppointment.setPreferredSize(this.increaseSize(btnControllerViewNextAppointment.getPreferredSize()));
	        btnControllerAssign.setPreferredSize(this.increaseSize(btnControllerAssign.getPreferredSize()));
	   

	        
	        if("employee".equals(accountLogged)) {
	        	btnControllerEmployee.setEnabled(false);
	        	btnControllerPatologyPatient.setEnabled(false);
	        	btnControllerViewEntryExit.setEnabled(false);
	        	btnControllerViewPatoPatie.setEnabled(false);
	        	btnControllerViewReport.setEnabled(false);
	        	btnControllerAssign.setEnabled(false);
	      
	        }else if("doctor".equals(accountLogged)) {
	        	btnControllerEmployee.setEnabled(false);
	        	btnControllerBillPatient.setEnabled(false);
	        	btnControllerViewEntryExit.setEnabled(false);
	        	btnControllerViewDebtPat.setEnabled(false);
	        	btnControllerAssign.setEnabled(false);
	        	btnControllerViewDebtTotal.setEnabled(false);
	        	
	        }
	        
	        
	        this.labelAccount = new JLabel(ACCOUNT_LOGGED_STRING + accountLogged, SwingConstants.RIGHT);
	        labelAccount.setHorizontalAlignment(SwingConstants.CENTER);
	        labelAccount.setVerticalAlignment(SwingConstants.CENTER);

	        final JLabel labelMenu = new JLabel(MENU_STRING);
	        labelMenu.setHorizontalAlignment(SwingConstants.CENTER);
	        labelMenu.setVerticalAlignment(SwingConstants.CENTER);

	        northPanel.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_NORTH_PANEL));
	        northPanel.add(labelMenu, BorderLayout.CENTER);
	        northPanel.add(labelAccount, BorderLayout.EAST);

	        btnControllerEmployee.addActionListener(a -> {
	            this.observer.showControllerInsEmployee();
	            frame.setVisible(false);
	        });
	        btnControllerPatient.addActionListener(a -> {
	            this.observer.showControllerInsPatient();
	            frame.setVisible(false);
	        });
	        
	        btnControllerVisit.addActionListener(a -> {
	            this.observer.showControllerInsVisit();
	            frame.setVisible(false);
	        });
	        btnControllerPatologyPatient.addActionListener(a -> {
	            this.observer.showControllerInsPatPatient();
	            frame.setVisible(false);
	        });
	        btnControllerAppointment.addActionListener(a -> {
	            this.observer.showControllerInsAppointment();
	            frame.setVisible(false);
	        });
	        
	        btnControllerBillPatient.addActionListener(a -> {
	            this.observer.showControllerInsDebt();
	            frame.setVisible(false);
	        });
	        
	        //VISUALIZZA
	        btnControllerViewEntryExit.addActionListener(a -> {
	            this.observer.showControllerVisEntr();
	            frame.setVisible(false);
	        });
	        
	        btnControllerViewPatoPatie.addActionListener(a -> {
	            this.observer.showControllerVisPatPatient();
	            frame.setVisible(false);
	        });
	        btnControllerViewAppointment.addActionListener(a -> {
	            this.observer.showControllerVisAppointment();
	            frame.setVisible(false);
	        });
	        btnControllerViewDebtPat.addActionListener(a -> {
	            this.observer.showControllerVisDebtPatient();
	            frame.setVisible(false);
	        });
	        btnControllerViewReport.addActionListener(a -> {
	            this.observer.showControllerVisReport();
	            frame.setVisible(false);
	        });
	        btnControllerViewNextAppointment.addActionListener(a -> {
	            this.observer.showControllerVisNextApp();
	            frame.setVisible(false);
	        });
	        btnControllerAssign.addActionListener(a -> {
	            this.observer.showControllerAssign();
	            frame.setVisible(false);
	        });
	        btnControllerViewDebtTotal.addActionListener(a -> {
	            this.observer.showControllerDebtTotal();
	            frame.setVisible(false);
	        });
	        
	        
	        mainPanel.add(northPanel, BorderLayout.NORTH);
	        mainPanel.add(centerPanel, BorderLayout.CENTER);
	        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        this.frame.setMinimumSize(new Dimension((int) (screenSize.getWidth() * WIDTH_MINIMUM_FRAME), (int) (screenSize.getHeight() * HEIGTH_MINMUM_FRAME)));

	        frame.getContentPane().add(mainPanel);
	        frame.setLocationByPlatform(true);
	 }

	@Override
	public void setObserver(ControllerHospitalObserver observer) {
		this.observer = observer;	
	}

	@Override
	public void show() {
		 frame.setVisible(true);
		
	}
	private Dimension increaseSize(final Dimension dimension) {
        return new Dimension((int) (dimension.getWidth() * PROPORTION_BUTTON), (int) (dimension.getHeight() * PROPORTION_BUTTON));
    }
	
}
