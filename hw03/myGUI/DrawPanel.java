package homework3.myGUI;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class DrawPanel extends JPanel {
	// 手动管理版本
	private static final long serialVersionUID = 1L;
	private Shape[] shapes;

	public DrawPanel() {}

	public DrawPanel(Shape[] shapes) {
		setBackground(Color.WHITE);
		this.shapes = shapes;
	}

	@Override
	public void paintComponent(Graphics g) {
		// 相当于重新给面板刷漆，防止旧画面残留造成残影
		super.paintComponent(g);
		if (shapes == null) return;

		for (Shape shape : shapes) {
			if (shape != null) {
				shape.draw(g);
			}
		}
	}	
}
