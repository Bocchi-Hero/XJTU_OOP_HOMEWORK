package homework3.clock;

import homework3.myGUI.DrawPanel;
import homework3.myGUI.Shape;
import homework3.time.MyTime;

import javax.swing.*;
import java.time.LocalTime;

public class ClockApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("My Clock");


        LocalTime currentTime = LocalTime.now();
        int hour = currentTime.getHour();
        int minute = currentTime.getMinute();
        int second = currentTime.getSecond();
        MyTime myTimer = new MyTime(hour, minute, second);

        Shape[] shapes = new Shape[] { myTimer };
        DrawPanel panel = new DrawPanel(shapes);

        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

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
