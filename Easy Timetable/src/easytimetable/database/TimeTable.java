package easytimetable.database;

import java.util.ArrayList;

public class TimeTable {
	
	public ArrayList<SlotData> slots;
	public int year;
	
	public TimeTable(ArrayList<SlotData> slots, int year) {
		this.slots = slots;
		this.year = year;
	}

}
