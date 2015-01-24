package easytimetable.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.rmi.CORBA.Stub;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SubjectDB {
	
	private static ArrayList<SubjectData> list;
	
	public SubjectDB() {
		list = new ArrayList<>();
	}
	
	public static void storeSubjectsData(ArrayList<SubjectData> list) {
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
		else {
			f.delete();
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
	
	public static ArrayList<SubjectData> getSubjectData() {
		Gson g = new Gson();
		File f = new File("db/subjectDB.txt");
		f.mkdirs();
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
	
	public static void addSubject(SubjectData t) {
		if(!list.contains(t)) {
			list.add(t);
			storeSubjectsData(list);
		}
	}

	public static void updateSubject(SubjectData t) {
		list.remove(t);
		list.add(t);
		storeSubjectsData(list);
	}
	
	public static void deleteSubject(String name) {
		for(SubjectData t : list) {
			if(t.name.equalsIgnoreCase(name)) {
				list.remove(t);
				storeSubjectsData(list);
			}
		}
	}
	
	public static SubjectData getSubjectData(String name) {
		for(SubjectData t : list) {
			if(t.name.equalsIgnoreCase(name)) 
				return t;
		}
		return null;
	}

}
