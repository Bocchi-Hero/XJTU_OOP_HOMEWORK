package homework3.myGUI;

import java.awt.*;

public class MyRectangle implements Shape {
	public int x;
	public int y;
	public int width;
	public int height;
	public Color color;

	public MyRectangle(int x, int y, int width, int height,Color color) {
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
