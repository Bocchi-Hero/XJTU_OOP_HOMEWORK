package agentdemo.behavior;

import agentdemo.model.Agent;
import agentdemo.model.World;

public class AvoidNearestBehavior implements Behavior{
    public static final double MAX_PERCEPTION_RANGE = 125.0;
    private Agent currentThreat;

    @Override
    public String key() {
        return "avoid";
    }

    @Override
    public String displayName() {
        return "回避";
    }

    @Override
    public void update(Agent self, World world, double dt) {
        currentThreat = world.findNearestAgent(self, MAX_PERCEPTION_RANGE);
        if (currentThreat == null) {
            self.applyDrag(0.97);
            return;
        }

        double awayX = self.getX() - currentThreat.getX();
        double awayY = self.getY() - currentThreat.getY();
        self.accelerationToward(awayX, awayY, 1.15, dt);
    }

    public Agent getCurrentThreat() { return currentThreat; }
}
