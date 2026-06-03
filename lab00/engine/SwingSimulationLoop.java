package agentdemo.engine;

import javax.swing.*;

public class SwingSimulationLoop {
    private static final int FRAME_DELAY_MS = 30;

    private SimulationEngine engine;
    private Timer timer;

    public SwingSimulationLoop(SimulationEngine engine, Runnable afterStep) {
        this.engine = engine;
        this.timer = new Timer(FRAME_DELAY_MS, e -> {
            engine.step(FRAME_DELAY_MS / 1000.0);
            afterStep.run();
        });
    }

    public void start() { timer.start(); }
    public void stop() { timer.stop(); }
    public void setRunning(boolean running) { engine.setRunning(running); }
}
