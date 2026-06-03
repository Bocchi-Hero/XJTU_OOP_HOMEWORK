package agentdemo.model;

import agentdemo.behavior.Behavior;
import agentdemo.behavior.BehaviorRegistry;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World {
    private final int width;
    private final int height;
    private final List<Agent> agents = new ArrayList<>();
    private Agent selectedAgent;
    private List<double[]> initialStates;
    private List<Behavior> initialBehaviors;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static World createWorld(int width, int height, BehaviorRegistry registry) {
        World world = new World(width, height);
        world.addAgent(new Agent(100, 100, 10, 10, 10,
                "A", registry.getBehavior(0), java.awt.Color.BLUE));
        world.addAgent(new Agent(300, 200, 10, 10, 10,
                "B", registry.getBehavior(1), java.awt.Color.RED));
        world.addAgent(new Agent(200, 150, 10, 10, 10,
                "C", registry.getBehavior(2), Color.GREEN));
        world.selectedAgent = world.agents.get(0);
        return world;
    }

    public void update(double dt) {
        for (Agent a : agents) {
            a.update(this, dt);
        }
    }

    // World 掌管环境，以及各个agent，因此找到最近agent的逻辑写在World中
    public Agent findNearestAgent(Agent source, double maxPerceptionRange) {
        Agent nearestAgent = null;
        double minDistance = maxPerceptionRange;
        for (Agent agent : agents) {
            if (agent == source) { continue; }
            double distance = source.distanceTo(agent);
            if (distance < minDistance) {
                minDistance = distance;
                nearestAgent = agent;
            }
        }
        return nearestAgent;
    }

    public Agent selectAgentAt(double x, double y, double radius) {
        Agent nearestAgent = null;
        double nearestDistance = radius;
        for (Agent agent : agents) {
            double distance = agent.distanceTo(x, y);
            if (distance <= nearestDistance) {
                nearestDistance = distance;
                nearestAgent = agent;
            }
        }
        selectedAgent = nearestAgent;
        return selectedAgent;
    }

    public void saveInitialState() {
        initialStates = new ArrayList<>();
        initialBehaviors = new ArrayList<>();
        for (Agent a : agents) {
            initialStates.add(new double[]{a.getX(), a.getY(), a.getVx(), a.getVy()});
            initialBehaviors.add(a.getBehavior());
        }
    }

    public void reset() {
        for (int i = 0; i < initialStates.size(); i++) {
            Agent a = agents.get(i);
            double[] s = initialStates.get(i);
            a.setPosition(s[0], s[1]);
            a.setVelocity(s[2], s[3]);
            a.setBehavior(initialBehaviors.get(i));
        }
        // 移除新增的Agent
        while (agents.size() > initialStates.size()) {
            agents.remove(agents.size() - 1);
        }
        selectedAgent = agents.isEmpty() ? null : agents.get(0);
    }

    public void addAgent(Agent agent) {
        agents.add(agent);
    }

    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }
    public List<Agent> getAgents() {
        return agents;
    }
    public Agent getFirstAgent() {
        return agents.isEmpty() ? null : agents.get(0);
    }
    public Agent getSelectedAgent() { return selectedAgent; }
}
