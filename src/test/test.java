package test;
import api.*;
public class test {
	public static void main(String[] args) {
		while(true) {
		int[] color = api.analyzescreen.getcolor(5, 1920, 1080);
		//Here come some math - siwat
		float red=color[0],green=color[1],blue=color[2];
		float percentagered = 255*red/(red+green+blue),percentagegreen = 255*green/(red+green+blue),percentageblue = 255*blue/(red+green+blue);
		System.out.println("red:  "+percentagered+"\ngreen:"+percentagegreen+"\nblue: "+percentageblue);
	}}
}