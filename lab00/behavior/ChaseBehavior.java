package agentdemo.behavior;

import agentdemo.model.Agent;
import agentdemo.model.World;

public class ChaseBehavior implements Behavior {
    public static final double MAX_PERCEPTION_RANGE = 260.0;

    @Override
    public void update(Agent self, World world, double dt) {
        Agent target = world.findNearestAgent(self, MAX_PERCEPTION_RANGE);
        if (target == null) {
            self.applyDrag(0.97);
            return;
        }

        self.accelerationTo(target.getX(), target.getY(), 1.5, dt);
    }

    @Override
    public String name() { return "追逐"; }
}