package easytimetable.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

public class TimeTableDB {
	
	private static TimeTable tt;

	public static void storeTimeTable(TimeTable tt) {
		TimeTableDB.tt = tt;
		Gson g = new Gson();
		File f = new File("db/TimeTable " + tt.year + "-" + tt.division + ".txt");
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

		String data = g.toJson(tt);
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(f), true);
			pw.println(data);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static TimeTable getTimeTable(int year, int division) {
		Gson g = new Gson();
		File f = new File("db/TimeTable " + year + "-" + division + ".txt");
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

		tt = g.fromJson(data, TimeTable.class);
		return tt;
	}
}
