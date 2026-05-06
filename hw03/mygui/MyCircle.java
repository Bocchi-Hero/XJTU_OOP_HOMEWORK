package homework3.mygui;

import java.awt.Graphics;
import java.awt.Color;

public class MyCircle implements Shape {
	public int x;
	public int y;
	public int radius;
	public Color color;

	public MyCircle(int x, int y, int radius, Color color) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.color = color;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawOval(x, y, radius, radius);
	}
}
