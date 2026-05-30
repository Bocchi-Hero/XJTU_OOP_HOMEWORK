package agentdemo;

public class ChaseBehavior implements Behavior {

    @Override
    public void update(Agent self, World world) {
        Agent target = world.getFirstAgent();
        if (target == null || target == self) return;

        double dx = target.getX() - self.getX();
        double dy = target.getY() - self.getY();

        double len = Math.sqrt(dx * dx + dy * dy);
        if (len > 1e-6) {
            self.move(dx / len * 2, dy / len * 2);
        }
    }
}