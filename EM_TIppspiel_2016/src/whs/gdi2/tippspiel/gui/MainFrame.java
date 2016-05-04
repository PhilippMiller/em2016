package whs.gdi2.tippspiel.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import whs.gdi2.tippspiel.Config;
import whs.gdi2.tippspiel.Main;
import whs.gdi2.tippspiel.database.MySQLConnection;
import whs.gdi2.tippspiel.log.Log;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setResizable(false);
		setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		setTitle("Tippspiel Admin - Tool");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/whs/gdi2/tippspiel/data/em_Logo.png")));
		setBackground(Config.getGuiColor());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 860);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMen = new JMenu("Men\u00FC");
		mnMen.setBackground(Config.getGuiColor());
		mnMen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		menuBar.add(mnMen);
		
		JMenu mnSpielplan = new JMenu("Spielplan");
		mnSpielplan.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mnSpielplan.setBackground(Config.getGuiColor());
		mnMen.add(mnSpielplan);
		
		JMenuItem mntmEm = new JMenuItem("EM 2016");
		MainFrame tempSpielplan = this;
		mntmEm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SpielplanEM2016Frame spielplanFrame = new SpielplanEM2016Frame(tempSpielplan);
				spielplanFrame.setVisible(true);
				spielplanFrame.setModal(true);
				Log.info("Menue item 'EM 2016' clicked.");
			}
		});
		mntmEm.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mntmEm.setBackground(Config.getGuiColor());
		mnSpielplan.add(mntmEm);
		
		JMenuItem mntmBundesliga = new JMenuItem("Bundesliga 16/17");
		mntmBundesliga.setBackground(Config.getGuiColor());
		mntmBundesliga.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mntmBundesliga.setEnabled(false);
		mnSpielplan.add(mntmBundesliga);
		
		JMenu mnEinstellungen = new JMenu("Einstellungen");
		mnEinstellungen.setBackground(Config.getGuiColor());
		mnEinstellungen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		menuBar.add(mnEinstellungen);
								
										JMenu mnHilfe = new JMenu("Hilfe");
										mnHilfe.setBackground(Config.getGuiColor());
										mnHilfe.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
										menuBar.add(mnHilfe);
										
												JMenuItem mntmEinstellungen = new JMenuItem("\u00DCber Tippspiel Admin - Tool");
												mntmEinstellungen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
												mntmEinstellungen.setBackground(Config.getGuiColor());
												mnHilfe.add(mntmEinstellungen);
		contentPane = new JPanel();
		contentPane.setBackground(Config.getGuiColor());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		initialize();
	}

	private void initialize() {
		try {
			MySQLConnection tmp;

			if (Config.load()) {
				if (Config.isDBType() && !Config.getDBIp_online().equals("")) {
					// live db
					SplashFrame.setWorkOnIt("Connecting to test database...");
					tmp = MySQLConnection.getInstance(Config.isDBType());
					tmp.setDatabaseHost(Config.getDBIp_online());
					tmp.setDatabaseUser(Config.getDBUser_online());
					tmp.setDatabasePassword(Config.getDBPass_online());
					tmp.setDatabase(Config.getDB_online());

					if (tmp.connectToDatabase()) {
						Main.mainConnection = tmp;
						Thread.sleep(2000);
						SplashFrame.finish();
						MainFrame.main(null);
					} else {
						Thread.sleep(2000);
						SplashFrame.finish();
						DBConfigFrame.main(null);
					}

				} else if (!Config.isDBType() && !Config.getDBIp_offline().equals("")) {
					// test db
					SplashFrame.setWorkOnIt("Connecting to live database...");
					tmp = MySQLConnection.getInstance(Config.isDBType());
					tmp.setDatabaseHost(Config.getDBIp_offline());
					tmp.setDatabaseUser(Config.getDBUser_offline());
					tmp.setDatabasePassword(Config.getDBPass_offline());
					tmp.setDatabase(Config.getDB_offline());

					if (tmp.connectToDatabase()) {
						Main.mainConnection = tmp;
						Thread.sleep(2000);
						SplashFrame.finish();
						MainFrame.main(null);
					} else {
						Thread.sleep(2000);
						SplashFrame.finish();
						DBConfigFrame.main(null);
					}
				} else {
					Thread.sleep(2000);
					SplashFrame.finish();
					DBConfigFrame.main(null);
				}
			} else {
				Thread.sleep(2000);
				SplashFrame.finish();
				DBConfigFrame.main(null);
			}
		} catch (Exception e) {

		}
	}
}
