package homework3.time;

import homework3.myGUI.Shape;
import java.awt.*;

public class MyTime implements Shape {
    private int hour;
    private int minute;
    private int second;

    public MyTime() {
        this(0, 0, 0);
    }

    public MyTime(int hour) {
        this(hour, 0, 0);
    }

    public MyTime(int hour, int minute) {
        this(hour, minute, 0);
    }

    public MyTime(int hour, int minute, int second) {
        if (hour >= 0 && hour < 24) {
            this.hour = hour;
        } else {
            System.out.println("hour must be 0-23");
            this.hour = 0;
        }

        if (minute >= 0 && minute < 60) {
            this.minute = minute;
        } else {
            System.out.println("minute must be 0-59");
            this.minute = 0;
        }

        if (second >= 0 && second < 60) {
            this.second = second;
        } else {
            System.out.println("second must be 0-59");
            this.second = 0;
        }
    }

    public MyTime(MyTime time) {
        this(time.hour, time.minute, time.second);
    }

    public void incrementHour() { hour = (hour + 1) % 24; }

    public void incrementMinute() {
        if (++minute == 60) {
            incrementHour();
            minute = 0;
        }
    }

    public void incrementSecond() {
        if (++second == 60) {
            incrementMinute();
            second = 0;
        }
    }

    public String toUniversalString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d %s", ((hour == 0 || hour == 12) ? 12 : hour % 12),
        minute, second, ((hour < 12) ? "AM" : "PM"));
    }

    @Override
    public void draw(Graphics g) {
        Rectangle board = g.getClipBounds();
        int centerX = (int) board.getCenterX();
        int centerY = (int) board.getCenterY();
        int radius = (int) (Math.min(board.getWidth(), board.getHeight()) * 0.4);

        g.setColor(Color.BLACK);
        g.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
        g.drawString("12", centerX, (int) (centerY - 0.9 * radius));
        g.drawString("3", (int) (centerX + 0.9 * radius), centerY);
        g.drawString("6",centerX, (int) (centerY + 0.9 * radius));
        g.drawString("9", (int) (centerX - 0.9 * radius), centerY);

        drawPointer(g, centerX, centerY, second * 6, (int) (0.85 * radius), Color.RED);
        drawPointer(g, centerX, centerY, minute * 6, (int) (0.65 * radius), Color.BLUE);
        drawPointer(g, centerX, centerY, (hour % 12 + minute / 60.0) * 30, (int) (0.5 * radius), Color.GREEN);
    }

    private void drawPointer(Graphics g, int x, int y, double angle, int len, Color color) {
        double convertedAngle = Math.toRadians(angle - 90);

        int endX = (int) (x + len * Math.cos(convertedAngle));
        int endY = (int) (y + len * Math.sin(convertedAngle));

        g.setColor(color);
        g.drawLine(x, y, endX, endY);
    }
}
