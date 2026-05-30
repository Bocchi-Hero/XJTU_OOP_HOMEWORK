package agentdemo;

import java.awt.*;

public class Agent {
    private double x;
    private double y;
    private Behavior behavior;
    private Color color;

    public Agent(double x, double y, Behavior behavior, Color color) {
        this.x = x;
        this.y = y;
        this.behavior = behavior;
        this.color = color;
    }

    public void update(World world) {
        if (behavior != null) {
            behavior.update(this, world);
        }
    }

    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int) x - 5, (int) y - 5, 10, 10);
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }
}