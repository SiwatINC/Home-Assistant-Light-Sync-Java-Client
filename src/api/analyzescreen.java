package api;

import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class analyzescreen {
	public static int[] getcolor(int quality, int screenw, int screenh) {
		int pixel;
		quality = 10 - quality;
		Rectangle analyzearea = new Rectangle(screenw, screenh);
		Robot analyzer;
		int[] color = new int[4];
		color[0] = 0;
		color[1] = 0;
		color[2] = 0;
		color[3] = 0;
		try {
			analyzer = new Robot();
			BufferedImage screenshot = analyzer.createScreenCapture(analyzearea);
			int[] screenData = ((DataBufferInt) screenshot.getRaster().getDataBuffer()).getData();
			for (int i = 1; i <= screenh; i += quality) {
				for (int j = 1; j + quality <= screenw; j += quality) {
					pixel = screenData[i * screenw + j];
					color[0] += 0xff & (pixel >> 16);
					color[1] += 0xff & (pixel >> 8);
					color[2] += 0xff & pixel;
					color[3] += 0xff;
				}
			}
			return color;
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	public static object.color decodecolor(int[] color) {
		object.color decodedcolor = new object.color();
		decodedcolor.red = ((255.0) * color[0]) / color[3];
		decodedcolor.green = (255.0) * color[1] / color[3];
		decodedcolor.blue = (255.0) * color[2] / color[3];
		return decodedcolor;
	}
	public static object.screen getscreensize(){
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		object.screen screen = new object.screen();
		screen.width = gd.getDisplayMode().getWidth();
		screen.height = gd.getDisplayMode().getHeight();
		return screen;
	}
}
