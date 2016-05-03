package whs.gdi2.tippspiel.gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import whs.gdi2.tippspiel.Config;

import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Color;

/**
 * 
 * @version 1.0
 * @author Mario Kellner <mario.kellner@studmail.w-hs.de>
 * @author Jan-Markus Momper <jan-markus.momper@studmail.w-hs.de>
 * @author Philipp Miller <philipp.miller@studmail.w-hs.de>
 *
 */
public class SplashFrame extends JFrame {

	private JPanel contentPane;
	public static SplashFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new SplashFrame();
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
	private static JLabel workOnIt;

	public SplashFrame() {
		setBackground(new Color(248, 248, 255));
		setResizable(false);
		setType(Type.UTILITY);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 500, 280);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.disabledShadow"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		workOnIt = new JLabel("Work in progress...");
		workOnIt.setBounds(10, 235, 163, 14);
		contentPane.add(workOnIt);

		JTextPane txtpnEmTippspiel = new JTextPane();
		txtpnEmTippspiel.setEditable(false);
		txtpnEmTippspiel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtpnEmTippspiel.setBackground(UIManager.getColor("Button.disabledShadow"));
		txtpnEmTippspiel.setText("EM 2016 Tippspiel");
		txtpnEmTippspiel.setBounds(10, 11, 170, 30);
		contentPane.add(txtpnEmTippspiel);

		// MYJavaFile.class.getResource("/package/packageb/packagebb/packagebbb/../File.typ")
		// Loads a file out which contains to the Project
		URL imageResource = SplashFrame.class.getResource("/whs/gdi2/tippspiel/data/em_Logo.png");
		ImageIcon emLogo = new ImageIcon(imageResource, "The EM2016 WHS-selfmade-Logo");
		JLabel label = new JLabel("", emLogo, JLabel.CENTER);
		label.setBackground(UIManager.getColor("Button.disabledShadow"));
		label.setBounds(125, 15, 250, 250);
		contentPane.add(label);

		JLabel versionLabel = new JLabel("New label");
		versionLabel.setBounds(10, 255, 70, 14);
		versionLabel.setText("Version: " + Config.getVersion());
		contentPane.add(versionLabel);

		JLabel autorTopLabel = new JLabel("Autoren:");
		autorTopLabel.setBounds(360, 183, 105, 22);
		contentPane.add(autorTopLabel);

		JLabel autorLabel = new JLabel("");
		autorLabel.setBounds(360, 203, 127, 22);
		autorLabel.setText(Config.getAutor()[0]);
		contentPane.add(autorLabel);

		JLabel autor2Label = new JLabel("");
		autor2Label.setBounds(360, 223, 105, 22);
		autor2Label.setText(Config.getAutor()[1]);
		contentPane.add(autor2Label);

		JLabel autor3Label = new JLabel("");
		autor3Label.setBounds(360, 243, 105, 22);
		autor3Label.setText(Config.getAutor()[2]);
		contentPane.add(autor3Label);
	}
	
	public static void setWorkOnIt(String progress) {
		workOnIt.setText(progress);
	}

	/**
	 * Dispose / Close the frame
	 */
	public static void finish() {
		frame.dispose();
	}
}
