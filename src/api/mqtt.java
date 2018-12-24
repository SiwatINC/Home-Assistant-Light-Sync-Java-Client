package api;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class mqtt {
	public static MqttClient mqttclient;
	public static MemoryPersistence persistence = new MemoryPersistence();
	public static MqttConnectOptions connectoption = new MqttConnectOptions();
	public static MqttClient connect(String broker,String clientId,String username,char[] password) {
		try {
			mqttclient = new MqttClient(broker, clientId, persistence);
			connectoption.setUserName(username);
			connectoption.setCleanSession(true);
			connectoption.setPassword(password);
			mqttclient.connect(connectoption);
		} catch (MqttException e) {
			e.printStackTrace();
		}
		return mqttclient;
	}
	public static void disconnect() {
		try {
			mqtt.disconnect();
		}catch(Exception e) {
			
		}
	}
	public static boolean publish(String message,String topic,int qos) {
		try {
			if (!mqttclient.isConnected()) {
				return false;
			}
			MqttMessage payload = new MqttMessage(message.getBytes());
			payload.setQos(qos);
			mqttclient.publish(topic, payload);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	public static boolean testserver(String broker,String clientId,String username,char[] password) {
		try{
			api.mqtt.connect(broker,clientId,username,password);
			if(mqttclient.isConnected())
				{
				return true;
				}
		}catch(Exception e) {
			return false;
		}
		return false;
	}
}