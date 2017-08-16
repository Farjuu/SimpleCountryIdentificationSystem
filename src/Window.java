import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import jpl.Query;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;

public class Window {

	private JFrame frame;
	private String string_query = "";
	private LetterSelectionHelper letterSelectionHelper;
	private JPanel query_making_bckg;

	private JButton btnExecute;
	private JButton btnreset;

	private final String filepath = "consult('" + System.getProperty("user.dir")
			+ "\\resource\\Identify_countries.pl')";
	private boolean isConnected = new Query(filepath.replaceAll("\\\\", "/")).hasSolution();
	private Color whtColor = Color.decode("#ffffff");
	private int xm, ym;
	private int slider_op_counter = 1;
	private int slider_image_counter = 0;

	private JComboBox<String> letter_selection_dropdown;
	private JComboBox<String> currency_selection_dropdown;
	private JComboBox<String> devolopingstate_selection_dropdown;

	private JRadioButton weather_none_button;
	private JRadioButton continen_none_button;
	private JRadioButton religious_none_button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();

		frame.setBounds(100, 100, 800, 385);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.decode("#2B2B2B"));
		frame.setUndecorated(true);
		frame.getRootPane().setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, Color.decode("#5C5C5C")));

		init_header();

		// initialize Letter Selector drop down
		init_firstletter_dropdown();

		// initialize Currency area
		init_currency_dropdown();

		// initialize Religion area
		init_relious_area();

		// initialize continent area
		init_continent_area();

		// initialize weather area
		init_wheather_area();

		init_devolopingstate_dropdown();

		JLabel make_your_query = new JLabel("Make Your Query Here");
		make_your_query.setForeground(new Color(204, 255, 51));
		make_your_query.setBounds(360, 93, 130, 14);

		frame.getContentPane().add(make_your_query);

		btnExecute = new CustomButton("Execute");
		btnExecute.setBounds(301, 319, 99, 40);
		frame.getContentPane().add(btnExecute);

		btnExecute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isConnected) {

					System.out.println(string_query);

					if (string_query.length() == 0) {
						JOptionPane.showMessageDialog(frame, "You Must Provide\nA hint to Find Resuit");
					} else {
						Hashtable<?, ?>[] solution;
						try {
							solution = new Query(string_query).allSolutions();

							if (solution.length > 0) {
								new CustomList(solution, btnExecute);
							} else {
								JOptionPane.showMessageDialog(frame, "Sorry No country available\n for the query");
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});

		btnreset = new CustomButton("Reset");
		btnreset.setBounds(411, 319, 99, 40);
		btnreset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				letter_selection_dropdown.setSelectedIndex(0);
				currency_selection_dropdown.setSelectedIndex(0);
				devolopingstate_selection_dropdown.setSelectedIndex(0);

				weather_none_button.setSelected(true);
				continen_none_button.setSelected(true);
				religious_none_button.setSelected(true);
				string_query = "";

			}
		});

		frame.getContentPane().add(btnreset);

		query_making_bckg = new JPanel();
		query_making_bckg.setBounds(10, 61, 776, 310);
		query_making_bckg.setBackground(Color.decode("#3C3F41"));
		query_making_bckg.setBorder(BorderFactory.createLineBorder(Color.decode("#6A8759")));
		frame.getContentPane().add(query_making_bckg);

	}

	private void init_header() {

		JPanel framePanel = new JPanel();
		framePanel.setBackground(Color.decode("#3C3F41"));
		framePanel.setLayout(new BorderLayout(0, 0));

		framePanel.setBounds(0, 0, 795, 50);

		framePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				xm = e.getX();
				ym = e.getY();
			}
		});
		framePanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {

				frame.setLocation(e.getXOnScreen() - xm, e.getYOnScreen() - ym);
			}
		});

		JLabel project_title = new JLabel("Country Identification System");
		project_title.setFont(new Font("Tahoma", Font.PLAIN, 28));
		project_title.setForeground(whtColor);
		framePanel.add(project_title, BorderLayout.CENTER);

		JPanel button_holder = new JPanel();
		button_holder.setLayout(new BorderLayout(0, 0));
		button_holder.setOpaque(false);

		String path = System.getProperty("user.dir") + "\\images\\".replaceAll("\\\\", "/");
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path + "closebtnm.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JButton close_btn = new JButton("", new ImageIcon(img)) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Dimension getPreferredSize() {
				return new Dimension(20, 20);
			}

		};
		close_btn.setOpaque(false);
		close_btn.setContentAreaFilled(false);
		close_btn.setBorderPainted(false);
		close_btn.setFocusable(false);
		close_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		try {
			img = ImageIO.read(new File(path + "minbtn.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JButton min_btn = new JButton("", new ImageIcon(img));
		min_btn.setOpaque(false);
		min_btn.setContentAreaFilled(false);
		min_btn.setBorderPainted(false);
		min_btn.setFocusable(false);
		min_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setState(Frame.ICONIFIED);
			}
		});

		button_holder.add(min_btn, BorderLayout.CENTER);
		button_holder.add(close_btn, BorderLayout.EAST);

		framePanel.add(button_holder, BorderLayout.EAST);

		add_slider(framePanel);

		frame.getContentPane().add(framePanel);

	}

	private void init_firstletter_dropdown() {

		letter_selection_dropdown = new JComboBox<String>();
		letter_selection_dropdown.setEditable(true);
		letter_selection_dropdown.setOpaque(false);
		letter_selection_dropdown.setBorder(BorderFactory.createLineBorder(Color.decode("#3E86A0")));
		letter_selection_dropdown.setRenderer(new CustomJcomboBoxRenderer());
		letter_selection_dropdown.setEditor(new MyComboBoxEditor());

		letter_selection_dropdown.setForeground(whtColor);

		for (Component component : letter_selection_dropdown.getComponents()) {
			System.out.println(component.getClass());
			if (component instanceof AbstractButton) {
				letter_selection_dropdown.remove(component);
			}

		}

		letterSelectionHelper = new LetterSelectionHelper();
		JLabel lblSelectFirstLetter = new JLabel("Select First Letter :");
		lblSelectFirstLetter.setForeground(Color.decode("#A9B7C6"));
		lblSelectFirstLetter.setBounds(20, 150, 109, 14);
		frame.getContentPane().add(lblSelectFirstLetter);

		letter_selection_dropdown.setBounds(135, 147, 150, 20);

		JLabel bt = new JLabel("˅");
		bt.setForeground(whtColor);
		bt.setBounds(270, 147, 7, 20);
		frame.getContentPane().add(bt);

		letter_selection_dropdown.addItem("Any");

		for (int i = 'A'; i <= 'Z'; i++) {
			letter_selection_dropdown.addItem(Character.toString(((char) i)));
		}

		frame.getContentPane().add(letter_selection_dropdown);

		letter_selection_dropdown.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String value = letter_selection_dropdown.getSelectedItem().toString();
					string_query = letterSelectionHelper.get_startwith_query(string_query, value.toLowerCase());

				}
			}
		});

	}

	private void init_currency_dropdown() {
		currency_selection_dropdown = new JComboBox<String>();
		currency_selection_dropdown.setEditable(true);
		currency_selection_dropdown.setOpaque(false);
		currency_selection_dropdown.setBorder(BorderFactory.createLineBorder(Color.decode("#3E86A0")));
		currency_selection_dropdown.setRenderer(new CustomJcomboBoxRenderer());
		currency_selection_dropdown.setEditor(new MyComboBoxEditor());
		for (Component component : currency_selection_dropdown.getComponents()) {
			System.out.println(component.getClass());
			if (component instanceof AbstractButton) {

				currency_selection_dropdown.remove(component);
			}

		}

		JLabel lblSelectCurrency = new JLabel("Select Currency :");
		lblSelectCurrency.setForeground(Color.decode("#A9B7C6"));
		lblSelectCurrency.setBounds(20, 190, 109, 14);
		frame.getContentPane().add(lblSelectCurrency);

		JLabel bt = new JLabel("˅");
		bt.setForeground(whtColor);
		bt.setBounds(270, 187, 7, 20);
		frame.getContentPane().add(bt);

		currency_selection_dropdown.setBounds(135, 187, 150, 20);
		currency_selection_dropdown.addItem("Any");
		currency_selection_dropdown.addItem("Afghan Afghani");
		currency_selection_dropdown.addItem("Bhutanese Ngultrum");
		currency_selection_dropdown.addItem("Dollar");
		currency_selection_dropdown.addItem("Dirham");
		currency_selection_dropdown.addItem("European Euro");
		currency_selection_dropdown.addItem("Krona");
		currency_selection_dropdown.addItem("Rupee");
		currency_selection_dropdown.addItem("Ruble");
		currency_selection_dropdown.addItem("Swiss Franc");
		currency_selection_dropdown.addItem("Taka");
		currency_selection_dropdown.addItem("Yen");
		frame.getContentPane().add(currency_selection_dropdown);

		currency_selection_dropdown.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String value = currency_selection_dropdown.getSelectedItem().toString();
					string_query = new Querymaker().get_startwith_query("currency", string_query,
							value.trim().toLowerCase().replaceAll(" ", "_"));

				}
			}
		});

	}

	private void init_relious_area() {

		// Making action for radio buttons
		ActionListener sliceActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton aButton = (AbstractButton) actionEvent.getSource();

				string_query = new Querymaker().get_startwith_query("main_religion", string_query,
						aButton.getText().trim().toLowerCase());

			}
		};

		// initializing radio buttons and setting up there Actions
		religious_none_button = new JRadioButton("   Any");
		religious_none_button.setOpaque(false);
		religious_none_button.setFocusable(false);
		religious_none_button.setForeground(Color.decode("#CB772F"));
		religious_none_button.addActionListener(sliceActionListener);

		JRadioButton islam = new JRadioButton("   Muslim");
		islam.setOpaque(false);
		islam.setFocusable(false);
		islam.setForeground(Color.decode("#CB772F"));
		islam.addActionListener(sliceActionListener);

		JRadioButton hindu = new JRadioButton("   Hindu");
		hindu.setOpaque(false);
		hindu.setFocusable(false);
		hindu.setForeground(Color.decode("#CB772F"));
		hindu.addActionListener(sliceActionListener);

		JRadioButton christian = new JRadioButton("   Christian");
		christian.setOpaque(false);
		christian.setFocusable(false);
		christian.setForeground(Color.decode("#CB772F"));
		christian.addActionListener(sliceActionListener);

		JRadioButton catholic = new JRadioButton("   Catholic");
		catholic.setOpaque(false);
		catholic.setFocusable(false);
		catholic.setForeground(Color.decode("#CB772F"));
		catholic.addActionListener(sliceActionListener);

		JRadioButton shinto = new JRadioButton("   Shinto");
		shinto.setOpaque(false);
		shinto.setFocusable(false);
		shinto.setForeground(Color.decode("#CB772F"));
		shinto.addActionListener(sliceActionListener);

		JRadioButton buddist = new JRadioButton("   Buddhist");
		buddist.setOpaque(false);
		buddist.setFocusable(false);
		buddist.setForeground(Color.decode("#CB772F"));
		buddist.addActionListener(sliceActionListener);

		// Making group of buttons to make sure only one is selected
		ButtonGroup religion_button_group = new ButtonGroup();
		religion_button_group.add(religious_none_button);
		religion_button_group.add(islam);
		religion_button_group.add(hindu);
		religion_button_group.add(christian);
		religion_button_group.add(catholic);
		religion_button_group.add(shinto);
		religion_button_group.add(buddist);

		// Initially selecting none button
		religious_none_button.setSelected(true);

		// Making area of religion sector
		JPanel r_lbl_bckg = new JPanel();
		r_lbl_bckg.setBounds(350, 121, 50, 14);
		r_lbl_bckg.setBackground(Color.decode("#3C3F41"));
		r_lbl_bckg.setLayout(new GridLayout(0, 1));
		JLabel label = new JLabel(" Religion");
		label.setForeground(new Color(0, 204, 0));
		r_lbl_bckg.add(label);

		JPanel relious_area_bckg = new JPanel();
		relious_area_bckg.setOpaque(false);
		relious_area_bckg.setBounds(310, 127, 130, 170);
		relious_area_bckg.setBorder(BorderFactory.createLineBorder((new Color(0, 204, 0))));
		relious_area_bckg.setLayout(new GridLayout(0, 1));

		// adding button to religion area
		relious_area_bckg.add(religious_none_button);
		relious_area_bckg.add(islam);
		relious_area_bckg.add(hindu);
		relious_area_bckg.add(christian);
		relious_area_bckg.add(catholic);
		relious_area_bckg.add(shinto);
		relious_area_bckg.add(buddist);

		// adding religion area to main frame
		frame.getContentPane().add(r_lbl_bckg);
		frame.getContentPane().add(relious_area_bckg);

	}

	private void init_continent_area() {

		// Making action for radio buttons
		ActionListener sliceActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton aButton = (AbstractButton) actionEvent.getSource();

				string_query = new Querymaker().get_startwith_query("continent", string_query,
						aButton.getText().trim().toLowerCase().replaceAll(" ", "_"));
			}
		};

		// initializing radio buttons and setting up there Actions
		continen_none_button = new JRadioButton("   Any");
		continen_none_button.setOpaque(false);
		continen_none_button.setFocusable(false);
		continen_none_button.setForeground(Color.decode("#CB772F"));
		continen_none_button.addActionListener(sliceActionListener);

		JRadioButton asia = new JRadioButton("   Asia");
		asia.setOpaque(false);
		asia.setFocusable(false);
		asia.setForeground(Color.decode("#CB772F"));
		asia.addActionListener(sliceActionListener);

		JRadioButton africa = new JRadioButton("   Africa");
		africa.setOpaque(false);
		africa.setFocusable(false);
		africa.setForeground(Color.decode("#CB772F"));
		africa.addActionListener(sliceActionListener);

		JRadioButton north_america = new JRadioButton("   North America");
		north_america.setOpaque(false);
		north_america.setFocusable(false);
		north_america.setForeground(Color.decode("#CB772F"));
		north_america.addActionListener(sliceActionListener);

		JRadioButton south_america = new JRadioButton("   South America");
		south_america.setOpaque(false);
		south_america.setFocusable(false);
		south_america.setForeground(Color.decode("#CB772F"));
		south_america.addActionListener(sliceActionListener);

		JRadioButton antarctica = new JRadioButton("   Antarctica");
		antarctica.setOpaque(false);
		antarctica.setFocusable(false);
		antarctica.setForeground(Color.decode("#CB772F"));
		antarctica.addActionListener(sliceActionListener);

		JRadioButton europe = new JRadioButton("   Europe");
		europe.setOpaque(false);
		europe.setFocusable(false);
		europe.setForeground(Color.decode("#CB772F"));
		europe.addActionListener(sliceActionListener);

		JRadioButton australia = new JRadioButton("   Australia");
		australia.setOpaque(false);
		australia.setFocusable(false);
		australia.setForeground(Color.decode("#CB772F"));
		australia.addActionListener(sliceActionListener);

		// Making group of buttons to make sure only one is selected
		ButtonGroup continent_button_group = new ButtonGroup();
		continent_button_group.add(continen_none_button);
		continent_button_group.add(asia);
		continent_button_group.add(africa);
		continent_button_group.add(north_america);
		continent_button_group.add(south_america);
		continent_button_group.add(antarctica);
		continent_button_group.add(europe);
		continent_button_group.add(australia);

		// Initially selecting none button
		continen_none_button.setSelected(true);

		// Making area of continent sector
		JPanel r_lbl_bckg = new JPanel();
		r_lbl_bckg.setBounds(510, 121, 60, 14);
		r_lbl_bckg.setBackground(Color.decode("#3C3F41"));
		r_lbl_bckg.setLayout(new GridLayout(0, 1));
		JLabel label = new JLabel(" Continent");
		label.setForeground(new Color(0, 207, 0));
		r_lbl_bckg.add(label);

		JPanel continent_area_bckg = new JPanel();
		continent_area_bckg.setOpaque(false);
		continent_area_bckg.setBounds(470, 127, 130, 170);
		continent_area_bckg.setBorder(BorderFactory.createLineBorder((new Color(0, 207, 0))));
		continent_area_bckg.setLayout(new GridLayout(0, 1));

		// adding button to continent area

		continent_area_bckg.add(continen_none_button);
		continent_area_bckg.add(asia);
		continent_area_bckg.add(africa);
		continent_area_bckg.add(north_america);
		continent_area_bckg.add(south_america);
		continent_area_bckg.add(antarctica);
		continent_area_bckg.add(europe);
		continent_area_bckg.add(australia);

		// adding continent area to main frame
		frame.getContentPane().add(r_lbl_bckg);
		frame.getContentPane().add(continent_area_bckg);

	}

	private void init_wheather_area() {

		// Making action for radio buttons
		ActionListener sliceActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton aButton = (AbstractButton) actionEvent.getSource();
				string_query = new Querymaker().get_startwith_query("weather", string_query,
						aButton.getText().trim().toLowerCase().replaceAll(" ", "_"));

			}
		};

		// initializing radio buttons and setting up there Actions
		weather_none_button = new JRadioButton("   Any");
		weather_none_button.setOpaque(false);
		weather_none_button.setFocusable(false);
		weather_none_button.setForeground(Color.decode("#CB772F"));
		weather_none_button.addActionListener(sliceActionListener);

		JRadioButton moist = new JRadioButton("   Moist");
		moist.setOpaque(false);
		moist.setFocusable(false);
		moist.setForeground(Color.decode("#CB772F"));
		moist.addActionListener(sliceActionListener);

		JRadioButton hot = new JRadioButton("   Hot");
		hot.setOpaque(false);
		hot.setFocusable(false);
		hot.setForeground(Color.decode("#CB772F"));
		hot.addActionListener(sliceActionListener);

		JRadioButton cold = new JRadioButton("   Cold");
		cold.setOpaque(false);
		cold.setFocusable(false);
		cold.setForeground(Color.decode("#CB772F"));
		cold.addActionListener(sliceActionListener);

		JRadioButton dry = new JRadioButton("   Dry");
		dry.setOpaque(false);
		dry.setFocusable(false);
		dry.setForeground(Color.decode("#CB772F"));
		dry.addActionListener(sliceActionListener);

		JRadioButton extreme_mixed = new JRadioButton("   Extreme Mixed");
		extreme_mixed.setOpaque(false);
		extreme_mixed.setFocusable(false);
		extreme_mixed.setForeground(Color.decode("#CB772F"));
		extreme_mixed.addActionListener(sliceActionListener);

		// Making group of buttons to make sure only one is selected
		ButtonGroup weather_button_group = new ButtonGroup();

		// Adding Buttons to the group

		weather_button_group.add(weather_none_button);
		weather_button_group.add(moist);
		weather_button_group.add(hot);
		weather_button_group.add(cold);
		weather_button_group.add(dry);
		weather_button_group.add(extreme_mixed);

		// Initially selecting none button
		weather_none_button.setSelected(true);

		// Making area of continent sector
		JPanel w_lbl_bckg = new JPanel();
		w_lbl_bckg.setBounds(670, 121, 54, 14);
		w_lbl_bckg.setBackground(Color.decode("#3C3F41"));
		w_lbl_bckg.setLayout(new GridLayout(0, 1));
		JLabel label = new JLabel(" Weather");
		label.setForeground(new Color(0, 207, 0));
		w_lbl_bckg.add(label);

		JPanel weather_area_bckg = new JPanel();
		weather_area_bckg.setOpaque(false);
		weather_area_bckg.setBounds(630, 127, 130, 170);
		weather_area_bckg.setBorder(BorderFactory.createLineBorder((new Color(0, 207, 0))));
		weather_area_bckg.setLayout(new GridLayout(0, 1));

		// adding button to continent area

		weather_area_bckg.add(weather_none_button);
		weather_area_bckg.add(moist);
		weather_area_bckg.add(hot);
		weather_area_bckg.add(cold);
		weather_area_bckg.add(dry);
		weather_area_bckg.add(extreme_mixed);

		// adding continent area to main frame
		frame.getContentPane().add(w_lbl_bckg);
		frame.getContentPane().add(weather_area_bckg);

	}

	private void add_slider(JPanel panel) {
		JLabel image_lbl = new JLabel();
		image_lbl.setBorder(BorderFactory.createEmptyBorder(0, 230, 0, 5));

		Thread t = new Thread(new Runnable() {
			ArrayList<BufferedImage> images = new ContryImages().getCountryImagees();
			Image img1;

			@Override
			public void run() {
				// TODO Auto-generated method stub
				image_lbl.setBorder(BorderFactory.createEmptyBorder(0, 170, 0, 5));
				while (true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					img1 = images.get(slider_image_counter);
					BufferedImage scaledImage = new BufferedImage(60, 40, BufferedImage.TYPE_INT_ARGB);
					Graphics2D graphics2D = scaledImage.createGraphics();
					AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
							(float) slider_op_counter / 20);
					graphics2D.setComposite(ac);
					graphics2D.drawImage(img1, 0, 0, img1.getWidth(null), img1.getHeight(null), null);
					graphics2D.dispose();

					image_lbl.setIcon(new ImageIcon(scaledImage));
					if (slider_op_counter == 20) {
						slider_op_counter = 1;
						if (slider_image_counter == images.size() - 1) {
							slider_image_counter = 0;
						} else
							slider_image_counter++;
					} else
						slider_op_counter++;

					frame.revalidate();

				}

			}
		});
		t.start();

		panel.add(image_lbl, BorderLayout.WEST);
	}

	private void init_devolopingstate_dropdown() {
		devolopingstate_selection_dropdown = new JComboBox<String>();
		devolopingstate_selection_dropdown.setEditable(true);
		devolopingstate_selection_dropdown.setOpaque(false);
		devolopingstate_selection_dropdown.setBorder(BorderFactory.createLineBorder(Color.decode("#3E86A0")));
		devolopingstate_selection_dropdown.setRenderer(new CustomJcomboBoxRenderer());
		devolopingstate_selection_dropdown.setEditor(new MyComboBoxEditor());
		for (Component component : devolopingstate_selection_dropdown.getComponents()) {
			System.out.println(component.getClass());
			if (component instanceof AbstractButton) {

				devolopingstate_selection_dropdown.remove(component);
			}

		}

		JLabel lblSelectCurrency = new JLabel("Developing Status :");
		lblSelectCurrency.setForeground(Color.decode("#A9B7C6"));
		lblSelectCurrency.setBounds(20, 230, 109, 14);
		frame.getContentPane().add(lblSelectCurrency);

		JLabel bt = new JLabel("˅");
		bt.setForeground(whtColor);
		bt.setBounds(270, 227, 7, 20);
		frame.getContentPane().add(bt);

		devolopingstate_selection_dropdown.setBounds(135, 227, 150, 20);
		devolopingstate_selection_dropdown.addItem("Any");
		devolopingstate_selection_dropdown.addItem("Developed");
		devolopingstate_selection_dropdown.addItem("Developing");
		devolopingstate_selection_dropdown.addItem("Underdeveloped");
		frame.getContentPane().add(devolopingstate_selection_dropdown);

		devolopingstate_selection_dropdown.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String value = devolopingstate_selection_dropdown.getSelectedItem().toString();
					string_query = new CurrencyQuerymaker()
							.get_startwith_query(value.trim().toLowerCase().replaceAll(" ", "_"), string_query, "");

				}
			}
		});

	}

}
