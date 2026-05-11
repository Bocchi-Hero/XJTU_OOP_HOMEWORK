package homework3.clock;

import homework3.mygui.Shape;
import homework3.time.MyTime;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ClockShape implements Shape {
    private final MyTime time;

    public ClockShape(MyTime time) {
        this.time = time;
    }

    @Override
    public void draw(Graphics g) {
        Rectangle board = g.getClipBounds();
        int centerX = (int) board.getCenterX();
        int centerY = (int) board.getCenterY();
        int radius = (int) (Math.min(board.getWidth(), board.getHeight()) * 0.4);

        g.setColor(Color.BLACK);
        g.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
        g.drawString("12", centerX - 6, (int) (centerY - 0.9 * radius));
        g.drawString("3", (int) (centerX + 0.9 * radius), centerY + 5);
        g.drawString("6", centerX - 3, (int) (centerY + 0.9 * radius));
        g.drawString("9", (int) (centerX - 0.9 * radius), centerY + 5);

        drawPointer(g, centerX, centerY, time.getSecond() * 6, (int) (0.85 * radius), Color.RED);
        drawPointer(g, centerX, centerY, time.getMinute() * 6, (int) (0.65 * radius), Color.BLUE);
        drawPointer(g, centerX, centerY,
                (time.getHour() % 12 + time.getMinute() / 60.0) * 30,
                (int) (0.5 * radius), Color.GREEN);
    }

    private void drawPointer(Graphics g, int x, int y, double angle, int len, Color color) {
        double convertedAngle = Math.toRadians(angle - 90);
        int endX = (int) (x + len * Math.cos(convertedAngle));
        int endY = (int) (y + len * Math.sin(convertedAngle));

        g.setColor(color);
        g.drawLine(x, y, endX, endY);
    }
}
