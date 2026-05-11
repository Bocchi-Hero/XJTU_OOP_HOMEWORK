package homework3.mygui;

import java.awt.*;

public class MyRectangle implements Shape {
	private final int x;
	private final int y;
	private final int width;
	private final int height;
	private final Color color;

	public MyRectangle(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawRect(x, y, width, height);
	}
}
