package agentdemo;

import agentdemo.behavior.BehaviorRegistry;
import agentdemo.model.Agent;
import agentdemo.model.World;
import agentdemo.ui.ControlPanel;
import agentdemo.ui.WorldPanel;

import javax.swing.*;
import java.awt.*;

import static agentdemo.model.World.createWorld;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Font defaultFont = new Font("Microsoft YaHei UI", Font.PLAIN, 14);
            UIManager.put("Label.font", defaultFont);
            UIManager.put("Button.font", defaultFont);
            UIManager.put("ComboBox.font", defaultFont);
            JFrame frame = new JFrame("Agent Behavior Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            BehaviorRegistry registry = BehaviorRegistry.createDefault();
            World world = World.createWorld(600, 400, registry);
            WorldPanel panel = new WorldPanel(world);
            ControlPanel controlPanel = new ControlPanel(world, registry);
            controlPanel.updateSelection(world.getSelectedAgent());

            panel.setSelectionListener((x, y) -> {
                world.selectAgentAt(x, y, 20);
                controlPanel.updateSelection(world.getSelectedAgent());
                panel.repaint();
            });

            frame.setLayout(new BorderLayout());
            frame.add(panel, BorderLayout.CENTER);
            frame.add(controlPanel, BorderLayout.EAST);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // 简单定时器驱动仿真
            new Timer(30, e -> {
                world.update(0.03 * world.getSpeedMultiplier());
                controlPanel.updateSpeed();
                panel.repaint();
            }).start();
        });
    }
}