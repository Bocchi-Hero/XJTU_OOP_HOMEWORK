package agentdemo;

import agentdemo.behavior.BehaviorRegistry;
import agentdemo.engine.SimulationEngine;
import agentdemo.engine.SwingSimulationLoop;
import agentdemo.model.World;
import agentdemo.ui.ControlPanel;
import agentdemo.ui.WorldPanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Font defaultFont = new Font("Microsoft YaHei UI", Font.PLAIN, 14);
            UIManager.put("Label.font", defaultFont);
            UIManager.put("Button.font", defaultFont);
            UIManager.put("ComboBox.font", defaultFont);

            JFrame frame = new JFrame("Agent Behavior Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            BehaviorRegistry registry = BehaviorRegistry.defaultRegistry();
            World world = World.createWorld(600, 400, registry);
            SimulationEngine engine = new SimulationEngine(world);
            WorldPanel worldPanel = new WorldPanel(world);
            ControlPanel controlPanel = new ControlPanel(world, engine, registry);
            SwingSimulationLoop loop = new SwingSimulationLoop(engine, () -> {
                controlPanel.updateStatus();
                worldPanel.repaint();
            });
            world.saveInitialState();
            controlPanel.updateSelection(world.getSelectedAgent());

            worldPanel.setSelectionListener((x, y) -> {
                world.selectAgentAt(x, y, 20);
                controlPanel.updateSelection(world.getSelectedAgent());
                worldPanel.repaint();
            });

            frame.setLayout(new BorderLayout());
            frame.add(worldPanel, BorderLayout.CENTER);
            frame.add(controlPanel, BorderLayout.EAST);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            loop.start();
        });
    }
}
