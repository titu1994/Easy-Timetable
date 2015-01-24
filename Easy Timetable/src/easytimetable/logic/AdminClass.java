package easytimetable.logic;

import java.util.ArrayList;

import javax.security.auth.Subject;

import easytimetable.database.SlotDB;
import easytimetable.database.SlotData;
import easytimetable.database.SubjectDB;
import easytimetable.database.SubjectData;
import easytimetable.database.TeacherDB;
import easytimetable.database.TeacherData;

public class AdminClass {

	private int breakList[];
	private ArrayList<TeacherData> teacherList;
	private ArrayList<SubjectData> subjectList;
	private ArrayList<SlotData> slotList;
	
	private int slotCount;

	public void addTeacher(String name, ArrayList<TeacherData> subs) {
		SubjectData[] subjects = subs.toArray(new SubjectData[subs.size()]);
		TeacherData t = new TeacherData(name, subjects);
		TeacherDB.addTeacher(t);
	}

	public void removeTeacher(int id) {
		TeacherDB.deleteTeacher(id);
	}

	public void updateTeacher(int id, ArrayList<TeacherData> subs) {
		SubjectData[] subjects = subs.toArray(new SubjectData[subs.size()]);
		TeacherData t = TeacherDB.getTeacherData(id);
		t.subjects = subjects;
		TeacherDB.updateTeacher(t);
	}

	public void updateTeacher(int id, boolean isAvailable) {
		TeacherData t = TeacherDB.getTeacherData(id);
		t.isAvailable = isAvailable;
		TeacherDB.updateTeacher(t);
	}

	public void addSubject(String name) {
		SubjectData data = new SubjectData(name);
		SubjectDB.addSubject(data);
	}

	public void deleteSubject(String name) {
		SubjectDB.deleteSubject(name);
	}

	public void setBreakLimit(int... breaks) {
		breakList = breaks;
	}

	/**
	 * Creates the timetable
	 */
	public void createTimeTable() {
		teacherList = TeacherDB.getTeacherData();
		subjectList = SubjectDB.getSubjectData();
		
		slotList = SlotDB.getSlotData();
		slotList.clear();
		slotCount = SlotDB.getSlotCount();

		calculateTimeTable();
		
		SlotDB.storeSlot(slotList);
	}
	
	public void setSlotCount(int slotCount) {
		this.slotCount = slotCount;
		SlotDB.storeSlotCount(slotCount);
	}

	public boolean isTeacherAvailable(SlotData data) {
		boolean test = true;
		for(TeacherData t : data.teachers) {
			test = test & t.isAvailable;
		}
		return test;
	}

	public boolean isSubjectAvailable(SlotData data) {
		boolean test = true;
		for(SubjectData s : data.subs) {
			test = test & s.isAvailable;
		}
		return test;
	}

	public boolean isSlotBreak(SlotData slot) {
		for(int x : breakList) {
			if(x == slot.no) {
				slot.isBreak = true;
				return true;
			}
		}
		return false;
	}
	
	public void setSchoolName(String name) {
		SlotDB.storeSchoolName(name);
	}

	public String getSchoolName() {
		return SlotDB.getSchoolName();
	}

	/*
	 * Checks if the slot is available
	 */
	public boolean isSlotAvailable(SlotData slot) {
		return isTeacherAvailable(slot) & isSubjectAvailable(slot) && !isSlotBreak(slot);
	}
	
	/*
	 * Main functionality
	 */
	private void calculateTimeTable() {
		for(int day = 0; day < 6; day++) {
			for(int slot = 1; slot <= slotCount; slot++) {
				
			}
		}
	}

} 
