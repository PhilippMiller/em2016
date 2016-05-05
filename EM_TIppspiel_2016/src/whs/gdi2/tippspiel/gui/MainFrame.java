package whs.gdi2.tippspiel.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import whs.gdi2.tippspiel.Config;
import whs.gdi2.tippspiel.database.DatabaseManagement;
import whs.gdi2.tippspiel.log.Log;

import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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

import javax.swing.JSeparator;
import javax.swing.JRadioButtonMenuItem;

import java.awt.Component;

import javax.swing.Box;

import java.awt.SystemColor;
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
		mnMen.add(mnSpielplan);

		JMenuItem mntmEm = new JMenuItem("EM 2016");
		MainFrame classContext = this;
		mntmEm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SpielplanEM2016Frame spielplanFrame = new SpielplanEM2016Frame(classContext);
				spielplanFrame.setVisible(true);
				spielplanFrame.setModal(true);
				Log.info("Menue item 'EM 2016' clicked.");
			}
		});
		mntmEm.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mnSpielplan.add(mntmEm);

		JMenuItem mntmBundesliga = new JMenuItem("Bundesliga 16/17");
		mntmBundesliga.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mntmBundesliga.setEnabled(false);
		mnSpielplan.add(mntmBundesliga);

		JMenu mnEinstellungen = new JMenu("Einstellungen");
		mnEinstellungen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		menuBar.add(mnEinstellungen);

		

		JMenuItem mntmDbEinstellungen = new JMenuItem("DB Einstellungen");
		mntmDbEinstellungen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mntmDbEinstellungen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dbConfigFrame = new DBConfigFrame(classContext);
				dbConfigFrame.setVisible(true);
			}
		});
		mnEinstellungen.add(mntmDbEinstellungen);
		
		JMenuItem mntmTestdatenEinpflegen = new JMenuItem("Datenbank Verwaltung");
		mntmTestdatenEinpflegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Config.isDBType()) {
					JOptionPane.showMessageDialog(classContext, "Testdaten k�nnen nur in der Testdatenbank eingespielt werden.\n Bitte �ndere die benutze Datenbank zur test Datenbank.");
					return;
				}
				DBToolDialog dbtd = new DBToolDialog(classContext);
				dbtd.setVisible(true);
			}
		});
		mntmTestdatenEinpflegen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));	
		
		JMenu mnDbSwitcher = new JMenu("DB Switcher");
		mnDbSwitcher.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mnEinstellungen.add(mnDbSwitcher);
		
	    ActionListener actionPrinter = new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	          try {
	            Log.info("Action [" + e.getActionCommand()+ "] performed!");
	          } catch (Exception ex) {
	            ex.printStackTrace();
	          }	
	        }
	      };
		
		JRadioButtonMenuItem rdbtnmntmLiveDatenbank = new JRadioButtonMenuItem("Live Datenbank");
		rdbtnmntmLiveDatenbank.setFont(new Font(Config.getFont(), Font.PLAIN, 15));		
		mnDbSwitcher.add(rdbtnmntmLiveDatenbank);
		rdbtnmntmLiveDatenbank.setSelected(true);
		rdbtnmntmLiveDatenbank.addActionListener(actionPrinter);
		
		JRadioButtonMenuItem rdbtnmntmTestDatenbank = new JRadioButtonMenuItem("Test Datenbank");
		rdbtnmntmTestDatenbank.setFont(new Font(Config.getFont(), Font.PLAIN, 15));				
		mnDbSwitcher.add(rdbtnmntmTestDatenbank);
		rdbtnmntmTestDatenbank.addActionListener(actionPrinter);
		
		
	    ButtonGroup group = new ButtonGroup();
	    group.add(rdbtnmntmLiveDatenbank);
	    group.add(rdbtnmntmTestDatenbank);
		
		JSeparator separator = new JSeparator();
		mnEinstellungen.add(separator);
		mntmTestdatenEinpflegen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mnEinstellungen.add(mntmTestdatenEinpflegen);

		JMenu mnHilfe = new JMenu("Hilfe");
		mnHilfe.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		menuBar.add(mnHilfe);

		JMenuItem mntmEinstellungen = new JMenuItem("\u00DCber Tippspiel Admin - Tool");
		mntmEinstellungen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
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
		table.setShowGrid(false);
		table.setFillsViewportHeight(true);
		table.setFont(new Font(Config.getFont(), Font.PLAIN, 13));
		table.setModel(DatabaseManagement.implementNext10Games());
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(559, 63, 404, 350);
		content.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setShowGrid(false);
		table_1.setFillsViewportHeight(true);
		table_1.setFont(new Font(Config.getFont(), Font.PLAIN, 13));
		table_1.setModel(DatabaseManagement.implementNext10Games());
		table_1.setAutoCreateRowSorter(true);
		scrollPane_1.setViewportView(table_1);

		JButton btnAktualisieren = new JButton("Aktualisieren");
		btnAktualisieren.setBounds(831, 752, 132, 23);
		btnAktualisieren.setFont(new Font(Config.getFont(), Font.PLAIN, 14));
		btnAktualisieren.setBackground(Config.getGuiColor());
		content.add(btnAktualisieren);
		
		setVisible(true);
		if (!showDBSettings) {
			dbConfigFrame = new DBConfigFrame(classContext);
			dbConfigFrame.setVisible(true);;
		}
	}
}
