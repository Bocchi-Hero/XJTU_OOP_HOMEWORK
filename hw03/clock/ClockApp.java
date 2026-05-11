package homework3.clock;

import homework3.mygui.DrawPanel;
import homework3.mygui.Shape;
import homework3.time.MyTime;

import javax.swing.*;
import java.time.LocalTime;

public class ClockApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClockApp::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        // 创建窗口
        JFrame frame = new JFrame("My Clock");

        // 取当前时间并根据当前时间创建myTimer对象
        LocalTime currentTime = LocalTime.now();
        int hour = currentTime.getHour();
        int minute = currentTime.getMinute();
        int second = currentTime.getSecond();
        MyTime myTime = new MyTime(hour, minute, second);

        // 将时钟图形包装进数组中，方便对DrawPanel传参
        Shape[] shapes = new Shape[] { new ClockShape(myTime) };
        DrawPanel panel = new DrawPanel(shapes);

        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // 确定关闭行为
        frame.setVisible(true); // 使窗口显现

        Timer timer = new Timer(1000, event -> {
            myTime.incrementSecond();
            panel.repaint();
        });
        timer.start();
    }
}
