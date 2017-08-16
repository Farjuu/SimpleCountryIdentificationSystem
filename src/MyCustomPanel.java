import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MyCustomPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String path = System.getProperty("user.dir") + "\\images\\";
	private BufferedImage imagebc;

	public MyCustomPanel() {
		path = path.replaceAll("\\\\", "/");
		try {
			imagebc = ImageIO.read(new File(path + "listbckg.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.drawImage(imagebc, 0, 0, getWidth(), getHeight(), this);
	}
}
