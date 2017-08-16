import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class CustomButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Color color1 = Color.decode("#344963");
	Color color2 = Color.decode("#28394E");

	CustomButton(String btn_name) {
		super(btn_name);
		setForeground(Color.decode("#A3BABA"));
		setContentAreaFilled(false);
		setFocusPainted(false);
		setFocusable(false);// used for demonstration
		setBorder(BorderFactory.createEmptyBorder());

		DropShadowBorder shadow = new DropShadowBorder();
		shadow.setShadowColor(Color.decode("#79D7FE"));
		shadow.setShowLeftShadow(true);
		shadow.setShowRightShadow(true);
		shadow.setShowBottomShadow(true);
		shadow.setShowTopShadow(true);
		shadow.setCornerSize(5);
		setBorder(shadow);
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				setBorderPainted(false);
				DropShadowBorder shadow = new DropShadowBorder();
				shadow.setShadowColor(Color.decode("#79D7FE"));
				shadow.setShowLeftShadow(true);
				shadow.setShowRightShadow(true);
				shadow.setShowBottomShadow(true);
				shadow.setShowTopShadow(true);
				shadow.setCornerSize(5);
				setBorder(shadow);
				setBorderPainted(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				setBorderPainted(false);
				DropShadowBorder shadow = new DropShadowBorder();
				shadow.setShadowColor(Color.red);
				shadow.setShowLeftShadow(true);
				shadow.setShowRightShadow(true);
				shadow.setShowBottomShadow(true);
				shadow.setShowTopShadow(true);
				shadow.setCornerSize(5);
				setBorder(shadow);
				setBorderPainted(true);

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();

		g2.setPaint(new GradientPaint(new Point(0, 0), color1, new Point(0, getHeight()), color2));
		g2.fillRect(5, 5, getWidth() - 10, getHeight() - 10);
		g2.dispose();

		super.paintComponent(g);
	}

}
