package easytimetable.database;

import java.util.ArrayList;

public class TimeTable {
	
	public ArrayList<SlotData> slots;
	public int year;
	
	public int maxTeacher;
	public int slotCount;
	public int division;
	
	public TimeTable(ArrayList<SlotData> slots, int maxTeacher, int year, int slotCount, int division) {
		this.slots = slots;
		this.maxTeacher = maxTeacher;
		this.year = year;
		this.slotCount = slotCount;
		this.division = division;
	}

}
