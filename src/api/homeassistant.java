package api;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.net.http.HttpResponse.*;
import org.json.simple.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class homeassistant {
	public boolean debug = false;
	public String uri = null;
	public String token = null;
	public String auth_user = null;
	public String auth_pass = null;
	public HttpClient http = HttpClient.newHttpClient();
	
	//HASS GET Function | Pipe to HTTPClient GET Request
	public JSONObject HassGet(String path) {
		//Trim out trailing / from URL
		if (uri.endsWith("/"))uri = uri.substring(0, uri.length() - 1);
		HttpResponse<String> response = null;
		//Make a HTTP(S) request
		try {
			HttpRequest httpreq = HttpRequest.newBuilder().uri(URI.create(uri+path))
					.header("authorization", "Bearer "+token).build();
			response = this.http.send(httpreq,HttpResponse.BodyHandlers.ofString());
		} catch (IOException | IllegalArgumentException | InterruptedException e ) {
			if(this.debug)e.printStackTrace();
		}
		//Parse the response to JSON
		try {
			JSONParser parser = new JSONParser();
		
			return (JSONObject) parser.parse(response.body());
		} catch (Exception e) {
			if(this.debug)e.printStackTrace();
			return null;
		}
		
	}
	
	//HASS GET Function | Pipe to HTTPClient GET Request
	public JSONArray HassGetArray(String path) {
		//Trim out trailing / from URL
		if (uri.endsWith("/"))uri = uri.substring(0, uri.length() - 1);
		HttpResponse<String> response = null;
		//Make a HTTP(S) request
		try {
			HttpRequest httpreq = HttpRequest.newBuilder().uri(URI.create(uri+path))
					.header("authorization", "Bearer "+token).build();
			response = this.http.send(httpreq,HttpResponse.BodyHandlers.ofString());
		} catch (IOException | IllegalArgumentException | InterruptedException e ) {
			if(this.debug)e.printStackTrace();
		}
		//Parse the response to JSON
		try {
			JSONParser parser = new JSONParser();
		
			return (JSONArray) parser.parse(response.body());
		} catch (Exception e) {
			if(this.debug)e.printStackTrace();
			return null;
		}
		
	}
	
	//Return a boolean indicating the state of home assistant
	public boolean isOnline() {
		try {
		if(HassGet("/api/").get("message").equals("API running.")) return true;
		}
		catch(NullPointerException e) {
			return false;
		}
		return false;
	}
	
	public object.light[] getLights() {
		JSONArray a = HassGetArray("/api/states");
		System.out.println(a);
		return new object.light[0];
	}
}
