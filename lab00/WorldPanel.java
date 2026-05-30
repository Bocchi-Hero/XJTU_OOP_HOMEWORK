package agentdemo;

import javax.swing.*;
import java.awt.*;

public class WorldPanel extends JPanel {
    private final World world;

    public WorldPanel(World world) {
        this.world = world;
        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (Agent a : world.getAgents()) {
            a.draw(g2);
        }
    }
}