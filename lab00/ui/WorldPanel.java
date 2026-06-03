package agentdemo.ui;

import agentdemo.model.Agent;
import agentdemo.model.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WorldPanel extends JPanel {
    private final World world;
    private SelectionListener selectionListener = (x, y) -> {};

    public WorldPanel(World world) {
        this.world = world;
        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectionListener.select(e.getX(), e.getY());
            }
        });
    }

    public void setSelectionListener(SelectionListener selectionListener) {
        this.selectionListener = selectionListener;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 绘制网格线
        drawGrid(g2);
        drawAgents(g2);
    }

    private void drawGrid(Graphics2D g2) {
        int gridSize = 30;
        g2.setColor(new Color(220, 220, 220));
        for (int x = 0; x < getWidth(); x += gridSize) {
            g2.drawLine(x, 0, x, getHeight());
        }
        for (int y = 0; y < getHeight(); y += gridSize) {
            g2.drawLine(0, y, getWidth(), y);
        }

        for (Agent a : world.getAgents()) {
            a.draw(g2, a == world.getSelectedAgent());
        }
    }

    private void drawAgents(Graphics2D g2) {
        FontMetrics metrics = g2.getFontMetrics();
        for (Agent agent : world.getAgents()) {
            boolean selected = agent == world.getSelectedAgent();
            agent.draw(g2, selected);
            String label = agent.getName() + " - " + agent.getBehavior().name();
            int labelX = (int) (agent.getX() + agent.getRadius() + 5);
            int labelY = (int) (agent.getY() - agent.getRadius() - 4);
            g2.setColor(new Color(30, 34, 38));
            g2.drawString(label, labelX, labelY);
            if (selected) {
                String speed = String.format("v=%.1f", agent.getV());
                g2.drawString(speed, labelX, labelY + metrics.getHeight());
            }
        }
    }

    public interface SelectionListener {
        void select(int x, int y);
    }
}