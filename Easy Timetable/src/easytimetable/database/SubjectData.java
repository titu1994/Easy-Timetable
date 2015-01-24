package easytimetable.database;

public class SubjectData {

	public final String name;
	
	public SubjectData(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		return name.equals(obj);
	}
	
}
