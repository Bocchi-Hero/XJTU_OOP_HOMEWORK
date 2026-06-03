package agentdemo.model;


import agentdemo.behavior.AvoidNearestBehavior;
import agentdemo.behavior.ChaseBehavior;
import agentdemo.behavior.RandomMoveBehavior;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World {
    private final int width;
    private final int height;
    private final List<Agent> agents = new ArrayList<>();
    private Agent selectedAgent;

    public World(int width, int height) {
        this.width = width;
        this.height = height;

        agents.add(new Agent(100, 100, 10, 10, 10,
                "A", new RandomMoveBehavior(), java.awt.Color.BLUE));
        agents.add(new Agent(300, 200, 10, 10, 10,
                "B", new ChaseBehavior(), java.awt.Color.RED));
        agents.add(new Agent(200, 150, 10, 10, 10,
                "C", new AvoidNearestBehavior(), Color.GREEN));
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
