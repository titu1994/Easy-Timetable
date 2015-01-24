package easytimetable.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SubjectDB {
	
	private static ArrayList<SubjectData> list;
	
	public SubjectDB() {
		list = new ArrayList<>();
	}
	
	public void storeSubjectsData(ArrayList<SubjectData> list) {
		SubjectDB.list = list;
		Gson g = new Gson();
		File f = new File("db/subjectDB.txt");
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		String data = g.toJson(list);
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(f), true);
			pw.println(data);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addSubject(SubjectData t) {
		if(!list.contains(t)) {
			list.add(t);
			storeSubjectsData(list);
		}
	}

	public ArrayList<SubjectData> getTeacherData() {
		Gson g = new Gson();
		File f = new File("db/subjectDB.txt");
		String data = "";

		BufferedReader bb;
		try {
			bb = new BufferedReader(new FileReader(f));
			data = bb.readLine();
			bb.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		list = g.fromJson(data, new TypeToken<ArrayList<SubjectData>>(){}.getType());
		return list;
	}

	public void updateSubject(SubjectData t) {
		list.remove(t);
		list.add(t);
		storeSubjectsData(list);
	}
	

}
