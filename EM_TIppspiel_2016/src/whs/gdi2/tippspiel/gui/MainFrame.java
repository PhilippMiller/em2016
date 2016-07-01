package whs.gdi2.tippspiel.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import whs.gdi2.tippspiel.Config;
import whs.gdi2.tippspiel.Main;
import whs.gdi2.tippspiel.database.DatabaseManagement;
import whs.gdi2.tippspiel.database.RankingHelper;
import whs.gdi2.tippspiel.log.Log;

import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JRadioButtonMenuItem;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

/**
 * 
 * @version 1.0
 * @author Mario Kellner <mario.kellner@studmail.w-hs.de>
 * @author Jan-Markus Momper <jan-markus.momper@studmail.w-hs.de>
 * @author Philipp Miller <philipp.miller@studmail.w-hs.de>
 *
 */

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	protected DBConfigFrame dbConfigFrame;
	protected MainFrame classContext;
	
	// Gui Elemente. Bitte die Hauptsachen immer als Attribut
	private JPanel content;
	private JMenuBar menuBar;
	private JMenu mnSpielplan;
	private JMenuItem mntmEm;
	private JMenu mnErgebnisse;
	private JMenuItem menuItem;
	private JMenu mnSpielerranking;
	private JMenu mnEm;
	private JMenuItem mntmSpielerRanking;
	private JMenuItem mntmGruppenRanking;
	private JTable tableTopTenBetters;
	private JTable tableNextGames;
	private JMenuItem mntmSchlieen;
	private JMenu mnEinstellungen;
	private JMenuItem mntmDbEinstellungen;
	private JMenuItem mntmTestdatenEinpflegen;
	private JMenu mnDbSwitcher;
	private JRadioButtonMenuItem rdbtnmntmLiveDatenbank;
	private JRadioButtonMenuItem rdbtnmntmTestDatenbank;
	private JMenu mnHilfe;
	private JMenuItem mntmEinstellungen;
	private JLabel lblTop;
	private JLabel lblDieNchsten;
	private JButton btnAktualisieren;
	private JMenuItem mntmErgebnisseEingeben;
	private JMenu mnMen;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private ButtonGroup group;
	private JSeparator separator;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JMenuItem mntmRankingBerechnen;
	private JMenuItem mntmErgebnisEingabe;

	public MainFrame(boolean showDBSettings) {
		classContext = this;
		
		setResizable(false);
		setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		setTitle("Tippspiel Admin - Tool | Version: " + Config.getVersion());
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/whs/gdi2/tippspiel/data/em_Logo.png")));
		setBackground(Config.getGuiColor());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(665, 365);
		
        Dimension windowSize = this.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width/2 - windowSize.width/2, screenSize.height/2 - windowSize.height/2);

		InitializeGui();
		InitializeEvents();
		
		if (!showDBSettings) {
			dbConfigFrame = new DBConfigFrame(classContext);
			dbConfigFrame.setVisible(true);
		}
		else {
			reload();
		}
		
		setVisible(true);
	}

	public void InitializeGui() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnMen = new JMenu("Men\u00FC");
		mnMen.setBackground(Config.getGuiColor());
		mnMen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		menuBar.add(mnMen);

		mnSpielplan = new JMenu("Spielplan");
		mnSpielplan.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mnMen.add(mnSpielplan);

		mntmEm = new JMenuItem("EM 2016");
		mntmEm.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mnSpielplan.add(mntmEm);

		mntmErgebnisseEingeben = new JMenuItem("Spielpaarungen aktualisieren");
		mntmErgebnisseEingeben.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mnMen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));

		mnErgebnisse = new JMenu("Ergebnisse");
		mnErgebnisse.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		mnMen.add(mnErgebnisse);

		menuItem = new JMenuItem("EM 2016");
		menuItem.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		mnErgebnisse.add(menuItem);

		mnSpielerranking = new JMenu("Ranking");
		mnSpielerranking.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		mnMen.add(mnSpielerranking);

		mnEm = new JMenu("EM 2016");
		mnEm.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		mnSpielerranking.add(mnEm);

		mntmSpielerRanking = new JMenuItem("Spieler Ranking");
		mntmSpielerRanking.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		mnEm.add(mntmSpielerRanking);


		mntmGruppenRanking = new JMenuItem("Gruppen Ranking");
		mntmGruppenRanking.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		mnEm.add(mntmGruppenRanking);

		separator_1 = new JSeparator();
		mnMen.add(separator_1);
		mnMen.add(mntmErgebnisseEingeben);
		
		mntmErgebnisEingabe = new JMenuItem("Ergebnis Eingabe");

		mntmErgebnisEingabe.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		mnMen.add(mntmErgebnisEingabe);
		
		mntmRankingBerechnen = new JMenuItem("Ranking berechnen");
		mntmRankingBerechnen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		
		mnMen.add(mntmRankingBerechnen);

		separator_2 = new JSeparator();
		mnMen.add(separator_2);

		mntmSchlieen = new JMenuItem("Schlie\u00DFen");
		mntmSchlieen.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		mnMen.add(mntmSchlieen);

		mnEinstellungen = new JMenu("Einstellungen");
		mnEinstellungen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		menuBar.add(mnEinstellungen);

		mntmDbEinstellungen = new JMenuItem("DB Einstellungen");
		mntmDbEinstellungen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mnEinstellungen.add(mntmDbEinstellungen);

		mntmTestdatenEinpflegen = new JMenuItem("Datenbank Verwaltung");
		mntmTestdatenEinpflegen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));

		mnDbSwitcher = new JMenu("DB Switcher");
		mnDbSwitcher.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mnEinstellungen.add(mnDbSwitcher);
		
		rdbtnmntmLiveDatenbank = new JRadioButtonMenuItem("Live Datenbank");
		rdbtnmntmLiveDatenbank.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mnDbSwitcher.add(rdbtnmntmLiveDatenbank);

		rdbtnmntmTestDatenbank = new JRadioButtonMenuItem("Test Datenbank");
		rdbtnmntmTestDatenbank.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mnDbSwitcher.add(rdbtnmntmTestDatenbank);

		group = new ButtonGroup();
		group.add(rdbtnmntmLiveDatenbank);
		group.add(rdbtnmntmTestDatenbank);
		
		separator = new JSeparator();
		mnEinstellungen.add(separator);
		mntmTestdatenEinpflegen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mnEinstellungen.add(mntmTestdatenEinpflegen);

		mnHilfe = new JMenu("Hilfe");
		mnHilfe.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		menuBar.add(mnHilfe);

		mntmEinstellungen = new JMenuItem("\u00DCber Tippspiel Admin - Tool");
		mntmEinstellungen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(classContext, "Created by\nJan-Markus Momper\nMario Kellner\nPhilipp Miller\n\n\u00a9 Copyright 2016", "Über", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mntmEinstellungen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mnHilfe.add(mntmEinstellungen);

		content = new JPanel();
		content.setBackground(Config.getGuiColor());
		content.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(content);
		content.setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) topPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		content.add(topPanel, BorderLayout.NORTH);
		topPanel.setBackground(Config.getGuiColor());
		
		JLabel lblDashboard = new JLabel("Dashboard");
		lblDashboard.setFont(new Font(Config.getFont(), Font.PLAIN, 20));
		topPanel.add(lblDashboard);
		
		JPanel btnPanel = new JPanel();
		content.add(btnPanel, BorderLayout.SOUTH);
		btnPanel.setBackground(Config.getGuiColor());

		btnAktualisieren = new JButton("Aktualisieren");
		btnPanel.add(btnAktualisieren);
		btnAktualisieren.setFont(new Font(Config.getFont(), Font.PLAIN, 14));
		btnAktualisieren.setBackground(Config.getGuiColor());

		JPanel tablePanel = new JPanel();
		content.add(tablePanel);
		tablePanel.setLayout(null);
		tablePanel.setBackground(Config.getGuiColor());

		lblTop = new JLabel("Top 10 - Tipper");
		lblTop.setBounds(10, 0, 109, 23);
		tablePanel.add(lblTop);
		lblTop.setFont(new Font(Config.getFont(), Font.PLAIN, 18));
		lblTop.setHorizontalAlignment(SwingConstants.CENTER);

		lblDieNchsten = new JLabel("Die n\u00E4chsten 10 Spiele");
		lblDieNchsten.setBounds(340, 0, 162, 23);
		tablePanel.add(lblDieNchsten);
		lblDieNchsten.setFont(new Font(Config.getFont(), Font.PLAIN, 18));
		lblDieNchsten.setHorizontalAlignment(SwingConstants.CENTER);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 25, 300, 185);
		tablePanel.add(scrollPane);
		
		tableTopTenBetters = new JTable();
		tableTopTenBetters.setBounds(642, 16, 0, 0);
		tableTopTenBetters.setShowGrid(false);
		tableTopTenBetters.setFillsViewportHeight(true);
		tableTopTenBetters.setFont(new Font(Config.getFont(), Font.PLAIN, 16));
		tableTopTenBetters.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(tableTopTenBetters);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(340, 25, 300, 185);
		tablePanel.add(scrollPane_1);

		tableNextGames = new JTable();
		tableNextGames.setBounds(654, 16, 0, 0);
		tableNextGames.setShowGrid(false);
		tableNextGames.setFillsViewportHeight(true);
		tableNextGames.setFont(new Font(Config.getFont(), Font.PLAIN, 16));
		tableNextGames.setAutoCreateRowSorter(true);
		scrollPane_1.setViewportView(tableNextGames);
	}
	
	public void InitializeEvents() {
		btnAktualisieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reload();
			}
		});
		mntmErgebnisEingabe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ErgebnisEingabeFrame ergebnisFrame = new ErgebnisEingabeFrame(classContext);
				ergebnisFrame.setVisible(true);
				ergebnisFrame.setModal(true);

				Log.info("Menue item 'Ergebnis Eingabe' clicked.");
			}
		});
		

		mntmErgebnisseEingeben.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				KOEditorFrame koeditor = new KOEditorFrame(classContext);
				koeditor.setVisible(true);
				koeditor.setModal(true);

				Log.info("Menue item 'Spielpaarungen aktualisieren' clicked.");
			}
		});
		
		mntmEm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SpielplanFrame spielplanFrame = new SpielplanFrame(classContext);
				spielplanFrame.setVisible(true);
				spielplanFrame.setModal(true);
				Log.info("Menue item 'EM 2016' clicked.");
			}
		});
				
		mntmRankingBerechnen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Log.info("Menue item 'Ranking berechnen' clicked.");
				
				try {
					RankingHelper.calculateRanking(Main.mainConnection);
					JOptionPane.showMessageDialog(classContext, "Ranking erfolgreich berechnet.");
					reload();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(classContext, "Ranking konnte nicht berechnet werden.\nFehler: " + e.getMessage());
				}
			}
		});

		
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ErgebnisAnzeigeFrame ergebnisseFrame = new ErgebnisAnzeigeFrame(classContext);
				ergebnisseFrame.setVisible(true);
				ergebnisseFrame.setModal(true);
				Log.info("Menue item 'Ergebnisse > EM2016' clicked.");
			}
		});
		
		mntmSpielerRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SpielerRankingFrame srkemframe = new SpielerRankingFrame(classContext);
				srkemframe.setVisible(true);
				srkemframe.setModal(true);
				Log.info("Menue item 'Ranking > EM2016 > Spieler Ranking' clicked.");
			}
		});
		
		mntmGruppenRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GruppenRankingFrame grura = new GruppenRankingFrame(classContext);
				grura.setVisible(true);
				grura.setModal(true);
				Log.info("Menue item 'Ranking > EM2016 > Gruppen Ranking' clicked.");
			}
		});
		
		mntmSchlieen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Log.info("'Schließen' clicked.");
				System.exit(0);
			}
		});
		
		mntmDbEinstellungen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dbConfigFrame = new DBConfigFrame(classContext);
				dbConfigFrame.setVisible(true);
				Log.info("Menue item 'DB Einstellungen' clicked.");
			}
		});
		
		mntmTestdatenEinpflegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Config.isDBType()) {
					JOptionPane.showMessageDialog(classContext,
							"Testdaten können nur in der Testdatenbank eingespielt werden.\n Bitte ändere die benutze Datenbank zur test Datenbank.");
					return;
				}
				DBToolDialog dbtd = new DBToolDialog(classContext);
				dbtd.setVisible(true);
				Log.info("Menue item 'Datenbank Verwaltung' clicked.");
			}
		});
		
		ActionListener databaseSwitcher = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean dbtype = false;	
				if(rdbtnmntmLiveDatenbank.isSelected()) {
					dbtype = true;
				}
				try {
					Log.info("Action [" + e.getActionCommand() + "] performed!");
					if(Main.switchDatabaseConnection(dbtype)) {
						JOptionPane.showMessageDialog(classContext, "Datenbankverbindung erfolgreich geändert.", "Datenbank Switcher", JOptionPane.PLAIN_MESSAGE);
						reload();
					}
					else {
						JOptionPane.showMessageDialog(classContext, "Datenbankverbindung konnte nicht gewechselt werden. Bitte überprüfen Sie ihre Datenbank Einstellungen", "Datenbank Switcher", JOptionPane.WARNING_MESSAGE);
						if(((JRadioButtonMenuItem)e.getSource()).getText() == "Test Datenbank") {
							rdbtnmntmLiveDatenbank.setSelected(true);
						}
						else {
							rdbtnmntmTestDatenbank.setSelected(true);
						}
					}
					
					
				} catch (Exception ex) {
					if(((JRadioButtonMenuItem)e.getSource()).getText() == "Test Datenbank") {
						rdbtnmntmLiveDatenbank.setSelected(true);
					}
					else {
						rdbtnmntmTestDatenbank.setSelected(true);
					}
				}
			}
		};

		rdbtnmntmLiveDatenbank.addActionListener(databaseSwitcher);
		rdbtnmntmTestDatenbank.addActionListener(databaseSwitcher);
	}
	
	public void reload() {
		if (Config.isDBType()) {
			rdbtnmntmLiveDatenbank.setSelected(true);
		} else {
			rdbtnmntmTestDatenbank.setSelected(true);
		}
		tableTopTenBetters.setModel(DatabaseManagement.implementUserTop10Data());
		tableTopTenBetters.repaint();
		tableNextGames.setModel(DatabaseManagement.implementNext10Games());
		tableNextGames.repaint();
		
		Log.info("Reload MainFrame data complete.");
	}
}
