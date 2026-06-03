package agentdemo.ui;

import agentdemo.behavior.AvoidNearestBehavior;
import agentdemo.behavior.BehaviorRegistry;
import agentdemo.behavior.ChaseBehavior;
import agentdemo.engine.SimulationEngine;
import agentdemo.model.Agent;
import agentdemo.model.World;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private World world;
    private BehaviorRegistry registry;
    private Agent selectedAgent;
    private JLabel agentLabel;
    private JComboBox<String> behaviorCombo;
    private JSlider speedSlider;
    private JLabel speedLabel;
    private JLabel targetLabel;
    private SimulationEngine engine;

    public ControlPanel(World world, SimulationEngine engine, BehaviorRegistry registry) {
        this.world = world;
        this.engine = engine;
        this.registry = registry;

        // 设置面板的首选尺寸，Dimension是一个表示宽高的对象
        setPreferredSize(new Dimension(230, 520));
        // 设置面板的内边距
        setBorder(BorderFactory.createEmptyBorder(16, 14, 16, 14));
        // 设置布局管理器为BoxLayout，子组件沿垂直方向从上到下排列
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // 标题Label
        JLabel mainLabel = new JLabel("控 制 面 板");
        mainLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
        mainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(mainLabel);
        // 创建一个不可见的固定间距块
        add(Box.createRigidArea(new Dimension(0, 20)));

        // agent选中状态Label
        agentLabel = new JLabel("未选中");
        agentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        agentLabel.setMaximumSize(new Dimension(200, 30));
        add(agentLabel);
        add(Box.createRigidArea(new Dimension(0, 12)));

        speedLabel = new JLabel("速度：--");
        speedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        speedLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
        add(speedLabel);
        add(Box.createRigidArea(new Dimension(0, 12)));

        targetLabel = new JLabel("");
        targetLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        targetLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
        targetLabel.setForeground(new Color(80, 80, 80));
        add(targetLabel);
        add(Box.createRigidArea(new Dimension(0, 8)));
        add(Box.createRigidArea(new Dimension(0, 12)));

        // 行为策略Label
        JLabel behaviorTitle = new JLabel("行为策略: ");
        behaviorTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        behaviorTitle.setMaximumSize(new Dimension(200, 30));
        add(behaviorTitle);
        add(Box.createRigidArea(new Dimension(0, 12)));

        // 有下拉表，可以选择行为策略
        behaviorCombo = new JComboBox<>(registry.getDisplayName());
        behaviorCombo.setMaximumSize(new Dimension(200, 30));
        behaviorCombo.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(behaviorCombo);
        add(Box.createRigidArea(new Dimension(0, 30)));

        // 对下拉表的选择进行监听，对所选中的agent应用对应的behavior
        behaviorCombo.addActionListener(e -> {
            if (selectedAgent != null) {
                int index = behaviorCombo.getSelectedIndex();
                selectedAgent.setBehavior(registry.getBehavior(index));
            }
        });

        // 时间流速Label
        JLabel timeSpeedLabel = new JLabel("时间流速");
        timeSpeedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(timeSpeedLabel);
        add(Box.createRigidArea(new Dimension(0, 12)));

        // 速度条
        //创建水平方向滑块，范围 0 ~ 300，默认值 100，即 1x 速度
        speedSlider = new JSlider(JSlider.HORIZONTAL, 0, 300, 100);
        speedSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        // 主副刻度间隔
        speedSlider.setMajorTickSpacing(100);
        speedSlider.setMinorTickSpacing(25);
        // 是否显示刻度线和标签文字
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        // 限制滑块最大尺寸为 200x60px，防止 BoxLayout 拉伸变形
        speedSlider.setMaximumSize(new Dimension(200, 60));

        // 用 Hashtable 对刻度标签进行对应以显示正常倍速
        java.util.Hashtable<Integer, JLabel> labelTable = new java.util.Hashtable<>();
        labelTable.put(0,   new JLabel("0x"));
        labelTable.put(100, new JLabel("1x"));
        labelTable.put(200, new JLabel("2x"));
        labelTable.put(300, new JLabel("3x"));
        speedSlider.setLabelTable(labelTable);

        add(speedSlider);
        add(Box.createRigidArea(new Dimension(0, 16)));

        // 监听滑块变化，值除以 100.0 转化为倍率
        speedSlider.addChangeListener(e -> {
            engine.setTimeSpeedMultiplier(speedSlider.getValue() / 100.0);
        });

        JButton reset = new JButton("   重   置   ");
        reset.setAlignmentX(Component.CENTER_ALIGNMENT);
        reset.addActionListener(e -> {
            world.reset();
            engine.setTimeSpeedMultiplier(1.0);
            speedSlider.setValue(100);
        });
        add(reset);
        add(Box.createRigidArea(new Dimension(0,12)));

        JButton addAgent = getButton(world, registry);
        add(addAgent);
    }

    private JButton getButton(World world, BehaviorRegistry registry) {
        JButton addAgent = new JButton("添加 Agent");
        addAgent.setAlignmentX(Component.CENTER_ALIGNMENT);
        addAgent.addActionListener(e -> {
            if (world.getAgents().size() >= 7) {
                JOptionPane.showMessageDialog(this, "最多只能添加到 7 个 Agent（A-G）", "提示", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String name = generateName(world.getAgents().size());
            double x = Math.random() * world.getWidth();
            double y = Math.random() * world.getHeight();
            Color color = Color.getHSBColor((float) Math.random(), 0.8f, 0.9f);
            Agent agent = new Agent(x, y, 0, 0, 10, name, registry.getBehavior((int) (Math.random() * 4)), color);
            world.addAgent(agent);
        });
        return addAgent;
    }

    // 每帧刷新速度显示
    public void updateStatus() {
        if (selectedAgent != null) {
            speedLabel.setText("速度：" + String.format("%.1f", selectedAgent.getV()));

        String info = "";
        if (selectedAgent.getBehavior() instanceof ChaseBehavior) {
            Agent target = ((ChaseBehavior) selectedAgent.getBehavior()).getCurrentTarget();
            info = target != null ? "目标：" + target.getName() : "目标：无";
        } else if (selectedAgent.getBehavior() instanceof AvoidNearestBehavior) {
            Agent threat = ((AvoidNearestBehavior) selectedAgent.getBehavior()).getCurrentThreat();
            info = threat != null ? "威胁：" + threat.getName() : "威胁：无";
        }
        targetLabel.setText(info);
    } else {
        speedLabel.setText("速度：--");
        targetLabel.setText("");
    }
    }

    // 更新选中对象
    public void updateSelection(Agent agent) {
        this.selectedAgent = agent;

        if (agent == null) {
            agentLabel.setText("当前选中：null");
            behaviorCombo.setEnabled(false);
        } else {
            agentLabel.setText("当前选中：Agent " + agent.getName());
            speedLabel.setText("速度：" + String.format("%.1f", agent.getV()));
            for (int i = 0; i < registry.size(); i++) {
                if (registry.getBehavior(i).getClass() == agent.getBehavior().getClass()) {
                    behaviorCombo.setSelectedIndex(i);
                    break;
                }
            }
            behaviorCombo.setEnabled(true);
        }
    }

    public String generateName(int index) {
        return "" + (char) ('A' + index);
    }
}
