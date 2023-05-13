package ControllerInterface;

public interface ControllerInsPatientObserver {
	void backToMenu();
	void register(String cf, String name, String surname, java.util.Date date, String gender, String tel, String room, String desc, String debt);
}
