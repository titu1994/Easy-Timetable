package easytimetable.logic;

import java.util.ArrayList;

import javax.security.auth.Subject;

import easytimetable.database.SubjectDB;
import easytimetable.database.SubjectData;
import easytimetable.database.TeacherDB;
import easytimetable.database.TeacherData;

public class AdminClass {
	
	public void addTeacher(String name, ArrayList<Subject> subs) {
		Subject[] subjects = subs.toArray(new Subject[subs.size()]);
		TeacherData t = new TeacherData(name, subjects);
		TeacherDB.addTeacher(t);
	}
	
	public void removeTeacher(int id) {
		TeacherDB.deleteTeacher(id);
	}
	
	public void updateTeacher(int id, ArrayList<Subject> subs) {
		Subject[] subjects = subs.toArray(new Subject[subs.size()]);
		TeacherData t = TeacherDB.getTeacherData(id);
		t.subjects = subjects;
		TeacherDB.updateTeacher(t);
	}
	
	public void updateTeacher(int id, boolean isAvailable) {
		TeacherData t = TeacherDB.getTeacherData(id);
		t.isAvailable = isAvailable;
		TeacherDB.updateTeacher(t);
	}
	
	public void addSubject(String name) {
		SubjectData data = new SubjectData(name);
		SubjectDB.addSubject(data);
	}
	
	public void deleteSubject(String name) {
		SubjectDB.deleteSubject(name);
	}

	
	
}
