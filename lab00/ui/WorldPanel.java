package agentdemo.ui;

import agentdemo.model.Agent;
import agentdemo.model.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class WorldPanel extends JPanel {
    private final World world;
    private SelectionListener selectionListener = (x, y) -> {};

    public WorldPanel(World world) {
        this.world = world;
        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.WHITE);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                syncWorldBoundary();
            }
        });
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
        syncWorldBoundary();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 绘制网格线
        drawGrid(g2);
        drawAgents(g2);
    }

    private void syncWorldBoundary() {
        world.resize(getWidth(), getHeight());
    }

    private void drawGrid(Graphics2D g2) {
        int gridSize = 40;
        g2.setColor(new Color(220, 220, 220));
        for (int x = 0; x < getWidth(); x += gridSize) {
            g2.drawLine(x, 0, x, getHeight());
        }
        for (int y = 0; y < getHeight(); y += gridSize) {
            g2.drawLine(0, y, getWidth(), y);
        }
    }

    private void drawAgent(Graphics2D g, Agent agent, boolean selected) {
        double x = agent.getX();
        double y = agent.getY();
        double radius = agent.getRadius();
        double vx = agent.getVx();
        double vy = agent.getVy();
        Color color = agent.getColor();
        double size = 2 * radius;

        if (selected) {
            g.setColor(new Color(255, 190, 40, 120));
            g.fill(new Ellipse2D.Double(x - radius - 5, y - radius - 5, size + 10, size + 10));
        }
        g.setColor(color);
        g.fill(new Ellipse2D.Double(x - radius, y - radius, size, size));

        Stroke oldStroke = g.getStroke();
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2.0f));
        g.draw(new Ellipse2D.Double(x - radius, y - radius, size, size));
        g.setStroke(new BasicStroke(2.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.drawLine((int) x, (int) y, (int) (x + vx * 0.25), (int) (y + vy * 0.25));
        g.setStroke(oldStroke);
    }

    private void drawAgents(Graphics2D g2) {
        FontMetrics metrics = g2.getFontMetrics();
        for (Agent agent : world.getAgents()) {
            boolean selected = agent == world.getSelectedAgent();
            drawAgent(g2, agent, selected);
            String label = agent.getName() + " - " + agent.getBehavior().displayName();
            int labelX = (int) (agent.getX() + agent.getRadius() + 5);
            int labelY = (int) (agent.getY() - agent.getRadius() - 4);
            g2.setColor(new Color(30, 34, 38));
            g2.drawString(label, labelX, labelY);
            if (selected) {
                String speed = String.format("v=%.1f", agent.getV());
                g2.drawString(speed, labelX, labelY + metrics.getHeight());

                String extra = agent.getBehavior().statusText();
                if (!extra.isEmpty()) {
                    g2.drawString(extra, labelX, labelY + metrics.getHeight() * 2);
                }
            }
        }
    }

    public interface SelectionListener {
        void select(int x, int y);
    }
}
