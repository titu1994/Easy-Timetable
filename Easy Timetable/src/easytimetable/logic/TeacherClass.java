package easytimetable.logic;

import java.util.ArrayList;

import easytimetable.database.SlotDB;
import easytimetable.database.SlotData;
import easytimetable.database.SubjectData;
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
	
	public ArrayList<SlotData> getTimetable() {
		ArrayList<SlotData> dat = SlotDB.getSlotData();
		TeacherData[] ts;
		TeacherData teacher = null;
		SubjectData subs[];
		SubjectData[] tempSubs;
		
		ArrayList<SlotData> list = new ArrayList<>();
		
		for(SlotData s : dat) {
			ts = s.teachers;
			
			for(TeacherData t : ts) {
				if(t.tid == id) {
					teacher = t;
					break;
				}
				else {
					teacher = null;
				}
			}
			if(teacher == null)
				continue;
			
			subs = teacher.subjects;
			
			for(SlotData slot : dat) {
				tempSubs = slot.subs;
				for(SubjectData tempTS : subs) {
					for(SubjectData tempSub : tempSubs) {
						if(tempTS.name.equals(tempSub.name)) {
							list.add(slot);
							break;
						}
					}
				}
			}
		}
		
		return list;
	}

}
