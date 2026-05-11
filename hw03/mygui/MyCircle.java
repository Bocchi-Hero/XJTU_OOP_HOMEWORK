package homework3.mygui;

import java.awt.Graphics;
import java.awt.Color;

public class MyCircle implements Shape {
	private final int centerX;
	private final int centerY;
	private final int radius;
	private final Color color;

	public MyCircle(int centerX, int centerY, int radius, Color color) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
		this.color = color;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
	}
}
