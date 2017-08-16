import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Model {

	public String countryName;
	public BufferedImage img;

	public Model(String name) {
		String path = System.getProperty("user.dir") + "\\images\\countrys\\" + name + ".png";
		countryName = name.replaceAll("_", " ");
		char[] namearray = countryName.toCharArray();

		namearray[0] = Character.toUpperCase(namearray[0]);
		countryName = String.valueOf(namearray);
		path = path.replaceAll("\\\\", "/");

		try {
			img = ImageIO
					.read(new File(System.getProperty("user.dir") + "\\images\\countrys\\no_flag.png".replaceAll("\\\\", "/")));
		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}

		try {
			img = ImageIO.read(new File(path));
		} catch (Exception e) {
			
		}

	}

	public String getName() {
		return countryName;
	}

	public BufferedImage getIcon() {

		/*
		 * BufferedImage scaledImage = new BufferedImage(60, 40,
		 * BufferedImage.TYPE_INT_ARGB); Graphics2D graphics2D =
		 * scaledImage.createGraphics();
		 * graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		 * RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		 * graphics2D.drawImage(img, 0, 0, 60, 40, null); graphics2D.dispose();
		 */
		return img;
	}

}
