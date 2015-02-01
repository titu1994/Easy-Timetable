package easytimetable.database;

import java.util.Arrays;

public class AdminData {
	
	public String userName, password;
	
	public AdminData( String username, String password) {
		this.userName = username;
		this.password = Arrays.toString(password.getBytes());
	}

	public String getPassword() {
		password = password.replace("[", "");
		password = password.replace("]", "");
		String arr[] = password.split(",");
		byte[] bytes = new byte[arr.length];
		for(int i = 0; i < bytes.length; i++) {
			bytes[i] = Byte.parseByte(arr[i].trim());
		}
		String pass = new String(bytes);
		return pass;
	}
}
