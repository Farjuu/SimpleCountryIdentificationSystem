import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ContryImages {
	private File dir;

	static final String[] EXTENSIONS = new String[] { "png" };
	// filter to identify images based on their extensions
	static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

		@Override
		public boolean accept(final File dir, final String name) {
			for (final String ext : EXTENSIONS) {
				if (name.endsWith("." + ext)) {
					return (true);
				}
			}
			return (false);
		}
	};

	public ArrayList<BufferedImage> images;

	public ContryImages() {

		images = new ArrayList<>();

		URL url = this.getClass().getResource("countryflags/");
		if (url == null) {
			// error - missing folder
		} else {
			try {
				dir = new File(url.toURI());

				for (File f : dir.listFiles(IMAGE_FILTER)) {
					
					if (f.getName().equals("no_flag.png")) {
						continue;
					}
					BufferedImage img = null;

					try {
						img = ImageIO.read(f);

						images.add(img);
					} catch (final IOException e) {
						// handle errors here
					}

				}
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public ArrayList<BufferedImage> getCountryImagees() {
		return images;
	}

}
