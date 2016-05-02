package whs.gdi2.tippspiel.gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.JLabel;

public class SplashFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SplashFrame frame = new SplashFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		System.out.println(imgURL);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Create the frame.
	 */
	private BufferedImage image;

	public SplashFrame() {
		setResizable(false);
		setType(Type.UTILITY);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JTextPane txtpnEmTippspiel = new JTextPane();
		txtpnEmTippspiel.setEditable(false);
		txtpnEmTippspiel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtpnEmTippspiel.setBackground(UIManager.getColor("Button.background"));
		txtpnEmTippspiel.setText("EM 2016 Tippspiel");
		txtpnEmTippspiel.setBounds(165, 28, 170, 30);
		contentPane.add(txtpnEmTippspiel);

		URL imageResource = SplashFrame.class.getResource("/whs/gdi2/tippspiel/data/EMLogo.png");
		ImageIcon emLogo = new ImageIcon(imageResource, "The EM2016 WHS-selfmade-Logo");
		JLabel label = new JLabel("", emLogo, JLabel.CENTER);
		label.setBounds(125, 87, 250, 250);
		contentPane.add(label);
	}
}
