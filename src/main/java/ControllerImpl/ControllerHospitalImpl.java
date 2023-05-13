package ControllerImpl;

import ControllerInterface.ControllerAssignEmplo;
import ControllerInterface.ControllerHospital;
import ControllerInterface.ControllerHospitalObserver;
import ControllerInterface.ControllerInsAppoi;
import ControllerInterface.ControllerInsDebt;
import ControllerInterface.ControllerInsEmplo;
import ControllerInterface.ControllerInsPatPatient;
import ControllerInterface.ControllerInsPatient;
import ControllerInterface.ControllerInsVisit;
import ControllerInterface.ControllerLogin;
import ControllerInterface.ControllerVisAppoi;
import ControllerInterface.ControllerVisDebtPatient;
import ControllerInterface.ControllerVisDebtTotal;
import ControllerInterface.ControllerVisEntr;
import ControllerInterface.ControllerVisNextApp;
import ControllerInterface.ControllerVisPatPatient;
import ControllerInterface.ControllerVisReport;
import ViewImpl.MenuViewImpl;
import ViewInterface.MenuView;

public class ControllerHospitalImpl implements ControllerHospital,ControllerHospitalObserver {
	
	
	private MenuView menu;
	
	public ControllerHospitalImpl() {
		
		ControllerLogin log = new ControllerLoginImpl(this);
		log.show();
		
	}
	
	public void showMenu() {
		this.menu = new MenuViewImpl();
		this.menu.setObserver(this);
		this.menu.show();
	}

	@Override
	public void showControllerInsPatient() {
		ControllerInsPatient contrInsPatient = new ControllerInsPatientImpl(this);
		
		
	}
	public void showControllerInsEmployee() {
		ControllerInsEmplo contrInsEmplo = new ControllerInsEmploImpl(this);
		
	}
	public void showControllerInsVisit() {
		ControllerInsVisit contrInsVisit = new ControllerInsVisitImpl(this);
	}
	public void showControllerInsPatPatient() {
		ControllerInsPatPatient contrInsPatPatient = new ControllerInsPatPatientImpl(this);
		
	}
	public void showControllerInsAppointment() {
		ControllerInsAppoi contrInsAppoi = new ControllerInsAppoiImpl(this);
		
	}
	public void showControllerInsDebt() {
		ControllerInsDebt contrInsDebt = new ControllerInsDebtImpl(this);
		
	}
	public void showControllerVisEntr() {
		ControllerVisEntr contrVisEntr = new ControllerVisEntrImpl(this);
	}
	public void showControllerVisPatPatient() {
		ControllerVisPatPatient contrVisPatPatient = new ControllerVisPatPatientImpl(this);
	}
	public void showControllerVisAppointment() {
		ControllerVisAppoi contrVisAppo = new ControllerVisAppoiImpl(this);
	}
	public void showControllerVisDebtPatient() {
		ControllerVisDebtPatient contrVisDebt = new ControllerVisDebtPatientImpl(this);
	}
	public void showControllerVisReport() {
		ControllerVisReport contrVisReport = new ControllerVisReportImpl(this);
	}
	public void showControllerVisNextApp() {
		ControllerVisNextApp contrVisNextApp = new ControllerVisNextAppImpl(this);
	}
	public void showControllerAssign() {
		ControllerAssignEmplo contrAssign = new ControllerAssignEmploImpl(this);
	}
	public void showControllerDebtTotal() {
		ControllerVisDebtTotal contrDebtTotal = new ControllerVisDebtTotalImpl(this);
	}
}
