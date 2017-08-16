
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.JLabel;

public class MyComboBoxEditor implements ComboBoxEditor {

	private JLabel label = new JLabel();

	public MyComboBoxEditor() {
		label.setOpaque(false);
		label.setForeground(Color.WHITE);
		label.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

	}

	@Override
	public void addActionListener(ActionListener ac) {

	}

	@Override
	public Component getEditorComponent() {
		// TODO Auto-generated method stub
		return label;

	}

	@Override
	public Object getItem() {
		// TODO Auto-generated method stub
		return label.getText();

	}

	@Override
	public void removeActionListener(ActionListener arg0) {

	}

	@Override
	public void selectAll() {

	}

	@Override
	public void setItem(Object arg0) {
		if (arg0 != null) {
			label.setText(arg0.toString());
		}

	}

}
