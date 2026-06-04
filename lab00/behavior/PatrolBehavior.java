package agentdemo.behavior;

import agentdemo.model.Agent;
import agentdemo.model.World;

import java.util.HashMap;
import java.util.Map;

public class PatrolBehavior implements Behavior {
    private double[][] waypoints;
    private final Map<Agent, Integer> agentTargets = new HashMap<>();
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
    public String key() {
        return "patrol";
    }

    @Override
    public String displayName() {
        return "巡逻";
    }

    @Override
    public void update(Agent self, World world, double dt) {
        int currentTarget = agentTargets.getOrDefault(self, 0);
        double targetX = waypoints[currentTarget][0];
        double targetY = waypoints[currentTarget][1];

        double dx = targetX - self.getX();
        double dy = targetY - self.getY();
        double dist = Math.hypot(dx, dy);

        if (dist < arrivalThreshold) {
            currentTarget = (currentTarget + 1) % waypoints.length;
            agentTargets.put(self, currentTarget);
        } else {
            self.accelerationTo(targetX, targetY, 1.0, dt);
        }
    }

    public void reset() {
        agentTargets.clear();
    }
}
