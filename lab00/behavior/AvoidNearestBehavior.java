package agentdemo.behavior;

import agentdemo.model.Agent;
import agentdemo.model.World;

public class AvoidNearestBehavior implements Behavior{
    public static final double MAX_PERCEPTION_RANGE = 125.0;

    @Override
    public void update(Agent self, World world, double dt) {
        Agent threat = world.findNearestAgent(self, MAX_PERCEPTION_RANGE);
        if (threat == null) {
            self.applyDrag(0.97);
            return;
        }

        double awayX = self.getX() - threat.getX();
        double awayY = self.getY() - threat.getY();
        self.accelerationToward(awayX, awayY, 1.15, dt);
    }

    @Override
    public String name() { return "回避"; }
}
