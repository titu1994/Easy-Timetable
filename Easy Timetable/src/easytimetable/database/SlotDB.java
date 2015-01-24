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

public class SlotDB {

	private static ArrayList<SlotData> list;
	private static int slotCount;
	private static String schoolName;
	
	public SlotDB() {
		list = new ArrayList<>();
	}
	
	public static void storeSlot(ArrayList<SlotData> list) {
		SlotDB.list = list;
		Gson g = new Gson();
		File f = new File("db/slotDB.txt");
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
	}
	
	public static ArrayList<SlotData> getSlotData() {
		Gson g = new Gson();
		File f = new File("db/slotDB.txt");
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
		
		list = g.fromJson(data, new TypeToken<ArrayList<SlotData>>(){}.getType());
		return list;
	}
	
	public static void addSlot(SlotData t) {
		if(!list.contains(t)) {
			list.add(t);
			storeSlot(list);
		}
	}
	
	public static SlotData getSlotData(int no) {
		for(SlotData t : list) {
			if(t.no == no) 
				return t;
		}
		return null;
	}
	
	public static void deleteSlot(int no) {
		for(SlotData t : list) {
			if(t.no == no) {
				list.remove(t);
				storeSlot(list);
			}	
		}
	}
	
	public static void updateSlot(SlotData t) {
		list.remove(t);
		list.add(t);
		storeSlot(list);
	}
	
	public static void storeSchoolName(String schoolName) {
		SlotDB.schoolName = schoolName;
		Gson g = new Gson();
		File f = new File("db/schoolname.txt");
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
	}
	
	public static String getSchoolName() {
		Gson g = new Gson();
		File f = new File("db/schoolname.txt");
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
		
		schoolName = g.fromJson(data, String.class);
		return schoolName;
	}
	
	public static void storeSlotCount(int slotCount) {
		SlotDB.slotCount = slotCount;
		Gson g = new Gson();
		File f = new File("db/slotcount.txt");
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
	}
	
	public static int getSlotCount() {
		Gson g = new Gson();
		File f = new File("db/slotcount.txt");
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
		
		slotCount = g.fromJson(data, Integer.class);
		return slotCount;
	}
}
