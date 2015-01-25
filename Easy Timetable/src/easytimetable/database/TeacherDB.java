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
	public static int teacherPerSlot;
	
	public TeacherDB() {
		list = new ArrayList<>();
	}
	

	public static int getTeacherPerSlot() {
		File f = new File("db/teacherSlot.txt");
		f.mkdirs();
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		int data = 0;
		BufferedReader bb;
		try {
			bb = new BufferedReader(new FileReader(f));
			data = Integer.parseInt(bb.readLine());
			bb.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public static void storeTeacherPerSlot(int slot) {
		teacherPerSlot = slot;
		File f = new File("db/teacherSlot.txt");
		f.mkdirs();
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
				e.printStackTrace();
			}
		}
		
		int id = slot;
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(f), true);
			pw.println(id);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int getID() {
		File f = new File("db/ID.txt");
		f.mkdirs();
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		int data = 0;
		BufferedReader bb;
		try {
			bb = new BufferedReader(new FileReader(f));
			data = Integer.parseInt(bb.readLine());
			bb.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public static void setID() {
		File f = new File("db/ID.txt");
		f.mkdirs();
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
				e.printStackTrace();
			}
		}
		
		int id = TeacherData.getId();
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(f), true);
			pw.println(id);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void storeTeacherData(ArrayList<TeacherData> list) {
		TeacherDB.list = list;
		Gson g = new Gson();
		File f = new File("db/TeacherDB.txt");
		f.mkdirs();
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
		
		setID();
	}

	public static ArrayList<TeacherData> getTeacherData() {
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
		
		int id = getID();
		TeacherData.setId(id);
		return list;
	}
	
	public static TeacherData getTeacherData(int id) {
		for(TeacherData t : list) {
			if(t.tid == id) 
				return t;
		}
		return null;
	}
	
	public static void addTeacher(TeacherData t) {
		if(!list.contains(t)) {
			list.add(t);
			storeTeacherData(list);
		}
	}

	
	public static void deleteTeacher(int id) {
		for(TeacherData t : list) {
			if(t.tid == id) {
				list.remove(t);
				storeTeacherData(list);
			}
		}
	}
	
	public static void availableChange(TeacherData t, boolean b) {
		t.isAvailable = b;
		list.remove(t);
		list.add(t);
		storeTeacherData(list);
	}
	
	public static void updateTeacher(TeacherData t) {
		list.remove(t);
		list.add(t);
		storeTeacherData(list);
	}
	
	public static ArrayList<TeacherData> getTeachersWhoTeachSubject(ArrayList<TeacherData> teachers, SubjectData sub) {
		ArrayList<TeacherData> temp = new ArrayList<TeacherData>();
		
		for(TeacherData t : teachers) {
			for(SubjectData s : t.subjects) {
				if(sub.equals(s.name)) {
					temp.add(t);
					break;
				}
			}
		}
		
		return temp;
	}
}
