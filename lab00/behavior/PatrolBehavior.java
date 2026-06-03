package agentdemo.behavior;

import agentdemo.model.Agent;
import agentdemo.model.World;

public class PatrolBehavior implements Behavior {
    private double[][] waypoints;
    private int currentTarget = 0;
    private final double arrivalThreshold = 15.0;

    public PatrolBehavior() {
        this.waypoints = new double[][]{
            {50, 50},
            {550, 50},
            {550, 350},
            {50, 350}
        };
    }

    @Override
    public void update(Agent self, World world, double dt) {
        double targetX = waypoints[currentTarget][0];
        double targetY = waypoints[currentTarget][1];

        double dx = targetX - self.getX();
        double dy = targetY - self.getY();
        double dist = Math.hypot(dx, dy);

        if (dist < arrivalThreshold) {
            currentTarget = (currentTarget + 1) % waypoints.length;
        } else {
            self.accelerationTo(targetX, targetY, 1.0, dt);
        }
    }

    @Override
    public String name() { return "巡逻"; }
}
