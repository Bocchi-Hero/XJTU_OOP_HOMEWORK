package agentdemo.ui;

import agentdemo.model.World;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private World world;
    public ControlPanel(World world) {
        this.world = world;
        setPreferredSize(new Dimension(230, 520));
        setBorder(BorderFactory.createEmptyBorder(16, 14, 16, 14));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel mainLabel = new JLabel("Control Panel");
        mainLabel.setFont(new Font("Maple Mono", Font.BOLD, 18));
        mainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(mainLabel);
    }


}
