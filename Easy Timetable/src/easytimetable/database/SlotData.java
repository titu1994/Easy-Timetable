package easytimetable.database;


public class SlotData {
	
	public TeacherData[] teachers;
	public SubjectData[] subs;
	private static int count = 1;
	public int no;
	
	public SlotData(TeacherData[] teachers, SubjectData[] subs) {
		this.teachers = teachers;
		this.subs = subs;
		this.no = count++;
	}
	
	@Override
	public boolean equals(Object obj) {
		SlotData s = (SlotData) obj;
		return teachers[0].tid == s.teachers[0].tid;
	}

}
