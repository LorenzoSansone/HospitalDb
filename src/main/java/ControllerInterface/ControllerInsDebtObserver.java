package ControllerInterface;

public interface ControllerInsDebtObserver {
	void backToMenu();
	void register(String patient, java.util.Date date, String total ,String description, String state);
}
