package easytimetable.logic;

import java.util.ArrayList;

import easytimetable.database.SlotDB;
import easytimetable.database.SlotData;
import easytimetable.database.TeacherDB;
import easytimetable.database.TeacherData;

public class TeacherClass {

	private String password;
	private int id;

	/*
	 * GUI
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * GUI
	 */
	public void setId(int id) {
		this.id = id;
	}

	public interface TeacherInterface {
		void isValidTeacher(boolean isValid);
		ArrayList<SlotData> getTeacherTT();
	}
	
	public TeacherInterface inf;
	
	public void setInf(TeacherInterface inf) {
		this.inf = inf;
	}

	public void isTeacherValid() {
		TeacherData t = TeacherDB.getTeacherData(id);
		if(t.tid == id && t.getPassword().equals(password)) {
			if(inf != null)
				inf.isValidTeacher(true);
		}
		else {
			if(inf != null)
				inf.isValidTeacher(false);
		}
	}
	
	public void getTimetable() {
		ArrayList<SlotData> dat = SlotDB.getSlotData();
		TeacherData[] ts;
		for(SlotData s : dat) {
			
		}
	}

}
