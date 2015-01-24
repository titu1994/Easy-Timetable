package easytimetable.database;

public class TeacherData {
	
	private static int id = 1;
	public int tid;
	public final String name;
	public SubjectData[] subjects;
	public boolean isAvailable = true;
	
	public TeacherData(String name, SubjectData[] subjects) {
		this.tid = id++;
		this.name = name;
		this.subjects = subjects;
	}


	public static void setId(int id) {
		TeacherData.id = id;
	}

	public static int getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		TeacherData t = (TeacherData) obj;
		return this.tid == t.tid;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

}
