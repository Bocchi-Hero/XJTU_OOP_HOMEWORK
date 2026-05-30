package agentdemo;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Agent Behavior Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            World world = new World(600, 400);
            WorldPanel panel = new WorldPanel(world);

            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // 简单定时器驱动仿真
            new Timer(30, e -> {
                world.update();
                panel.repaint();
            }).start();
        });
    }
}