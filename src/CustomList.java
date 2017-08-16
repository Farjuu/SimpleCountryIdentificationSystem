import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class CustomList {

	private JFrame frame = new JFrame("Country List");
	private JScrollPane scrollPane = new JScrollPane();
	private JPanel panel = new JPanel();
	@SuppressWarnings("rawtypes")
	private Hashtable[] solution;
	private ArrayList<Model> list;
	private JButton button;
	private String path = System.getProperty("user.dir") + "\\images\\";
	private BufferedImage img = null;
	private int xm, ym;

	public CustomList(@SuppressWarnings("rawtypes") Hashtable[] solution, JButton button) {
		this.button = button;
		this.solution = solution;
		initComponents();
		
		if(solution.length<3){
			frame.setBounds(530, 200, 415, 140*solution.length);
		}else
			frame.setBounds(530, 200, 415, 350);

		
		

		frame.setUndecorated(true);
		frame.getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
		
		this.button.setEnabled(false);
		frame.setVisible(true);
		
		//3C3F41
		
		
		//frame.pack();

	}

	public void initComponents() {
		list = new ArrayList<>();
		path = path.replaceAll("\\\\", "/");

		for (Hashtable<?, ?> h : solution) {
			list.add(new Model(h.get("X").toString()));
		}

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(Box.createVerticalStrut(2));

		for (Model h : list) {

			MyCustomPanel image_text_container = new MyCustomPanel();
			image_text_container.setLayout(new BorderLayout(0, 0));
			image_text_container.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					JLabel lb = (JLabel) image_text_container.getComponent(1);
					System.out.println(lb.getText());
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub

				}
			});

			JLabel imageContainer = new JLabel(new ImageIcon(h.getIcon()));
			imageContainer.setBorder(new EmptyBorder(25, 20, 25, 0));
			image_text_container.add(imageContainer, BorderLayout.WEST);

			JLabel name_view = new JLabel(h.getName());
			name_view.setFont(new Font("Tahoma", Font.PLAIN, 28));
			name_view.setBorder(new EmptyBorder(25, 40, 25, 0));
			name_view.setForeground(Color.decode("#ffffff"));
			image_text_container.add(name_view, BorderLayout.CENTER);
			image_text_container.setOpaque(false);

			panel.add(image_text_container);
			panel.add(Box.createVerticalStrut(3));
		}

		panel.setBackground(Color.decode("#2B2B2B"));
		
		scrollPane.setBackground(Color.decode("#2B2B2B"));
		
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		
		scrollPane.setViewportView(panel);

		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel header = new JPanel();
		header.setLayout(new BorderLayout(0, 0));
		header.setBackground(Color.decode("#3C3F41"));
		//3C3F41
		try {
			img = ImageIO.read(new File(path + "closebtn.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JButton closebtn = new JButton("", new ImageIcon(img));

		closebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				button.setEnabled(true);
				frame.dispose();
			}
		});
		closebtn.setOpaque(false);
		closebtn.setContentAreaFilled(false);
		closebtn.setBorderPainted(false);
		closebtn.setFocusable(false);
		JLabel title = new JLabel("Country List");
		title.setBorder(BorderFactory.createEmptyBorder(0, 160, 0, 0));
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Tahoma", Font.PLAIN, 18));

		header.add(title, BorderLayout.WEST);
		header.add(closebtn, BorderLayout.EAST);
		header.add(Box.createVerticalStrut(5), BorderLayout.SOUTH);
		header.add(Box.createVerticalStrut(5), BorderLayout.NORTH);

		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xm = e.getX();
				ym = e.getY();
			}
		});
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {

				frame.setLocation(e.getXOnScreen() - xm, e.getYOnScreen() - ym);
			}
		});

		frame.getContentPane().add(header, BorderLayout.NORTH);
		// JScrollBar sb = scrollPane.getVerticalScrollBar();
		// sb.setUI(new MyScrollbarUI());
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

	}

}
