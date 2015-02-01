package easytimetable.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

public class AdminDB {
	
	private static AdminData admin;
	
	public static void storeAdminData(AdminData admin) {
		AdminDB.admin = admin;
		Gson g = new Gson();
		File f = new File("db/AdminDB.txt");
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

		String data = g.toJson(admin);
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(f), true);
			pw.println(data);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static AdminData getAdminData() {
		Gson g = new Gson();
		File f = new File("db/AdminDB.txt");
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
		
		admin = g.fromJson(data, AdminData.class);
		;
		return admin;
	}

}
