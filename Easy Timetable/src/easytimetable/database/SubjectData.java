package easytimetable.database;

public class SubjectData {

	public final String name;
	public boolean isAvailable = true;
	
	public SubjectData(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		SubjectData s = (SubjectData) obj;
		return name.equalsIgnoreCase(s.name);
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}

}
