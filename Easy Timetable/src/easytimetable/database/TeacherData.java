package easytimetable.database;

import javax.security.auth.Subject;


public class TeacherData {
	
	private static int id = 1;
	public int tid;
	public final String name;
	public final Subject[] subjects;
	public boolean isAvailable = true;
	
	public TeacherData(String name, Subject[] subjects) {
		this.tid = id++;
		this.name = name;
		this.subjects = subjects;
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
