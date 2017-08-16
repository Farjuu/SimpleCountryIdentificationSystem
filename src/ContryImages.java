import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ContryImages {
	static final File dir = new File(System.getProperty("user.dir") + "\\images\\countrys\\".replaceAll("\\\\", "/"));

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

		if (dir.isDirectory()) { // make sure it's a directory
			for (final File f : dir.listFiles(IMAGE_FILTER)) {
				
				if(f.getName().equals("no_flag.png")){
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
		}
	}
	
	
	public ArrayList<BufferedImage> getCountryImagees(){
		return images;
	}
	
	
	

}
