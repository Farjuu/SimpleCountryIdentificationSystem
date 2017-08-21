import javax.swing.ImageIcon;

public class Model {

	private String countryName;
	private ImageIcon img;

	public Model(String name) {
		countryName = name.replaceAll("_", " ");
		char[] namearray = countryName.toCharArray();

		namearray[0] = Character.toUpperCase(namearray[0]);
		countryName = String.valueOf(namearray);
		img = new ImageIcon(getClass().getResource("/countryflags/no_flag.png"));
		try {
			img = new ImageIcon(getClass().getResource("/countryflags/" + name + ".png"));
		} catch (Exception e) {

		}

	}

	public String getName() {
		return countryName;
	}

	public ImageIcon getIcon() {
		return img;
	}

}
