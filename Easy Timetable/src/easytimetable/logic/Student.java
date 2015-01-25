package easytimetable.logic;

import java.util.ArrayList;

import easytimetable.database.SlotDB;
import easytimetable.database.SlotData;
import easytimetable.database.TimeTable;
import easytimetable.database.TimeTableDB;

public class Student {
	
	public int year, division;

	/*
	 * GUI
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/*
	 * GUI
	 */
	public void setDivision(int division) {
		this.division = division;
	}
	
	public interface StudentInterface {
		void getTT(ArrayList<SlotData> list);
	}
	
	public TimeTable getStudentTimetable() {
		TimeTable tt = TimeTableDB.getTimeTable(year, division);
		return tt;
	}
	

}
