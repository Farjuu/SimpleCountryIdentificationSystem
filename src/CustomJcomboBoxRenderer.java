import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class CustomJcomboBoxRenderer extends JLabel implements ListCellRenderer<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int mHoveredJListIndex = -1;

	public CustomJcomboBoxRenderer() {
		setOpaque(true);
		setBackground(Color.decode("#2B2B2B"));
		setForeground(Color.decode("#BAB428"));

	}

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		// TODO Auto-generated method stub

		Color backgroundColor = mHoveredJListIndex == index ? Color.gray : Color.decode("#2B2B2B");
		Color foregroundColor = mHoveredJListIndex == index ? Color.white : Color.decode("#BAB428");
		list.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent me) {
				Point p = new Point(me.getX(), me.getY());
				int index = list.locationToIndex(p);
				if (index != mHoveredJListIndex) {
					mHoveredJListIndex = index;
					list.repaint();
				}
			}
		});

		setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		setForeground(foregroundColor);
		setBackground(backgroundColor);
		setText((String) value);
		setFont(list.getFont());

		return this;

	}

}
