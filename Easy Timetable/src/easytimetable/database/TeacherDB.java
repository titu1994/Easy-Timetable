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

public class TeacherDB {

	private static ArrayList<TeacherData> list;
	public TeacherDB() {
		list = new ArrayList<>();
	}

	public void storeTeacherData(ArrayList<TeacherData> list) {
		TeacherDB.list = list;
		Gson g = new Gson();
		File f = new File("db/TeacherDB.txt");
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

	public void addTeacher(TeacherData t) {
		if(!list.contains(t)) {
			list.add(t);
			storeTeacherData(list);
		}
	}

	public ArrayList<TeacherData> getTeacherData() {
		Gson g = new Gson();
		File f = new File("db/TeacherDB.txt");
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
		
		list = g.fromJson(data, new TypeToken<ArrayList<TeacherData>>(){}.getType());
		return list;
	}

	public void updateTeacherDB(TeacherData t) {
		list.remove(t.tid);
		list.add(t);
		storeTeacherData(list);
	}
}
