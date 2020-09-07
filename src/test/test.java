package test;

public class test {
	public static void main(String[] argStringss) {
		api.homeassistant HASS = new api.homeassistant();
		HASS.uri = "https://ha.siwatsyste.com/";
		HASS.token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiI5YTlhMmUyMmQ4ZmY0NmFmYTliNWRhMjNlNWJiOThjZSIsImlhdCI6MTU5OTQxNTI3MywiZXhwIjoxOTE0Nzc1MjczfQ.X51OVaERAzWzkmnKiPdqKuaYwj5QAyMXQwGHKVu3GdA";
		
		System.out.println(HASS.getLights());
		System.out.println();
		System.out.println();
	}
}