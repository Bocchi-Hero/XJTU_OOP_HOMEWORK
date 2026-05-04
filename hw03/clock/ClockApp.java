package homework3.clock;

import homework3.myGUI.DrawPanel;
import homework3.myGUI.Shape;
import homework3.time.MyTime;

import javax.swing.*;
import java.time.LocalTime;

public class ClockApp {
    public static void main(String[] args) {
        // 创建窗口
        JFrame frame = new JFrame("My Clock");

        // 取当前时间并根据当前时间创建myTimer对象
        LocalTime currentTime = LocalTime.now();
        int hour = currentTime.getHour();
        int minute = currentTime.getMinute();
        int second = currentTime.getSecond();
        MyTime myTimer = new MyTime(hour, minute, second);

        // 将myTimer包装进数组中，方便对DrawPanel的传参
        Shape[] shapes = new Shape[] { myTimer };
        DrawPanel panel = new DrawPanel(shapes);

        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // 确定关闭行为
        frame.setVisible(true); // 使窗口显现

        // 启用多线程驱动时钟
        Thread t = new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000); //设置绘制的时间间隔为 1 秒
                    } catch (InterruptedException e) {
                        System.err.println(e);
                    }
                    myTimer.incrementSecond();
                    //更新绘制图形面板上的内容（也就是绘制的图像）
                    panel.updateUI();
                }
            }
        };
        t.start();
    }
}
