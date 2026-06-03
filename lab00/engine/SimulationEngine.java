package agentdemo.engine;

import agentdemo.model.World;

public class SimulationEngine {
    private final World world;
    private boolean running = true;
    private double timeSpeedMultiplier = 1.0;

    public SimulationEngine(World world) {
        this.world = world;
    }

    public void step(double dt) {
        if (!running || timeSpeedMultiplier == 0) {
            return;
        }
        stepOnce(dt);
    }

    public void stepOnce(double dt) {
        world.update(dt * timeSpeedMultiplier);
    }

    public boolean isRunning() { return running; }
    public World getWorld() { return world; }
    public double getTimeSpeedMultiplier() { return timeSpeedMultiplier; }

    public void setRunning(boolean running) { this.running = running; }
    public void setTimeSpeedMultiplier(double timeSpeedMultiplier) {
        if (timeSpeedMultiplier < 0) {
            throw new IllegalArgumentException("timeSpeedMultiplier must be non-negative.");
        }
        this.timeSpeedMultiplier = timeSpeedMultiplier;
    }


}
