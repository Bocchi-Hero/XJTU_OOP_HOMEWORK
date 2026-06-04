package agentdemo.behavior;

import agentdemo.model.Agent;
import agentdemo.model.World;

public class ChaseBehavior implements Behavior {
    private static final double MAX_PERCEPTION_RANGE = 260.0;
    private Agent currentTarget;

    @Override
    public String key() {
        return "chase";
    }

    @Override
    public String displayName() {
        return "追逐";
    }

    @Override
    public void update(Agent self, World world, double dt) {
        currentTarget = world.findNearestAgent(self, MAX_PERCEPTION_RANGE);
        if (currentTarget == null) {
            self.applyDrag(0.97);
            return;
        }

        self.accelerationTo(currentTarget.getX(), currentTarget.getY(), 1.5, dt);
    }

    public Agent getCurrentTarget() { return currentTarget; }
}