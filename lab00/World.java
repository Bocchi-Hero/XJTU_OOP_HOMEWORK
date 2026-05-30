package agentdemo;


import java.util.ArrayList;
import java.util.List;

public class World {
    private final int width;
    private final int height;
    private final List<Agent> agents = new ArrayList<>();

    public World(int width, int height) {
        this.width = width;
        this.height = height;

        agents.add(new Agent(100, 100, new RandomMoveBehavior(), java.awt.Color.BLUE));
        agents.add(new Agent(300, 200, new RandomMoveBehavior(), java.awt.Color.RED));
    }

    public void update() {
        for (Agent a : agents) {
            a.update(this);
        }
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public Agent getFirstAgent() {
        return agents.isEmpty() ? null : agents.get(0);
    }
}
