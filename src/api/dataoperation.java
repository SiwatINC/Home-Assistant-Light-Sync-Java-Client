package api;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
//The FileStreamer should READ/WRITE a JSON object instead of a seperate file in a folder.
public class dataoperation {
	public static boolean store(String data,String key) {
		Properties setting = new Properties();
		OutputStream writer = null;
		try {
			writer = new FileOutputStream("halsconf/"+key);
			setting.setProperty(key, data);
			setting.store(writer, null);
			writer.close();
			return true;
		} catch(Exception e) {
			System.out.println(e);
			return false;
	}
}
	public static String retrieve(String key) {
		Properties setting = new Properties();
		InputStream reader = null;
		try {
			reader = new FileInputStream("halsconf/"+key);
			setting.load(reader);
			String data = new String();
			data = setting.getProperty(key);
			reader.close();
			return data;
		} catch(Exception e) {
			System.out.println(e);
			return "Error";
	}
}
}