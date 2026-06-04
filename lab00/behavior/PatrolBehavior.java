package agentdemo.behavior;

import agentdemo.model.Agent;
import agentdemo.model.World;

import java.util.HashMap;
import java.util.Map;

public class PatrolBehavior implements Behavior {
    private static final double BOUNDARY_PADDING = 8.0;

    private final Map<Agent, Integer> agentTargets = new HashMap<>();
    private final double arrivalThreshold = 15.0;

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
        double[][] waypoints = boundaryWaypoints(self, world);
        int currentTarget = agentTargets.getOrDefault(self, nearestWaypointIndex(self, waypoints));
        double targetX = waypoints[currentTarget][0];
        double targetY = waypoints[currentTarget][1];

        double dx = targetX - self.getX();
        double dy = targetY - self.getY();
        double dist = Math.hypot(dx, dy);

        if (dist < arrivalThreshold) {
            currentTarget = (currentTarget + 1) % waypoints.length;
            agentTargets.put(self, currentTarget);
            targetX = waypoints[currentTarget][0];
            targetY = waypoints[currentTarget][1];
        }
        self.accelerationTo(targetX, targetY, 1.0, dt);
    }

    private int nearestWaypointIndex(Agent self, double[][] waypoints) {
        int nearestIndex = 0;
        double nearestDistance = Double.MAX_VALUE;
        for (int i = 0; i < waypoints.length; i++) {
            double distance = self.distanceTo(waypoints[i][0], waypoints[i][1]);
            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearestIndex = i;
            }
        }
        return nearestIndex;
    }

    private double[][] boundaryWaypoints(Agent self, World world) {
        double margin = self.getRadius() + BOUNDARY_PADDING;
        double right = Math.max(margin, world.getWidth() - margin);
        double bottom = Math.max(margin, world.getHeight() - margin);
        return new double[][]{
                {margin, margin},
                {right, margin},
                {right, bottom},
                {margin, bottom}
        };
    }
}
