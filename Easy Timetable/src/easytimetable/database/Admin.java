package easytimetable.database;

import java.util.Arrays;

public class Admin {
	
	public String userName, password;
	
	public Admin( String username, String password) {
		this.userName = username;
		this.password = Arrays.toString(password.getBytes());
	}

	public String getPassword() {
		String arr[] = password.split(",");
		byte[] bytes = new byte[arr.length];
		for(int i = 0; i < bytes.length; i++) {
			bytes[i] = Byte.parseByte(arr[i]);
		}
		String pass = new String(bytes);
		return pass;
	}
}
