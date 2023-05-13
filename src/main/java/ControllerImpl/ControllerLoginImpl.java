package ControllerImpl;

import ControllerInterface.ControllerHospitalObserver;
import ControllerInterface.ControllerLogin;
import ControllerInterface.ControllerLoginObserver;
import ViewImpl.LoginViewImpl;
import ViewInterface.LoginView;

public class ControllerLoginImpl implements ControllerLogin, ControllerLoginObserver{
	ControllerHospitalObserver observer;
	LoginView view;
	
	
	public ControllerLoginImpl(ControllerHospitalObserver observer) {
		this.observer = observer;
		this.view = new LoginViewImpl(this);
	}
	
	public void show() {
		view.showLogin();
	}

	@Override
	public void showMenu() {
		observer.showMenu();
		
	}
}
