import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MyCustomPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage imagebc;

	public MyCustomPanel() {
		try {
			imagebc = ImageIO.read(this.getClass().getResourceAsStream("/icons/listbckg.png"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.drawImage(imagebc, 0, 0, getWidth(), getHeight(), this);
	}

}
