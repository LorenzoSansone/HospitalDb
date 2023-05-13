package ControllerInterface;

import java.util.Date;

public interface ControllerInsVisitObserver {
	void backToMenu();
	void register(String doctor, String patient, String ambulatory, Date date, String motivation, String result, String medicine);
}
