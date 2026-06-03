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

        for (Agent a : world.getAgents()) {
            a.draw(g2, a == world.getSelectedAgent());
        }
    }

    public interface SelectionListener {
        void select(int x, int y);
    }
}