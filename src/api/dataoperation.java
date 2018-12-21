package api;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class dataoperation {
	public static boolean store(String data,String key) {
		Properties setting = new Properties();
		OutputStream writer = null;
		try {
			writer = new FileOutputStream("config.properties");
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
			reader = new FileInputStream("config.properties");
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