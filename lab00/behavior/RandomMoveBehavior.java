package agentdemo.behavior;

import agentdemo.model.Agent;
import agentdemo.model.World;

import java.util.Random;

public class RandomMoveBehavior implements Behavior {
    private final Random random = new Random();
    private double angle = random.nextDouble() * Math.PI * 2;

    @Override
    public String key() {
        return "random";
    }

    @Override
    public String displayName() {
        return "随机";
    }

    @Override
    public void update(Agent self, World world, double dt) {
        angle += (random.nextDouble() - 0.5) * 1.5;
        double dirX = Math.cos(angle);
        double dirY = Math.sin(angle);
        self.accelerationToward(dirX, dirY, 1.0, dt);
    }
}