package agentdemo;

import agentdemo.model.Agent;
import agentdemo.model.World;
import agentdemo.ui.ControlPanel;
import agentdemo.ui.WorldPanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Agent Behavior Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            World world = new World(600, 400);
            WorldPanel panel = new WorldPanel(world);
            ControlPanel controlPanel = new ControlPanel(world);

            panel.setSelectionListener((x, y) -> {
                world.selectAgentAt(x, y, 20);
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
                world.update(0.03);
                panel.repaint();
            }).start();
        });
    }
}