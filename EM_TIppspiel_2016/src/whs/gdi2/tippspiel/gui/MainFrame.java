package whs.gdi2.tippspiel.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import whs.gdi2.tippspiel.Config;
import whs.gdi2.tippspiel.database.DatabaseManagement;
import whs.gdi2.tippspiel.log.Log;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;

/**
 * 
 * @version 1.0
 * @author Mario Kellner <mario.kellner@studmail.w-hs.de>
 * @author Jan-Markus Momper <jan-markus.momper@studmail.w-hs.de>
 * @author Philipp Miller <philipp.miller@studmail.w-hs.de>
 *
 */

public class MainFrame extends JFrame {

	private JPanel content;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame(true);
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
	
	protected DBConfigFrame dbConfigFrame;
	private JTable table;
	private JTable table_1;
	
	public MainFrame(boolean showDBSettings) {
		setResizable(false);
		setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		setTitle("Tippspiel Admin - Tool");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(MainFrame.class.getResource("/whs/gdi2/tippspiel/data/em_Logo.png")));
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

		

		JMenuItem mntmDbEinstellungen = new JMenuItem("DB Einstellungen");
		mntmDbEinstellungen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dbConfigFrame = new DBConfigFrame(tempSpielplan);
				dbConfigFrame.setVisible(true);
			}
		});
		mntmDbEinstellungen.setBackground(Config.getGuiColor());
		mntmDbEinstellungen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mnEinstellungen.add(mntmDbEinstellungen);

		JMenu mnHilfe = new JMenu("Hilfe");
		mnHilfe.setBackground(Config.getGuiColor());
		mnHilfe.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		menuBar.add(mnHilfe);

		JMenuItem mntmEinstellungen = new JMenuItem("\u00DCber Tippspiel Admin - Tool");
		mntmEinstellungen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mntmEinstellungen.setBackground(Config.getGuiColor());
		mnHilfe.add(mntmEinstellungen);
		
		content = new JPanel();
		content.setBackground(Config.getGuiColor());
		content.setBorder(new EmptyBorder(5, 5, 5, 5));
		content.setLayout(null);
		setContentPane(content);

		JLabel lblTop = new JLabel("Top 10 - Tipper");
		lblTop.setBounds(10, 40, 504, 20);
		lblTop.setFont(new Font(Config.getFont(), Font.PLAIN, 18));
		lblTop.setHorizontalAlignment(SwingConstants.CENTER);
		content.add(lblTop);
		
		JLabel lblDieNchsten = new JLabel("Die n\u00E4chsten 10 Spiele");
		lblDieNchsten.setBounds(509, 40, 504, 20);
		lblDieNchsten.setFont(new Font(Config.getFont(), Font.PLAIN, 18));
		lblDieNchsten.setHorizontalAlignment(SwingConstants.CENTER);
		content.add(lblDieNchsten);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 63, 404, 350);
		content.add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setShowGrid(false);
		table.setFont(new Font(Config.getFont(), Font.PLAIN, 13));
		table.setModel(DatabaseManagement.implementUserTop10Data());
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(559, 63, 404, 350);
		content.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setFillsViewportHeight(true);
		table_1.setFont(new Font(Config.getFont(), Font.PLAIN, 13));
		table_1.setModel(DatabaseManagement.implementNext10Games());
		table_1.setAutoCreateRowSorter(true);
		scrollPane_1.setViewportView(table_1);
		
		JButton btnZurWebsite = new JButton("Zur Website");
		btnZurWebsite.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		btnZurWebsite.setBounds(59, 424, 404, 23);
		btnZurWebsite.setBackground(Config.getGuiColor());
		content.add(btnZurWebsite);
		
		JButton btnZurLiveDatenbank = new JButton("Zur Live - Datenbank");
		btnZurLiveDatenbank.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		btnZurLiveDatenbank.setBounds(59, 456, 404, 23);
		btnZurLiveDatenbank.setBackground(Config.getGuiColor());
		content.add(btnZurLiveDatenbank);
		
		JButton btnZurOffline = new JButton("Zur Offline - Datenbank");
		btnZurOffline.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		btnZurOffline.setBackground(Config.getGuiColor());
		btnZurOffline.setBounds(59, 490, 404, 23);
		content.add(btnZurOffline);
		
		setVisible(true);
		if (!showDBSettings) {
			dbConfigFrame = new DBConfigFrame(tempSpielplan);
			dbConfigFrame.setVisible(true);;
		}
	}
}
