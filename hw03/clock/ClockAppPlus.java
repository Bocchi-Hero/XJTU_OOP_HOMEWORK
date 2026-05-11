package homework3.clock;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ClockAppPlus {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClockAppPlus::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Clock Plus");
        ClockPanel panel = new ClockPanel();

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Timer timer = new Timer(1000, event -> panel.repaint());
        timer.start();
    }

    private static class ClockPanel extends JPanel {
        private static final Color BACKGROUND_TOP = new Color(244, 247, 251);
        private static final Color BACKGROUND_BOTTOM = new Color(218, 226, 236);
        private static final Color FACE = new Color(252, 253, 255);
        private static final Color RIM = new Color(35, 48, 66);
        private static final Color TICK = new Color(76, 88, 106);
        private static final Color TEXT = new Color(23, 34, 52);
        private static final Color HOUR_HAND = new Color(34, 48, 73);
        private static final Color MINUTE_HAND = new Color(42, 68, 102);
        private static final Color SECOND_HAND = new Color(211, 64, 73);
        private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
        private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        ClockPanel() {
            setPreferredSize(new Dimension(520, 560));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            int centerX = width / 2;
            int centerY = height / 2 - 26;
            int radius = Math.min(width, height - 70) / 2 - 42;

            paintBackground(g2, width, height);
            paintFace(g2, centerX, centerY, radius);
            paintTicksAndNumbers(g2, centerX, centerY, radius);
            paintHands(g2, centerX, centerY, radius, LocalTime.now());
            paintCenterCap(g2, centerX, centerY);
            paintDigitalTime(g2, width, centerY + radius + 48, LocalTime.now());

            g2.dispose();
        }

        private void paintBackground(Graphics2D g2, int width, int height) {
            g2.setPaint(new GradientPaint(0, 0, BACKGROUND_TOP, 0, height, BACKGROUND_BOTTOM));
            g2.fillRect(0, 0, width, height);
        }

        private void paintFace(Graphics2D g2, int centerX, int centerY, int radius) {
            int shadowOffset = 8;
            g2.setColor(new Color(44, 62, 86, 38));
            g2.fillOval(centerX - radius + shadowOffset, centerY - radius + shadowOffset,
                    radius * 2, radius * 2);

            g2.setColor(FACE);
            g2.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

            g2.setStroke(new BasicStroke(8f));
            g2.setColor(RIM);
            g2.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

            g2.setStroke(new BasicStroke(2f));
            g2.setColor(new Color(200, 209, 221));
            g2.drawOval(centerX - radius + 16, centerY - radius + 16,
                    (radius - 16) * 2, (radius - 16) * 2);
        }

        private void paintTicksAndNumbers(Graphics2D g2, int centerX, int centerY, int radius) {
            g2.setColor(TICK);

            for (int i = 0; i < 60; i++) {
                double angle = Math.toRadians(i * 6 - 90);
                boolean hourTick = i % 5 == 0;
                int outer = radius - 18;
                int inner = hourTick ? radius - 42 : radius - 30;

                double x1 = centerX + Math.cos(angle) * inner;
                double y1 = centerY + Math.sin(angle) * inner;
                double x2 = centerX + Math.cos(angle) * outer;
                double y2 = centerY + Math.sin(angle) * outer;

                g2.setStroke(new BasicStroke(hourTick ? 4f : 1.4f,
                        BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2.draw(new Line2D.Double(x1, y1, x2, y2));
            }

            g2.setFont(new Font("SansSerif", Font.BOLD, Math.max(18, radius / 8)));
            FontMetrics metrics = g2.getFontMetrics();
            g2.setColor(TEXT);

            for (int hour = 1; hour <= 12; hour++) {
                double angle = Math.toRadians(hour * 30 - 90);
                String text = String.valueOf(hour);
                int textX = (int) (centerX + Math.cos(angle) * (radius - 70) - metrics.stringWidth(text) / 2.0);
                int textY = (int) (centerY + Math.sin(angle) * (radius - 70) + metrics.getAscent() / 2.8);
                g2.drawString(text, textX, textY);
            }
        }

        private void paintHands(Graphics2D g2, int centerX, int centerY, int radius, LocalTime time) {
            double hourAngle = (time.getHour() % 12 + time.getMinute() / 60.0) * 30;
            double minuteAngle = (time.getMinute() + time.getSecond() / 60.0) * 6;
            double secondAngle = time.getSecond() * 6;

            drawHand(g2, centerX, centerY, hourAngle, radius * 0.48, 9f, HOUR_HAND);
            drawHand(g2, centerX, centerY, minuteAngle, radius * 0.66, 6f, MINUTE_HAND);
            drawHand(g2, centerX, centerY, secondAngle, radius * 0.78, 2.2f, SECOND_HAND);
        }

        private void drawHand(Graphics2D g2, int centerX, int centerY,
                              double clockAngle, double length, float strokeWidth, Color color) {
            double angle = Math.toRadians(clockAngle - 90);
            double endX = centerX + Math.cos(angle) * length;
            double endY = centerY + Math.sin(angle) * length;

            g2.setColor(color);
            g2.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.draw(new Line2D.Double(centerX, centerY, endX, endY));
        }

        private void paintCenterCap(Graphics2D g2, int centerX, int centerY) {
            g2.setColor(RIM);
            g2.fill(new Ellipse2D.Double(centerX - 9, centerY - 9, 18, 18));
            g2.setColor(SECOND_HAND);
            g2.fill(new Ellipse2D.Double(centerX - 4, centerY - 4, 8, 8));
        }

        private void paintDigitalTime(Graphics2D g2, int width, int baselineY, LocalTime time) {
            String digital = time.format(TIME_FORMAT);
            String date = LocalDate.now().format(DATE_FORMAT);

            g2.setFont(new Font("Consolas", Font.BOLD, 26));
            FontMetrics timeMetrics = g2.getFontMetrics();
            g2.setColor(TEXT);
            g2.drawString(digital, (width - timeMetrics.stringWidth(digital)) / 2, baselineY);

            g2.setFont(new Font("SansSerif", Font.PLAIN, 13));
            FontMetrics dateMetrics = g2.getFontMetrics();
            g2.setColor(new Color(84, 96, 112));
            g2.drawString(date, (width - dateMetrics.stringWidth(date)) / 2, baselineY + 24);
        }
    }
}
