package agentdemo.model;

import agentdemo.behavior.Behavior;

import java.awt.*;

public class Agent {
    private double x;
    private double y;
    private double vx;
    private double vy;
    private double radius;
    private String name;
    private Behavior behavior;
    private Color color;

    public Agent(double x, double y, double vx, double vy, double radius, String name, Behavior behavior, Color color) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
        this.name = name;
        this.behavior = behavior;
        this.color = color;
    }

    // dt 时间（即一帧）后对agent状态的更新
    public void update(World world, double dt) {
        if (behavior != null) {
            behavior.update(this, world, dt);
        }
        move(dt);
        applyDrag(0.98);
        handleBoundary(world);
    }

    public void move(double dt) {
        x += vx * dt;
        y += vy * dt;
    }

    // 边界处理
    public void handleBoundary(World world) {
        if (x < radius) {
            x = radius;
            vx = Math.abs(vx);
        } else if (x > world.getWidth() - radius) {
            x = world.getWidth() - radius;
            vx = -Math.abs(vx);
        }

        if (y < radius) {
            y = radius;
            vy = Math.abs(vy);
        } else if (y > world.getHeight() - radius) {
            y = world.getHeight() - radius;
            vy = -Math.abs(vy);
        }
    }

    // 限制速度
    public void limitSpeed() {
        double speed = Math.sqrt(vx * vx + vy * vy);
        double maxSpeed = 100.0;
        if (speed <= maxSpeed) {
            return;
        }
        vx = vx / speed * maxSpeed;
        vy = vy / speed * maxSpeed;
    }

    // 朝某个方向加速
    public void accelerationToward(double dirX, double dirY, double strength, double dt) {
        double length = Math.sqrt(dirX * dirX + dirY * dirY);
        if (length < 1e-9) {
            return;
        }

        double acceleration = 170.0;
        vx += dirX / length * acceleration * strength * dt;
        vy += dirY / length * acceleration * strength * dt;

        limitSpeed();
    }

    public void applyDrag(double factor) {
        vx *= factor;
        vy *= factor;
    }

    // 朝目标点加速
    public void accelerationTo(double targetX, double targetY, double strength, double dt) {
        accelerationToward(targetX - this.x, targetY - this.y, strength, dt);
    }

    // 和目标点距离
    public double distanceTo(Agent another) {
        return distanceTo(another.x, another.y);
    }

    // 和鼠标距离
    public double distanceTo(double mouseX, double mouseY) {
        return Math.hypot(this.x - mouseX, this.y - mouseY);
    }

    // 是否被鼠标选中
    public boolean contains(double mouseX, double mouseY) {
        return distanceTo(mouseX, mouseY) <= this.radius;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getVx() { return vx; }
    public double getVy() { return vy; }
    public double getV() { return Math.hypot(vx, vy); }
    public double getRadius() { return radius; }
    public Color getColor() { return color; }
    public String getName() { return this.name; }
    public String getBehaviorKey() {
        return behavior == null ? "" : behavior.key();
    }
    public Behavior getBehavior() { return this.behavior; }

    public void setRadius(double radius) { this.radius = radius; }
    public void setColor(Color color) { this.color = color; }
    public void setName(String name) { this.name = name; }
    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public void setVelocity(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }
}
