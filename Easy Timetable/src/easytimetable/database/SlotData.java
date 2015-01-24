package easytimetable.database;


public class SlotData {

	private static int count = 1;
	public TeacherData[] teachers;
	public SubjectData[] subs;
	public int no;
	public boolean isBreak;
	
	public SlotData(TeacherData[] teachers, SubjectData[] subs) {
		this.teachers = teachers;
		this.subs = subs;
		this.no = (count % SlotDB.getSlotCount()) + 1;
		count++;
	}
	
	@Override
	public boolean equals(Object obj) {
		SlotData s = (SlotData) obj;
		return teachers[0].tid == s.teachers[0].tid;
	}
	
}
