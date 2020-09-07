package object;

public class light {
	api.homeassistant HASS;
	object.color color = new object.color(); 
	//The m part of y = mx + c
	public double r_slope = 0.0;
	public double g_slope = 0.0;
	public double b_slope = 0.0;
	
	//The c part of y = mx + c
	public int r_adjustment = 0;
	public int g_adjustment = 0;
	public int b_adjustment = 0;
	
	public light (api.homeassistant HASS) {
		this.HASS=HASS;
	}
	
	public void setColor(object.color color) {
		this.color = color;
		
	}
	
	//The y part of y = mx + c | The Current color of the light
	public object.color getColor() {
		return this.color;
	}
}
