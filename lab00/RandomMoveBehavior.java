package agentdemo;

import java.util.Random;

public class RandomMoveBehavior implements Behavior {
    private final Random random = new Random();

    @Override
    public void update(Agent self, World world) {
        double dx = random.nextDouble() * 6 - 2;
        double dy = random.nextDouble() * 6 - 2;
        self.move(dx, dy);
    }
}