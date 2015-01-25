package easytimetable.database;

import java.util.Arrays;

public class TeacherData {
	
	private static int id = 1;
	public int tid;
	public final String name;
	public SubjectData[] subjects;
	public boolean isAvailable = true;
	
	public String  password;
	
	public TeacherData(String name, SubjectData[] subjects, String password) {
		this.tid = id++;
		this.name = name;
		this.subjects = subjects;
		this.password = Arrays.toString(password.getBytes());
	}

	public String getPassword() {
		String arr[] = password.split(",");
		byte[] bytes = new byte[arr.length];
		for(int i = 0; i < bytes.length; i++) {
			bytes[i] = Byte.parseByte(arr[i]);
		}
		String pass = new String(bytes);
		return pass;
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
