package easytimetable.logic;

import java.util.ArrayList;

import easytimetable.database.Admin;
import easytimetable.database.AdminDB;
import easytimetable.database.SlotDB;
import easytimetable.database.SlotData;
import easytimetable.database.SubjectDB;
import easytimetable.database.SubjectData;
import easytimetable.database.TeacherDB;
import easytimetable.database.TeacherData;
import easytimetable.database.TimeTable;
import easytimetable.database.TimeTableDB;

public class AdminClass {

	private int breakList[];
	private ArrayList<TeacherData> teacherList;
	private ArrayList<SubjectData> subjectList;
	private ArrayList<SlotData> slotList;

	private int subPointer;
	private int slotCount;
	private int year;
	private int maxTeacher;
	private int division;

	private String teacherPassword;
	private String adminUsername, adminPassword;

	public interface AdminInterface {
		public void notifyTeacherUnavailable();
		public void notifyAdmin(boolean isValid);
	}
	
	/*
	 * GUI 
	 */
	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}
	
	/*
	 * GUI 
	 */
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
	public void checkAdmin() {
		Admin a = AdminDB.getAdminData();
		if(a.getPassword().equals(adminPassword) && a.userName.equals(adminUsername)) {
			if(interf != null)
				interf.notifyAdmin(true);
		}
		else {
			if(interf != null) 
				interf.notifyAdmin(false);
		}
	}

	/*
	 * GUI
	 */
	public void setTeacherPassword(String password) {
		this.teacherPassword = password;
	}
	
	public void addTeacher(String name, ArrayList<TeacherData> subs, int grade) {
		SubjectData[] subjects = subs.toArray(new SubjectData[subs.size()]);
		TeacherData t = new TeacherData(name, subjects, teacherPassword, grade);
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

	/**
	 * GUI Break list is added from GUI here
	 */
	public void setBreakList(int... breaks) {
		breakList = breaks;
	}

	/**
	 * Creates the timetable
	 */
	public void createTimeTable() {
		teacherList = TeacherDB.getTeacherData();

		slotList = SlotDB.getSlotData();
		slotList.clear();
		slotCount = SlotDB.getSlotCount();

		calculateTimeTable();

		SlotDB.storeSlot(slotList);
	}

	/*
	 * GUI calls this function to set the checked subjects from the checked
	 * list.
	 */
	public void setSubjectList(ArrayList<SubjectData> subs) {
		this.subjectList = subs;
	}

	/*
	 * GUI sets the year in this function
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/*
	 * GUI sets the max no of teachers in a room at a time.
	 */
	public void setMaxNoOfTeachersInClassRoom(int maxTeacher) {
		this.maxTeacher = maxTeacher;
	}

	/*
	 * GUI sets the division
	 */
	public void setDivisin(int division) {
		this.division = division;
	}

	public void setSlotCount(int slotCount) {
		this.slotCount = slotCount;
		SlotDB.storeSlotCount(slotCount);
	}

	public boolean isTeacherAvailable(SlotData data) {
		boolean test = true;
		for (TeacherData t : data.teachers) {
			test = test & t.isAvailable;
		}
		return test;
	}

	public boolean isSubjectAvailable(SlotData data) {
		boolean test = true;
		for (SubjectData s : data.subs) {
			test = test & s.isAvailable;
		}
		return test;
	}

	public boolean isSlotBreak(int no) {
		for (int x : breakList) {
			if (x == no) {
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
		return isTeacherAvailable(slot) & isSubjectAvailable(slot)
				&& !isSlotBreak(slot.no);
	}

	/*
	 * Main functionality
	 */
	private void calculateTimeTable() {
		SlotData sData = null;
		for (int day = 0; day < 5; day++) {
			for (int slot = 1; slot <= slotCount; slot++) {
				if (isSlotBreak(slot)) {
					sData = new SlotData(null, null);
					sData.isBreak = true;
				} else {
					sData = calculateSlot(slot);
				}

				slotList.add(sData);
			}
		}
		TimeTable tt = new TimeTable(slotList, maxTeacher, year, slotCount, division);
		TimeTableDB.storeTimeTable(tt);
	}


	

	public AdminInterface interf;

	public void setInterf(AdminInterface interf) {
		this.interf = interf;
	}

	private SlotData calculateSlot(int slotNo) {
		TeacherData teachers[] = new TeacherData[maxTeacher];
		SubjectData sublist[] = subjectList.toArray(new SubjectData[subjectList.size()]);
		ArrayList<TeacherData> al;
		SlotData soda;
		SubjectData[] subArr = new SubjectData[maxTeacher];
		int pointer = subPointer; 

		for (int i = 0; i < maxTeacher; i++) {
			al = TeacherDB.getTeachersWhoTeachSubject(teacherList, sublist[subPointer]);

			if (al.size() == 0) {
				// Alert via GUI that teachers unavailable
				if(interf != null) {
					interf.notifyTeacherUnavailable();
				}
			}

			for (TeacherData t : al) {
				if (t.isAvailable == true && t.grade == year) {
					teachers[i] = t;
					TeacherDB.availableChange(t, false);
					break;
				}

			}

			subPointer = (subPointer + 1) % sublist.length;
		}


		for(int i = 0; i < maxTeacher; i++) {
			subArr[i] = sublist[pointer];
			pointer = (pointer + 1) % maxTeacher;
		}

		soda = new SlotData(teachers, subArr);
		soda.no = slotNo;

		for(int i = 0; i < teachers.length; i++) {
			TeacherDB.availableChange(teachers[i], true);
		}

		return soda;
	}

}
