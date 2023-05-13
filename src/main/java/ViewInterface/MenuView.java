package ViewInterface;

import ControllerInterface.ControllerHospitalObserver;

public interface MenuView {
	void setObserver(ControllerHospitalObserver observer);
	void show();
	
}
