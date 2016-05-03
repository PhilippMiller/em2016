package whs.gdi2.tippspiel.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

/**
 * 
 * @version 1.0
 * @author Mario Kellner <mario.kellner@studmail.w-hs.de>
 * @author Jan-Markus Momper <jan-markus.momper@studmail.w-hs.de>
 * @author Philipp Miller <philipp.miller@studmail.w-hs.de>
 *
 */

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnDatenbank = new JMenu("Datenbank");
		menuBar.add(mnDatenbank);
		
		JRadioButtonMenuItem rdbtnmntmOffline = new JRadioButtonMenuItem("Offline");
		mnDatenbank.add(rdbtnmntmOffline);
		
		JRadioButtonMenuItem rdbtnmntmOnline = new JRadioButtonMenuItem("Online");
		mnDatenbank.add(rdbtnmntmOnline);
		
		JMenu mnHilfe = new JMenu("Hilfe");
		menuBar.add(mnHilfe);
		
		JMenuItem mntmEinstellungen = new JMenuItem("Einstellungen");
		mnHilfe.add(mntmEinstellungen);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
}
